package com.example.davidgong.donation_tracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.davidgong.donation_tracker.controllers.MainActivity;
import com.example.davidgong.donation_tracker.controllers.RegistrationActivity;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
//import android.test.ActivityInstrumentationTestCase2;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegistrationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void testEmptyPassword() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("normalUsername"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError() == null);
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError().toString().equals(a.getString(R.string.error_field_required)));
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError().toString().equals(a.getString(R.string.error_password_mismatch)));
        Espresso.pressBack();
    }

    @Test
    public void testEmptyUsername() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError().equals(a.getString(R.string.error_field_required)));
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError() == null);
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError() == null);
        Espresso.pressBack();
    }

    @Test
    public void testEmptyConfirm() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("normalUsername"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError() == null);
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError() == null);
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError().equals(a.getString(R.string.error_field_required)));
        Espresso.pressBack();
    }

    @Test
    public void testShortPassword() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("normalUsername"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("short"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("short"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError() == null);
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError().equals(a.getString(R.string.error_invalid_password)));
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError() == null);
        Espresso.pressBack();
    }

    @Test
    public void testShortUsername() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("short"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError().equals(a.getString(R.string.error_invalid_username)));
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError() == null);
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError() == null);
        Espresso.pressBack();
    }

    @Test
    public void testConfirmNotEqual() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("normalUsername"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("normalPassword"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("notEqual"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError() == null);
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError() == null);
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError().equals(a.getString(R.string.error_password_mismatch)));
        Espresso.pressBack();
    }

    @Test
    public void testContainsUsername() {
        MainActivity a = mActivityRule.getActivity();
        onView(withId(R.id.registration_button)).perform(click());
        onView(withId(R.id.txt_username)).perform(typeText("abhishek"), closeSoftKeyboard());
        onView(withId(R.id.txt_password)).perform(typeText("abhishek"), closeSoftKeyboard());
        onView(withId(R.id.txt_confirmPassword)).perform(typeText("abhishek"), closeSoftKeyboard());
        onView(withId(R.id.radioUser)).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
        TextView tu = a.findViewById(R.id.txt_username);
        assert(tu.getError().equals(a.getString(R.string.error_username_taken)));
        TextView tp = a.findViewById(R.id.txt_password);
        assert(tp.getError() == null);
        TextView tc = a.findViewById(R.id.txt_confirmPassword);
        assert(tc.getError() == null);
        Espresso.pressBack();
    }
}
