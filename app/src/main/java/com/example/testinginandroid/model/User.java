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
    private final boolean isDataSharable;

    public User(String name, Calendar dateOfBirth, String email, boolean isDataSharable) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isDataSharable = isDataSharable;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isDataSharable() {
        return isDataSharable;
    }
}
