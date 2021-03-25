package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AirbnbDaoJdbcTemplateImplTest {

    @Autowired
    protected AirbnbDao airbnbDao;



    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Airbnb> aList = airbnbDao.getAllAirbnbs();

        aList.stream()
                .forEach(airbnb -> airbnbDao.deleteAirbnb(airbnb.getRoom_id()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteAirbnb() {

        Airbnb airbnb = new Airbnb();
        airbnb.setName("House");
        airbnb.setDescription("blah blah blah");
        airbnb.setDaily_rate(200.0);

        airbnb = airbnbDao.addAirbnb(airbnb);


        Airbnb airbnb2 = airbnbDao.getAirbnb(airbnb.getRoom_id());

        assertEquals(airbnb, airbnb2);

        airbnbDao.deleteAirbnb(airbnb.getRoom_id());

        airbnb2= airbnbDao.getAirbnb(airbnb.getRoom_id());

        assertNull(airbnb2);
    }

    @Test
    public void getAllAirbnbs() {

        Airbnb airbnb = new Airbnb();
        airbnb.setRoom_id(1);
        airbnb.setName("House");
        airbnb.setDescription("blah blah blah");
        airbnb.setDaily_rate(200.0);

        airbnbDao.addAirbnb(airbnb);

        airbnb = new Airbnb();
        airbnb.setRoom_id(1);
        airbnb.setName("House");
        airbnb.setDescription("blah blah blah");
        airbnb.setDaily_rate(200.0);

        airbnbDao.addAirbnb(airbnb);

        List<Airbnb> airbnbList = airbnbDao.getAllAirbnbs();

        assertEquals(airbnbList.size(), 2);
    }


    @Test
    public void updateAirbnb() {

        Airbnb airbnb = new Airbnb();
        airbnb.setName("House");
        airbnb.setDescription("blah blah blah");
        airbnb.setDaily_rate(200.0);

        airbnb = airbnbDao.addAirbnb(airbnb);


        airbnb.setName("Hbooboo");
        airbnb.setDescription("!!blah blah blah");
        airbnb.setDaily_rate(300.0);

        airbnbDao.updateAirbnb(airbnb);

        Airbnb airbnb2 = airbnbDao.getAirbnb(airbnb.getRoom_id());

        assertEquals(airbnb, airbnb2);
    }

}