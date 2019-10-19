package com.sopra.challenge.receptor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String company;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull
    private LocalDate sendDate;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @NotNull
    @Min(0)
    private float kg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getKg() {
        return kg;
    }

    public void setKg(float kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return "Order{" +
                "company='" + company + '\'' +
                ", sendDate=" + sendDate +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", kg=" + kg +
                '}';
    }

    public static class OrderBuilder {

        private String company;
        private LocalDate sendDate;
        private String city;
        private String address;
        private float kg;

        public OrderBuilder setCompany(String company) {
            this.company = company;
            return this;
        }

        public OrderBuilder setSendDate(LocalDate sendDate) {
            this.sendDate = sendDate;
            return this;
        }

        public OrderBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public OrderBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public OrderBuilder setKg(float kg) {
            this.kg = kg;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setCompany(company);
            order.setCity(city);
            order.setAddress(address);
            order.setKg(kg);
            order.setSendDate(sendDate);
            return order;
        }

    }

}
