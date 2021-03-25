package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao{

    private JdbcTemplate jdbcTemplate;

    // Prepared statement strings
    private static final String INSERT_CUSTOMER_SQL =
            "insert into customer (first_name, last_name, email, phone) values (?, ?, ?, ?)";

    private static final String SELECT_CUSTOMER_SQL =
            "select * from customer where customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS_SQL =
            "select * from customer";

    private static final String DELETE_CUSTOMER_SQL =
            "delete from customer where customer_id = ?";

    private static final String UPDATE_CUSTOMER_SQL =
            "update customer set first_name = ?, last_name = ?,  email = ?, phone = ? where customer_id = ?";


    @Autowired
    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Customer addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail(),
                customer.getPhone());





        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

       customer.setCustomer_id(id);

        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_SQL, this::mapRowToCustomer, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS_SQL, this::mapRowToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER_SQL,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomer_id());
        //id is needed here
    }


    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER_SQL, id);
    }
    private Customer mapRowToCustomer(ResultSet rs, int theNumberOfTheRow) throws SQLException {
        Customer customer = new Customer();
        //id is needed here
        customer.setCustomer_id(rs.getInt("customer_id"));
        customer.setFirst_name(rs.getString("first_name"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));

        return customer;
    }
}
