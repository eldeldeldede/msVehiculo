package cl.duoc.msVehiculo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String patente;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
    
    @Column(nullable = false)
    private Integer kilometraje;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "tipovehiculo_id")
    private TipoVehiculo tipoVehiculo; 
}
