package com.example.sqliteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter  extends ArrayAdapter<Contacts> {
    public ContactAdapter(Context context,  List<Contacts> contacts) {
        super(context, 0, contacts);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_row,parent,false);
        TextView text1=convertView.findViewById(R.id.text1);
        TextView text2=convertView.findViewById(R.id.text2);

        Contacts contacts=getItem(position);
        text1.setText(contacts.getMname());
        text2.setText(contacts.getMphone());
        return convertView;
    }
}
