package org.gfg.JBDL49L10.controller;

import org.gfg.JBDL49L10.CustomException;
import org.gfg.JBDL49L10.model.Author;
import org.gfg.JBDL49L10.model.Person;
import org.gfg.JBDL49L10.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @PostMapping("/addAuthorData")
    public Author createPerson(@RequestBody Author a) throws SQLException, CustomException {
        return authorService.saveAuthor(a);
    }

}
