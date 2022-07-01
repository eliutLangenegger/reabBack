package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.entitys.UnitCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 01-07-2022
 **/
@Repository
public interface UnitCalendarRepository extends JpaRepository<UnitCalendar, Long> {
}
