package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;
import unnoba.poo.QRPriceTag.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="empresas")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razon_social")
    @NotBlank(message = "La Razon Social es obligatoria")
    private String razonSocial;
    @Column(name = "cuit")
    @NotBlank(message = "El Cuit es obligatorio")
    private String cuit;
    @Column(name = "logo")
    private String logo;
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(mappedBy = "company")
    private List<User> users;
    @Transient
    private MultipartFile logoFile;

    public static List<Company> getAllCompanies(CompanyRepository repository) {
        return repository.findAll();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public MultipartFile getLogoFile() {
        return logoFile;
    }
    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuit='" + cuit + '\'' +
                ", logo='" + logo + '\'' +
                ", activo=" + activo +
                ", users=" + users +
                ", logoFile=" + logoFile +
                '}';
    }
}
