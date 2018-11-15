package com.example.davidgong.donation_tracker;

import com.example.davidgong.donation_tracker.model.Account;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AbhishekTest {
    @Test
    public void accountCreationTest() {
        try {
            Account accBad = new Account("", "", "");
        } catch (Exception e) {
            assertEquals("One of the inputs was empty.", e.getMessage());
        }

        Account accGood = new Account("asdf", "asdf", "asdf");
        assertEquals(accGood.getUsername(), "asdf");
    }
}