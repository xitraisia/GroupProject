package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Customer;
import com.example.U1M6GroupProject.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    //create
    //delete
    //update
    //getAll
    //getbyId

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(int id);

    List<InvoiceItem> getAllInvoiceItems();

    void updateInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(int id);
}
