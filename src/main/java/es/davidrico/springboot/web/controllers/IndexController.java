package es.davidrico.springboot.web.controllers;

import java.util.Arrays;
import java.util.List;

import es.davidrico.springboot.web.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/app"}) // ----> ruta 1º nivel
public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;
	
	@GetMapping({"/index", "/", "/home"}) // ----> ruta 2º nivel ----->  ejemplo: www.localhost:8080/app/index
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
	
	@RequestMapping("/perfil") // SI NO SE INDICA method POR DEFECTO EL METODO ES get... NO post.. NI CUALQUIER OTRO
	public String perfil(Model model) {
		
		Usuario usuario = new Usuario();
		usuario.setNombre("David");
		usuario.setApellido("Rico");
		usuario.setEmail("davidRico@gmail.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		
		return "perfil";
	}
	
	@RequestMapping("/listar") 
	public String listar(Model model) {
		
		model.addAttribute("titulo", textoListar);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("David", "Rico", "davidRico@gmail.com"), 
				new Usuario("Miguel", "Vázquez", "miguelVazquez@gmail.com"),
				new Usuario("Manuel", "Pérez", "ManuelPerez@gmail.com"), 
				new Usuario("Chani", "Merino", "chaniMerino@gmail.com"));
		
		return usuarios;
	}
	
}
