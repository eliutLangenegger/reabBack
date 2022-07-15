package cl.gendarmeria.reabback.resources;


import cl.gendarmeria.reabback.domain.objects.CustomResponse;
import cl.gendarmeria.reabback.dtos.LawyerSaveDto;
import cl.gendarmeria.reabback.dtos.RecordSaveDto;
import cl.gendarmeria.reabback.services.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@RestController
@RequestMapping("reabwork")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorkController {

    private final WorkService workService;

    @PostMapping("/new/lawyer")
    public ResponseEntity<CustomResponse> saveLawyer(@RequestBody LawyerSaveDto dto)throws Exception{
        workService.saveLawyer(dto);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(null)
                        .message("Lawyer created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/lawyer")
    public ResponseEntity<CustomResponse> getLawyer(@RequestParam String run)throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(workService.getBasicLawyer(run))
                        .message("Lawyer retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/new/record")
    public ResponseEntity<CustomResponse> saveLawyer(@RequestBody RecordSaveDto dto)throws Exception{
        workService.registerNewRecord(dto);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(null)
                        .message("Record created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/record")
    public ResponseEntity<CustomResponse> getRecord(@RequestParam String user, @RequestParam long id)throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(workService.getBasicRecord(id, user))
                        .message("Record retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/edit/record")
    public ResponseEntity<CustomResponse> setOutDate(@RequestParam long id)throws Exception{
        workService.setOutDate(id);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(null)
                        .message("Record created")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/unit-records")
    public ResponseEntity<CustomResponse> getLUnitRecords(@RequestParam String user,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "visitDate") String order,
                                                          @RequestParam(defaultValue = "true") boolean asc
    )throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(workService.getBasicUnitRecords(user, PageRequest.of(page, size, Sort.by(order))))
                        .message("Unit records retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
