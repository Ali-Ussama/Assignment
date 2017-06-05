package com.example.aliosama.porjectandroid.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aliosama.porjectandroid.Database.Models.SolutionModel;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/23/2017.
 */

public class SolutionHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "college.db";
    private static int version_code = 1;
    private static final String Solution_Table = "assignment";
    private static final String id = "id";
    private static final String content = "content";
    private static final String assignment_id = "assignment_id";
    private static final String student_id = "student_id";

    public SolutionHelper(Context context) {
        super(context, DataBaseName, null, version_code);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table "+Solution_Table+" ( "+id+" integer primary key autoincrement , "+
                                                            content+" blob, "+assignment_id+" integer, "+
                                                            student_id+" integer ); ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + Solution_Table);
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL("drop table if exists " + Solution_Table);
        }catch (Exception e){
            e.printStackTrace();
        }
        onCreate(db);
    }

    public SolutionModel getSolution(int StudentID,int AssignmentID){
        SQLiteDatabase db = getReadableDatabase();
        SolutionModel Solution = null;
        try {
            String Sql = "select * from "+Solution_Table+" where "+student_id+" = "+StudentID+" and "+assignment_id+" = "+AssignmentID;
            Cursor cursor = db.rawQuery(Sql,null);
            while (cursor.moveToNext()){
                Solution = new SolutionModel(cursor.getInt(cursor.getColumnIndex(id)),
                                                cursor.getBlob(cursor.getColumnIndex(content)),
                                                cursor.getInt(cursor.getColumnIndex(assignment_id)),
                                                cursor.getInt(cursor.getColumnIndex(student_id)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return Solution;
    }
}
