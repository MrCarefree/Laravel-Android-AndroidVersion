package com.mrcarefree.laravel_android.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mrcarefree.laravel_android.R;
import com.mrcarefree.laravel_android.Sessions;
import com.mrcarefree.laravel_android.data.model.student.ResponseStudent;
import com.mrcarefree.laravel_android.data.model.student.delete.ResponseDelete;
import com.mrcarefree.laravel_android.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainInterface.View {
    Unbinder unbinder;
    MainPresenter presenter;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.rvMain)
    RecyclerView rvMain;
    @BindView(R.id.srMain)
    SwipeRefreshLayout srMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Laravel Android CRUD");
        getSupportActionBar().setElevation(0);

        unbinder = ButterKnife.bind(this);

        tvName.setText("Nama : " + Sessions.getInstance(getApplicationContext()).getString(Sessions.name));
        tvEmail.setText("Email : " + Sessions.getInstance(getApplicationContext()).getString(Sessions.email));

        presenter = new MainPresenter(this);
        presenter.getStudent();

        srMain.setDistanceToTriggerSync(500);
        srMain.setOnRefreshListener(() -> presenter.getStudent());

    }

    @OnClick(R.id.btnLogout)
    void onLogout() {
        Sessions.getInstance(getApplicationContext()).logout();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onLoadingStudent(boolean loading) {
        if (loading) {
            Toast.makeText(this, "Menerima Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResultStudent(ResponseStudent responseStudent) {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new MainAdapter(this, responseStudent.getData(), this));
    }

    @Override
    public void onErrorStudent() {
        Toast.makeText(this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDelete(String nim) {
        presenter.deleteStudent(nim);
    }

    @Override
    public void onLoadDelete(boolean loading) {
        if (loading) {
            Toast.makeText(this, "Menghapus Data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResultDelete(ResponseDelete responseDelete) {
        presenter.getStudent();
    }

    @Override
    public void onErrorDelete() {
        Toast.makeText(this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:
                Toast.makeText(this, "Sedang dalam pembangunan", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
