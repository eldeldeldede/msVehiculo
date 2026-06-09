package cl.duoc.msVehiculo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.msVehiculo.dto.SucursalDTO;

@FeignClient(name = "msSucursal")
public interface SucursalClient {

    @GetMapping("/api/v1/sucursales/dto/{id}")
    public SucursalDTO buscarSucursalDTO(@PathVariable ("id")Integer id);

}
