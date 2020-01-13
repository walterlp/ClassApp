package com.example.walterlp.rpgclass.activity.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.walterlp.rpgclass.R;
import com.example.walterlp.rpgclass.activity.activity.DAO.FirebaseDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity  extends AppCompatActivity {
    private static FirebaseAuth auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseDAO.getAuth();


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser currentUser ){
        if (currentUser == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
        }
    }




}
