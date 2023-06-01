package es.cifpcm.AUT06_BartolomeCesar.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roles_id;
    private String rolename;


    @ManyToMany(mappedBy = "rolesList")
    private List<User> userlist = new ArrayList<>();

    public Roles(String rolename) {
        this.rolename = rolename;
    }
}

