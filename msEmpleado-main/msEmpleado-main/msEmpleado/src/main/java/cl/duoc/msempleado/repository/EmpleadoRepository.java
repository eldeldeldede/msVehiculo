package cl.duoc.msempleado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.msempleado.model.Empleado;
import jakarta.transaction.Transactional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

    Optional<Empleado> findByRut(String rut);

    Optional<Empleado> deleteByRut(String rut);

    @Transactional
    boolean existsByRut(String rut);

}
