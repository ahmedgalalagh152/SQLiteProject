package com.example.sqliteproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
   EditText text1;
   EditText text2;
   Button button;
   MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        text1=findViewById(R.id.editname);
        text2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);
        myHelper=new MyHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=text1.getText().toString();
                String phone =text2.getText().toString();
                Contacts contacts=new Contacts(name,phone);
                myHelper.addContact(contacts);
                Toast.makeText(AddActivity.this,"data added",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
