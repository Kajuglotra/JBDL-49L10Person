package org.gfg.demo.Requests;

import lombok.*;
import org.gfg.demo.Model.Person;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequest {
    @NotBlank(message = "firstName is Blank")
    String firstName;
    String lastName;

    @NotBlank(message = "dob is Blank")
    String dob;

    public Person to(){
        return Person.builder().name(firstName+" " + lastName).dob(dob).build();
    }
}
