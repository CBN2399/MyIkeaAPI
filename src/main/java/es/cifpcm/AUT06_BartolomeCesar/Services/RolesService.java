package es.cifpcm.AUT06_BartolomeCesar.Services;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IRolesServices;
import es.cifpcm.AUT06_BartolomeCesar.Models.Roles;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService implements IRolesServices {

    @Autowired
    IRolesRepository rolRe;

    @Override
    public List<Roles> getRoleslist() {
        return rolRe.findAll();
    }

    @Override
    public Roles getRoles(String rolename) {
        return rolRe.findByRolename(rolename);
    }

    @Override
    public void addRoles(Roles roles) {
        rolRe.save(roles);
    }

    @Override
    public void editRoles(String rolename, Roles roles) {
        Roles rol = rolRe.findByRolename(rolename);
        if(rol != null){
            rolRe.save(roles);
        }
    }

    @Override
    public void deleteRoles(String rolename) {
        rolRe.deleteByRolename(rolename);
    }
}
