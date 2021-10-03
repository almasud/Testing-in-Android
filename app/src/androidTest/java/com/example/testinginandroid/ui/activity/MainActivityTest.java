package com.example.testinginandroid.ui.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.testinginandroid.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * UI tests (Instrumented) which will execute on an Android device or emulator
 *
 * @author Abdullah Almasud
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    // Context of the app under test.
    private final Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();


    @Test
    public void test_01_user_can_enter_name() {
        onView(withId(R.id.usernameInput)).perform(clearText());
        onView(withId(R.id.usernameInput)).perform(typeText("Almasud"));
    }

    @Test
    public void test_02_user_can_enter_email() {
        onView(withId(R.id.emailInput)).perform(clearText());
        onView(withId(R.id.emailInput)).perform(typeText("almasud.arm@gmail.com"));
    }

    @Test
    public void test_03_user_can_enter_datOfBirth() {
        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
    }

    @Test
    public void test_04_user_can_check_switch() {
        onView(withId(R.id.switchShare)).perform(click());
    }

    @Test
    public void test_04_fill_all_data_and_save_success() {
        // For name
        onView(withId(R.id.usernameInput)).perform(clearText());
        onView(withId(R.id.usernameInput)).perform(typeText("Almasud"));
        // Close the soft keyboard
        onView(withId(R.id.usernameInput)).perform(closeSoftKeyboard());

        // For email address
        onView(withId(R.id.emailInput)).perform(clearText());
        onView(withId(R.id.emailInput)).perform(typeText("almasud.arm@gmail.com"));
        // Close the soft keyboard
        onView(withId(R.id.emailInput)).perform(closeSoftKeyboard());

        // For date of birth
        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
        // Close the soft keyboard
        onView(withId(R.id.dateOfBirthInput)).perform(closeSoftKeyboard());

        // For submit button
//        onView(withId(R.id.saveButton))
//                .perform(scrollTo())
//                .perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        // Check the submit message is displayed
        onView(withId(R.id.submitMessage)).check(matches(isDisplayed()));
        // Check the displayed submit message is success
        onView(withId(R.id.submitMessage)).check(matches(withText(appContext.getResources().getString(R.string.settings_save_success_message))));
    }

    @Test
    public void test_05_fill_all_data_and_save_failed() {
        // For name
        onView(withId(R.id.usernameInput)).perform(clearText());
        onView(withId(R.id.usernameInput)).perform(typeText("Developer"));
        // Close the soft keyboard
        onView(withId(R.id.usernameInput)).perform(closeSoftKeyboard());

        // For email address
        onView(withId(R.id.emailInput)).perform(clearText());
        onView(withId(R.id.emailInput)).perform(typeText("dev.almasud@gmail.com"));
        // Close the soft keyboard
        onView(withId(R.id.emailInput)).perform(closeSoftKeyboard());

        // For date of birth
        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
//        // Close the soft keyboard
        onView(withId(R.id.dateOfBirthInput)).perform(closeSoftKeyboard());

        // For submit button
//        onView(withId(R.id.saveButton))
//                .perform(scrollTo())
//                .perform(click());
        onView(withId(R.id.saveButton)).perform(click());

        // Check the submit message is displayed
        onView(withId(R.id.submitMessage)).check(matches(isDisplayed()));
        // Check the displayed submit message is error
        onView(withId(R.id.submitMessage)).check(matches(withText(appContext.getResources().getString(R.string.settings_save_error_message))));
    }
}
