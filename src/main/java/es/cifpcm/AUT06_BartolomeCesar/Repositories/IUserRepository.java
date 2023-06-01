package es.cifpcm.AUT06_BartolomeCesar.Repositories;

import es.cifpcm.AUT06_BartolomeCesar.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
