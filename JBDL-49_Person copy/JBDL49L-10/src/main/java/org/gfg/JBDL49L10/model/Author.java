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
@Table(name = "my_author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    @SequenceGenerator(name="author_sequence" ,allocationSize = 2)
    private Integer id;
    @Column(length = 30)
    private String authorName;

}
