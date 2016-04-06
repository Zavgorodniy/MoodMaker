package com.zavgorodniy.moodmaker;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * initial activity
 * @version 0.1beta
 * @author Nick Zavgorodnii
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * function that creates a menu in 1 bar
     * in version 0.1 contains only "about" item
     * @param menu of the 1 bar
     * @return boolean result of creating menu in 1 bar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * function, that controls clicks on items  in 1 bar
     * @param item that clicked on
     * @return boolean result of function work
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //getting the item id
        int itemId = item.getItemId();
        //on item "about" click
        if (itemId == R.id.about) {
            //creating dialog
            Dialog dialog = new AboutDialog(this);
            dialog.show();
        }
        return true;
    }

    /**
     * starting the CriteriaActivity with application logic
     */
    public void startCriteria(View view) {
        Intent criteria = new Intent(MainActivity.this, CriteriaActivity.class);
        startActivity(criteria);
    }
}
