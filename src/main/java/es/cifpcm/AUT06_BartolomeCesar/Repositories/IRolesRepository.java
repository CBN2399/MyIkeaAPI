package es.cifpcm.AUT06_BartolomeCesar.Repositories;

import es.cifpcm.AUT06_BartolomeCesar.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<Roles,Integer> {

    public Roles findByRolename(String name);

    public void deleteByRolename(String name);
}
