package unnoba.poo.QRPriceTag.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import unnoba.poo.QRPriceTag.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    public User findByEmail(String email);
    public User createUser(User user) throws Exception;
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public BCryptPasswordEncoder passwordEncoder();
}
