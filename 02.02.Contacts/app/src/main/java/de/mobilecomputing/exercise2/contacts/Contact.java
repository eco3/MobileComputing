package de.mobilecomputing.exercise2.contacts;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String telephone;
    private String email;

    public Contact(String name, String telephone, String email) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
