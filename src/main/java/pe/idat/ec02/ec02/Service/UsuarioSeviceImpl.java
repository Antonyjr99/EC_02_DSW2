package pe.idat.ec02.ec02.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import pe.idat.ec02.ec02.Dao.IUsuarioDao;
import pe.idat.ec02.ec02.Model.Usuario;
import pe.idat.ec02.ec02.Util.SeguridadUtil;



@Service
public class UsuarioSeviceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao dao;
	
	@Override
	public Usuario buscarEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Optional<String> buscarSession() {
		String session=SeguridadUtil.getCurrentUser();
		return Optional.ofNullable(session);
	}

	@Override
	public List<Usuario> listarUsuario() {
		return dao.findAll();
	}

}
