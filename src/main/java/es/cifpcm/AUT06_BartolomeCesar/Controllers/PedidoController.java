package es.cifpcm.AUT06_BartolomeCesar.Controllers;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IPedidoService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Pedido;
import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    IPedidoService pedSer;

    @Autowired
    UserService userSer;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getPedidos(Principal principal){

        String userEmail = principal.getName();
        User user = userSer.getUser(userEmail);
        List<Pedido> pedidoList = user.getPedidoList();
        return new ResponseEntity<>(pedidoList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> getPedido(Principal principal, @PathVariable("id") Integer id){

        String userEmail = principal.getName();
        User user = userSer.getUser(userEmail);
        List<Pedido> pedidoList = user.getPedidoList();
        Pedido pedido = pedSer.getPedido(id);

        if(pedido == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(!pedidoList.contains(pedido)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(pedido,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/pedido/create")
    public ResponseEntity<String> createPedido(Principal principal){

        String userEmail = principal.getName();
        User user = userSer.getUser(userEmail);
        List<Producto> proLi = user.getCarrito();
        List<Producto> productoList= new ArrayList<>();
        float total = 0;
        for(Producto p : proLi){
            total += p.getProduct_price();
            productoList.add(p);
        }

        Pedido pedido = new Pedido(total,user,productoList);
        List<Pedido> pedidoList = user.getPedidoList();
        pedidoList.add(pedido);
        proLi.clear();
        userSer.saveUser(user);
        return new ResponseEntity<>("Pedido realizado correctamente",HttpStatus.OK);
    }
}
