package cl.gendarmeria.reabback.services.implementations;

import cl.gendarmeria.reabback.domain.entitys.Lawyer;
import cl.gendarmeria.reabback.domain.repositorys.LawyerRepository;
import cl.gendarmeria.reabback.dtos.LawyerDto;
import cl.gendarmeria.reabback.services.LawyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Christopher Langenegger
 * @since 28-06-2022
 * @version 1.0
 **/
@Service @Transactional
@RequiredArgsConstructor
public class LawyerServiceImplementation implements LawyerService {

    private final LawyerRepository lawyerRepository;

    @Override
    public Lawyer saveLawyer(LawyerDto dto) throws Exception {
        try {
            if(!lawyerRepository.existsByRun(dto.getRun())){
                Lawyer lawyer = new Lawyer();
                lawyer.setRun(dto.getRun());
                lawyer.setName(dto.getName());
                lawyer.setSurname(dto.getSurname());
                lawyer.setUpDate(new Date());
                lawyer.setType(dto.getType());
                lawyer.setActive(true);
                lawyer.setExpirationDate(dto.getExpirationDate()!=null ? dto.getExpirationDate(): null);
                lawyer.setUpUser(dto.getUpUser());
                lawyerRepository.save(lawyer);
                return lawyer;
            }else
                return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public LawyerDto findLawyer(String run) throws Exception {
        try {
            if (lawyerRepository.existsByRun(run)){
                Lawyer lawyer = lawyerRepository.findByRun(run);
                LawyerDto dto = new LawyerDto();
                dto.setRun(lawyer.getRun());
                dto.setName(lawyer.getName());
                dto.setSurname(lawyer.getSurname());
                dto.setExpirationDate(lawyer.getExpirationDate());
                dto.setUpDate(lawyer.getUpDate());
                dto.setUpUser(lawyer.getUpUser());
                return dto;
            }else
                return null;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public LawyerDto updateLawyer(LawyerDto dto) throws Exception {
        return null;
    }

    @Override
    public void changeActive(String run) throws Exception {
        try {
            Lawyer lawyer = lawyerRepository.findByRun(run);
            lawyer.setActive(!lawyer.isActive());
            lawyerRepository.save(lawyer);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
