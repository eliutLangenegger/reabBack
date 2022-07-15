package cl.gendarmeria.reabback.domain.repositorys;

import cl.gendarmeria.reabback.domain.entitys.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    //Record by user unit in system
    @Query("select r.id, r.lawyer.run, r.visitDate, r.outDate from Record r where r.unitCode=?1 order by r.visitDate")
    Page<Record> recordByUnit (int code, Pageable pageable);

    boolean existsById(long id);
}
