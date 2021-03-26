package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Customer;
import com.example.U1M6GroupProject.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {
    @Autowired
    protected InvoiceDao invoiceDao;
    @Autowired
    protected CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Invoice> iList = invoiceDao.getAllInvoice();

        iList.stream()
                .forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoice_id()));
        List<Customer> cList = customerDao.getAllCustomers();

        cList.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteInvoice() {

        Customer customer = new Customer();
        customer.setFirst_name("Broad St");
        customer.setLast_name("Savannah");
        customer.setEmail("GA");
        customer.setPhone("31401");

        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setInvoice_id(invoice.getInvoice_id());
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,22));
        invoice.setCheckout_date(LocalDate.of(2020,12,23));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertEquals(invoice, invoice2);

        invoiceDao.deleteInvoice(invoice.getInvoice_id());

        invoice2 = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertNull(invoice2);
    }

    @Test
    public void getAllInvoices() {

        Customer customer = new Customer();
        customer.setFirst_name("Sam");
        customer.setLast_name("Jones");
        customer.setEmail("14jonessl@gmail.com");
        customer.setPhone("911");

        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoice();

        assertEquals(invoiceList.size(), 2);
    }


    @Test
    public void updateInvoice() {

        Customer customer = new Customer();
        customer.setFirst_name("Sam");
        customer.setLast_name("Jones");
        customer.setEmail("14jonessl@gmail.com");
        customer.setPhone("911");

        customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        invoice.setBooking_date(LocalDate.of(2021,1,10));
        invoice.setCheckin_date(LocalDate.of(2021,1,12));
        invoice.setCheckout_date(LocalDate.of(2021,1,15));

        invoiceDao.updateInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoice_id());

        assertEquals(invoice2, invoice);
    }

    @Test
    public void getInvoiceByCustomer() {

        Customer customer = new Customer();
        customer.setFirst_name("Sam");
        customer.setLast_name("Jones");
        customer.setEmail("14jonessl@gmail.com");
        customer.setPhone("911");

        customer =customerDao.addCustomer(customer);

        Customer customer2 = new Customer();
        customer2.setFirst_name("Sam");
        customer2.setLast_name("Jones");
        customer2.setEmail("14jonessl@gmail.com");
        customer2.setPhone("911");

        customer2 = customerDao.addCustomer(customer2);

        Customer customer3 = new Customer();
        customer3.setFirst_name("Sam");
        customer3.setLast_name("Jones");
        customer3.setEmail("14jonessl@gmail.com");
        customer3.setPhone("911");

        customer3 = customerDao.addCustomer(customer3);

        Invoice invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setCustomer_id(customer.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setCustomer_id(customer2.getCustomer_id());
        invoice.setBooking_date(LocalDate.of(2020,12,21));
        invoice.setCheckin_date(LocalDate.of(2020,12,21));
        invoice.setCheckout_date(LocalDate.of(2020,12,22));
        invoice.setLate_fee(25.99);
        invoice.setRoom_id(1);

        invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getInvoiceByCustomer_id(customer.getCustomer_id());
        assertEquals(2, iList.size());

        List<Invoice> iList2 = invoiceDao.getInvoiceByCustomer_id(customer2.getCustomer_id());
        assertEquals(1, iList2.size());

        List<Invoice> iList3 = invoiceDao.getInvoiceByCustomer_id(customer3.getCustomer_id());
        assertEquals(0, iList3.size());

    }

//    @Test
//    public void getCoffeeByType() {
//
//        Roaster roaster = new Roaster();
//        roaster.setName("PERC");
//        roaster.setStreet("Broad St");
//        roaster.setCity("Savannah");
//        roaster.setState("GA");
//        roaster.setPostal_code("31401");
//        roaster.setPhone("912-555-5555");
//        roaster.setEmail("totallyrealemail@perc.com");
//        roaster.setNote("Delicious coffee.");
//
//        roaster = RoasterDao.addRoaster(roaster);
//
//        Coffee coffee = new Coffee();
//        coffee.setRoaster_id(roaster.getRoaster_id());
//        coffee.setName("Juggernaut");
//        coffee.setCount(73);
//        coffee.setUnit_price(12.99);
//        coffee.setDescription("Bold and full flavored, this coffee will start your day right.");
//        coffee.setType("Espresso");
//
//        CoffeeDao.addCoffee(coffee);
//
//        coffee = new Coffee();
//        coffee.setRoaster_id(roaster.getRoaster_id());
//        coffee.setName("PERC UP");
//        coffee.setCount(42);
//        coffee.setUnit_price(11.49);
//        coffee.setDescription("Sweet and Balanced.");
//        coffee.setType("Espresso");
//
//        CoffeeDao.addCoffee(coffee);
//
//        coffee = new Coffee();
//        coffee.setRoaster_id(roaster.getRoaster_id());
//        coffee.setName("Captain Foggy");
//        coffee.setCount(33);
//        coffee.setUnit_price(12.49);
//        coffee.setDescription("Light and crisp.");
//        coffee.setType("Sumatra");
//
//        CoffeeDao.addCoffee(coffee);
//
//        List<Coffee> cList = CoffeeDao.getCoffeesByType("Espresso");
//        assertEquals(2, cList.size());
//
//        List<Coffee> cList2 = CoffeeDao.getCoffeesByType("Sumatra");
//        assertEquals(1, cList2.size());
//
//        List<Coffee> cList3 = CoffeeDao.getCoffeesByType("Kona");
//        assertEquals(0, cList3.size());
//
//    }

}