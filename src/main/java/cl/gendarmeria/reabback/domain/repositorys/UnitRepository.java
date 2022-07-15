package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.daosEntitys.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    Unit findByDescripcion(String desc);
    List<Unit> findAll();
    List<Unit> findAllByRegion(int reg);

    boolean existsByDescripcion(String des);
}
