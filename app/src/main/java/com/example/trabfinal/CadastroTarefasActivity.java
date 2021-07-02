package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroTarefasActivity extends AppCompatActivity {

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
        //alarme=findViewById(R.id.alarme);
        local=findViewById(R.id.local);
        Intent it = getIntent();
        altTarefa=(Tarefas)it.getSerializableExtra("ch_tarefa");
        tarefa = new Tarefas();
        dbHelper = new DBToDoHelper(CadastroTarefasActivity.this);
        btnVariavel = findViewById(R.id.btnVariavel);

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

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = tituloTarefa.getText().toString();
                String descricao = descricaoTarefa.getText().toString();
                String date = data.getText().toString();
                String hour = hora.getText().toString();
                String alarm = alarme.getText().toString();
                String locall = local.getText().toString();
                long retornoDB;
                tarefa.setTituloTarefa(titulo);
                tarefa.setDescricaoTarefa(descricao);
                tarefa.setData(date);
                tarefa.setHora(hour);
                tarefa.setAlarme(alarm);
                tarefa.setLocal(locall);
                if(btnVariavel.getText().toString().equals("Salvar")){
                    retornoDB=dbHelper.insertTarefa(tarefa);
                    if(retornoDB==-1){
                        Toast.makeText(CadastroTarefasActivity.this,"Erro ao cadastar",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CadastroTarefasActivity.this,"Cadastro Realizado com Sucesso",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    dbHelper.atualizar(tarefa);
                    dbHelper.close();
                }
                finish();
            }
        });





    }

    public void criarTarefa(View view) {
    }
}