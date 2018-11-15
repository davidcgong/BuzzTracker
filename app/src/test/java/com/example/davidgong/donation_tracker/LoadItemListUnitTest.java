package com.example.davidgong.donation_tracker;

import android.widget.ArrayAdapter;

import com.example.davidgong.donation_tracker.controllers.LocationDetailActivity;
import com.example.davidgong.donation_tracker.model.Item;
import com.example.davidgong.donation_tracker.model.Location;

import org.junit.Test;
import java.lang.reflect.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoadItemListUnitTest {
    @Test
    public void search_withItems() {
        LocationDetailActivity lda = new LocationDetailActivity();

        Class ldaClass = lda.getClass();

        Location testLocation = new Location("Name", "Type", 0.0, 0.0, "Address", "City", "State", "Zip", "Phone Number");
        Item item1 = new Item(1, 1, 2000, 12, 0, testLocation, "short", "Long", "Value", Item.ItemType.ELECTRONICS);
        Item item2 = new Item(31, 12, 9999, 1, 59, testLocation, "ShtDescription", "Long Description", "$100", Item.ItemType.ELECTRONICS);
        Item item3 = new Item(17, 9, 2018, 6, 31, testLocation, "shortshortshort", "longlonglong", "$100,000", Item.ItemType.OTHER);

        testLocation.addItem(item1);
        testLocation.addItem(item2);
        testLocation.addItem(item3);



        try {
            Field locField = ldaClass.getDeclaredField("thisLocation");
            locField.setAccessible(true);
            locField.set(lda, testLocation);

            Method loadItemList = ldaClass.getDeclaredMethod("loadItemList", String.class, Item.ItemType.class);
            loadItemList.setAccessible(true);

            //Search and Item Type
            ArrayList<Item> itemList = (ArrayList<Item>) loadItemList.invoke(lda, "short", Item.ItemType.ELECTRONICS);
            assertEquals(1, itemList.size());
            assertEquals(item1, itemList.get(0));

            //Search
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "short", Item.ItemType.NONE);
            assertEquals(2, itemList.size());
            assertEquals(item1, itemList.get(0));
            assertEquals(item3, itemList.get(1));

            //Item Type
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "", Item.ItemType.ELECTRONICS);
            assertEquals(2, itemList.size());
            assertEquals(item1, itemList.get(0));
            assertEquals(item2, itemList.get(1));

            //Nothing
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "", Item.ItemType.NONE);
            assertEquals(item1, itemList.get(0));
            assertEquals(item2, itemList.get(1));
            assertEquals(item3, itemList.get(2));
            assertEquals(3, itemList.size());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void search_noItems() {
        LocationDetailActivity lda = new LocationDetailActivity();

        Class ldaClass = lda.getClass();

        Location testLocation = new Location("Name", "Type", 0.0, 0.0, "Address", "City", "State", "Zip", "Phone Number");

        try {
            Field locField = ldaClass.getDeclaredField("thisLocation");
            locField.setAccessible(true);
            locField.set(lda, testLocation);

            Method loadItemList = ldaClass.getDeclaredMethod("loadItemList", String.class, Item.ItemType.class);
            loadItemList.setAccessible(true);

            //Search and Item Type
            ArrayList<Item> itemList = (ArrayList<Item>) loadItemList.invoke(lda, "short", Item.ItemType.ELECTRONICS);
            assertEquals(0, itemList.size());

            //Search
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "short", Item.ItemType.NONE);
            assertEquals(0, itemList.size());

            //Item Type
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "", Item.ItemType.ELECTRONICS);
            assertEquals(0, itemList.size());

            //Nothing
            itemList = (ArrayList<Item>) loadItemList.invoke(lda, "", Item.ItemType.NONE);
            assertEquals(0, itemList.size());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}