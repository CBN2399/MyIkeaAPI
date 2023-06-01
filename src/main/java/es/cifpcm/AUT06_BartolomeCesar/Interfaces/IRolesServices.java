package es.cifpcm.AUT06_BartolomeCesar.Interfaces;

import es.cifpcm.AUT06_BartolomeCesar.Models.Roles;

import java.util.List;

public interface IRolesServices {

    public List<Roles> getRoleslist();

    public Roles getRoles(String rolename);

    public void addRoles(Roles roles);

    public void editRoles(String rolename, Roles roles);

    public void deleteRoles(String rolename);
}
