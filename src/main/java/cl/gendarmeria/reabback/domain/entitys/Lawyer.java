package cl.gendarmeria.reabback.domain.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Christopher Langenegger
 * @version 1.0
 * @since 28-06-2022
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lawyer {
    @Id
    private String run;
    @NotEmpty(message = "Name cannot be empty or null")
    private String name;
    @NotEmpty(message = "Surname cannot be empty or null")
    private String surname;
    private Date upDate;
    private Date expirationDate;
    @NotEmpty(message = "Type cannot be empty or null")
    private String type;
    private boolean active;
    private byte[] document;
    @NotEmpty(message = "UpUser cannot be empty or null")
    private String upUser;
    @OneToMany (mappedBy = "lawyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> records = new ArrayList<>();
}
