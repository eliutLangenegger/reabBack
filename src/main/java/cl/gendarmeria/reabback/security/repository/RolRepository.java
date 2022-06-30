package cl.gendarmeria.reabback.security.repository;

import cl.gendarmeria.reabback.security.entity.Rol;
import cl.gendarmeria.reabback.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
