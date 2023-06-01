package es.cifpcm.AUT06_BartolomeCesar.Controllers;

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
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userSer;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> getlist(Principal principal){

        List<User> userList = userSer.getUserList();
        String userEmail = principal.getName();
        User user = userSer.getUser(userEmail);
        userList.remove(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/usuario/delete/{id}")
    public ResponseEntity<String> deleteUser(Principal principal, @PathVariable("id") Integer id){

        User userSelected = userSer.getUser(id);
        if(userSelected == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        if(currentUser.getUser_id() == id){
            return new ResponseEntity<>("No se puede eliminar al usuario actual",HttpStatus.BAD_REQUEST);
        }

        userSer.deleteUser(id);
        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }
}
