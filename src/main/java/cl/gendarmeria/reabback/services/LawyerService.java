package cl.gendarmeria.reabback.services;

import cl.gendarmeria.reabback.domain.entitys.Lawyer;
import cl.gendarmeria.reabback.dtos.LawyerDto;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
public interface LawyerService {
    Lawyer saveLawyer(LawyerDto dto) throws Exception;
    LawyerDto findLawyerUser(String run) throws Exception;
    LawyerDto updateLawyer(LawyerDto dto) throws Exception;

    void changeActive(String run) throws Exception;
}
