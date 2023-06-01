package es.cifpcm.AUT06_BartolomeCesar.Controllers;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class CarritoController {

    @Autowired
    UserService userSer;

    @Autowired
    IProductoService prodSer;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/carrito")
    public ResponseEntity<List<Producto>> getLista(Principal principal){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        List<Producto> proLi = currentUser.getCarrito();
        return new ResponseEntity<>(proLi, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/comprar/{id}")
    public ResponseEntity<String> addProducto(@PathVariable("id") Integer id,Principal principal){

        Producto producto = prodSer.getProducto(id);
        if(producto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User currentUser = userSer.getUser(principal.getName());
        List<Producto> productoList = currentUser.getCarrito();
        productoList.add(producto);
        userSer.saveUser(currentUser);

        return  new ResponseEntity<>("Se ha añadido el producto al carrito",HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/carrito/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,Principal principal){

        Producto producto = prodSer.getProducto(id);
        if(producto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User currentUser = userSer.getUser(principal.getName());
        List<Producto> productoList = currentUser.getCarrito();
        if(!productoList.contains(producto)){
            return new ResponseEntity<>("El producto no se encuentra en la lista",HttpStatus.NOT_FOUND);
        }
        productoList.remove(producto);
        userSer.saveUser(currentUser);
        return  new ResponseEntity<>("Se ha eliminado el producto del carrito",HttpStatus.OK);
    }
}
