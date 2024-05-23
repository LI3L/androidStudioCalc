package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText leftNum,rightNum;
    Button calc;
    Spinner actions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actions=findViewById(R.id.actions);
        result=findViewById(R.id.result);
        calc=findViewById(R.id.calc);
        leftNum=findViewById(R.id.leftNum);
        rightNum=findViewById(R.id.rightNum);

        actions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String action = ""+adapterView.getItemIdAtPosition(position);
                //Toast.makeText(MainActivity.this, ""+action, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayList<String> list=new ArrayList<>();
        list.add("+");
        list.add("-");
        list.add("/");
        list.add("*");
        list.add("^");

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        actions.setAdapter(adapter);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double num1 = Double.parseDouble(leftNum.getText().toString());
                double num2 = Double.parseDouble(rightNum.getText().toString());
                double r=0;
                String operator = actions.getSelectedItem().toString();

                try{
                    switch (operator){
                        case "+":
                            r = num1+num2;
                            break;
                        case "-":
                            r= num1-num2;
                            break;
                        case "/":
                            if(num2==0)throw new Exception("devision by 0");
                            r= num1/num2;
                            break;
                        case "*":
                            r=num1*num2;
                            break;
                        case "^":
                            r=Math.pow(num1,num2);
                            break;
                    }
                    result.setText("result: "+r);
                }catch (Exception e){
                    result.setText("Error: "+e.getMessage());
                };
            }
        });



    }


}