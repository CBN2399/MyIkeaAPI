package es.cifpcm.AUT06_BartolomeCesar.Controllers;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IMunicipioService;
import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductoController {

    @Autowired
    IProductoService prodSer;

    @Autowired
    IMunicipioService munSer;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getlist(){

        return new ResponseEntity<>(prodSer.getProductoList(), HttpStatus.OK);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id") Integer id){
        Producto producto = prodSer.getProducto(id);
        if(producto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping("/producto/create")
    public ResponseEntity<String> createProducto(@Valid @RequestBody Producto producto, BindingResult br){

        if(br.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(munSer.getMunicipio(producto.getMunicipio().getId_municipio()) == null){
            return new ResponseEntity<>("El municipio del producto no esta en la base de datos",HttpStatus.BAD_REQUEST);
        }

        prodSer.addProducto(producto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("/producto/edit/{id}")
    public ResponseEntity<String> editProducto(@PathVariable("id") Integer id,@Valid @RequestBody Producto producto, BindingResult br){

        Producto prod = prodSer.getProducto(id);
        if(prod == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(br.hasErrors()){
            return new ResponseEntity<>("Ha habido errores al editar el producto",HttpStatus.BAD_REQUEST);
        }

        producto.setProduct_id(id);
        prodSer.editProducto(id,producto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("/producto/delete/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") Integer id){

        Producto producto = prodSer.getProducto(id);
        if(producto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prodSer.deleteProducto(id);
        return new ResponseEntity<>("Producto eliminado",HttpStatus.OK);
    }

}
