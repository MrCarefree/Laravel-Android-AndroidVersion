package com.mrcarefree.laravel_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mrcarefree.laravel_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.tvRegister) void onRegister() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    @OnClick(R.id.btnLogin) void onLogin(){

    }

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    TextView etPassword;
}
