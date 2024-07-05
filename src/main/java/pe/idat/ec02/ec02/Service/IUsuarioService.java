package pe.idat.ec02.ec02.Service;

import java.util.List;
import java.util.Optional;

import pe.idat.ec02.ec02.Model.Usuario;


public interface IUsuarioService {

	Usuario buscarEmail(String email);
	
	Optional<String> buscarSession();
	
	List<Usuario> listarUsuario();
	
}
