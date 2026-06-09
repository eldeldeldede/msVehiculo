package cl.duoc.msVehiculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.msVehiculo.dto.VehiculoDTO;
import cl.duoc.msVehiculo.model.Vehiculo;
import cl.duoc.msVehiculo.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService service;

    @GetMapping
    @Operation(
        summary = "Obtener la lista de empleados registrados",
        description = "Retorna la lista de empleados registrados en el sistema del Rent a Car."
    )
    public ResponseEntity<List<Vehiculo>> listar(){
        try {
            List<Vehiculo> vehiculos = service.listarVehiculos();
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @Operation(
        summary = "Registrar un nuevo empleado",
        description = "Permite registrar un nuevo empleado en el sistema del Rent a Car."
    )
    public ResponseEntity<Vehiculo> guardarVehiculo(Vehiculo vehiculo){
        try {
            Vehiculo vehiculoNuevo = service.guardarVehiculo(vehiculo);
            return ResponseEntity.ok(vehiculoNuevo);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    @Operation(
        summary = "Buscar empleado por ID",
        description = "Retorna los detalles de un empleado específico por su ID."
    )
    public ResponseEntity<Vehiculo> buscarPorId(@PathVariable Integer id){
        try {
            Vehiculo vehiculo = service.buscarVehiculo(id);
            return ResponseEntity.ok(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/patente/{patente}")
    @Operation(
        summary = "Buscar empleado por patente",
        description = "Retorna los detalles de un empleado específico por su patente."
    )
    public ResponseEntity<Vehiculo> buscarPorPatente(@PathVariable String patente){
        try {
            Vehiculo vehiculo = service.buscarVehiculoPorPatente(patente);
            return ResponseEntity.ok(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    @Operation(
        summary = "Buscar empleado por ID (DTO)",
        description = "Retorna los detalles de un empleado específico por su ID en formato DTO."
    )
    public ResponseEntity<VehiculoDTO> buscarDTO(@PathVariable Integer id){
        try {
            VehiculoDTO vehiculoDTO = service.buscarVehiculoDTO(id);
            return ResponseEntity.ok(vehiculoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar empleado",
        description = "Permite actualizar los detalles de un empleado específico por su ID."
    )
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Integer id,@RequestBody Vehiculo vehiculoActualizado){
        try {
            Vehiculo vehiculo = service.actualizarVehiculo(id, vehiculoActualizado);
            return ResponseEntity.ok(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar empleado",
        description = "Permite eliminar un empleado específico por su ID."
    )
    public ResponseEntity<Void> eliminar(Integer id){
        try {
            service.eliminarVehiculo(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
