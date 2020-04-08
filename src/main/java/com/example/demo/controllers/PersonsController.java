package com.example.demo.controllers;

import com.example.demo.entity.Persons;
import com.example.demo.repositories.PersonsRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonsController {

    @Autowired
    private PersonsRepository person;

    @RequestMapping(value="/create", method= RequestMethod.PUT, consumes="text/plain")
    public int createPerson(@RequestBody String param){
        String name = null;
        try{
            JSONObject json = new JSONObject(param);
            name = json.getString("name");
        }catch(JSONException e){
            e.getLocalizedMessage();
            return 0;
        }
        return person.createPerson(name);
    }

    @RequestMapping(value="update",method=RequestMethod.POST,consumes="text/plain")
    public int updatePerson(@RequestBody String param){
        Persons p = new Persons();
        try{
            JSONObject json = new JSONObject(param);
            p.setId(json.getInt("id"));
            p.setName(json.getString("name"));
        }catch(JSONException e){
            e.getLocalizedMessage();
            return 0;
        }
        return person.updatePerson(p);
    }

    @RequestMapping(value="{id}", method=RequestMethod.DELETE)
    public int deletePerson(@PathVariable Integer id){
        return person.deletePerson(id);
    }

    @RequestMapping(value = "/getperson", method=RequestMethod.GET)
    public Persons getPerson(@RequestParam("id") Integer id){
        return person.getPerson(id);
    }

    @RequestMapping(value = "/getpersons", method=RequestMethod.GET)
    public List<Persons> getPersons(){
        return person.getPersons();
    }
}
