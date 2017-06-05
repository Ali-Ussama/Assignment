package com.example.aliosama.porjectandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/23/2017.
 */

public class AssignmentHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "college.db";
    private static int Version_Number = 1;
    private static final String Assignment_Table = "assignment";
    private static final String id = "id";
    private static final String name = "name";
    private static final String content = "content";
    private static final String description = "description";
    private static final String typeofwork = "typeofwork";
    private static final String start_time = "start_time";
    private static final String end_time = "end_time";
    private static final String course_id = "course_id";

    AssignmentModel mAssignmentModel;

    public AssignmentHelper(Context context) {
        super(context, DataBaseName, null, Version_Number);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table "+Assignment_Table+" ( "+id+ " integer primary key autoincrement , " +
                                       name+" text, "+content+" blob, "+start_time+" text, "+end_time+" text, "+
                                       typeofwork+" text, "+description+" text, "+
                                       course_id+" integer ); ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + Assignment_Table);
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + Assignment_Table);
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

    public void AddAssignment(AssignmentModel mAssignmentModel){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(name,mAssignmentModel.getName());
            contentValues.put(content,mAssignmentModel.getContent());
            contentValues.put(start_time,mAssignmentModel.getStartTime());
            contentValues.put(end_time,mAssignmentModel.getEndTime());
            contentValues.put(typeofwork,mAssignmentModel.getTypeOfWork());
            contentValues.put(description,mAssignmentModel.getDescription());
            contentValues.put(course_id,mAssignmentModel.getCourse_ID());

            db.insert(Assignment_Table,null,contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void DeleteAssignment(int Assignment_ID){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(Assignment_Table, "id=?", new String[]{Integer.toString(Assignment_ID)});
        }catch (Exception e){
         e.printStackTrace();
        }
    }
    public void DeleteAllAssignments(){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(Assignment_Table, "1",null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UpdateAssignment(AssignmentModel mAssignmentModel){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(name,mAssignmentModel.getName());
            contentValues.put(content,mAssignmentModel.getContent());
            contentValues.put(start_time,mAssignmentModel.getStartTime());
            contentValues.put(end_time,mAssignmentModel.getEndTime());
            contentValues.put(typeofwork,mAssignmentModel.getTypeOfWork());
            contentValues.put(description,mAssignmentModel.getDescription());
            contentValues.put(course_id,mAssignmentModel.getCourse_ID());
            db.update(Assignment_Table,contentValues,"id=?",new String[]{Integer.toString(mAssignmentModel.getId())});
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public AssignmentModel getAssignment(int AssignmentID){
        try {
            SQLiteDatabase db = getReadableDatabase();
            String Query = "select * from " + Assignment_Table + " where "+id+" = " + AssignmentID;
            Cursor cursor = db.rawQuery(Query, null);
            while (cursor.moveToNext()){
                mAssignmentModel = new AssignmentModel(
                        cursor.getInt(cursor.getColumnIndex(id)),
                        cursor.getString(cursor.getColumnIndex(name)),
                        cursor.getString(cursor.getColumnIndex(description)),
                        cursor.getBlob(cursor.getColumnIndex(content)),
                        cursor.getString(cursor.getColumnIndex(typeofwork)),
                        cursor.getString(cursor.getColumnIndex(start_time)),
                        cursor.getString(cursor.getColumnIndex(end_time)),
                        cursor.getInt(cursor.getColumnIndex(content)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return mAssignmentModel;
    }
    public ArrayList<AssignmentModel> getAssignments(int CourseID){

        ArrayList<AssignmentModel> Assignments = new ArrayList<>();
       try {
           SQLiteDatabase db = getReadableDatabase();
           String Query = "select * from " + Assignment_Table+" where "+course_id+" = "+CourseID ;
           Cursor cursor = db.rawQuery(Query, null);
           while (cursor.moveToNext()){
               Assignments.add(new AssignmentModel(
                       cursor.getInt(cursor.getColumnIndex(id)),
                       cursor.getString(cursor.getColumnIndex(name)),
                       cursor.getString(cursor.getColumnIndex(description)),
                       cursor.getBlob(cursor.getColumnIndex(content)),
                       cursor.getString(cursor.getColumnIndex(typeofwork)),
                       cursor.getString(cursor.getColumnIndex(start_time)),
                       cursor.getString(cursor.getColumnIndex(end_time)),
                       cursor.getInt(cursor.getColumnIndex(course_id))));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return Assignments;
    }
}
