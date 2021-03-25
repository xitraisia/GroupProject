package com.example.U1M6GroupProject.model;

import java.util.Objects;

public class Airbnb {
    private int room_id;
    private String name;
    private String description;
    private double daily_rate;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(double daily_rate) {
        this.daily_rate = daily_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airbnb airbnb = (Airbnb) o;
        return room_id == airbnb.room_id && Double.compare(airbnb.daily_rate, daily_rate) == 0 && Objects.equals(name, airbnb.name) && Objects.equals(description, airbnb.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_id, name, description, daily_rate);
    }

    @Override
    public String toString() {
        return "Airbnb{" +
                "room_id=" + room_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", daily_rate=" + daily_rate +
                '}';
    }
}
