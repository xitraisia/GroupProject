package com.example.U1M6GroupProject.dao;


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

    @Test
    public void addGetDeleteCustomer() {
        //ARRANGE
        Customer customer = new Customer();
        customer.setFirst_name("Daniele");
        customer.setLast_name("Tenga");
        customer.setEmail("danieletenga@gmail.com");
        customer.setPhone("123456789");

        customer = customerDao.addCustomer(customer);

        //ACT
        //add
        Customer customer2 = customerDao.getCustomer(customer.getCustomer_id());
        //ASSERT
        assertEquals(customer, customer2);

        //ACT
        //get
        customerDao.deleteCustomer(customer.getCustomer_id());

        customer2 = customerDao.getCustomer(customer.getCustomer_id());
        //ASSERT
        assertNull(customer2);
    }

    @Test
    public void getAllCustomers() {

        Customer customer = new Customer();
        //id needed to get all customers
        customer.setCustomer_id(1);
        customer.setFirst_name("Daniele");
        customer.setLast_name("Tenga");
        customer.setEmail("danieletenga@gmail.com");
        customer.setPhone("123456789");

        customerDao.addCustomer(customer);

        customer = new Customer();
        //id needed here as well
        customer.setCustomer_id(1);
        customer.setFirst_name("Daniele");
        customer.setLast_name("Tenga");
        customer.setEmail("danieletenga@gmail.com");
        customer.setPhone("123456789");

        customerDao.addCustomer(customer);

        List<Customer> customerList = customerDao.getAllCustomers();

        assertEquals(customerList.size(), 2);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirst_name("Daniele");
        customer.setLast_name("Tenga");
        customer.setEmail("danieletenga@gmail.com");
        customer.setPhone("123456789");

        customer = customerDao.addCustomer(customer);

        customer.setFirst_name("Kendall");
        customer.setLast_name("Arceo");
        customer.setEmail("kendallarceo@gmail.com");
        customer.setPhone("123456788");

        customerDao.updateCustomer(customer);

        Customer customer2 = customerDao.getCustomer(customer.getCustomer_id());
        assertEquals(customer,customer2);

    }



}