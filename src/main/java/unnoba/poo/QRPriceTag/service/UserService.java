package unnoba.poo.QRPriceTag.service;

import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.Model.User;
import java.util.List;

@Service
public interface UserService {
    public User createUser(User usuario);
    public List<User> getAllUsers();
}
