package com.zavgorodniy.moodmaker;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * activity with tho logic of app
 * creates criteria for the content by the user selections
 * starts the content activity
 * @version 0.1beta
 * @author Nick Zavgorodnii
 */
public class CriteriaActivity extends AppCompatActivity {

    //0 equals "male" state, 1 equals "female" state
    int genderCriteria = 0;
    //index of item in ages string array
    int ageCriteria = 0;
    //index of item in moods string array
    int moodCriteria = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

//        //retreiving data from bundle for enabling landscape mode
//        if (savedInstanceState != null) {
//            genderCriteria = savedInstanceState.getInt("gender");
//            ageCriteria = savedInstanceState.getInt("age");
//            moodCriteria = savedInstanceState.getInt("mood");
//        }

        //initialization of spinners
        agesSpinnerInit();
        moodsSpinnerInit();
    }

//    /**
//     * putting data to a bundle for enabling landscape mode
//     * @param outState which contains criteria states
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("gender", genderCriteria);
//        outState.putInt("age", ageCriteria);
//        outState.putInt("mood", moodCriteria);
//    }

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
        int itemId = item.getItemId();
        //add this checkind for future widening of menu
        if (itemId == R.id.about) {
            //creating dialog
            Dialog dialog = new AboutDialog(this);
            dialog.show();
        }
        return true;
    }

    /**
     * initialization of ages spinner
     * creates a list of ages from the string array
     * setting spinner adapter and click listener
     */
    private void agesSpinnerInit() {
        Spinner ageSpinner = (Spinner) findViewById(R.id.sp_age);
//        //setting the item selected in spinner
//        ageSpinner.setSelection(ageCriteria);
        //list of ages from string array
        String[] ageList = getResources().getStringArray(R.array.st_ages);
        //adapter for the age spinner
        ArrayAdapter<String> ageListAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageList);
        ageListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting an adapter for spinner
        ageSpinner.setAdapter(ageListAdapter);
        //set the item click listener
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //setting age criteria value
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageCriteria = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * initialization of moods spinner
     * creates a list of moods from the string array
     * setting spinner adapter and click listener
     */
    private void moodsSpinnerInit() {
        Spinner moodSpinner = (Spinner) findViewById(R.id.sp_mood);
//        //setting the item selected in spinner
//        moodSpinner.setSelection(moodCriteria);
        //list of moods from string array
        String[] moodList = getResources().getStringArray(R.array.st_moods);
        ArrayAdapter<String> moodListAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moodList);
        moodListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moodSpinner.setAdapter(moodListAdapter);
        moodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //setting mood criteria value
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moodCriteria = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * changing gender criteria by clicking on RadioButtons
     * @param view clicked on
     */
    public void genderChange(View view) {
        int viewId = view.getId();
        switch(viewId) {
            //click on "male" button case
            case R.id.rb_male:
                genderCriteria = 0;
                break;
            //click on "female" button case
            case R.id.rb_female:
                genderCriteria = 1;
                break;
        }
    }

    /**
     * starting activity with the content
     */
    public void onConfirmCriteria(View view) {
        Intent content = new Intent(CriteriaActivity.this, ContentActivity.class);
        content.putExtra("gender", genderCriteria);
        content.putExtra("age", ageCriteria);
        content.putExtra("mood", moodCriteria);
        startActivity(content);
    }
}