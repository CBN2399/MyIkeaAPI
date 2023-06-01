package es.cifpcm.AUT06_BartolomeCesar.Security;

import es.cifpcm.AUT06_BartolomeCesar.Models.Roles;
import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    @Autowired
    IUserRepository userRe;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                User usuarioSelected = userRe.findByEmail(username);
                if(usuarioSelected == null){
                    throw new UsernameNotFoundException("No se ha encontrado un usuario con ese email...");
                }

                UserDetails user = new org.springframework.security.core.userdetails.User(usuarioSelected.getEmail(),
                        usuarioSelected.getPassword(),mapRolesToAuthorities(usuarioSelected.getRolesList()));
                return user;
            }
        };
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles)
    {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
