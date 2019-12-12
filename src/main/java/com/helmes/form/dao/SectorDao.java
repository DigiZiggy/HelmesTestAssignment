package com.helmes.form.dao;

import com.helmes.form.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.NestedServletException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SectorDao implements SectorDaoInterface {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(Sector sector) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO sector(name) VALUES (:name)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(sector), keyHolder);
        sector.setId(keyHolder.getKey().intValue());
    }

    public List<Sector> findAll() {
        String sql = "SELECT * FROM sector";
        List<Sector> sectors = namedParameterJdbcTemplate.query(sql, new SectorMapper());

        return sectors;
    }

    public List<Sector> findAllPersonSectors(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT s.id, s.name FROM sector s, person_sectors ps, person p " +
                "WHERE s.id = ps.sector_id AND p.id = ps.person_id AND ps.person_id = :id";

        List<Sector> sectors = null;
        try {
            sectors = namedParameterJdbcTemplate.query(sql, params, new PersonSectorMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }
        return sectors;
    }

    @Override
    public Sector findSectorById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM sector WHERE id = :id";

        Sector sector = null;
        try {
            sector = namedParameterJdbcTemplate.queryForObject(sql, params, new SectorMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }
        return sector;
    }

    @Override
    public void update(Sector sector) {
        String sql = "UPDATE sector SET name = :name WHERE id = :id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(sector));
    }

    private SqlParameterSource getSqlParameterByModel(Sector sector) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", sector.getId());
        paramSource.addValue("name", sector.getName());
        return paramSource;
    }

    private static final class SectorMapper implements RowMapper<Sector> {
        public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sector sector = new Sector();
            sector.setId(rs.getInt("id"));
            sector.setName(rs.getString("name"));
            return sector;
        }
    }

    private static final class PersonSectorMapper implements RowMapper<Sector> {
        public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sector sector = new Sector();
            sector.setId(rs.getInt("id"));
            sector.setName(rs.getString("name"));
            return sector;
        }
    }
}
