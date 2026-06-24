package cl.duoc.msVehiculo.model;

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
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description="ID unico con el cual se identifica la marca del vehiculo")
    private Integer id;

    @Column(nullable = false)
    @Schema(description="nombre con el cual se uidentifica el auto")
    private String nombre;

    @Column(nullable = false)
    @Schema(description="pais de origen del vehiculo")
    private String pais;
}
