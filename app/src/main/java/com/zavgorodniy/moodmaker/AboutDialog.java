package com.zavgorodniy.moodmaker;

import android.app.Dialog;
import android.content.Context;

/**
 * "About" dialog item of action bar menu
 * @version 0.1beta
 * @author Nick Zavgorodnii
 */
public class AboutDialog extends Dialog {

    public AboutDialog(Context context) {
        super(context);
        //setting the title
        setTitle(R.string.about);
        //setting the layout
        setContentView(R.layout.about_dialog);
    }
}
