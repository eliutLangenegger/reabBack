package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.entitys.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByUnitCode(int code, Pageable  pageable);
    Page<Record> findByLawyer_Run(String run, Pageable  pageable);
    Page<Record> findByInternalsContains(String run, Pageable  pageable);
    Page<Record> findByVisitDateBetween(Date init, Date finish, Pageable  pageable);
    Page<Record> findByVisitDate(Date date, Pageable  pageable);

    boolean existsById(long id);
}
