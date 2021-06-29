package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLogin;
    DBToDoHelper userHelper;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginSenha);
        btnLogin = findViewById(R.id.bttnLogin);
        userHelper = new DBToDoHelper(Login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtLoginEmail.getText().toString();
                String pass = edtLoginPassword.getText().toString();
                String password = userHelper.buscaSenha(email);
                userHelper.close();
                if(password.equals(pass)) {
                    Intent it = new Intent(Login.this, TarefasActivity.class);
                    startActivity(it);
                } else if(password.equals("Não encontrado")) {
                    Toast toast = Toast.makeText(Login.this, "Usuário não encontrado", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(Login.this, "Senha incorreta", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    }
