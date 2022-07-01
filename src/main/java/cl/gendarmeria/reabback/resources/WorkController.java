package cl.gendarmeria.reabback.resources;

import cl.gendarmeria.reabback.domain.objects.CustomResponse;
import cl.gendarmeria.reabback.dtos.LawyerDto;
import cl.gendarmeria.reabback.dtos.RecordDto;
import cl.gendarmeria.reabback.services.implementations.LawyerServiceImplementation;
import cl.gendarmeria.reabback.services.implementations.RecordServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private final LawyerServiceImplementation lawyerService;
    private final RecordServiceImplementation recordService;

    //lawyer methods only
    @PostMapping("/new/lawyer")
    public ResponseEntity<CustomResponse> saveLawyer(@RequestBody LawyerDto dto)throws Exception{
        System.out.println("llama");
        lawyerService.saveLawyer(dto);
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

    @GetMapping("/get/lawyerUser")
    public ResponseEntity<CustomResponse> getLawyer(@RequestParam String run) throws Exception{
        List<LawyerDto> data = new ArrayList();
        data.add(lawyerService.findLawyerUser(run));
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(data)
                        .message("Lawyer retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/lawyerList")
    public ResponseEntity<CustomResponse> getLawyerByUser(Pageable pageable) throws Exception{
        List<Page<?>> data = new ArrayList();
        data.add(recordService.getLawersPage(pageable));
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(data)
                        .message("Lawyers Page retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    //Records methods contain
    @PostMapping("/new/record")
    public ResponseEntity<CustomResponse> saveRecord(@RequestBody RecordDto dto)throws Exception{
        recordService.saveRecord(dto);
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(null)
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/recordUser")
    public ResponseEntity<CustomResponse> getRecordUser(@RequestParam String user, @RequestParam long id) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.getRecordUser(id, user))))
                        .message("Record retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/setOutDate")
    public ResponseEntity<CustomResponse> setOutDate(@RequestParam String user, @RequestParam long id) throws Exception{
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(Collections.singletonList(new ArrayList<>().add(recordService.setOutDate(id, user))))
                        .message("Record retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/recordByUnitUser")
    public ResponseEntity<CustomResponse> getRecordByUnitUser(@RequestParam String user, Pageable pageable) throws Exception{
        List<Page<?>> data = new ArrayList();
        data.add(recordService.getRecodsPageUser(user, pageable));
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .dateTime(now())
                        .data(data)
                        .message("Record retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get/existLawyer")
    public ResponseEntity<Boolean> getExistLawyer(@RequestParam String run) throws Exception{
        return new ResponseEntity<>(recordService.existLowyer(run), OK);
    }

}
