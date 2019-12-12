package com.helmes.form.service;

import com.helmes.form.dao.PersonDaoInterface;
import com.helmes.form.dao.SectorDaoInterface;
import com.helmes.form.model.Person;
import com.helmes.form.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PersonService implements PersonServiceInterface {

    PersonDaoInterface personDao;

    @Autowired
    public void setPersonDao(PersonDaoInterface personDao) {
        this.personDao = personDao;
    }

    SectorDaoInterface sectorDao;

    @Autowired
    public void setSectorDao(SectorDaoInterface sectorDao) {
        this.sectorDao = sectorDao;
    }

    @Override
    public Person findPersonById(Integer id) {
        Person person = personDao.findPersonById(id);
        findPersonSectorsValues(Collections.singletonList(person));
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> people = personDao.findAll();
        findPersonSectorsValues(people);
        return people;
    }

    @Override
    public void saveOrUpdate(Person person) {
        if (person.getId() == null) {
            personDao.save(person);
        } else {
            personDao.update(person);
        }
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

    private void findPersonSectorsValues(List<Person> people) {
        for (Person person : people) {
            List<Sector> sectors = sectorDao.findAllPersonSectors(person.getId());

            ArrayList<String> sectorStringList = new ArrayList<String>();
            for (Sector sector : sectors) {
                sectorStringList.add(sector.getName().replaceAll("&nbsp;", ""));
            }
            person.setSectors(sectorStringList);
        }
    }
}
