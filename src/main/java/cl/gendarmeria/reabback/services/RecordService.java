package cl.gendarmeria.reabback.services;

import cl.gendarmeria.reabback.domain.entitys.Record;
import cl.gendarmeria.reabback.dtos.RecordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
public interface RecordService {
    void saveRecord(RecordDto dto) throws Exception;
    RecordDto getRecordUser(long id, String user) throws Exception;
    boolean setOutDate(long id, String user) throws Exception;

    Page<Record> getRecordsByLawyer(String run, Pageable pageable) throws Exception;
    Page<Record> getRecordsByUnit(int code, Pageable  pageable) throws Exception;
    Page<Record> getRecordsByDate(Date init, Date finish, Pageable  pageable) throws Exception;
    Page<Record> getRecordsByInternal(String run, Pageable  pageable) throws Exception;
}
