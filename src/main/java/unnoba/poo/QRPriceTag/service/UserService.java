package unnoba.poo.QRPriceTag.service;

import unnoba.poo.QRPriceTag.model.User;
import java.util.List;

public interface UserService {
    public User findByEmail(String email);
    public User createUser(User user) throws Exception;
    public List<User> getAllUsers();
    public User getUserById(Long id);
}
