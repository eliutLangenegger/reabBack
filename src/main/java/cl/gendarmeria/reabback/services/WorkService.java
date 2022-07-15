package cl.gendarmeria.reabback.services;

import cl.gendarmeria.reabback.domain.entitys.Lawyer;
import cl.gendarmeria.reabback.domain.entitys.Record;
import cl.gendarmeria.reabback.domain.repositorys.LawyerRepository;
import cl.gendarmeria.reabback.domain.repositorys.RecordRepository;
import cl.gendarmeria.reabback.dtos.LawyerDto;
import cl.gendarmeria.reabback.dtos.LawyerSaveDto;
import cl.gendarmeria.reabback.dtos.RecordDto;
import cl.gendarmeria.reabback.dtos.RecordSaveDto;
import cl.gendarmeria.reabback.exceptions.InternalServerErrorException;
import cl.gendarmeria.reabback.exceptions.NotFoundException;
import cl.gendarmeria.reabback.exceptions.ReabException;
import cl.gendarmeria.reabback.security.entity.Usuario;
import cl.gendarmeria.reabback.security.service.UsuarioService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 15-07-2022
 **/
@Service @Getter @Setter @RequiredArgsConstructor
public class WorkService {

    private final LawyerRepository lawyerRepository;
    private final RecordRepository recordRepository;

    private final UsuarioService usuarioService;

    public void saveLawyer(LawyerSaveDto dto) throws ReabException {
        try {
            if (!lawyerRepository.existsByRun(dto.getRun())){
                Lawyer l  =new Lawyer(dto.getRun(), dto.getName(), dto.getSurname(), new Date(), dto.getExpirationDate(),
                        dto.getType(), true, null, dto.getUserUp(), null );
                lawyerRepository.save(l);
            }else
                throw new DuplicateKeyException("LAWYER_ALREADY_REGISTERED");
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LAWYER_CANT_REGISTERED");
        }
    }

    public Map<String , LawyerDto> getBasicLawyer(String run) throws ReabException{
        try {
            Map<String , LawyerDto> response = new HashMap<>();
            if (lawyerRepository.existsByRun(run)){
                response.put("lawyer", mapperLawyerDto(lawyerRepository.findByRun(run)));
                return response;
            }else
                throw new NotFoundException("LAWYER_NOT_FOUND", "LAWYER_NOT_FOUND");
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LAWYER_CANT_RETRIEVED");
        }
    }

    public void registerNewRecord(RecordSaveDto dto) throws ReabException{
        try {
            if (lawyerRepository.existsByRun(dto.getLawyerRun())){
                Record r = new Record();
                r.setLawyer(lawyerRepository.findByRun(dto.getLawyerRun()));
                r.setVisitDate(new Date());
                StringBuilder internals = new StringBuilder();
                for(String i: dto.getInternals()){
                    internals.append(";").append(i);}
                r.setInternals(internals.toString());
                r.setUnitCode(dto.getUnitCode());
                recordRepository.save(r);
            }else
                throw new NotFoundException("LAWYER_NOT_FOUND", "LAWYER_NOT_FOUND");
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","RECORD_CANT_CREATED");
        }
    }

    public Map<String , RecordDto> getBasicRecord(long id, String user) throws ReabException{
        try {
            if (recordRepository.existsById(id)){
                Record r = recordRepository.findById(id).get();
                if (r.getUnitCode().equals(usuarioService.getByNombreUsuario(user).get().getUnidad())){
                    Map<String, RecordDto> response = new HashMap<>();
                    response.put("record", mapperRecordDto(r));
                    return response;
                }else
                    throw new IllegalAccessError("NOT_AUTHORIZED");
            }else
                throw new NotFoundException("RECORD_NOT_FOUND", "RECORD_NOT_FOUND");
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","RECORD_CANT_CREATED");
        }
    }

    public void setOutDate(long id) throws ReabException{
        try {
            if (recordRepository.existsById(id)){
                Record r = recordRepository.findById(id).get();
                r.setOutDate(new Date());
                recordRepository.save(r);
            }else
                throw new NotFoundException("RECORD_NOT_FOUND", "RECORD_NOT_FOUND");
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","RECORD_CANT_CREATED");
        }
    }


    public Map<String , Page<Record>> getBasicUnitRecords (String user, Pageable pageable) throws ReabException {
        try {
            Map<String , Page<Record>> response = new HashMap<>();
            Usuario u = usuarioService.getByNombreUsuario(user).get();
            response.put("records", recordRepository.recordByUnit(u.getUnidad(), pageable));
            return response;
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","RECORD_CANT_CREATED");
        }
    }

    // Privates Methods --------------------------------------------->
    private LawyerDto mapperLawyerDto(Lawyer l) throws ReabException {
        try {
            LawyerDto dto = new LawyerDto();
            dto.setRun(l.getRun());
            dto.setName(l.getName());
            dto.setSurname(l.getSurname());
            dto.setActive(l.isActive());
            dto.setType(l.getType());
            dto.setUpDate(l.getUpDate());
            dto.setExpirationDate(l.getExpirationDate());
            return dto;
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LAWYER_CANT_RETRIEVED");
        }
    }

    private RecordDto mapperRecordDto(Record r) throws ReabException{
        try {
            RecordDto dto = new RecordDto();
            dto.setId(r.getId());
            dto.setLawyer(r.getLawyer().getRun());
            dto.setVisitDate(r.getVisitDate());
            dto.setOutDate(r.getOutDate());
            return dto;
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LAWYER_CANT_RETRIEVED");
        }
    }
}
