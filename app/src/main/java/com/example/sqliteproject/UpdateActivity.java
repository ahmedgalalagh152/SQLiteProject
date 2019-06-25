package com.example.sqliteproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText text1;
    EditText text2;
    Button button;
    MyHelper myHelper;
    int id;
    Contacts contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        text1=findViewById(R.id.editname);
        text2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);
        id=getIntent().getIntExtra("id",0);
        myHelper=new MyHelper(this);
        contacts=myHelper.getContactId(id);
        text1.setText(contacts.getMname());
        text2.setText(contacts.getMphone());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=text1.getText().toString();
                String phone=text2.getText().toString();
                Contacts new_contact=new Contacts(id,name,phone);
                myHelper.updateContacts(new_contact);
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("cconfirmation")
                .setMessage("are you sure")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myHelper.deleteContact(id);
                        finish();
                    }
                }
                ).setNegativeButton("no ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


}
