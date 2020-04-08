package com.example.demo.controllers;

import com.example.demo.entity.PhoneNumbers;
import com.example.demo.repositories.PhoneNumbersRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pn")
public class PhoneNumberController {

    @Autowired
    private PhoneNumbersRepository phoneNumbersRepository;

    @RequestMapping(value = "/create", method = RequestMethod.PUT, consumes = "text/plain")
    public int createPhoneNumber(@RequestBody String param) {
        Long value = null;
        Integer id = null;
        try {
            JSONObject json = new JSONObject(param);
            value = json.getLong("value");
            id = json.getInt("idperson");
        } catch (JSONException e) {
            e.getLocalizedMessage();
            return 0;
        }
        return phoneNumbersRepository.createPhoneNumber(value, id);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "text/plain")
    public int updatePhoneNumber(@RequestBody String param) {
        PhoneNumbers pn = new PhoneNumbers();
        try {
            JSONObject json = new JSONObject(param);
            pn.setId(json.getInt("id"));
            pn.setValue(json.getLong("value"));
            pn.setIdPerson(json.getInt("idperson"));
        } catch (JSONException e) {
            e.getLocalizedMessage();
            return 0;
        }
        return phoneNumbersRepository.updatePhoneNumber(pn);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public int deletePhoneNumber(@PathVariable Integer id) {
        return phoneNumbersRepository.deletePhoneNumbers(id);
    }

    @RequestMapping(value = "/getpb", method = RequestMethod.GET)
    public String getItemPhoneBook() {
        return phoneNumbersRepository.getPhoneBook().toString();
    }
}