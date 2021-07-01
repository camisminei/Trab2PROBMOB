package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TelaTarefas extends AppCompatActivity {

    private Button bttnAddTarefa;
    private ListView listTarefas;
    List<Tarefas> listaTarefas;
    ArrayAdapter<Tarefas> arrayAdapterTarefas;
    DBToDoHelper db;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_tela);
        bttnAddTarefa = findViewById(R.id.bttnAddTarefa);
        listTarefas = findViewById(R.id.listTarefas);


    }

    public void criarTarefa(View view) {
        Intent intent = new Intent(this, TarefasActivity.class);
        startActivity(intent);
    }

    public void preencherLista() {
        db = new DBToDoHelper(TelaTarefas.this);
        listaTarefas = db.selectAllTarefas();
        db.close();
        if(listaTarefas != null) {
            arrayAdapterTarefas = new ArrayAdapter<Tarefas>(TelaTarefas.this,
            android.R.layout.simple_list_item_1, listaTarefas);
            listTarefas.setAdapter(arrayAdapterTarefas);
        }
    }


}
