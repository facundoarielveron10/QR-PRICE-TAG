package unnoba.poo.QRPriceTag.model;

import jakarta.persistence.*;

@Entity
@Table(name="empresas")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "logo")
    private String logo;
    @Column(name = "activo")
    private boolean activo;
}
