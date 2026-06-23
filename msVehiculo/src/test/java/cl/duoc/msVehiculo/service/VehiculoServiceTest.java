package cl.duoc.msVehiculo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.msVehiculo.client.SucursalClient;
import cl.duoc.msVehiculo.dto.SucursalDTO;
import cl.duoc.msVehiculo.dto.VehiculoDTO;
import cl.duoc.msVehiculo.model.Modelo;
import cl.duoc.msVehiculo.model.TipoVehiculo;
import cl.duoc.msVehiculo.model.Vehiculo;
import cl.duoc.msVehiculo.repository.VehiculoRepository;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private SucursalClient sucursalClient;

    @InjectMocks
    private VehiculoService vehiculoService;

    private Vehiculo vehiculoEjemplo;

    @BeforeEach
    void setUp() {
        vehiculoEjemplo = new Vehiculo();
        vehiculoEjemplo.setId(1);
        vehiculoEjemplo.setPatente("ABCD12");
        vehiculoEjemplo.setKilometraje(10000);
        vehiculoEjemplo.setColor("Rojo");
        vehiculoEjemplo.setSucursalId(5);
        vehiculoEjemplo.setModelo(new Modelo());
        vehiculoEjemplo.setTipoVehiculo(new TipoVehiculo());
    }

    @Test
    void listarVehiculos_retornaLista() {
        when(vehiculoRepository.findAll()).thenReturn(List.of(vehiculoEjemplo));

        List<Vehiculo> resultado = vehiculoService.listarVehiculos();

        assertEquals(1, resultado.size());
        assertEquals("ABCD12", resultado.get(0).getPatente());
    }

    @Test
    void buscarVehiculo_encontrado() {
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculoEjemplo));

        Vehiculo resultado = vehiculoService.buscarVehiculo(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void buscarVehiculo_noEncontrado() {
        when(vehiculoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehiculoService.buscarVehiculo(99);
        });

        assertEquals("Vehiculo no encontrado", exception.getMessage());
    }

    @Test
    void buscarVehiculoPorPatente_encontrado() {
        when(vehiculoRepository.findByPatente("ABCD12")).thenReturn(Optional.of(vehiculoEjemplo));

        Vehiculo resultado = vehiculoService.buscarVehiculoPorPatente("ABCD12");

        assertNotNull(resultado);
        assertEquals("ABCD12", resultado.getPatente());
    }

    @Test
    void buscarVehiculoPorPatente_noEncontrado() {
        when(vehiculoRepository.findByPatente("XYZ123")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehiculoService.buscarVehiculoPorPatente("XYZ123");
        });

        assertEquals("Vheìculo no encontrado", exception.getMessage());
    }

    @Test
    void guardarVehiculo_exitoso() {
        when(vehiculoRepository.save(vehiculoEjemplo)).thenReturn(vehiculoEjemplo);

        Vehiculo resultado = vehiculoService.guardarVehiculo(vehiculoEjemplo);

        assertNotNull(resultado);
        assertEquals("ABCD12", resultado.getPatente());
    }

    @Test
    void eliminarVehiculo_exitoso() {
        when(vehiculoRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> vehiculoService.eliminarVehiculo(1));
        verify(vehiculoRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminarVehiculo_noExiste() {
        when(vehiculoRepository.existsById(99)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehiculoService.eliminarVehiculo(99);
        });

        assertEquals("El vehiculo no existe", exception.getMessage());
        verify(vehiculoRepository, times(0)).deleteById(99);
    }

    @Test
    void buscarVehiculoDTO_exitoso() {
        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculoEjemplo));
        
        SucursalDTO sucursalSimulada = new SucursalDTO();
        when(sucursalClient.buscarSucursalDTO(5)).thenReturn(sucursalSimulada);

        VehiculoDTO resultado = vehiculoService.buscarVehiculoDTO(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("ABCD12", resultado.getPatente());
        assertNotNull(resultado.getSucursal());
    }

    @Test
    void actualizarVehiculo_exitoso() {
        Vehiculo datosNuevos = new Vehiculo();
        datosNuevos.setModelo(new Modelo());
        datosNuevos.setPatente("NEWW34");
        datosNuevos.setTipoVehiculo(new TipoVehiculo());
        datosNuevos.setColor("Azul");
        datosNuevos.setKilometraje(15000);

        when(vehiculoRepository.findById(1)).thenReturn(Optional.of(vehiculoEjemplo));
        when(vehiculoRepository.save(vehiculoEjemplo)).thenReturn(vehiculoEjemplo);

        Vehiculo resultado = vehiculoService.actualizarVehiculo(1, datosNuevos);

        assertEquals("NEWW34", resultado.getPatente());
        assertEquals("Azul", resultado.getColor());
        assertEquals(15000, resultado.getKilometraje());
        verify(vehiculoRepository, times(1)).save(vehiculoEjemplo);
    }

    @Test
    void actualizarVehiculo_noEncontrado() {
        when(vehiculoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            vehiculoService.actualizarVehiculo(99, vehiculoEjemplo);
        });

        assertEquals("vehículo no encontrado", exception.getMessage());
        verify(vehiculoRepository, times(0)).save(any(Vehiculo.class));
    }
}
