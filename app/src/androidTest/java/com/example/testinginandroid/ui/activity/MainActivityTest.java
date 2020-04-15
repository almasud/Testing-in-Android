package com.example.testinginandroid.ui.activity;

import android.content.Context;

//import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.testinginandroid.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * UI tests (Instrumented) which will execute on an Android device or emulator
 *
 * @author Abdullah Almasud
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    // Context of the app under test.
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void user_can_enter_name() {
        onView(withId(R.id.usernameInput)).perform(clearText());
        onView(withId(R.id.usernameInput)).perform(typeText("Almasud"));
    }

    // PickerActions need the dependency'androidx.test.espresso:espresso-contrib:3.2.0' and build
    // failed for dependency / constraint error
//    @Test
//    public void user_can_enter_datOfBirth() {
//        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
//    }

    @Test
    public void user_can_enter_email() {
        onView(withId(R.id.emailInput)).perform(clearText());
        onView(withId(R.id.emailInput)).perform(typeText("almasud.arm@gmail.com"));
    }

    // PickerActions need the dependency'androidx.test.espresso:espresso-contrib:3.2.0' and build
    // failed for dependency / constraint error
//    @Test
//    public void fill_all_data_and_save_success() {
//        // For name
//        onView(withId(R.id.usernameInput)).perform(clearText());
//        onView(withId(R.id.usernameInput)).perform(typeText("Munira"));
//        // For date of birth
//        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
//        // For email address
//        onView(withId(R.id.emailInput)).perform(clearText());
//        onView(withId(R.id.emailInput)).perform(typeText("munira.arm@gmail.com"));
//        // For submit button
//        onView(withId(R.id.saveButton)).perform(click());
//        // Check the submit message is displayed
////        onView(withId(R.id.submitMessage)).check(matches(isDisplayed()));
//        // Check the displayed submit message is success
//        onView(withId(R.id.submitMessage)).check(matches(withText(appContext.getResources().getString(R.string.settings_save_success_message))));
//    }

//    @Test
//    public void fill_all_data_and_save_failed() {
//        // For name
//        onView(withId(R.id.usernameInput)).perform(clearText());
//        onView(withId(R.id.usernameInput)).perform(typeText("Munira"));
//        // For date of birth
//        onView(withId(R.id.dateOfBirthInput)).perform(PickerActions.setDate(1993, 3, 24));
//        // For email address
//        onView(withId(R.id.emailInput)).perform(clearText());
//        onView(withId(R.id.emailInput)).perform(typeText("munira.arm@gmail.com"));
//        // For submit button
//        onView(withId(R.id.saveButton)).perform(click());
//        // Check the submit message is displayed
////        onView(withId(R.id.submitMessage)).check(matches(isDisplayed()));
//        // Check the displayed submit message is error
//        onView(withId(R.id.submitMessage)).check(matches(withText(appContext.getResources().getString(R.string.settings_save_error_message))));
//    }
}