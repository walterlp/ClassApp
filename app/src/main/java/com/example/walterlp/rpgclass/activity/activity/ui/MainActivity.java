package com.example.walterlp.rpgclass.activity.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.walterlp.rpgclass.R;
import com.example.walterlp.rpgclass.activity.activity.DAO.FirebaseDAO;
import com.example.walterlp.rpgclass.activity.activity.model.Sessao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity  extends AppCompatActivity {
    private static FirebaseAuth auth;
    private Toolbar toolbar;
    private TextView textView;
    private Sessao sessao ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseDAO.getAuth();
        initView();






    }

    public void initView(){

        textView = findViewById(R.id.text);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Nome Aleatório");
        }

        try{
            
            sessao = Sessao.findById(Sessao.class, 1L);
            Log.v("SESSAO_MAIN", sessao.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void updateUI(FirebaseUser currentUser ){
        if (currentUser == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.desconectar:
                new FirebaseDAO().singOut(MainActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
