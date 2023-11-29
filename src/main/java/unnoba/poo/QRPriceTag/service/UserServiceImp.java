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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

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


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void editUser(User user, User userEdit) throws Exception {
        // Actualizamos los datos
        userEdit.setNombre(user.getNombre());
        userEdit.setApellido(user.getApellido());
        userEdit.setEmail(user.getEmail());
        user.setCompany(user.getCompany());

        repository.save(userEdit);
    }

    @Override
    public void deleteUser(User user) {
        repository.delete(user);
    }
}
