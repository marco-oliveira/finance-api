package com.finance.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class PersonAddress {

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String cep;

    private String city;

    private String state;

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
