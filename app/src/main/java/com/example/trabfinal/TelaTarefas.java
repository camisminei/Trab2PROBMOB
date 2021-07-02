package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TelaTarefas extends AppCompatActivity {

    private ListView listTarefas;
    private Button bttnAddTarefa;
    Tarefas tarefa;
    DBToDoHelper db;
    ArrayList<Tarefas> listaTarefas;
    ArrayAdapter<Tarefas> arrayAdapterTarefas;
    private int id1, id2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_tela);
        listTarefas = findViewById(R.id.listTarefas);
        bttnAddTarefa = findViewById(R.id.bttnAddTarefa);
        registerForContextMenu(listTarefas);
        preencherLista();
        bttnAddTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaTarefas.this, CadastroTarefasActivity.class);
                startActivity(intent);
            }

        });
        listTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Tarefas a1=(Tarefas)arrayAdapterTarefas.getItem(position);
                Intent it=new Intent(TelaTarefas.this, CadastroTarefasActivity.class);
                it.putExtra("ch_tarefa",a1);
                startActivity(it);
            }
        });

        listTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                tarefa = arrayAdapterTarefas.getItem(position);
                return false;
            }
        });
    }

    public void preencherLista() {
        db = new DBToDoHelper(TelaTarefas.this);
        listaTarefas = db.selectAllTarefas();
        db.close();
        if (listaTarefas != null) {
            arrayAdapterTarefas = new ArrayAdapter<Tarefas>(TelaTarefas.this,
                    android.R.layout.simple_expandable_list_item_1, listaTarefas);
            listTarefas.setAdapter(arrayAdapterTarefas);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencherLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancela");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                long retornoBD;
                db = new DBToDoHelper(TelaTarefas.this);
                retornoBD = db.deleteTarefa(tarefa);
                db.close();
                if (retornoBD == -1) {
                    alert("Erro de exclusão!");
                } else {
                    alert("Registro excluído com sucesso!");
                }
                preencherLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    private void alert(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
