package com.helmes.form.dao;


import com.helmes.form.model.Person;

import java.util.List;

public interface PersonDaoInterface {

    void save(Person person);

    void savePersonSectors(Integer id, List<String> sectors);

    List<Person> findAll();

    Person findPersonById(Integer id);

    void update(Person person);

    void updatePersonSectors(Integer id, List<String> sectors);

    void delete(Integer id);
}
