package com.example.testinginandroid.utils;

import android.content.SharedPreferences;

import com.example.testinginandroid.model.User;

import java.util.Calendar;

/**
 * Helper class to manage access to {@link SharedPreferences}
 *
 * @author Abdullah Almasud
 */
public class SharedPreferencesHelper {
    // Keys for saving values in SharedPreferences
    public static final String KEY_NAME = "key_name";
    public static final String KEY_DOB = "key_dob_millis";
    public static final String KEY_EMAIL = "key_email";

    // The injected SharedPreferences implementation to use for persistence
    private final SharedPreferences mSharedPreferences;

    /**
     * Constructor with dependency injection
     * @param mSharedPreferences The {@link SharedPreferences} that will be used in this DAO
     */
    public SharedPreferencesHelper(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    /**
     * Saves the {@link User}'s information to {@link SharedPreferences}
     * @param user The data to save
     * @return the {@link User}
     */
    public boolean savePersonalInfo(User user) {
        // Start a SharedPreferences transaction
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, user.getName());
        editor.putLong(KEY_DOB, user.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, user.getEmail());

        // Commit changes to SharedPreferences
        return editor.commit();
    }

    /**
     * Retrieves the {@link User}'s information from {@link SharedPreferences}.
     * @return The {@link User}
     */
    public User getPersonalInfo() {
        // Get data from the SharedPreferences
        String name = mSharedPreferences.getString(KEY_NAME, "");
        long dobMillis = mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTimeInMillis(dobMillis);
        String email = mSharedPreferences.getString(KEY_EMAIL, "");

        // Create and fill a SharedPreferenceEntry model object
        return new User(name, dateOfBirth, email);
    }
}
