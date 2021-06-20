package com.example.trabfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBToDoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo.db";
    private static final String TABLE_NAME_TAREFA = "Tarefas";
    private static final String TAREFA_COLUM_ID = "ID";
    private static final String TAREFA_COLUM_TITULO = "Titulo";
    private static final String TAREFA_COLUM_DESCRICAO = "Descricao";
    private static final String TAREFA_COLUM_DATA = "Data";
    private static final String TAREFA_COLUM_HORA = "Hora";
    private static final String TAREFA_COLUM_ALARME = "Alarme";
    private static final String TAREFA_COLUM_LOCAL = "Localizacao";

    SQLiteDatabase db;

    private static final String TABLE_CREATE_TAREFA =
            "create table " + TABLE_NAME_TAREFA + " ("
                    + TAREFA_COLUM_ID + " integer primary key, "
                    + TAREFA_COLUM_TITULO + " text, "
                    + TAREFA_COLUM_DESCRICAO + " text, "
                    + TAREFA_COLUM_DATA + " text, "
                    + TAREFA_COLUM_HORA + " text, "
                    + TAREFA_COLUM_ALARME + " boolean, "
                    + TAREFA_COLUM_LOCAL + " boolean);";

    public DBToDoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_TAREFA);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME_TAREFA + ";";
    }

    //@Override

}
