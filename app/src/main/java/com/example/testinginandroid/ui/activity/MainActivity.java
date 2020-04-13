package com.example.testinginandroid.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testinginandroid.ui.utils.EmailValidator;
import com.example.testinginandroid.R;
import com.example.testinginandroid.utils.SharedPreferencesHelper;
import com.example.testinginandroid.databinding.ActivityMainBinding;
import com.example.testinginandroid.model.User;

import java.util.Calendar;

/**
 * An {@link AppCompatActivity} that represents an input from page where the user can
 * provide his name, date of birth and email address. The personal information can be
 * saved to {@link SharedPreferences} by clicking a button.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private EmailValidator mEmailValidator;
    private ActivityMainBinding mVewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mVewBinding.getRoot());

        // Set the actionbar sub title
        getSupportActionBar().setSubtitle(getResources().getString(R.string.main_activity_subtitle));

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        mVewBinding.emailInput.addTextChangedListener(mEmailValidator);

        // Instantiate a SharedPreferenceHelper
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesHelper = new SharedPreferencesHelper(sharedPreferences);

        // Fill the all input fields of personal information saved in the SharedPreferences.
        initFields();
    }

    /**
     * Initialize all fields of User information saved in the SharedPreferences.
     */
    private void initFields() {
        User user = mSharedPreferencesHelper.getPersonalInfo();
        mVewBinding.userNameInput.setText(user.getName());
        Calendar dateOfBirth = user.getDateOfBirth();
        mVewBinding.dateOfBirthInput.init(dateOfBirth.get(Calendar.YEAR),
                dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DAY_OF_MONTH),
                null);
        mVewBinding.emailInput.setText(user.getEmail());
    }

    /**
     * Called when the save button is clicked
     * @param view The button view
     */
    public void onSaveClick(View view) {
        if (!mEmailValidator.isValid()) {
            mVewBinding.emailInput.setError("Invalid email");
            Log.e(TAG, "Settings is not saved: Invalid email");
            return;
        }

        // Get the text from the input fields
        String name = mVewBinding.userNameInput.getText().toString();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(mVewBinding.dateOfBirthInput.getYear(),
                mVewBinding.dateOfBirthInput.getMonth(),
                mVewBinding.dateOfBirthInput.getDayOfMonth());
        String email = mVewBinding.emailInput.getText().toString();

        // Set the data into Person model and finally saved to persist
        User user = new User(name, dateOfBirth, email);
        boolean isSuccess = mSharedPreferencesHelper.savePersonalInfo(user);

        if (isSuccess) {
            Toast.makeText(this, "Settings is saved to your device", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Failed to save the settings", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Called when revert button is clicked
     * @param view The button view
     */
    public void onRevertClick(View view) {
        initFields();
        Toast.makeText(this, "Settings information is retrieved", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Settings information is retrieved");
    }
}
