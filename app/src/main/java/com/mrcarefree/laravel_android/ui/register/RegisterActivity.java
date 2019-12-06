package com.mrcarefree.laravel_android.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mrcarefree.laravel_android.R;
import com.mrcarefree.laravel_android.Sessions;
import com.mrcarefree.laravel_android.data.model.register.ResponseRegister;
import com.mrcarefree.laravel_android.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements RegisterInterface.View {
    Unbinder unbinder;
    RegisterPresenter presenter;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Buat akun baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        unbinder = ButterKnife.bind(this);
        presenter  = new RegisterPresenter(this);
    }

    @OnClick(R.id.btnDaftar) void onSubmit() {
        if(getName().isEmpty() || getEmail().isEmpty() || getPassword().isEmpty()){
            Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }else{
            presenter.postRegister(
                    getEmail(),
                    getName(),
                    getPassword()
            );
        }
    }

    @Override
    public void onResultRegister(ResponseRegister response) {
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        Sessions.getInstance(getApplicationContext()).putString(Sessions.email, getEmail());
        Sessions.getInstance(getApplicationContext()).putString(Sessions.name, getName());
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    public void onErrorRegister() {
        Toast.makeText(this, "Gagal Melakukan Registrasi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadRegister(boolean loading) {
        if (loading){
            Toast.makeText(this, "Mengirim Permintaan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return etName.getText().toString();
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
