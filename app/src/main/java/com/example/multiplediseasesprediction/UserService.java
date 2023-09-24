package com.example.multiplediseasesprediction;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
@POST("api/diabetes")
    Call<ResultResponse> diabetespred(@Body DiabetesPredRequest diabetesPredRequest);


}
