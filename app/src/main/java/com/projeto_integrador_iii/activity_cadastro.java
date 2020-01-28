package com.projeto_integrador_iii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_cadastro extends AppCompatActivity implements View.OnClickListener {
    private EditText eTNome;
    private EditText eTDescricao;
    private EditText eTPreco;
    private Button cancelar;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    @Override
    public void onClick(View v) {

    }
}
