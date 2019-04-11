package com.aset.probook.asetcalculator;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Probook on 20.04.2018.
 */

public class PartnersAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PartnersMenuItem> mData;
    int mSelectedItem;

    public PartnersAdapter(Context c, ArrayList<PartnersMenuItem> d, int selectedItem) {
        this.mContext = c;
        mData = d;
        mSelectedItem = selectedItem;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateData(ArrayList<PartnersMenuItem> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void itemSelected(int selectedItem) {
        mSelectedItem = selectedItem;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_partners, null);

        } else {
//            convertView = convertView;
        }

        PartnersMenuItem item = mData.get(position);


        TextView txtMenuName = convertView.findViewById(R.id.txtMenuName);
        txtMenuName.setText(item.getMenuName());

        if (position == mSelectedItem) {
            txtMenuName.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
//            rltLeft.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.input_round_gray_image));
            txtMenuName.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        }

        convertView.setTag(item);
        return convertView;
    }
}