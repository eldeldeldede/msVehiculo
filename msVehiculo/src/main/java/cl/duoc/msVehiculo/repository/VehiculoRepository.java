package cl.duoc.msVehiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.msVehiculo.model.Vehiculo;
import java.util.Optional;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>{

    public Optional<Vehiculo> findByPatente(String patente);

}
