package pe.idat.ec02.ec02.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.ec02.ec02.Model.Usuario;



public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
	
}
