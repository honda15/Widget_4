package com.example.widget_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName,editTextEmail,editTextPhone,editTextHeight,editTextWeight;
    private Button buttonCancel,buttonOk;
    private TextView textViewBMI;

//    private Intent intent = new Intent(MainActivity.this, Show_data.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edit_name);
        editTextEmail = findViewById(R.id.edit_email);
        editTextPhone = findViewById(R.id.edit_phone);

        editTextHeight = findViewById(R.id.edit_hight);
        editTextWeight = findViewById(R.id.edit_weight);

        buttonCancel = findViewById(R.id.button_cancel);
        buttonOk = findViewById(R.id.button_ok);

        textViewBMI = findViewById(R.id.textView_bmi);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextEmail.setText("");
                editTextPhone.setText("");
                editTextHeight.setText("");
                editTextWeight.setText("");
                textViewBMI.setText("");
                textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.bmi_range_chart,0,0,0);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            private String name,email,phone,height,weight;
            @Override
            public void onClick(View v) {
                if(editTextName.length()==0 ||editTextEmail.length()==0 || editTextPhone.length()==0){
                    Toast.makeText(MainActivity.this, "Please input personal information", Toast.LENGTH_SHORT).show();
                    textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                }else{
                    Intent intent = new Intent(MainActivity.this, Show_data.class);

                    name = editTextName.getText().toString();
                    email = editTextEmail.getText().toString();
                    phone = editTextPhone.getText().toString();

                    textViewBMI.setText("Name: "+name+"\n"+"Email: "+email+"\n"+"phone: "+phone+"\n");
//                    intent.putExtra("name",name);
//                    intent.putExtra("emial",email);
//                    intent.putExtra("phone",phone);
//                    startActivity(intent);
                    textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                }

                if(editTextHeight.length()==0 ||editTextWeight.length()==0 ){
                    Toast.makeText(MainActivity.this, "Please input your height & weight", Toast.LENGTH_SHORT).show();
                    textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
                }else{
                    height = editTextHeight.getText().toString();
                    weight = editTextWeight.getText().toString();
                    float heightvalue = Float.parseFloat(height);
                    float weightvalue = Float.parseFloat(weight);
                    double bmiValue = weightvalue * 100.0 * 100.0 / (heightvalue*heightvalue);
                    Log.v("main","bmi= "+bmiValue);
                    NumberFormat nf = new DecimalFormat("##.00");
                    String bmiString = nf.format(bmiValue);
 //                   intent.putExtra("BMI value",bmiString);
//                    startActivity(intent);
                    textViewBMI.append("BMI value ="+bmiString+"\n");
                    checkBMI(bmiValue);

                }
            }
        });
    }

    private  void checkBMI(double bmiValue){
        if(bmiValue < 18.5){
            textViewBMI.append(getString(R.string.bmi_low));
            textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.fat_3,0);
        }else if(bmiValue < 25){
            textViewBMI.append(getString(R.string.bmi_normal));
            textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.fat_4,0);
        }else if(bmiValue < 30) {
            textViewBMI.append(getString(R.string.bmi_high));
            textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.fat_2, 0);
        }else {
                textViewBMI.append(getString(R.string.bmi_over));
                textViewBMI.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.fat_1,0);
        }
    }
}