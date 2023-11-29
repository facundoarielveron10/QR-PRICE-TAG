package unnoba.poo.QRPriceTag.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import unnoba.poo.QRPriceTag.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    public User createUser(User user) throws Exception;
    public BCryptPasswordEncoder passwordEncoder();
    public void editUser(User user, User userEdit) throws Exception;
    public void deleteUser(User user);
}
