package pe.idat.ec02.ec02.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.ec02.ec02.Dao.IProductoDao;
import pe.idat.ec02.ec02.Model.Producto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private IProductoDao productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/crear")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto savedProducto = productoRepository.save(producto);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }
}
