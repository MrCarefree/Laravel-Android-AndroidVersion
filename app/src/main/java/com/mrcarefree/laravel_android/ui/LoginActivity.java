package com.mrcarefree.laravel_android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mrcarefree.laravel_android.R;
import com.mrcarefree.laravel_android.Sessions;
import com.mrcarefree.laravel_android.data.model.login.ResponseLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {
    Unbinder unbinder;
    LoginPresenter presenter;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    TextView etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        presenter  = new LoginPresenter(this);
    }

    @OnClick(R.id.tvRegister)
    void onRegister() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    @OnClick(R.id.btnLogin)
    void onLogin() {
        if(getEmail().isEmpty() || getPassword().isEmpty()){
            Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }else{
            presenter.postLogin(
                    getEmail(),
                    getPassword()
            );
        }
    }

    @Override
    public void onLoadLogin(boolean loading) {
        if (loading){
            Toast.makeText(this, "Mengirim Permintaan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResultLogin(ResponseLogin response) {
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        Sessions.getInstance(getApplicationContext()).putString(Sessions.email, getEmail());
        Sessions.getInstance(getApplicationContext()).putString(Sessions.name, response.getData().getName());
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onErrorLogin() {
        Toast.makeText(this, "Gagal Melakukan Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }
}
