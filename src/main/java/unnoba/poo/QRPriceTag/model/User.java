package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usuarios")
public class User implements UserDetails {
    // ATRIBUTOS
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @Column(name = "apellido")
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "Al menos 8 caracteres")
    private String password;
    @Transient
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "Al menos 8 caracteres")
    private String confirmarPassword;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public User() {

    }

    // METODOS DE LA CLASE
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getConfirmarPassword() {
        return confirmarPassword;
    }
    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return  email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // METODOS DE LA INTERFAZ (UserDetails)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public String getPassword() {
        return this.password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmarPassword='" + confirmarPassword + '\'' +
                ", rol=" + rol +
                '}';
    }
}
