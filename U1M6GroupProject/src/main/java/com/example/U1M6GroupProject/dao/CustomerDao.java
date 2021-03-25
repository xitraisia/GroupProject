package com.example.U1M6GroupProject.dao;

import com.example.U1M6GroupProject.model.Airbnb;
import com.example.U1M6GroupProject.model.Customer;

import java.util.List;

public interface CustomerDao {
    //create
    //delete
    //update
    //getAll
    //getbyId

    Customer addCustomer(Customer customer);

    Customer getCustomer(int id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
