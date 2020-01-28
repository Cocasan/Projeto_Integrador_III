package com.projeto_integrador_iii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button bTVisualizar;
    private ImageButton ibtnCadastrar;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bTVisualizar = (Button) findViewById(R.id.bTVisualizar);
        ibtnCadastrar = (ImageButton) findViewById(R.id.ibTCadastro);

        ibtnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_cadastro.class);
                startActivity(intent);
            }
        });

        bTVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_visualizar.class);
            }
        });
    }

}
