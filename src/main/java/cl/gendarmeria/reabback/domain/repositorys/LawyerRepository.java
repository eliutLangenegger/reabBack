package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.entitys.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, String> {
    boolean existsByRun(String run);
    Lawyer findByRun(String run);
}
