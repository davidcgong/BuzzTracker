package com.example.davidgong.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Home");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_app:
                //add in more log out functionality at some point here and maybe make modular
                Toast.makeText(this, "Logging you out...", Toast.LENGTH_SHORT).show();
                Intent returnToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(returnToMain);
        }
        return super.onOptionsItemSelected(item);
    }
}
