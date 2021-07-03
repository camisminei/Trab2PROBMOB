package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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

    private Button btnCancel, btnAtivar;
    AlarmManager alarmManager;
    PendingIntent pedingIntent;
    boolean isRepeat=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        tituloTarefa=findViewById(R.id.tituloTarefa);
        descricaoTarefa=findViewById(R.id.descricaoTarefa);
        data=findViewById(R.id.data);
        hora=findViewById(R.id.hora);
        btnAtivar=findViewById(R.id.btnAtivar);
        btnCancel=findViewById(R.id.btnCancel);
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
            local.setText(altTarefa.getLocal());
            tarefa.setIdTarefa(altTarefa.getIdTarefa());
        }else{
            btnVariavel.setText("Salvar");
        }
        btnAtivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroTarefasActivity.this,"Alarme Ativado",Toast.LENGTH_SHORT).show();
                isRepeat=false;
                startAlarme();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroTarefasActivity.this,"Alarme Cancelado",Toast.LENGTH_SHORT).show();
                if(alarmManager !=null){
                    alarmManager.cancel(pedingIntent);
                }
            }
        });

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = tituloTarefa.getText().toString();
                String descricao = descricaoTarefa.getText().toString();
                String date = data.getText().toString();
                String hour = hora.getText().toString();
                String locall = local.getText().toString();
                long retornoDB;
                tarefa.setTituloTarefa(titulo);
                tarefa.setDescricaoTarefa(descricao);
                tarefa.setData(date);
                tarefa.setHora(hour);
                tarefa.setLocal(locall);
                if(btnVariavel.getText().toString().equals("Salvar")){
                    retornoDB=dbHelper.insertTarefa(tarefa);
                    if(retornoDB==-1){
                        Toast.makeText(CadastroTarefasActivity.this,"Erro ao cadastar",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CadastroTarefasActivity.this,"Cadastro Realizado com Sucesso",Toast.LENGTH_SHORT).show();
                        //Toast.makeText(CadastroTarefasActivity.this, "Alarme Disparado!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    dbHelper.atualizar(tarefa);
                    dbHelper.close();
                }
                finish();
            }
        });
    }

    private void startAlarme() {
        alarmManager=(AlarmManager)
                this.getSystemService(Context.ALARM_SERVICE);
        Intent it = new Intent(CadastroTarefasActivity.this, AlarmeToastReceiver.class);
        pedingIntent=PendingIntent.getBroadcast(this,0,it,0);
        if(!isRepeat){
            alarmManager.set(AlarmManager.RTC, SystemClock.elapsedRealtime()+60*1000,pedingIntent);
        }else{
            //alarmManager.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime()+60*1000, 60*1000, pedingIntent);
            alarmManager.set(AlarmManager.RTC, SystemClock.elapsedRealtime()+60*1000,pedingIntent);
        }
    }

    public void criarTarefa(View view) {
    }
}