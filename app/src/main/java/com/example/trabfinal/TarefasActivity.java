package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TarefasActivity extends AppCompatActivity {

    private EditText tituloTarefa, descricaoTarefa, data, hora, alarme, local;
    DBToDoHelper dbHelper;
    Tarefas tarefa, altTarefa;
    long retornoDB;
    private Button btnVariavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        tituloTarefa=findViewById(R.id.tituloTarefa);
        descricaoTarefa=findViewById(R.id.descricaoTarefa);
        data=findViewById(R.id.data);
        hora=findViewById(R.id.hora);
        alarme=findViewById(R.id.alarme);
        local=findViewById(R.id.local);
        Intent it = getIntent();
        altTarefa=(Tarefas)it.getSerializableExtra("ch_tarefa");
        tarefa = new Tarefas();
        dbHelper=new DBToDoHelper(TarefasActivity.this);
        btnVariavel=findViewById(R.id.btnConcluido);

        if(altTarefa!=null){
            btnVariavel.setText("Alterar");
            tituloTarefa.setText(altTarefa.getTituloTarefa());
            descricaoTarefa.setText(altTarefa.getDescricaoTarefa());
            data.setText(altTarefa.getData());
            hora.setText(altTarefa.getHora());
            alarme.setText(altTarefa.getAlarme());
            local.setText(altTarefa.getLocal());
            tarefa.setIdTarefa(altTarefa.getIdTarefa());
        }else{
            btnVariavel.setText("Salvar");
        }





    }
}