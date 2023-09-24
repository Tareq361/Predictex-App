package com.example.multiplediseasesprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button submit,cancel;
    EditText pregnancies,glucose,bp,skinThickness,insulin,bmi,diabetesPeDFunc,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submitbtn);
        cancel = findViewById(R.id.cancelbtn);
        pregnancies = findViewById(R.id.pregnancies);
        glucose = findViewById(R.id.glucose);
        bp = findViewById(R.id.blood_pressure);
        skinThickness = findViewById(R.id.skin_thickness);
        insulin = findViewById(R.id.insulin);
        bmi = findViewById(R.id.bmi);
        diabetesPeDFunc = findViewById(R.id.diabetes_pedigreeFunction);
        age = findViewById(R.id.age);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DiabetesPredRequest diabetesPredRequest= new DiabetesPredRequest();
                diabetesPredRequest.setAge(age.getText().toString());
                diabetesPredRequest.setBMI(bmi.getText().toString());
                diabetesPredRequest.setInsulin(insulin.getText().toString());
                diabetesPredRequest.setBloodPressure(bp.getText().toString());
                diabetesPredRequest.setSkinThickness(skinThickness.getText().toString());
                diabetesPredRequest.setGlucose(glucose.getText().toString());
                diabetesPredRequest.setPregnancies(pregnancies.getText().toString());
                diabetesPredRequest.setDiabetesPedigreeFunction(diabetesPeDFunc.getText().toString());
                diabetespredt(diabetesPredRequest);

            }
        });


    }

    public void diabetespredt(DiabetesPredRequest diabetesPredRequest){
        Call<ResultResponse> resultResponseCall = ApiClient.getService().diabetespred(diabetesPredRequest);
        resultResponseCall.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                if(response.isSuccessful()) {
                    ResultResponse resultResponse = response.body();
                    startActivity(new Intent(MainActivity.this, Result.class).putExtra("data",resultResponse));
                    finish();


                } else {
                    String message = "An Error Occurred!";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
//    private void resultActivity() {
//        Intent intent = new Intent(this, resultActivity.class);
//        startActivity(intent);
//    }
}