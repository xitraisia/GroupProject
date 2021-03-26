package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    //Create and delete Invoices,
    // including the associated Invoice Items
    //update
    //getAll
    //getbyId

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoice();

    void updateInvoice(Invoice invoice);

//    void deleteInvoice();

    List<Invoice> getInvoiceByCustomer_id(int customer_id);

    void deleteInvoice(int invoice_id);
}
