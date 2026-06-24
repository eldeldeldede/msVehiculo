package cl.duoc.msVehiculo.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modelo")
@Schema(description = "Entidad que representa un modelo de vehículo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="ID unico con el cual se identifica el tipo de vehiculo")
    private Integer id;

    @Column(nullable = false)
    @Schema(description="nombre o modelo del vehiculo")
    private String nombre;

    @Column(nullable = false)
    @Schema(description="año de fabricacion del vehiculo")
    private Integer anio;

    @Column(nullable = false)
    @Schema(description="Cantidad de puertas con las que cuenta el vehiculo")
    private Integer puertas;

    @Column(nullable = false)
    @Schema(description="cantidad de pasajeros en el cual pueden ir los clientes")
    private Integer pasajeros;

    @Column(nullable = false)
    @Schema(description="tipo de transmision del vehiculo",
            examples={"mecanico, automatico"})
    private String transmision;

    @Column(nullable = false)
    @Schema(description="Tipo de combustible que utiliza el vehiculo a arrendar",
            examples={"diesel, bencina, electrico"})
    private String combustible;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    @Schema(description="Marca del vehiculo a arrendar")
    private Marca marca;

}
