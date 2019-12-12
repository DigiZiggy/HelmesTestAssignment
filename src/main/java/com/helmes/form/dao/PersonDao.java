package com.helmes.form.dao;

import com.helmes.form.model.Person;
import com.helmes.form.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class PersonDao implements PersonDaoInterface {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO person(firstName, lastName, sectors, acceptTerms) " +
                "VALUES ( :firstName, :lastName, :sectors, :acceptTerms)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(person), keyHolder);
        person.setId(keyHolder.getKey().intValue());
    }

    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        List<Person> people = namedParameterJdbcTemplate.query(sql, new PersonMapper());

        return people;
    }

    @Override
    public Person findPersonById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM person WHERE id = :id";

        Person person = null;
        try {
            person = namedParameterJdbcTemplate.queryForObject(sql, params, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }
        return person;
    }

    @Override
    public void update(Person person) {
        String sql = "UPDATE person SET firstName = :firstName, lastName = :lastName, " +
                "sectors = :sectors, acceptTerms = :acceptTerms WHERE id = :id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(person));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM person WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private static final class PersonMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setFirstName(rs.getString("firstName"));
            person.setLastName(rs.getString("lastName"));
            person.setSectors(convertStringToList(rs.getString("sectors")));
            person.setAcceptTerms(rs.getBoolean("acceptTerms"));
            return person;
        }
    }

    private static final class SectorMapper implements RowMapper<Sector> {
        public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sector sector = new Sector();
            sector.setId(rs.getInt("id"));
            sector.setName(rs.getString("name"));
            return sector;
        }
    }

    private SqlParameterSource getSqlParameterByModel(Person person) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", person.getId());
        paramSource.addValue("firstName", person.getFirstName());
        paramSource.addValue("lastName", person.getLastName());
        paramSource.addValue("sectors", convertListToString(person.getSectors()));
        paramSource.addValue("acceptTerms", person.isAcceptTerms());
        return paramSource;
    }

    private static List<String> convertStringToList(String string) {
        List<String> result = new ArrayList<String>();

        if (!StringUtils.isEmpty(string)) {
            result = Arrays.asList(StringUtils.delimitedListToStringArray(string, ","));
        }
        return result;
    }

    private String convertListToString(List<String> list) {
        String result = "";
        if (list != null) {
            result = StringUtils.arrayToCommaDelimitedString(list.toArray());
        }
        return result;
    }
}
