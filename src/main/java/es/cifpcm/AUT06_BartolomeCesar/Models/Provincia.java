package es.cifpcm.AUT06_BartolomeCesar.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_provincia;

    private String nombre;

    @OneToMany(mappedBy = "provincia",
            cascade = CascadeType.ALL)
    private List<Municipio> municipioList = new ArrayList<>();
}

