package es.cifpcm.AUT06_BartolomeCesar.Models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name ="municipios")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_municipio;

    @JsonIgnoreProperties("municipioList")
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    private Integer cod_municipio;

    private Integer DC;

    private String nombre;

    @JsonIgnoreProperties("municipio")
    @OneToMany(mappedBy = "municipio",
            cascade = CascadeType.ALL)
    private List<Producto> productoList;


}
