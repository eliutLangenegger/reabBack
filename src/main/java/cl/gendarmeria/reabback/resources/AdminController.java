package cl.gendarmeria.reabback.resources;

import cl.gendarmeria.reabback.domain.objects.CustomResponse;
import cl.gendarmeria.reabback.services.implementations.LawyerServiceImplementation;
import cl.gendarmeria.reabback.services.implementations.RecordServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/

@RestController
@RequestMapping("/administration")
@RequiredArgsConstructor
public class AdminController {

    private final LawyerServiceImplementation lawyerService;
    private final RecordServiceImplementation recordService;

    @GetMapping("/get/recordPage/unit")
    public ResponseEntity<CustomResponse> getUnitPage(@RequestParam int code, Pageable pageable) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.getRecordsByUnit(code, pageable))))
                        .message("Records retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/recordPage/lawyer")
    public ResponseEntity<CustomResponse> getLawyerPage(@RequestParam String run, Pageable pageable) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.getRecordsByLawyer(run, pageable))))
                        .message("Records retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/recordPage/internal")
    public ResponseEntity<CustomResponse> getInternalPage(@RequestParam String run, Pageable pageable) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.getRecordsByInternal(run, pageable))))
                        .message("Records retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/recordPage/date")
    public ResponseEntity<CustomResponse> getInternalPage(@RequestParam Date init, @RequestParam Date finish , Pageable pageable) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.getRecordsByDate(init, finish, pageable))))
                        .message("Records retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
