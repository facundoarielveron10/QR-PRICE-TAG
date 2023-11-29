package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.attoparser.dom.Text;

@Entity
@Table(name="productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    @NotBlank(message = "El Nombre es obligatorio")
    private String nombre;
    @Column(name = "descripcion")
    @NotBlank(message = "La Descripcion es obligatoria")
    private String descripcion;
    @Column(name = "codigo")
    @NotBlank(message = "El Codigo es obligatorio")
    private String codigo;
    @Column(name = "activo")
    private Boolean activo = false;
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Company company;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getActivo() {
        return activo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
}
