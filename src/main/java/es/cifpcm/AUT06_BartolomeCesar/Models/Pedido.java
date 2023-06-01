package es.cifpcm.AUT06_BartolomeCesar.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedido_id;

    private Date fecha;

    private float total;

    @JsonIgnoreProperties("pedidoList")
    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedido_producto",
            joinColumns = { @JoinColumn(name = "pedido_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Producto> productoList;
    @JsonIgnoreProperties("pedidoList")
    @ManyToOne
    private User user;
    public Pedido(float total,User user,List<Producto> productoList) {
        this.fecha = new Date();
        this.total = total;
        this.user = user;
        this.productoList = productoList;
    }
}
