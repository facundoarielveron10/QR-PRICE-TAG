package unnoba.poo.QRPriceTag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import unnoba.poo.QRPriceTag.model.Rol;
import unnoba.poo.QRPriceTag.model.User;
import unnoba.poo.QRPriceTag.repository.UserRepository;
import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public User findByEmail(String username) {
        return repository.findByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("El usuario " + username + " no existe");
        }
        user.getAuthorities();
        return user;
    }

    private boolean checkEmail(User user) throws Exception {
        User userFound = repository.findByEmail(user.getUsername());
        if (userFound != null) {
            throw new Exception("Email ya registrado");
        }
        return true;
    }

    private boolean checkPassword(User user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmarPassword())) {
            throw new Exception("Las contraseñas no son iguales");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (checkEmail(user) && checkPassword(user)) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRol(Rol.ROLE_GESTOR);
            user = repository.save(user);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
