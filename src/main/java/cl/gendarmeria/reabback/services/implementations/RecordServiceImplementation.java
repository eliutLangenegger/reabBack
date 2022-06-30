package cl.gendarmeria.reabback.services.implementations;

import cl.gendarmeria.reabback.domain.entitys.Lawyer;
import cl.gendarmeria.reabback.domain.entitys.Record;
import cl.gendarmeria.reabback.domain.repositorys.LawyerRepository;
import cl.gendarmeria.reabback.domain.repositorys.RecordRepository;
import cl.gendarmeria.reabback.domain.repositorys.UnitRepository;
import cl.gendarmeria.reabback.dtos.RecordDto;
import cl.gendarmeria.reabback.security.entity.Usuario;
import cl.gendarmeria.reabback.security.service.UsuarioService;
import cl.gendarmeria.reabback.services.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Service @Transactional
@RequiredArgsConstructor
public class RecordServiceImplementation  implements RecordService {

    private final RecordRepository recordRepository;
    private final LawyerRepository lawyerRepository;
    private final UnitRepository unitRepository;

    private final UsuarioService usuarioService;

    //User level -------------------------------------------------------------------------------->
    public RecordDto getRecordUser(long id, String user) throws Exception {
        try {
            Usuario usuario = usuarioService.getByNombreUsuario(user).get();
            Record record = recordRepository.findById(id).get();

            if (record.getUnitCode().equals(unitRepository.findByDescripcion(usuario.getUnidad()).getCodigo())){
                return mapperRecordDto(record);
            }else
                return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public boolean setOutDate(long id, String user) throws Exception {
        try {
            Record record = recordRepository.findById(id).get();
            Usuario usuario = usuarioService.getByNombreUsuario(user).get();
            if (record.getUnitCode().equals(unitRepository.findByDescripcion(usuario.getUnidad()).getCodigo())){
                record.setOutDate(new Date());
                recordRepository.save(record);
                return true;
            } return false;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Page<RecordDto> getRecodsPageUser(String user, Pageable pageable) throws Exception{
        try {
            Usuario usuario = usuarioService.getByNombreUsuario(user).get();
            List<Record> records = recordRepository.findByUnitCode(unitRepository.findByDescripcion(usuario.getUnidad()).getCodigo(), pageable);
            List<RecordDto> dtos = new ArrayList<>();
            for(Record r: records){
                RecordDto dto = mapperRecordDto(r);
                dtos.add(dto);
            }
           return new PageImpl<>(dtos, pageable, dtos.size());
        }catch (Exception e){
            throw new Exception(e);
        }
    }



    //Admin level -------------------------------------------------------------------------------------->
    @Override
    public void saveRecord(RecordDto dto) throws Exception {
        try {
            Record record = new Record();
            Lawyer lawyer = lawyerRepository.findByRun(dto.getLawyer());
            record.setLawyer(lawyer);
            record.setUnitCode(dto.getUnitCode());
            record.setVisitDate(new Date());
            record.setInternals(dto.getInternals());
            recordRepository.save(record);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Page<Record> getRecordsByLawyer(String run, Pageable  pageable) throws Exception {
        try {
            return recordRepository.findByLawyer_Run(run, pageable);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Page<Record> getRecordsByUnit(int code, Pageable  pageable) throws Exception {
        try {
            //return recordRepository.findByUnitCode(code,pageable);
            return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Page<Record> getRecordsByDate(Date init, Date finish, Pageable pageable) throws Exception {
        try {
            if (finish!= null)
                return recordRepository.findByVisitDateBetween(init, finish, pageable);
            else
                return recordRepository.findByVisitDate(init, pageable);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Page<Record> getRecordsByInternal(String run, Pageable  pageable) throws Exception {
        try {
            return recordRepository.findByInternalsContains(run, pageable);
        }catch (Exception e){
            throw new Exception(e);
        }
    }


    private RecordDto mapperRecordDto(Record record){
        RecordDto dto = new RecordDto();
        dto.setId(record.getId());
        dto.setInternals(record.getInternals());
        dto.setLawyerRun(record.getLawyer().getRun());
        dto.setLawyer(record.getLawyer().getName()+" "+record.getLawyer().getSurname());
        dto.setDate(record.getVisitDate());
        dto.setOut(record.getOutDate());
        dto.setUnit(unitRepository.findById(record.getUnitCode()).get().getDescripcion());
        return dto;
    }

}
