package cl.gendarmeria.reabback.security.repository;

import cl.gendarmeria.reabback.security.entity.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroLoginRepository extends JpaRepository<RegistroLogin, Long> {
}
