package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Customer;
import com.example.U1M6GroupProject.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    // Prepared statement strings
    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, booking_date, checkin_date, checkout_date, late_fee, room_id) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, booking_date = ?, checkin_date = ?, checkout_date = ?,late_fee = ?, room_id = ? where invoice_id = ?";

    private static final String SELECT_INVOICES_BY_CUSTOMER_ID_SQL =
            "select * from invoice where customer_id = ?";

//    private static final String SELECT_COFFEES_BY_TYPE_SQL =
//            "select * from coffee where type = ?";

    private final JdbcTemplate jdbcTemplate;
    /* Foreign Keys: invoice */
//    alter table invoice add constraint fk_invoice_customer foreign key (customer_id) references customer(customer_id);
    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getBooking_date(),
                invoice.getCheckin_date(),
                invoice.getCheckout_date(),
                invoice.getLate_fee(),
                invoice.getRoom_id());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        invoice.setInvoice_id(id);

        return invoice;
    }

    @Override
    public Invoice getInvoice(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getCustomer_id(),
                invoice.getBooking_date(),
                invoice.getCheckin_date(),
                invoice.getCheckout_date(),
                invoice.getLate_fee(),
                invoice.getRoom_id(),
                invoice.getInvoice_id());

    }


    @Override
    public List<Invoice> getInvoiceByCustomer_id(int customer_id) {
        return jdbcTemplate.query(SELECT_INVOICES_BY_CUSTOMER_ID_SQL, this::mapRowToInvoice, customer_id);
    }

    @Override
    public void deleteInvoice(int invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoice_id);

    }
    // Helper methods
    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(rs.getInt("invoice_id"));
        invoice.setCustomer_id(rs.getInt("customer_id"));
        invoice.setBooking_date(rs.getDate("booking_date").toLocalDate());
        invoice.setCheckin_date(rs.getDate("checkin_date").toLocalDate());
        invoice.setCheckout_date(rs.getDate("checkout_date").toLocalDate());
        invoice.setLate_fee(rs.getDouble("late_fee"));
        invoice.setRoom_id(rs.getInt("room_id"));

        return invoice;
    }
}
