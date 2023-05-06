package org.gfg.JBDL49L10.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "my_person")
public class Myperson {
    @Id
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    private String lastName;

    private String dob;

    private int age;

    @Transient
    private String dummy;
}
