package com.example.walterlp.rpgclass.activity.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.walterlp.rpgclass.R;
import com.example.walterlp.rpgclass.activity.activity.DAO.FirebaseDAO;
import com.example.walterlp.rpgclass.activity.activity.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextSenha;
    private Button buttonEntrar;
    private ProgressBar progressBar;
    private TextView textViewCadastrar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        editTextEmail = findViewById(R.id.editLoginEmail);
        editTextSenha = findViewById(R.id.editLoginSenha);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        progressBar = findViewById(R.id.progressLogin);
        textViewCadastrar = findViewById(R.id.textCadastrar);
        textViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText>editTexts = new ArrayList<>();
                editTexts.add(editTextEmail);
                editTexts.add(editTextSenha);
                if(UiUtils.validFields(editTexts,LoginActivity.this)){
                    new FirebaseDAO().login(editTextEmail.getText().toString(), editTextSenha.getText().toString(), progressBar,LoginActivity.this);


                }
            }
        });

    }
}
