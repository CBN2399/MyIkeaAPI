package es.cifpcm.AUT06_BartolomeCesar.Services;
import es.cifpcm.AUT06_BartolomeCesar.Models.LoginDTO;
import es.cifpcm.AUT06_BartolomeCesar.Models.Token;
import es.cifpcm.AUT06_BartolomeCesar.Models.RegisterDTO;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IRolesRepository;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    IUserRepository userRe;

    @Autowired
    IRolesRepository rolRe;
    @Autowired
    PasswordEncoder passEnc;

    @Autowired
    JwtService jwtSer;

    @Autowired
    AuthenticationManager authMan;

    public User getUser(String email){
        return userRe.findByEmail(email);
    }

    public void saveUser(User user){
        userRe.save(user);
    }

    public List<User> getUserList(){
        return userRe.findAll();
    }

    public void deleteUser(Integer id){
        userRe.deleteById(id);
    }

    public User getUser(Integer id){ return userRe.findById(id).orElse(null);}

    public Token register(RegisterDTO request){

        User user = new User(request.getName(),
                request.getEmail(),
                passEnc.encode(request.getPassword()));

        user.setRolesList(List.of(rolRe.findByRolename("ROLE_USER")));
        userRe.save(user);
        String token = jwtSer.generateToken(user);
        return Token.builder().token(token).build();
    }

    public Token login(LoginDTO request){

        authMan.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRe.findByEmail(request.getEmail());
        String token = jwtSer.generateToken(user);
        return Token.builder()
                .token(token)
                .build();
    }



    /*public void createUser(User user){

        PasswordEncoder encoder = passwordEncoder();
        if(userRe.findByEmail("Admin@Ikea.com") == null)
        {
            User Admin = new User("Admin", "Admin@Ikea.com", encoder.encode("admin"));
            Admin.setRolesList(List.of(roleRe.findByRolename("ROLE_ADMIN")));
            userRe.save(Admin);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRolesList(List.of(roleRe.findByRolename("ROLE_USER")));
        userRe.save(user);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/


}
