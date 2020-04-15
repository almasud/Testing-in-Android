package com.example.testinginandroid.utils;

import android.content.SharedPreferences;

import com.example.testinginandroid.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit tests (Local) for the {@link SharedPreferencesHelper} that mocks {@link SharedPreferences}
 *
 * @author Abdullah Almasud
 */
@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesHelperTest {
    private static final String TEST_NAME = "test_name";
    private static final String TEST_EMAIL = "test@email.com";
    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();
    private User mUser;
    private SharedPreferencesHelper mMockSharedPreferencesHelper;
    private SharedPreferencesHelper mMockBrokenSharedPreferencesHelper;
    @Mock
    SharedPreferences mMockSharedPreferences;
    @Mock
    SharedPreferences mMockBrokenSharedPreferences;
    @Mock
    SharedPreferences.Editor mMockEditor;
    @Mock
    SharedPreferences.Editor mMockBrokenEditor;

    static {
        TEST_DATE_OF_BIRTH.set(1993, 3, 24);
    }

    @Before
    public void initMocks() {
        // Person to persist
        mUser = new User(TEST_NAME, TEST_DATE_OF_BIRTH, TEST_EMAIL);
        // Mocked SharedPreferences
        mMockSharedPreferencesHelper = createMockSharedPreference();
        // Mocked SharedPreferences that fails when writing
        mMockBrokenSharedPreferencesHelper = createMockBrokenSharedPreference();
    }

    @Test
    public void sharedPreferencesHelper_SaveAndReadUserInformation() {
        // Save the User information to SharedPreferences
        boolean success = mMockSharedPreferencesHelper.savePersonalInfo(mUser);
        assertThat("Checking that SharedPreferencesHelper.savePersonalInfo() returns true", success, is(true));
        // Read User information from SharedPreferences
        User user = mMockSharedPreferencesHelper.getPersonalInfo();
        // Make sure both written and retrieved User information are equal
        assertThat("Checking that User.name has been persisted and read correctly",
                mUser.getName(), is(user.getName()));
        assertThat("Checking that User.dateOfBirth has been persisted and read correctly",
                mUser.getDateOfBirth(), is(user.getDateOfBirth()));
        assertThat("Checking that User.email has been persisted and read correctly",
                mUser.getEmail(), is(user.getEmail()));
    }

    @Test
    public void sharedPreferencesHelper_SaveUserInformationFailed() {
        // Read User information from broken SharedPreferencesHelper
        boolean success = mMockBrokenSharedPreferencesHelper.savePersonalInfo(mUser);
        assertThat("Make sure writing to a broken SharedPreferencesHelper returns false",
                success, is(false));
    }

    /**
     * Create a Mocked SharedPreferences
     */
    private SharedPreferencesHelper createMockSharedPreference() {
        // Mocking reading the SharedPreferences as if mMockedSharedPreferences
        // was previously written correctly
        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME),
                anyString())).thenReturn(mUser.getName());
        when(mMockSharedPreferences.getLong(eq(SharedPreferencesHelper.KEY_DOB),
                anyLong())).thenReturn(mUser.getDateOfBirth().getTimeInMillis());
        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_EMAIL),
                anyString())).thenReturn(mUser.getEmail());
        // Mocking a successful commit
        when(mMockEditor.commit()).thenReturn(true);
        // Return the MockEditor when requesting it
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);

        return new SharedPreferencesHelper(mMockSharedPreferences);
    }

    /**
     * Create a mocked SharedPreferences that fails when writing
     */
    private SharedPreferencesHelper createMockBrokenSharedPreference() {
        // Mocking a commit that fails
        when(mMockBrokenEditor.commit()).thenReturn(false);
        // Return the broken MockEditor when requesting it
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);

        return new SharedPreferencesHelper(mMockBrokenSharedPreferences);
    }
}