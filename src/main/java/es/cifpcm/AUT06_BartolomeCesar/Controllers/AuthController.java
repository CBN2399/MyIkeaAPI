package es.cifpcm.AUT06_BartolomeCesar.Controllers;

import es.cifpcm.AUT06_BartolomeCesar.Models.LoginDTO;
import es.cifpcm.AUT06_BartolomeCesar.Models.Token;
import es.cifpcm.AUT06_BartolomeCesar.Models.RegisterDTO;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    UserService userSer;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginDTO request){

        User user = userSer.getUser(request.getEmail());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSer.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody RegisterDTO request){

        User user = userSer.getUser(request.getEmail());
        if(user != null){
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userSer.register(request),HttpStatus.OK);
    }
}
