package com.example.ali.gpa;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    String GradesSybmols[] = {"A", "a", "A-", "a-", "B+", "b+", "B", "b", "B-", "b-", "C+", "c+", "C", "c", "C-", "c-",
            "D+", "d+", "D", "d", "D-", "d-", "F", "f"};
    double GradesValues[] = {4.00, 4.00, 3.67, 3.67, 3.33, 3.33, 3.00, 3.00, 2.67, 2.67, 2.33, 2.33, 2.00, 2.00, 1.67, 1.67
            , 1.33, 1.33, 1.00, 1.00, 0.67, 0.67, 0.00, 0.00};
    double Hours = 0, Grades_Sum = 0, Hours_Sum = 0, GPA = 0;
    String Grade;

    int NumberOfCreditHours, Counter = 0;
    double GetOldGPAInput = 0.0;

    //first Semester     Layout
    CheckBox OldGPACheck;
    EditText OldGPAInpt, NumOfCoursesInpt;
    Button NumberOfCoursebtn;
    TextView FirstLayoutTxt;

    // Degree_Input Layout
    EditText CreditHourInpt, GradesInpt;
    TextView SecondLayoutTxt;

    //Final Result Layout
    TextView ShowGPATextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CallFirstLayout();
    }
    //First Layout View
    public void CallFirstLayout(){
        setContentView(R.layout.first_semester);

        //first Semester Layout
        OldGPACheck = (CheckBox) findViewById(R.id.OldGPACheckbox);
        OldGPAInpt = (EditText) findViewById(R.id.OldGPAInput);
        NumOfCoursesInpt = (EditText) findViewById(R.id.NumOfCoursesInput);
        NumberOfCoursebtn = (Button) findViewById(R.id.NumOfCoursesbtn);
        OldGPAInpt.setEnabled(false);
        FirstLayoutTxt = (TextView) findViewById(R.id.OutputTextVeiw);
    }
    // First_Semester Layout Submit Button Action Method
    public void Action(View view) {

        NumberOfCreditHours = Integer.parseInt(NumOfCoursesInpt.getText().toString());
        if (!OldGPAInpt.getText().toString().matches(""))
            GetOldGPAInput = Double.parseDouble(OldGPAInpt.getText().toString());
        setContentView(R.layout.degree_input);

        // Degree_Input Layout
        CreditHourInpt = (EditText) findViewById(R.id.CreditHourInput);
        GradesInpt = (EditText) findViewById(R.id.GradeInput);
        SecondLayoutTxt = (TextView) findViewById(R.id.Output2TextVeiw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Checkbox Method
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.OldGPACheckbox:
                if (checked)
                    OldGPAInpt.setEnabled(true);
                else
                    OldGPAInpt.setEnabled(false);
                break;
        }
    }

    // degree_Input Layout Button(Submit) Action
    public void Caculate_GPA(View view) {

        // Read Inputs From Edit Texts
        Hours = Double.parseDouble(CreditHourInpt.getText().toString());
        Grade = GradesInpt.getText().toString();
        Counter++;
        CreditHourInpt.setText("");
        GradesInpt.setText("");

        //Search Loop of GPA Symbol
        for (int x = 0; x < GradesSybmols.length; x++) {//GradesSybmols[]
            if (Grade.equals(GradesSybmols[x])) {
                Grades_Sum += Math.floor((Hours * GradesValues[x]) * 100) / 100;
                Hours_Sum += Hours;
                break;
            }
        }

        SecondLayoutTxt.setText("Course " + (Counter + 1));
        //Goto To The Final Layout That Display GPA
        if (Counter == NumberOfCreditHours) {

            if (GetOldGPAInput > 0)
                GPA = Math.floor(((GetOldGPAInput + Math.floor((Grades_Sum / Hours_Sum) * 100) / 100) / 2) * 100) / 100;
            else
                GPA = Math.floor((Grades_Sum / Hours_Sum) * 100) / 100;

            setContentView(R.layout.final_result);
            ShowGPATextView = (TextView) findViewById(R.id.GPA_Final_result_Number);
            ShowGPATextView.setText(""+GPA);
        }
    }

    public void AgainBtnAction(View view){
        Counter = 0;
        GetOldGPAInput = 0.0;
        Hours_Sum = 0;
        Grades_Sum = 0;
        GPA = 0;
        NumberOfCreditHours = 0;
        CallFirstLayout();
    }
    public void QuitBtnAction(View view){
        Toast.makeText(this, "Thanks For Using our App" , Toast.LENGTH_LONG).show();
        finish();
        System.exit(0);
    }

}