package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME="myDatabase";
    private  static final int DB_VERSION=1;
    private  static final String TABLE_NAME="myTable";
    private  static final String KEY_ID="id";
    private  static final String KEY_PHONE="phone";
    private  static final String KEY_NAME="name";


    public MyHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
             String CREATE_TABLE="create table "+TABLE_NAME+" ( "+KEY_ID+ " int primary key  , "+KEY_NAME+" varchar(30),"+KEY_PHONE+" varchar(15) );";
             db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     String DELETE_TABLE="drop table "+TABLE_NAME+"IF EXISTS ;";
     db.execSQL(DELETE_TABLE);
     onCreate(db);

    }
    //Add Data To Database
    public void addContact (Contacts contacts){
     SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,contacts.getMname());
        values.put(KEY_PHONE,contacts.getMphone());
        db.insert(TABLE_NAME,null,values);
    }
    public ArrayList<Contacts> getAllContacts(){
       ArrayList<Contacts> contacts=new ArrayList<>();
       String SELECT_QUERY="select * from "+TABLE_NAME+" ;";
       SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                int id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contacts contact=new Contacts(id,name,phone);
                 contacts.add(contact);
            }while (cursor.moveToNext());

        }
        return contacts;
    }
    public  Contacts getContactId(int id){
     SQLiteDatabase db=this.getReadableDatabase();
     String SELECT_QUERY="select * from "+TABLE_NAME+" where id="+id;
     Cursor cursor=db.rawQuery(SELECT_QUERY,null);
     Contacts contacts=null;
     if(cursor.moveToFirst()){

         int contact_id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
         String name=cursor.getString(cursor.getColumnIndex(KEY_NAME));
         String phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE));
         contacts=new Contacts(contact_id,name,phone);
     }
     return contacts;


    }
    public void updateContacts(Contacts contacts){
      SQLiteDatabase db=this.getWritableDatabase();
      ContentValues values=new ContentValues();
      values.put(KEY_NAME,contacts.getMname());
      values.put(KEY_PHONE,contacts.getMphone());
      db.update(TABLE_NAME,values,"id=?",new String[] {String.valueOf(contacts.getMid())});

    }
    public void deleteContact(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
}
