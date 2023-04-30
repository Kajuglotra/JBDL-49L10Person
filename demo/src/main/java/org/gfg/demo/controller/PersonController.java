package org.gfg.demo.controller;

import org.gfg.demo.Model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PersonController {

    Map<Integer, Person> personMap = new HashMap<>();

    @GetMapping("/getPersonData")
    public ModelAndView passPersonParams(@RequestParam("id") int personId){
        ModelAndView modelAndView = new ModelAndView();
        Person person = personMap.get(personId);
        modelAndView.addObject("person" ,person.getName());
        modelAndView.setViewName("personPage");
        return modelAndView;
    }

    @PostMapping("/addPersonData")
    public ResponseEntity addPersonData(@RequestBody Person person ){
        Person p = Person.builder().dob(person.getDob()).name(person.getName()).id(person.getId()).age(person.getAge()).build();
        personMap.put(person.getId(), p);
        ResponseEntity response = new ResponseEntity(p,HttpStatus.OK);
        return response;
    }

    @PutMapping("/updatePerson")
    public ResponseEntity updatePersonData(@RequestBody Person person){
        Person p = Person.builder().dob(person.getDob()).name(person.getName()).id(person.getId()).age(person.getAge()).build();
        personMap.put(person.getId(), p);
        ResponseEntity response = new ResponseEntity(p,HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") int id){
        personMap.remove(personMap.get(id));
        ResponseEntity response = new ResponseEntity(id,HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAllData")
    public ResponseEntity getAllPerson(){
        List<String> p = personMap.values().stream().map(person -> person.getName()).collect(Collectors.toList());
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
}
