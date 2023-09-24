package com.example.multiplediseasesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Result extends AppCompatActivity {
 ResultResponse resultResponse;
 TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        res=findViewById(R.id.resultId);
        Intent intent=getIntent();
        if(intent.getExtras()!=null) {
            resultResponse = (ResultResponse) intent.getSerializableExtra("data");
            Log.e("TAG", "---->" + resultResponse.getResult());
            if(resultResponse.getResult().toString().equalsIgnoreCase("1")){
                res.setText("Positive");

            }
            else{

                res.setText("Negative");
            }

        }
    }

}