package cl.duoc.msVehiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msVehiculo.model.Vehiculo;
import cl.duoc.msVehiculo.repository.VehiculoRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository repo;

    public List<Vehiculo> listarVehiculos(){
        return repo.findAll();
    }

    public Vehiculo buscarVehiculo(Integer id){
        return repo.findById(id).orElseThrow(()-> new RuntimeException("Vehiculo no encontrado"));
    }

    public Vehiculo buscarVehiculoPorPatente(String patente){
        return repo.findByPatente(patente).orElseThrow(()-> new RuntimeException("Vheìculo no encontrado"));
    }

    public Vehiculo guardarVehiculo(Vehiculo vehiculo){
        return repo.save(vehiculo);
    }

    public void eliminarVehiculo(Integer id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        }else{
            throw new RuntimeException("El vehiculo no existe");
        }
    }

    public Vehiculo actualizarVehiculo(Integer id, Vehiculo vehiculoActualizado){
        Vehiculo vehiculo = repo.findById(id).orElseThrow(() -> new RuntimeException("vehículo no encontrado"));
        vehiculo.setModelo(vehiculoActualizado.getModelo());
        vehiculo.setPatente(vehiculoActualizado.getPatente());
        vehiculo.setTipoVehiculo(vehiculoActualizado.getTipoVehiculo());
        vehiculo.setColor(vehiculoActualizado.getColor());
        vehiculo.setKilometraje(vehiculoActualizado.getKilometraje());

        return repo.save(vehiculo);
    }
}
