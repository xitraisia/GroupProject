package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTemplateImplTest {

    @Autowired
    protected CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db

        List<Customer> cList = customerDao.getAllCustomers();

        cList.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));
    }
    @After
    public void tearDown() throws Exception {
    }

//    Customer customer = new Customer();
//        customer.setName("House");
//        customer.setDescription("blah blah blah");
//        customer.setDaily_rate(200.0);
}