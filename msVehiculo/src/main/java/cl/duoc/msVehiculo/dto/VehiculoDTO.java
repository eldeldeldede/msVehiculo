package cl.duoc.msVehiculo.dto;

import cl.duoc.msVehiculo.model.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {

    private Integer id;
    private String patente;
    private Modelo modelo;
}
