package org.gfg.JBDL49L10.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gfg.JBDL49L10.model.Myperson;
import org.gfg.JBDL49L10.model.Person;

import javax.validation.constraints.NotBlank;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestData {
    @NotBlank(message = "firstName should not be empty")
    String firstName;
    String lastName;
    @NotBlank(message = "dob should not be empty")
    String dob; //mandatory
    // id -> created its own id

    public Person to(){
        return Person.builder().firstName(firstName).lastName((lastName)).dob(dob).build();
    }

    public Myperson toMyPerson(){
        return Myperson.builder().id(new Random().nextInt(100)).firstName(firstName).lastName((lastName)).dob(dob).build();
    }

}
