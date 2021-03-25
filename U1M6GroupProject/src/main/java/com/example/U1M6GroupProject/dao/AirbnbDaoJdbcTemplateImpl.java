package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Repository
public class AirbnbDaoJdbcTemplateImpl implements AirbnbDao{

    private JdbcTemplate jdbcTemplate;

    // Prepared statement strings
    private static final String INSERT_AIRBNB_SQL =
            "insert into airbnb (name, description, daily_rate) values (?, ?, ?)";

    private static final String SELECT_AIRBNB_SQL =
            "select * from airbnb where room_id = ?";

    private static final String SELECT_ALL_AIRBNB_SQL =
            "select * from airbnb";

    private static final String DELETE_AIRBNB_SQL =
            "delete from airbnb where room_id = ?";

    private static final String UPDATE_AIRBNB_SQL =
            "update airbnb set name = ?, description = ?, daily_rate = ? where room_id = ?";


    @Autowired
    public AirbnbDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Airbnb addAirbnb(Airbnb airbnb) {
        jdbcTemplate.update(INSERT_AIRBNB_SQL,
                airbnb.getName(),
                airbnb.getDescription(),
                airbnb.getDaily_rate());



        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        airbnb.setRoom_id(id);

        return airbnb;
    }

    @Override
    public Airbnb getAirbnb(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_AIRBNB_SQL, this::mapRowToAirbnb, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Airbnb> getAllAirbnbs() {
        return jdbcTemplate.query(SELECT_ALL_AIRBNB_SQL, this::mapRowToAirbnb);
    }

    @Override
    public void updateAirbnb(Airbnb airbnb) {
        jdbcTemplate.update(UPDATE_AIRBNB_SQL,
                airbnb.getName(),
                airbnb.getDescription(),
                airbnb.getDaily_rate(),
                airbnb.getRoom_id());

    }

    @Override
    public void deleteAirbnb(int id) {
        jdbcTemplate.update(DELETE_AIRBNB_SQL, id);
    }
    private Airbnb mapRowToAirbnb(ResultSet rs, int theNumberOfTheRow) throws SQLException {
        Airbnb airbnb = new Airbnb();
        airbnb.setDaily_rate(rs.getDouble("daily_rate"));
        airbnb.setName(rs.getString("name"));
        airbnb.setRoom_id(rs.getInt("room_id"));
        airbnb.setDescription(rs.getString("description"));

        return airbnb;
    }
}
