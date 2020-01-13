package com.example.walterlp.rpgclass.activity.activity.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.walterlp.rpgclass.R;
import com.example.walterlp.rpgclass.activity.activity.DAO.FirebaseDAO;
import com.example.walterlp.rpgclass.activity.activity.model.Sessao;
import com.example.walterlp.rpgclass.activity.activity.model.TipoUsuario;
import com.example.walterlp.rpgclass.activity.activity.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText editTextNome, editTextEmail, editTextSenha;
    private Button buttonCadastrar;
    private Switch switchTipo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        initView();

    }

    private void initView() {
        progressBar = findViewById(R.id.progressCadastro);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        editTextEmail = findViewById(R.id.editCadastroEmail);
        editTextNome = findViewById(R.id.editCadastroNome);
        editTextSenha = findViewById(R.id.editCadastroSenha);
        switchTipo = findViewById(R.id.switc);



        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText> editTexts = new ArrayList<>();
                editTexts.add(editTextEmail);
                editTexts.add(editTextSenha);
                editTexts.add(editTextNome);
                if (UiUtils.validFields(editTexts, getApplicationContext())) {

                    Sessao sessao = new Sessao();

                    if(switchTipo.isChecked()){
                        sessao.setTipoUsuario(TipoUsuario.PROFESSOR);
                    }else{
                        sessao.setTipoUsuario(TipoUsuario.ALUNO);

                    }

                    new FirebaseDAO().register(editTextNome, editTextEmail, editTextSenha, sessao, progressBar,CadastroActivity.this);


                }


            }
        });
    }

}
