package unnoba.poo.QRPriceTag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unnoba.poo.QRPriceTag.model.User;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    public User findByEmail(String email);
}
