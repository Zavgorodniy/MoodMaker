package com.zavgorodniy.moodmaker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nick on 05.04.16.
 */
public class ContentListAdapter extends ArrayAdapter<ContentItem> {

    private List<ContentItem> mItems;
    private Context mContext;


    public ContentListAdapter(Context context, int resource, List<ContentItem> items) {
        super(context, resource, items);
        mItems = items;
        mContext = context;
    }

    @Override
    public ContentItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getPosition(ContentItem item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.content_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.tv_item_title);
        title.setText(mItems.get(position).getTitle());

        TextView name = (TextView) convertView.findViewById(R.id.tv_item_name);
        name.setText(mItems.get(position).getName());

        TextView desc = (TextView) convertView.findViewById(R.id.tv_item_description);
        desc.setText(mItems.get(position).getDescription());

        ImageView mImage = (ImageView) convertView.findViewById(R.id.iv_item_image);
        int id = mContext.getResources().getIdentifier(mItems.get(position).getImage(), "drawable", mContext.getPackageName());
        mImage.setImageDrawable(mContext.getResources().getDrawable(id));

        return convertView;
    }
}