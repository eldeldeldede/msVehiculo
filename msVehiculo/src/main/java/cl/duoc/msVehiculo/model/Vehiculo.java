package cl.duoc.msVehiculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @Schema(description="ID unico con el cual se identifica el vehiculo a arrendar")
    private Integer id;

    @Column(nullable = false, unique = true)
    @Schema(description="Patente o matricula con la cual se identifica el vehiculo a arrendar")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    @Schema(description="Modelo del vehiculo a arrendar")
    private Modelo modelo;
    
    @Column(nullable = false)
    @Schema(description="cantidad de kilometraje que recorrió el vehiculo anteriormente")
    private Integer kilometraje;

    @Column(nullable = false)
    @Schema(description="color del vehiculo a arrendar")
    private String color;

    @Column(name = "sucursal_id", nullable = false)
    @Schema(description="ID de la sucursal en la cual se solicitó el vehiculo")
    private Integer sucursalId;

    @ManyToOne
    @JoinColumn(name = "tipovehiculo_id", nullable = false)
    @JsonBackReference
    @Schema(description="tipo de vehiculo que se va a arrendar",
            examples={"SUV, hatchback, pickup, station wagon"})
    private TipoVehiculo tipoVehiculo; 
}
