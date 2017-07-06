package com.example.aliosama.porjectandroid.Database.Helper.Teacher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.aliosama.porjectandroid.Database.Models.AssignmentModel;
import com.example.aliosama.porjectandroid.Database.Models.CourseModel;
import com.example.aliosama.porjectandroid.Database.Models.SolutionModel;
import com.example.aliosama.porjectandroid.Database.Models.StudentModel;
import com.example.aliosama.porjectandroid.Database.Models.TeacherModel;

import java.util.ArrayList;

/**
 * Created by aliosama on 5/21/2017.
 */

public class LoginHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "college.db";
    private static int version_code = 1;
    // common columns

    private static final String id = "id";
    private static final String name = "name";
    private static final String content = "content";

    // login & sign up
    private static final String email = "email";
    private static final String phone = "phone";
    private static final String password = "password";
    private static final String StudentTable = "student";
    private static final String TeacherTable = "teacher";
// Course
    private static final String CourseTable = "course";
    private static final String point = "point";
    private static final String semester = "semester";
    private static final String teacher_id = "teacher_id";

    // Assignment
    private static final String Assignment_Table = "assignment";
    private static final String description = "description";
    private static final String typeofwork = "typeofwork";
    private static final String start_time = "start_time";
    private static final String end_time = "end_time";
    private static final String course_id = "course_id";

    // Solution
    private static final String Solution_Table = "assignment";
    private static final String assignment_id = "assignment_id";
    private static final String student_id = "student_id";

    Context context;
    StudentModel studentModel;
    TeacherModel teacherModel;
    static CourseModel mCourseModel;
    AssignmentModel mAssignmentModel;

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
            db.execSQL("create table "+CourseTable+" ( "+ id + " integer primary key autoincrement , "
                    + name + " text , "+point+" text , "
                    + semester + " text , "+ teacher_id + " integer ); ");
            db.execSQL("create table "+Assignment_Table+" ( "+id+ " integer primary key autoincrement , " +
                    name+" text, "+content+" blob, "+start_time+" text, "+end_time+" text, "+
                    typeofwork+" text, "+description+" text, "+
                    course_id+" integer ); ");
            db.execSQL("create table "+Solution_Table+" ( "+id+" integer primary key autoincrement , "+
                    content+" blob, "+assignment_id+" integer, "+
                    student_id+" integer ); ");
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
            db.execSQL("drop table if exists " + CourseTable);
            db.execSQL("drop table if exists " + Assignment_Table);
            db.execSQL("drop table if exists " + Solution_Table);

        } catch (SQLException e) {
            Toast.makeText(context, " DB Upgrade exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        onCreate(db); // to recreate the table again
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("drop table if exists " + StudentTable);
            db.execSQL("drop table if exists " + TeacherTable);
            db.execSQL("drop table if exists " + CourseTable);
            db.execSQL("drop table if exists " + Assignment_Table);
            db.execSQL("drop table if exists " + Solution_Table);

        } catch (SQLException e) {
            Toast.makeText(context, " DB Downgraged exception ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        onCreate(db); // to recreate the table again

    }

    //Login & sign up
    public int CheckUserLogin(String UserType,String email,String password){
       try {
           SQLiteDatabase db = getReadableDatabase();
           String Query = "";
           if(UserType.matches("Student"))
                 Query = "select "+this.id +" , "+ this.password + " from " + StudentTable + " where " + this.email + " =?";
           else if(UserType.matches("Teacher"))
                 Query = "select "+this.id +" , "+this.password + " from " + TeacherTable + " where " + this.email + " =?";

           Cursor cursor = db.rawQuery(Query, new String[]{email});
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
    public void UpdateStudent(StudentModel mStudentModel){
        try {
            studentModel = mStudentModel;
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(this.name,studentModel.getName());
            contentValues.put(this.email,studentModel.getEmail());
            contentValues.put(this.password,studentModel.getPassword());
            db.update(StudentTable,contentValues,"id=?",new String[]{Integer.toString(studentModel.getId())});
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Course

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

    // Assignment

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

    //Solution

    public SolutionModel getSolution(int StudentID, int AssignmentID){
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
