package cl.duoc.msVehiculo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipoVehiculo")
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="ID unico con el cual se identifica el tipo de vehiculo")
    private Integer id;

    @Column(nullable = false)
    @Schema(description="tipo de vehiculo el cual se solicita")
    private String tipo;
}
