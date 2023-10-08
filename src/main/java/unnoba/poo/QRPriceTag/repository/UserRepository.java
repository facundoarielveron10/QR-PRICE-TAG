package unnoba.poo.QRPriceTag.repository;

import unnoba.poo.QRPriceTag.Model.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
