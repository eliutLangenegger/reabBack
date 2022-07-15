package cl.gendarmeria.reabback.resources;

import cl.gendarmeria.reabback.domain.objects.CustomResponse;
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


}
