package com.example.piotrjanus.lab8zpo;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DatePickerListener{

    private Date birthDate;
    private Button calculateButton;
    private EditText heightEditText;
    private EditText weightEditTExt;
    private Spinner genderSpinner;
    private Spinner methodSpinner;
    private TextView resultsTextView;
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = findViewById(R.id.calculateButton);
        heightEditText = findViewById(R.id.heightEditText);
        weightEditTExt = findViewById(R.id.weightEditText);
        genderSpinner = findViewById(R.id.genderSpinner);
        methodSpinner = findViewById(R.id.methodSpinner);
        resultsTextView = findViewById(R.id.resultsTextView);
        mainLayout = findViewById(R.id.mainLayout);
    }

    public void showDatePicker(View view) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"DatePicker");
        hideKeyboard();
    }

    @Override
    public void onDatePicked(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year,month,day);
        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());

        birthDate = calendar.getTime();

        showToast(dateString);

    }

    public void calculate(View view) {
        hideKeyboard();

        double height;
        double weight;

        try {
            height = Double.parseDouble(heightEditText.getText().toString());
            weight = Double.parseDouble(weightEditTExt.getText().toString());
        }catch(NumberFormatException ex){
            showToast("Fill all text fields and choose date of birth");
            return;
        }

        Gender gender = Gender.of ((String) genderSpinner.getSelectedItem());
        String method = (String) methodSpinner.getSelectedItem();

        Date date = new Date();
        long years;
        
        try {
            years = (date.getTime() - birthDate.getTime()) / 86400000 / 365;
        }catch (NullPointerException ex){
            showToast("Please choose your date of birth before calculate PPM");
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("####.##");
        PPM ppm = new PPM(getApplicationContext());

        resultsTextView.setText("Height: "+height + " cm\nWeight: "+weight+ " kg\nAge: "+ years +
                "\nPPM: " + decimalFormat.format(ppm.getPPM(height,weight,years,method,gender)) +" kcal");

    }

    public void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mainLayout.getWindowToken(),0);

    }
}
