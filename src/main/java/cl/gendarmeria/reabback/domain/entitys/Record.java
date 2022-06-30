package cl.gendarmeria.reabback.domain.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Internals cannot be empty or null")
    private String internals;
    @NotEmpty(message = "Visit date cannot be empty or null")
    private Date visitDate;
    private Date outDate;
    @NotEmpty(message = "Unit code cannot be empty or null")
    private Integer unitCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyer")
    @JsonIgnore
    private Lawyer lawyer;
}
