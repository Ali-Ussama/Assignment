package com.example.aliosama.contacts.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/3/2017.
 */

public class ContactHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Contacts.db";
    static final int VERSION = 2;
    ContactTable mContactTable;
    Context context;

    public ContactHelper(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
        this.context = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            mContactTable = new ContactTable();
            String Create_Table = "create table "+mContactTable.Table_Name+" ( "+mContactTable.IDCol+" INTEGER PRIMARY KEY autoincrement , "+
                    mContactTable.NameCol+" text , "+mContactTable.PhoneCol+" text, "+mContactTable.ImageCol+" blob );";
            db.execSQL(Create_Table);
            Toast.makeText(context, "DataBase is Created Successfully .", Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(context, " ContactHelper onCreate exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            mContactTable = new ContactTable();
            db.execSQL("drop table if exists " + mContactTable.Table_Name);
            Toast.makeText(context, "database old version : " + oldVersion + " is Upgraded Successfully to newly version :" + newVersion, Toast.LENGTH_LONG).show();
            onCreate(db);
        }catch (SQLException e){
            Toast.makeText(context, " ContactHelper onUpgrade exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void InsertContact(ContactModel mContactModel){
        SQLiteDatabase db;
        try{
            mContactTable = new ContactTable();
            db = this.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(mContactTable.NameCol,mContactModel.getName());
            content.put(mContactTable.PhoneCol,mContactModel.getPhone());
            content.put(mContactTable.ImageCol,mContactModel.getContactImage());
            db.insert(mContactTable.Table_Name,null,content);
            Toast.makeText(context, "Contact Saved.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, " ContactHelper InsertContact exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }
    public ArrayList<ContactModel> ViewContacts(){
        SQLiteDatabase db;
        try {
            mContactTable = new ContactTable();
            ArrayList<ContactModel> data = new ArrayList<>();
             db = this.getReadableDatabase();
            Cursor mCursor = db.rawQuery("select * from " + mContactTable.Table_Name, null);
            if (mCursor.moveToNext()){
                while (mCursor.moveToNext()) {
                    data.add(new ContactModel(
                            mCursor.getString(mCursor.getColumnIndex(mContactTable.NameCol)),
                            mCursor.getString(mCursor.getColumnIndex(mContactTable.PhoneCol)),
                            mCursor.getBlob(mCursor.getColumnIndex(mContactTable.ImageCol))));
                }
            }
            mCursor.close();
            return data;
        }catch (Exception e){
            Toast.makeText(context, " ContactHelper ViewContact exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }
    public void UpdateContact(ContactModel mContactModel){
        SQLiteDatabase db;
        try {
            mContactTable = new ContactTable();
            db = this.getWritableDatabase();
            ContentValues content = new ContentValues();
            content.put(mContactTable.NameCol, mContactModel.getName());
            content.put(mContactTable.PhoneCol, mContactModel.getPhone());
            content.put(mContactTable.ImageCol, mContactModel.getContactImage());
            int result = db.update(mContactTable.Table_Name, content, "name=?", new String[]{mContactModel.getName()});
            if (result > 0) {
                Toast.makeText(context, "Contact is Updated.", Toast.LENGTH_SHORT).show();
            } else {
                result = db.update(mContactTable.Table_Name, content, "phone=?", new String[]{mContactModel.getPhone()});
                if (result > 0) {
                    Toast.makeText(context, "Contact is Updated.", Toast.LENGTH_SHORT).show();
                } else {
                    InsertContact(mContactModel);
                }
            }
        }catch (Exception e){
            Toast.makeText(context, " ContactHelper Update exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void DeleteContact(String Name){
        try {
            mContactTable = new ContactTable();
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(mContactTable.Table_Name,"name=?",new String[]{Name});
            Toast.makeText(context, "Contact is Deleted.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "ContactHelper Delete exception.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}
