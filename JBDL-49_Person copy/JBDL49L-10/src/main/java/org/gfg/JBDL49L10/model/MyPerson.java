package org.gfg.JBDL49L10.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "my_person")
public class MyPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "fName" , length = 30)
    private String firstName;
    private String lastName;
    private String dob;
    private Integer age;

    @Transient
    private String dummy;
    // static variable

}
