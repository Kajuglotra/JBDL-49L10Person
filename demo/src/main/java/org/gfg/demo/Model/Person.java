package org.gfg.demo.Model;

import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Person {
    Integer id;  // inserting id gets inserted by itself
    String name;
    String dob;
    Integer age;

}
