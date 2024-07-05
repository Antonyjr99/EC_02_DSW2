package pe.idat.ec02.ec02.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.ec02.ec02.Model.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long> {
    
}
