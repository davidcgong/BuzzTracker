package com.example.davidgong.donation_tracker;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.example.davidgong.donation_tracker.controllers.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DavidTests {

    private boolean registered = false;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    public void register() {
        MainActivity a = mActivityRule.getActivity();
        //register first
        onView(withId(R.id.txt_username)).perform(typeText("auniqueusername"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("cs2340iscool"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("cs2340iscool"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        registered = true;
    }
    @Test
    public void testIsAccount() {
        if (!registered) {
            register();
        }
        MainActivity a = mActivityRule.getActivity();
        //try to login with wrong credentials
    }
}