package com.example.baitap2android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitap2android.api.RetrofitConfig;
import com.example.baitap2android.model.Account;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnRegister , btnLogin ;
    private EditText username,password;
    private TextView txt_login;
    public boolean flag = false;
    public static ArrayList<Account> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        getData();


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                RetrofitConfig.retrofit.register(user,pass).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                getData();
                finish();
            }
        });
    }
    public void AnhXa(){
        btnRegister = findViewById(R.id.btn_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnCancel_register);
        txt_login = findViewById(R.id.txt_login);
    }
    public void getData(){
        RetrofitConfig.retrofit.login().enqueue(new Callback<ArrayList<Account>>() {
            @Override
            public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                data = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Account>> call, Throwable t) {

            }
        });
    }

}