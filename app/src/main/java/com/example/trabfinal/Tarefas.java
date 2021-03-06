package com.example.trabfinal;

import java.io.Serializable;

public class Tarefas implements Serializable {
    private int idTarefa;
    private String tituloTarefa;
    private String descricaoTarefa;
    private String data;
    private String hora;
    private String local;

    public Tarefas() {
    }

    public Tarefas(String titulo, String desc, String data, String hora) {
        //this.idTarefa = id;
        this.tituloTarefa = titulo;
        this.descricaoTarefa = desc;
        this.data = data;
        this.hora = hora;
    }

    public Tarefas(String titulo, String data, String hora) {
        this.tituloTarefa = titulo;
        this.data = data;
        this.hora = hora;
    }

    //getters and setters
    public int getIdTarefa() { return idTarefa; }

    public void setIdTarefa(int idTarefa) { this.idTarefa = idTarefa; }

    public String getTituloTarefa() { return this.tituloTarefa; }

    public void setTituloTarefa(String tituloTarefa) { this.tituloTarefa = tituloTarefa; }

    public String getDescricaoTarefa() { return this.descricaoTarefa; }

    public void setDescricaoTarefa(String descricaoTarefa) { this.descricaoTarefa = descricaoTarefa; }

    public String getData() { return this.data; }

    public void setData(String data) { this.data = data; }

    public String getHora() { return this.hora; }

    public void setHora(String hora) { this.hora = hora; }



    public String getLocal() { return local; }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString(){
        return "Tarefa: " + this.tituloTarefa + "\n" + "ToDo: " + this.descricaoTarefa;
    }

}
