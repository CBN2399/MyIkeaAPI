package es.cifpcm.AUT06_BartolomeCesar.Security;

import es.cifpcm.AUT06_BartolomeCesar.Models.Roles;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IRolesRepository;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthConfiguration {

    @Autowired
    IRolesRepository rolRe;

    @Autowired
    IUserRepository userRe;

    @Autowired
    PasswordEncoder passEnc;

    @Autowired
    JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.
                csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/register","/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public void seeder(){

        if(rolRe.findByRolename("ROLE_USER") == null){
            rolRe.save(new Roles("ROLE_USER"));
        }

        if(rolRe.findByRolename("ROLE_ADMIN") == null){
            rolRe.save(new Roles("ROLE_ADMIN"));
        }

        if(rolRe.findByRolename("ROLE_MANAGER") == null){
            rolRe.save(new Roles("ROLE_MANAGER"));
        }

        if(userRe.findByEmail("user1@ikea.com") == null){
            User user = new User("user1","user1@ikea.com",passEnc.encode("1234"));
            user.setRolesList(List.of(rolRe.findByRolename("ROLE_USER")));
            userRe.save(user);
        }

        if(userRe.findByEmail("manager@ikea.com") == null){
            User user = new User("manager","manager@ikea.com",passEnc.encode("1234"));
            user.setRolesList(List.of(rolRe.findByRolename("ROLE_MANAGER")));
            userRe.save(user);
        }

        if(userRe.findByEmail("admin@ikea.com") == null){
            User user = new User("admin","admin@ikea.com",passEnc.encode("1234"));
            user.setRolesList(List.of(rolRe.findByRolename("ROLE_ADMIN")));
            userRe.save(user);
        }
    }
}
