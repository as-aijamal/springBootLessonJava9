package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

//    User getUserByEmail(String email);

    @Query(value = "select u from User u where u.email=?1")
    User getUserWithEmail(String email);


}