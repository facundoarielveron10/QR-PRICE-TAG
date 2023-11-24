package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;
import org.attoparser.dom.Text;

@Entity
@Table(name="productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private Text descripcion;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "activo")
    private boolean activo;
}
