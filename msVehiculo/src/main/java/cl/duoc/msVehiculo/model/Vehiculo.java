package cl.duoc.msVehiculo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un vehículo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del vehículo", example = "1")
    private Integer id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Patente del vehiculo", example = "ABC123")
    private String patente;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    @Schema(description = "Modelo del vehiculo")
    private Modelo modelo;
    
    @Column(nullable = false)
    @Schema(description = "Kilometraje del vehiculo", example = "15000")
    private Integer kilometraje;

    @Column(nullable = false)
    @Schema(description = "Color del vehiculo", example = "Rojo")
    private String color;

    @Column(name = "sucursal_id", nullable = false)
    @Schema(description = "Identificador de la sucursal", example = "1")
    private Integer sucursalId;

    @ManyToOne
    @JoinColumn(name = "tipovehiculo_id", nullable = false)
    @JsonBackReference
    @Schema(description = "Tipo de vehiculo")
    private TipoVehiculo tipoVehiculo; 
}
