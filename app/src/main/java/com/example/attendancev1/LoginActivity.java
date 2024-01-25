package com.example.attendancev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private LinearLayout loginButton;

    private RetrofitInterface apiService;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //sharedPreferences

        usernameEditText = findViewById(R.id.et_username_login);
        passwordEditText = findViewById(R.id.et_password_login);
        loginButton = findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(LoginActivity.this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://attendance-a2qs.onrender.com/") // Replace with your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RetrofitInterface.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    public void login(){
        //loading();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(usernameEditText.getText().toString());
        loginRequest.setPassword(passwordEditText.getText().toString());

        Call<LoginResult> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                if(response.isSuccessful()){
                    LoginResult loginResponse = response.body();
                    progressDialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (response.body().getRole() == "Student")
                            {
                                startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("data",loginResponse.getUsername()));

                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Sorry,this app is for Student only", Toast.LENGTH_LONG).show();
                            }

                        }
                    },700);

                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    public void loading(){
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Finding account in database...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }





}