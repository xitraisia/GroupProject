package com.example.U1M6GroupProject.model;

import java.util.Objects;

public class InvoiceItem {

    private int invoice_room_id;
    private int invoice_id;
    private int room_id;
    private int room_quantity;
    private double unit_rate;
    private double discount;

    public int getInvoice_room_id() {
        return invoice_room_id;
    }

    public void setInvoice_room_id(int invoice_room_id) {
        this.invoice_room_id = invoice_room_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_quantity() {
        return room_quantity;
    }

    public void setRoom_quantity(int room_quantity) {
        this.room_quantity = room_quantity;
    }

    public double getUnit_rate() {
        return unit_rate;
    }

    public void setUnit_rate(double unit_rate) {
        this.unit_rate = unit_rate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return invoice_room_id == that.invoice_room_id && invoice_id == that.invoice_id && room_id == that.room_id && room_quantity == that.room_quantity && Double.compare(that.unit_rate, unit_rate) == 0 && Double.compare(that.discount, discount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_room_id, invoice_id, room_id, room_quantity, unit_rate, discount);
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoice_room_id=" + invoice_room_id +
                ", invoice_id=" + invoice_id +
                ", room_id=" + room_id +
                ", room_quantity=" + room_quantity +
                ", unit_rate=" + unit_rate +
                ", discount=" + discount +
                '}';
    }
}
