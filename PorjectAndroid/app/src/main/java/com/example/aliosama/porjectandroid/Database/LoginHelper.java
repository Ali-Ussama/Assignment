package com.example.aliosama.porjectandroid.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.aliosama.porjectandroid.Database.Models.StudentModel;
import com.example.aliosama.porjectandroid.Database.Models.TeacherModel;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/21/2017.
 */

public class LoginHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "college.db";
    private static int version_code = 1;
    private static final String id = "id";
    private static final String name = "name";
    private static final String email = "email";
    private static final String phone = "phone";
    private static final String password = "password";
    private static final String StudentTable = "student";
    private static final String TeacherTable = "teacher";


    Context context;
    StudentModel studentModel;
    TeacherModel teacherModel;
    public LoginHelper(Context context) {
        super(context, DataBaseName, null , version_code);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table "+StudentTable+" ( "+id+" integer primary key autoincrement , "+
                    name+" text , "+email+" text , "+password+" text); ");
            db.execSQL("create table "+TeacherTable+" ( "+id+" integer primary key autoincrement , "+
                    name+" text , "+phone+" text , " +email+" text , " +password+" text); ");
//            Toast.makeText(context, "DataBase is Created Successfully .", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(context, " DB Creation exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + StudentTable);
            db.execSQL("drop table if exists " + TeacherTable);
//            Toast.makeText(context, "database old version : " + oldVersion + " is Upgraded Successfully to newly version :" + newVersion, Toast.LENGTH_LONG).show();
            onCreate(db); // to recreate the table again
        } catch (SQLException e) {
            Toast.makeText(context, " DB Upgrade exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + StudentTable);
            db.execSQL("drop table if exists " + TeacherTable);
//            Toast.makeText(context, "database is downgraged - old version : " + oldVersion + " && newly version :" + newVersion, Toast.LENGTH_LONG).show();
            onCreate(db); // to recreate the table again
        } catch (SQLException e) {
            Toast.makeText(context, " DB Downgraged exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }

    public int CheckUserLogin(String email,String password){
       try {
           SQLiteDatabase db = getReadableDatabase();
           String Query = "select "+this.id +" , "+ this.password + " from " + StudentTable + " where " + this.email + " =?";
           Cursor cursor = db.rawQuery(Query, new String[]{email});
           while (cursor.moveToNext()) {
               if (password.matches(cursor.getString(cursor.getColumnIndex(this.password)))) {
                   return cursor.getInt(cursor.getColumnIndex(this.id));
               }
           }
           Query = "select "+this.id +" , "+this.password + " from " + TeacherTable + " where " + this.email + " =?";
           cursor = db.rawQuery(Query, new String[]{email});
           while (cursor.moveToNext()) {
               if (password.matches(cursor.getString(cursor.getColumnIndex(this.password)))) {
                   return cursor.getInt(cursor.getColumnIndex(this.id));
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return -1;
    }
    public ArrayList<StudentModel> getStudents(){
        ArrayList<StudentModel> Students = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String Sql = "select * from "+StudentTable;
        Cursor cursor = db.rawQuery(Sql,null);
        while (cursor.moveToNext()){
            Students.add(new StudentModel(cursor.getInt(cursor.getColumnIndex(id)),cursor.getString(cursor.getColumnIndex(name)),cursor.getString(cursor.getColumnIndex(this.email)),
                    cursor.getString(cursor.getColumnIndex(this.password))));
        }
        return Students;
    }
    public StudentModel getStudent(int studentID){
        SQLiteDatabase db = getReadableDatabase();
        String Sql = "select * from "+StudentTable+" where id = "+studentID;
        Cursor cursor = db.rawQuery(Sql,null);
        while (cursor.moveToNext()){
            studentModel = new StudentModel(cursor.getInt(cursor.getColumnIndex(id)),cursor.getString(cursor.getColumnIndex(name)),cursor.getString(cursor.getColumnIndex(this.email)),
                    cursor.getString(cursor.getColumnIndex(this.password)));
        }
        return studentModel;
    }
    public int AddStudent(StudentModel mStudentModel){
        int StudentID = -1;
        try {
            studentModel = mStudentModel;
            SQLiteDatabase db = getReadableDatabase();
            String Query = "select id from " + StudentTable + " where name = ?";
            Cursor cursor = db.rawQuery(Query, new String[]{studentModel.getName()});
            if (cursor.moveToNext()) {
                return StudentID;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put(name, studentModel.getName());
                contentValues.put(email, studentModel.getEmail());
                contentValues.put(password, studentModel.getPassword());
                db = getWritableDatabase();
                db.insert(StudentTable, null, contentValues);
                 Query = "select id from " + StudentTable + " where name = ?";
                 cursor = db.rawQuery(Query, new String[]{studentModel.getName()});
                while(cursor.moveToNext()) {
                    StudentID = cursor.getInt(cursor.getColumnIndex(this.id));
                }
                return StudentID;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return StudentID;
    }
    public int AddTeacher(TeacherModel mTeacherModel){
        int TeacherID = -1;
        try {
            teacherModel = mTeacherModel;
            SQLiteDatabase db = getReadableDatabase();
            String Query = "select id from " + TeacherTable + " where name = ?";
            Cursor cursor = db.rawQuery(Query, new String[]{teacherModel.getName()});
            if (cursor.moveToNext()) {
                return TeacherID;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put(name, teacherModel.getName());
                contentValues.put(phone, teacherModel.getPhone());
                contentValues.put(email, teacherModel.getEmail());
                contentValues.put(password, teacherModel.getPassword());
                db = getWritableDatabase();
                db.insert(TeacherTable, null, contentValues);
                db = getWritableDatabase();
                Query = "select id from " + TeacherTable + " where name = ?";
                cursor = db.rawQuery(Query, new String[]{teacherModel.getName()});

                while (cursor.moveToNext())
                            TeacherID = cursor.getInt(cursor.getColumnIndex(this.id));
                return TeacherID;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TeacherID;
    }
    public TeacherModel getTeacher(int teacherID){
        SQLiteDatabase db = getReadableDatabase();
        String Sql = "select * from "+TeacherTable+" where id = "+teacherID;
        Cursor cursor = db.rawQuery(Sql,null);
        while (cursor.moveToNext()){
            teacherModel = new TeacherModel(cursor.getString(cursor.getColumnIndex(this.name)),
                    cursor.getString(cursor.getColumnIndex(this.phone)),cursor.getString(cursor.getColumnIndex(this.email)),
                    cursor.getString(cursor.getColumnIndex(this.password)));
        }
        return teacherModel;
    }

    public void UpdateTeacher(TeacherModel mTeacherModel){
        try {
            teacherModel = mTeacherModel;
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.name,teacherModel.getName());
            contentValues.put(this.phone,teacherModel.getPhone());
            contentValues.put(this.email,teacherModel.getEmail());
            contentValues.put(this.password,teacherModel.getPassword());
            db.update(TeacherTable,contentValues,"id=?",new String[]{Integer.toString(teacherModel.getId())});
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
