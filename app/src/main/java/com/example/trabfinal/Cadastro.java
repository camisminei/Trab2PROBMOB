package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cadastro extends AppCompatActivity {
    private EditText edtEmail, edtTelefone, edtSenha, edtNome;
    Usuario user, altUser;
    DBToDoHelper userHelper;
    private Button btnVariavel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtSenha = findViewById(R.id.edtSenha);
        Intent it = getIntent();
        altUser = (Usuario) it.getSerializableExtra("ch_user");
        user = new Usuario();
        userHelper = new DBToDoHelper(Cadastro.this);
        btnVariavel = findViewById(R.id.bttnCadastrar);

        if (altUser != null) {
            btnVariavel.setText("Alterar");
            edtNome.setText(altUser.getNome());
            edtEmail.setText(altUser.getEmail());
            edtTelefone.setText(altUser.getTelefone());
            edtSenha.setText(altUser.getSenha());
            user.setId(altUser.getIdUsuario());
        } else {
            btnVariavel.setText("Cadastrar");
        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String telefone = edtTelefone.getText().toString();
                String senha = edtSenha.getText().toString();
                long retornoDB = -1;
                Usuario u = new Usuario();
                u.setNome(nome);
                u.setEmail(email);
                u.setTelefone(telefone);
                u.setSenha(senha);

                if (btnVariavel.getText().toString().equals("Cadastrar")) {

                    try {
                        retornoDB = userHelper.insertUser(u);
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }

                    if (retornoDB == -1) {
                        Toast.makeText(Cadastro.this, "Erro ao cadastrar",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Cadastro.this, "Cadastro Realizado com Sucesso",
                                Toast.LENGTH_SHORT).show();
                        userHelper.close();
                        addFoto();
                        finish();
                    }
               } else {

                    try {
                        userHelper.updateUsuario(user);
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }

                    userHelper.close();
                    finish();
            }
        }});

    }


    public void voltaInicio(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void addFoto() {
        Intent intent = new Intent(this, FotoActivity.class);
        startActivity(intent);
    }


}
