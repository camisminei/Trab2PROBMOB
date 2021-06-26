package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }

    public void conectar(View view) {

        String email = edtLoginEmail.getText().toString();
        String pass = edtLoginPassword.getText().toString();
        //implementar busca de senha
        /* String password = userHelper.buscarSenha(email);
        if(password.equals(pass)) {
            Intent it = new Intent(this, classeDpsDeLogar.class);
            startActivity(it);
        } else {

            Toast toast = Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_LONG);
            toast.show();
        }
         */
    }






}
