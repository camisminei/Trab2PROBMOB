package com.example.trabfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Objects;

public class DBToDoHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo";
    public static final String chave = "criptografia-facom";

    //Tabela tarefas
    private static final String TABLE_NAME_TAREFA = "Tarefas";
    private static final String TAREFA_COLUM_ID = "ID";
    private static final String TAREFA_COLUM_TITULO = "Titulo";
    private static final String TAREFA_COLUM_DESCRICAO = "Descricao";
    private static final String TAREFA_COLUM_DATA = "Data";
    private static final String TAREFA_COLUM_HORA = "Hora";
    private static final String TAREFA_COLUM_ALARME = "Alarme";
    private static final String TAREFA_COLUM_LOCAL = "Localizacao";

    //Tabela usuário
    private static final String TABLE_NAME_USUARIO = "Usuarios";
    private static final String USUARIO_COLUM_ID = "ID";
    private static final String USUARIO_COLUM_NOME = "Nome";
    private static final String USUARIO_COLUM_EMAIL = "Email";
    private static final String USUARIO_COLUM_TELEFONE = "Telefone";
    private static final String USUARIO_COLUM_SENHA = "Senha";
    private static final String USUARIO_COLUM_FOTO = "Foto";

    SQLiteDatabase db;

    public DBToDoHelper (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    private static final String TABLE_CREATE_TAREFA =
            "create table " + TABLE_NAME_TAREFA
                    + " ("
                    + TAREFA_COLUM_ID + " integer primary key autoincrement, "
                    + TAREFA_COLUM_TITULO + " text, "
                    + TAREFA_COLUM_DESCRICAO + " text, "
                    + TAREFA_COLUM_DATA + " text, "
                    + TAREFA_COLUM_HORA + " text, "
                    + TAREFA_COLUM_ALARME + " text, "
                    + TAREFA_COLUM_LOCAL + " text" +
                    ");";

    private static final String TABLE_CREATE_USUARIO =
            "create table " + TABLE_NAME_USUARIO
            + " ("
            + USUARIO_COLUM_ID + " integer primary key autoincrement, "
            + USUARIO_COLUM_NOME + " text, "
            + USUARIO_COLUM_EMAIL + " text, "
            + USUARIO_COLUM_TELEFONE + " text, "
            + USUARIO_COLUM_SENHA + " text, "
            + USUARIO_COLUM_FOTO + " BLOB"
            + " );";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_TAREFA);
        db.execSQL(TABLE_CREATE_USUARIO);
        //this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME_TAREFA;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + TABLE_NAME_USUARIO;
        db.execSQL(query);
        this.onCreate(db);
    }

    public long insertTarefa(Tarefas t) {
        long returnDB;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // os atributos que sempre devem existir
        //values.put(TAREFA_COLUM_ID, t.getIdTarefa());
        values.put(TAREFA_COLUM_TITULO, t.getTituloTarefa());
        values.put(TAREFA_COLUM_DESCRICAO, t.getDescricaoTarefa());
        values.put(TAREFA_COLUM_DATA, t.getData());
        values.put(TAREFA_COLUM_HORA, t.getHora());
        values.put(TAREFA_COLUM_ALARME, t.getAlarme());
        values.put(TAREFA_COLUM_LOCAL, t.getLocal());
        returnDB = db.insert(TABLE_NAME_TAREFA, null, values);
        String res = Long.toString(returnDB);
        Log.i("DBToDoHelper", res);
        db.close();
        return returnDB;
    }

    public long insertUser(Usuario u) throws GeneralSecurityException {
        long returnDB;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(!u.getNome().equals(""))
            values.put(USUARIO_COLUM_NOME, u.getNome());
        if(!u.getEmail().equals(""))
            values.put(USUARIO_COLUM_EMAIL, u.getEmail());
        if(!u.getTelefone().equals(""))
            values.put(USUARIO_COLUM_TELEFONE, u.getTelefone());

        String senha = u.getSenha();

        if(!senha.equals("")) {
            String novaSenha = cripto(senha);
            values.put(USUARIO_COLUM_SENHA, novaSenha);
        }

        //add foto e tratar

        byte[] foto = u.getFoto();

        returnDB = db.insert(TABLE_NAME_USUARIO, null, values);
        String res = Long.toString(returnDB);
        Log.i("DBToDoHelper", res);
        db.close();
        return returnDB;
    }

    public long deleteTarefa(Tarefas t) {
        long returnDB;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(t.getIdTarefa())};
        returnDB = db.delete(TABLE_NAME_TAREFA, TAREFA_COLUM_ID + "=?", args);
        return returnDB;
    }

    public long updateTarefa(Tarefas t) {
        long returnDB;
        this.db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAREFA_COLUM_TITULO, t.getTituloTarefa());
        values.put(TAREFA_COLUM_DESCRICAO, t.getDescricaoTarefa());
        values.put(TAREFA_COLUM_DATA, t.getData());
        values.put(TAREFA_COLUM_HORA, t.getHora());
        values.put(TAREFA_COLUM_LOCAL, t.getLocal());
        values.put(TAREFA_COLUM_ALARME, t.getAlarme());
        String[] args = {String.valueOf(t.getIdTarefa())};
        returnDB = db.update(TABLE_NAME_TAREFA, values, "ID=?", args);
        db.close();
        return returnDB;
    }

    public long updateUsuario(Usuario u) throws GeneralSecurityException {
        long returnDB;
        this.db = getWritableDatabase();
        ContentValues values = new ContentValues();
        if(!u.getNome().equals(""))
            values.put(USUARIO_COLUM_NOME, u.getNome());
        if(!u.getEmail().equals(""))
            values.put(USUARIO_COLUM_EMAIL, u.getEmail());
        if(!u.getTelefone().equals(""))
            values.put(USUARIO_COLUM_TELEFONE, u.getTelefone());

        String senha = u.getSenha();
        if(senha.equals("")) {
            String novaSenha = cripto(senha);
            values.put(USUARIO_COLUM_SENHA, novaSenha);
        }

        String[] args = {String.valueOf(u.getIdUsuario())};
        returnDB = db.update(TABLE_NAME_USUARIO, values, "ID=?", args);
        db.close();
        return returnDB;
    }

    public ArrayList<Tarefas> selectAllTarefas() {
        String[] coluns = {TAREFA_COLUM_ID,
                        TAREFA_COLUM_TITULO,
                        TAREFA_COLUM_DESCRICAO,
                        TAREFA_COLUM_DATA,
                        TAREFA_COLUM_HORA,
                        TAREFA_COLUM_ALARME,
                        TAREFA_COLUM_LOCAL};
        Cursor cursor = getWritableDatabase().query(TABLE_NAME_TAREFA, coluns, null, null, null, null, "ID", null);
        ArrayList<Tarefas> listTarefas = new ArrayList<Tarefas>();
        while(cursor.moveToNext()) {
            Tarefas t = new Tarefas();
            t.setIdTarefa(cursor.getInt(0));
            t.setTituloTarefa(cursor.getString(1));
            t.setDescricaoTarefa(cursor.getString(2));
            t.setData(cursor.getString(3));
            t.setHora(cursor.getString(4));
            t.setAlarme(cursor.getString(5));
            t.setLocal(cursor.getString(6));
            listTarefas.add(t);
        }
        cursor.close();

        return listTarefas;

    }

    public String buscaSenha(String email) throws GeneralSecurityException {
        String ret = "Não encontrado";
            db = this.getReadableDatabase();
            String[] coluns = {
                    USUARIO_COLUM_ID,
                    USUARIO_COLUM_NOME,
                    USUARIO_COLUM_EMAIL,
                    USUARIO_COLUM_TELEFONE,
                    USUARIO_COLUM_SENHA
            };
            Cursor cursor = db.query(TABLE_NAME_USUARIO, coluns, null, null, null, null, "ID", null);
            String aux;
            if (cursor.moveToFirst()) {
                do {
                    aux = cursor.getString(2);
                    if (aux.equals(email)) {
                        ret = cursor.getString(4);
                        break;
                    }
                } while (cursor.moveToNext());
            }

        ret = noCripto(ret);

        return ret;

    }


    public long atualizar(Tarefas tarefa) {
        long retorno;
        db=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAREFA_COLUM_TITULO, tarefa.getTituloTarefa());
        values.put(TAREFA_COLUM_DESCRICAO,tarefa.getDescricaoTarefa());
        values.put(TAREFA_COLUM_DATA,tarefa.getData());
        values.put(TAREFA_COLUM_ALARME,tarefa.getAlarme());
        values.put(TAREFA_COLUM_LOCAL,tarefa.getLocal());
        String[] args = {String.valueOf(tarefa.getIdTarefa())};
        retorno=db.update(TABLE_NAME_TAREFA,values,"id=?",args);
        db.close();
        return retorno;
    }

    public String cripto(String senha) throws GeneralSecurityException {

        return AESCrypt.encrypt(chave, senha);

    }

    public String noCripto(String senha) throws GeneralSecurityException {

        return AESCrypt.decrypt(chave, senha);

    }
}
