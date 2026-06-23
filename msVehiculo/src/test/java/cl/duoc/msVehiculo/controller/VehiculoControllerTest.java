package cl.duoc.msVehiculo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.msVehiculo.dto.VehiculoDTO;
import cl.duoc.msVehiculo.model.Vehiculo;
import cl.duoc.msVehiculo.service.VehiculoService;

@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VehiculoService vehiculoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vehiculo vehiculoEjemplo;

    @BeforeEach
    void setUp() {
        vehiculoEjemplo = new Vehiculo();
        vehiculoEjemplo.setId(1);
        vehiculoEjemplo.setPatente("ABCD12");
        vehiculoEjemplo.setKilometraje(12000);
        vehiculoEjemplo.setColor("Blanco");
        vehiculoEjemplo.setSucursalId(3);
    }

    @Test
    void listar_retornaListaYOk() throws Exception {
        when(vehiculoService.listarVehiculos()).thenReturn(List.of(vehiculoEjemplo));

        mockMvc.perform(get("/api/v1/vehiculos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patente").value("ABCD12"));
    }

    @Test
    void listar_error_retornaNoContent() throws Exception {
        when(vehiculoService.listarVehiculos()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/v1/vehiculos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void guardarVehiculo_exitoso_retornaOk() throws Exception {
        when(vehiculoService.guardarVehiculo(any(Vehiculo.class))).thenReturn(vehiculoEjemplo);

        mockMvc.perform(post("/api/v1/vehiculos")
                .param("patente", "ABCD12")
                .param("kilometraje", "12000")
                .param("color", "Blanco"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void guardarVehiculo_error_retornaNoContent() throws Exception {
        when(vehiculoService.guardarVehiculo(any(Vehiculo.class))).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/v1/vehiculos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarPorId_encontrado_retornaOk() throws Exception {
        when(vehiculoService.buscarVehiculo(1)).thenReturn(vehiculoEjemplo);

        mockMvc.perform(get("/api/v1/vehiculos/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarPorId_noEncontrado_retornaNotFound() throws Exception {
        when(vehiculoService.buscarVehiculo(99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/v1/vehiculos/id/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorPatente_encontrado_retornaOk() throws Exception {
        when(vehiculoService.buscarVehiculoPorPatente("ABCD12")).thenReturn(vehiculoEjemplo);

        mockMvc.perform(get("/api/v1/vehiculos/patente/ABCD12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value("ABCD12"));
    }

    @Test
    void buscarPorPatente_noEncontrado_retornaNotFound() throws Exception {
        when(vehiculoService.buscarVehiculoPorPatente("XYZ123")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/v1/vehiculos/patente/XYZ123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarDTO_encontrado_retornaOk() throws Exception {
        VehiculoDTO dto = new VehiculoDTO(1, "ABCD12", null, 12000, null);
        when(vehiculoService.buscarVehiculoDTO(1)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/vehiculos/dto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void buscarDTO_noEncontrado_retornaNotFound() throws Exception {
        when(vehiculoService.buscarVehiculoDTO(99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/v1/vehiculos/dto/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void actualizar_exitoso_retornaOk() throws Exception {
        when(vehiculoService.actualizarVehiculo(eq(1), any(Vehiculo.class))).thenReturn(vehiculoEjemplo);

        String jsonCuerpo = objectMapper.writeValueAsString(vehiculoEjemplo);

        mockMvc.perform(put("/api/v1/vehiculos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCuerpo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizar_error_retornaNotFound() throws Exception {
        when(vehiculoService.actualizarVehiculo(eq(99), any(Vehiculo.class))).thenThrow(new RuntimeException());

        String jsonCuerpo = objectMapper.writeValueAsString(vehiculoEjemplo);

        mockMvc.perform(put("/api/v1/vehiculos/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonCuerpo))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminar_exitoso_retornaNoContent() throws Exception {
        doNothing().when(vehiculoService).eliminarVehiculo(1);

        mockMvc.perform(delete("/api/v1/vehiculos/1")
                .param("id", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminar_error_retornaNotFound() throws Exception {
        doThrow(new RuntimeException()).when(vehiculoService).eliminarVehiculo(99);

        mockMvc.perform(delete("/api/v1/vehiculos/99")
                .param("id", "99"))
                .andExpect(status().isNotFound());
    }
}