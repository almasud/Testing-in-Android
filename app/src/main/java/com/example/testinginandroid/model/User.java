package com.example.testinginandroid.model;

import java.util.Calendar;

/**
 * Model class containing personal information that will be saved to SharedPreferences
 *
 * @author Abdullah Almasud
 */
public class User {
    private final String name;
    private final Calendar dateOfBirth;
    private final String email;

    public User(String name, Calendar dateOfBirth, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }
}
