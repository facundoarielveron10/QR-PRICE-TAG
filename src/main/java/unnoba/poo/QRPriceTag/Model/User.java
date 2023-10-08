package unnoba.poo.QRPriceTag.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    // ATRIBUTOS
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String confirmarPassword;

    // METODOS DE LA CLASE
    public String getEmail() {
        return  email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return  password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // METODOS DE LA INTERFAZ (UserDetails)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
        return this.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
