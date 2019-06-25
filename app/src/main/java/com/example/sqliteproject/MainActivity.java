package com.example.sqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        button=findViewById(R.id.add);
        myHelper=new MyHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Contacts Select_contact= (Contacts) parent.getItemAtPosition(position);
             Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
             intent.putExtra("id",Select_contact.getMid());
             startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Contacts> contacts=myHelper.getAllContacts();
        ContactAdapter contactAdapter=new ContactAdapter(this,contacts);
        listView.setAdapter(contactAdapter);
    }
}
