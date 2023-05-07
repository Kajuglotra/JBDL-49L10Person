package org.gfg.JBDL49L10.repository;

import org.gfg.JBDL49L10.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    public default Author saveAuthor(Author author){
        save(author);
        return author;
    }
}
