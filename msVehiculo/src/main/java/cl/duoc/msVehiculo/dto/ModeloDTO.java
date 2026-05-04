package cl.duoc.msVehiculo.dto;

import cl.duoc.msVehiculo.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDTO {

    private Integer id;
    private String nombre;
    private Integer anio;
    private Marca marca;
}
