package com.example.linearregressioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    MaterialButton btn1; MaterialButton btn2;
    EditText edtx; EditText edty;
    TextView resultTxt;
    RelativeLayout btnLayout;
    RelativeLayout resultLayout;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1); btn2 = findViewById(R.id.btn2);
        btnLayout = findViewById(R.id.btnLayout);
        resultLayout = findViewById(R.id.resultLayout);
        resultTxt = findViewById(R.id.resultTxt);
        edty = findViewById(R.id.edty); edtx = findViewById(R.id.edtx);

        resultLayout.setVisibility(View.GONE);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String y = edty.getText().toString();
                String x = edtx.getText().toString();
                resultLayout.setVisibility(View.VISIBLE);

                ArrayList<Double> xal= new ArrayList<Double>();
                ArrayList<Double> yal= new ArrayList<Double>();

                String inx[] = x.split(",");
                String iny[] = y.split(",");

                for (int i=0; i<inx.length; i++){
                    try {
                        xal.add(Double.parseDouble(inx[i].strip()));
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, R.string.toast2, Toast.LENGTH_SHORT).show();
                        resultLayout.setVisibility(View.GONE);
                    }

                }
                for (int i=0; i<iny.length; i++){
                    try {
                        yal.add(Double.parseDouble(iny[i].strip()));
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, R.string.toast2, Toast.LENGTH_SHORT).show();
                        resultLayout.setVisibility(View.GONE);
                    }
                }

                try{
                    LinearRegression linearRegression = new LinearRegression(xal,yal);
                    resultTxt.setText(linearRegression.toString());
                } catch (IllegalArgumentException e){
                    Toast.makeText(MainActivity.this, R.string.toast1, Toast.LENGTH_SHORT).show();
                    resultLayout.setVisibility(View.GONE);
                }

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtx.setText("");
                edty.setText("");
                resultLayout.setVisibility(View.GONE);
            }
        });

    }
}