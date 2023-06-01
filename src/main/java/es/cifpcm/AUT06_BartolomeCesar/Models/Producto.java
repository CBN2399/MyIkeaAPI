package es.cifpcm.AUT06_BartolomeCesar.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="productoffer")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    @NotEmpty(message = "El nombre del producto es obligatorio")
    private  String product_name;
    @Min(value = 1, message ="No puede haber un precio negativo")
    private float product_price;
    @ManyToOne
    @JsonIgnoreProperties("productoList")
    @JoinColumn(name = "id_municipio")
    @NotNull(message = "Seleccciona un municipio.")
    private Municipio municipio;
    @Min(value = 1, message = "No puede haber stock negativo")
    private Integer product_stock;


    @JsonIgnoreProperties("productoList")
    @ManyToMany(mappedBy = "productoList")
    private List<Pedido> pedidoList = new ArrayList<>();

    @JsonIgnoreProperties("carrito")
    @ManyToMany(mappedBy = "carrito")
    private List<User> userList = new ArrayList<>();

    public Producto(String product_name, float product_price, Municipio mun, Integer product_stock) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.municipio = mun;
        this.product_stock = product_stock;
    }
}

