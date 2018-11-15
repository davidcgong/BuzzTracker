package com.example.davidgong.donation_tracker;

import com.example.davidgong.donation_tracker.model.Item;
import com.example.davidgong.donation_tracker.model.Location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DavidTest {
    @Test
    public void itemCreationTest() {
        try {
            Item itemBad = new Item(8, 1, 8, 8, 5, new Location(), "", "", "", Item.ItemType.NONE);
        } catch (Exception e) {
            assertEquals("Short Description must be entered", e.getMessage());
        }

        Item itemGood = new Item(8, 1, 8, 8, 5, new Location(), "asdf", "", "", Item.ItemType.NONE);
        assertEquals(itemGood.getShortDesc(), "asdf");
    }
}