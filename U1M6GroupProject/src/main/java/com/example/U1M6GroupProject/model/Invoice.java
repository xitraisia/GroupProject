package com.example.U1M6GroupProject.model;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    private int invoice_id;
    private int customer_id;
    private LocalDate booking_date;
    private LocalDate checkin_date;
    private LocalDate checkout_date;
    private double late_fee;
    private int room_id;

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date = booking_date;
    }

    public LocalDate getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(LocalDate checkin_date) {
        this.checkin_date = checkin_date;
    }

    public LocalDate getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(LocalDate checkout_date) {
        this.checkout_date = checkout_date;
    }

    public double getLate_fee() {
        return late_fee;
    }

    public void setLate_fee(double late_fee) {
        this.late_fee = late_fee;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoice_id == invoice.invoice_id && customer_id == invoice.customer_id && Double.compare(invoice.late_fee, late_fee) == 0 && room_id == invoice.room_id && Objects.equals(booking_date, invoice.booking_date) && Objects.equals(checkin_date, invoice.checkin_date) && Objects.equals(checkout_date, invoice.checkout_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, customer_id, booking_date, checkin_date, checkout_date, late_fee, room_id);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", customer_id=" + customer_id +
                ", booking_date=" + booking_date +
                ", checkin_date=" + checkin_date +
                ", checkout_date=" + checkout_date +
                ", late_fee=" + late_fee +
                ", room_id=" + room_id +
                '}';
    }
}
