package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import unnoba.poo.QRPriceTag.repository.CompanyRepository;

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
    private Boolean activo = false;
    @OneToMany(mappedBy = "company")
    private List<User> users;
    @OneToMany(mappedBy = "company")
    private List<Product> products;
    @Transient
    private MultipartFile logoFile;

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
    public Boolean isActivo() {
        return activo;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
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
