package pe.idat.ec02.ec02.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.ec02.ec02.Model.Usuario;
import pe.idat.ec02.ec02.Service.IUsuarioService;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUsuarioService service;
	
	@GetMapping
	public ResponseEntity<?> bienvenida(){
		
		Optional<String> optSesion=service.buscarSession();
		if (!optSesion.isPresent())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		Usuario usu= service.buscarEmail(optSesion.get());
		if (usu==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok("Bienvenido Usuario: " + usu.getNombre() + " " + usu.getApellido());
		
	}
}
