package com.example.demo.repositories;

import com.example.demo.entity.Persons;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonsMapper implements RowMapper<Persons> {

    public Persons mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persons person = new Persons();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        return person;
    }
}
