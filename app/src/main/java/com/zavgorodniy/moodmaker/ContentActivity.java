package com.zavgorodniy.moodmaker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;


public class ContentActivity extends AppCompatActivity {

    //content items
    List<ContentItem> items;
    //0 equals "male" state, 1 equals "female" state
    int gender = 0;
    //index of item in ages string array
    int age = 0;
    //index of item in moods string array
    int mood = 0;

    //strings for parsing xml file
    final String HAPPY_STATE = "happy";
    final String SAD_STATE = "sad";
    final String APPEASEMENT_STATE = "appeasement";
    final String DRIVE_STATE = "drive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        gender = getIntent().getIntExtra("gender", 0);
        age = getIntent().getIntExtra("age", 0);
        mood = getIntent().getIntExtra("mood", 0);
        initItems();

        ListView contentItems = (ListView) findViewById(R.id.lv_items);
        ArrayAdapter<ContentItem> contentAdapter =
                new ContentListAdapter(this, R.layout.content_item, items);
        contentItems.setAdapter(contentAdapter);
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
        int itemId = item.getItemId();
        if (itemId == R.id.about) {
            //creating dialog
            Dialog dialog = new AboutDialog(this);
            dialog.show();
        }
        return true;
    }

    /**
     * parsing resouces by the criteria from xml file
     */
    private void initItems() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.content);
        items = new ArrayList<>();
        //age and gender filter
        int id = 0;
        switch (age) {
            case 0:
                id = R.xml.gr_10_16;
                break;
            case 1:
                if (gender == 0)
                    id = R.xml.gr_17_25_male;
                else
                    id = R.xml.gr_17_25_female;
                break;
            case 2:
                if (gender == 0)
                    id = R.xml.gr_26_40_male;
                else
                    id = R.xml.gr_26_40_female;
                break;
            case 3:
                if (gender == 0)
                    id = R.xml.gr_41_male;
                else
                    id = R.xml.gr_41_female;
                break;
        }
        //mood to string
        String moodString = "";

        switch (mood) {
            case 0:
                moodString = HAPPY_STATE;
                layout.setBackgroundResource(R.drawable.happy);
                break;
            case 1:
                moodString = SAD_STATE;
                layout.setBackgroundResource(R.drawable.sad);
                break;
            case 2:
                moodString = DRIVE_STATE;
                layout.setBackgroundResource(R.drawable.drive);
                break;
            case 3:
                moodString = APPEASEMENT_STATE;
                layout.setBackgroundResource(R.drawable.appeasement);
                break;
        }
        try {
            XmlPullParser parser = getResources().getXml(id);
            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(moodString)) {
                    items.add(new ContentItem(parser.getAttributeValue(0),
                            parser.getAttributeValue(1),
                            parser.getAttributeValue(2),
                            parser.getAttributeValue(3)));
                }
                parser.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}