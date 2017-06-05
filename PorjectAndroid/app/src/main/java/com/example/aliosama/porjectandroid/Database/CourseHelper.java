package com.example.aliosama.porjectandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aliosama.porjectandroid.Database.Models.CourseModel;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/22/2017.
 */

public class CourseHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "college.db";
    private static int version_code = 1;
    private static final String CourseTable = "course";
    private static final String id = "id";
    private static final String name = "name";
    private static final String point = "point";
    private static final String semester = "semester";
    private static final String teacher_id = "teacher_id";

    static CourseModel mCourseModel;
    public CourseHelper(Context context) {
        super(context, DataBaseName, null, version_code);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
          db.execSQL("create table "+CourseTable+" ( "+ id + " integer primary key autoincrement , "
                            + name + " text , "+point+" text , "
                            + semester + " text , "+ teacher_id + " integer ); ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + CourseTable);
            onCreate(db);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + CourseTable);

        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

    public CourseModel getCourse(int course_id){
       try {
           SQLiteDatabase db = getReadableDatabase();
           String Query = "select * from " + CourseTable + " where "+this.id+" = " + course_id;
           Cursor cursor = db.rawQuery(Query, null);
           while (cursor.moveToNext()) {
               mCourseModel = new CourseModel(course_id, cursor.getString(cursor.getColumnIndex(this.name)),
                       cursor.getString(cursor.getColumnIndex(this.point)),
                       cursor.getString(cursor.getColumnIndex(this.semester)),
                       cursor.getInt(cursor.getColumnIndex(this.teacher_id)));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return mCourseModel;
    }

     public ArrayList<CourseModel> getCourses(int Teacher_id){
        ArrayList<CourseModel> mCourseModels = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            String Query = "select * from " + CourseTable + " where "+teacher_id+" = " + Teacher_id;
            Cursor cursor = db.rawQuery(Query, null);
            while (cursor.moveToNext()) {
                mCourseModel = new CourseModel(cursor.getInt(cursor.getColumnIndex(id)), cursor.getString(cursor.getColumnIndex(name)),
                        cursor.getString(cursor.getColumnIndex(point)),
                        cursor.getString(cursor.getColumnIndex(semester)),
                        cursor.getInt(cursor.getColumnIndex(teacher_id)));
                mCourseModels.add(mCourseModel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mCourseModels;
        }

     public void AddCourse(CourseModel courseModel){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(name,courseModel.getName());
            contentValues.put(point,courseModel.getPoint());
            contentValues.put(semester,courseModel.getSemester());
            contentValues.put(teacher_id,courseModel.getTeacher_ID());
            db.insert(CourseTable,null,contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UpdateCourse(CourseModel courseModel){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(name,courseModel.getName());
            contentValues.put(point,courseModel.getPoint());
            contentValues.put(semester,courseModel.getSemester());
            contentValues.put(teacher_id,courseModel.getTeacher_ID());
            db.update(CourseTable,contentValues,"id = ?",new String[]{Integer.toString(courseModel.getID())});
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteCourse(int CourseID) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(CourseTable,"id=?",new String[]{Integer.toString(CourseID)});
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
