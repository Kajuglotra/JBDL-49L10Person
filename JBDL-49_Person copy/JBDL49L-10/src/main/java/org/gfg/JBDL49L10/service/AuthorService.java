package org.gfg.JBDL49L10.service;

import org.gfg.JBDL49L10.CustomException;
import org.gfg.JBDL49L10.model.Author;
import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Transactional(rollbackOn = {CustomException.class})
    public Author saveAuthor(Author author) throws SQLException, CustomException {
        authorRepository.save(author);
        Author author1 = getAuthorFromId(author.getId());
        if(author1.getAuthorName().equalsIgnoreCase("author")){
            throw new CustomException("SOmething went wrong !!");
        }
        return author;
    }
    public Author getAuthorFromId(Integer id) throws SQLException {
        Author author = authorRepository.findById(id).get();
        return author;
    }
}
