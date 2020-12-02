package com.ajudar.velhoaprendiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    EditText nomeCadastro, emailCadastro, senhaCadastro;
    Button botaoCadastrar;
    TextView botaoEntreAqui;

    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCadastro = findViewById(R.id.nomeCadastro);
        emailCadastro = findViewById(R.id.emailCadastro);
        senhaCadastro = findViewById(R.id.senhaCadastro);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);
        botaoEntreAqui = findViewById(R.id.botaoEntreAqui);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailCadastro.getText().toString().trim();
                String senha = senhaCadastro.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailCadastro.setError("Campo obrigatório!");
                    return;
                }
                if(TextUtils.isEmpty(senha)){
                    senhaCadastro.setError("Campo obrigatório!");
                    return;
                }

                if(senha.length() < 6){
                    senhaCadastro.setError("A senha deve ter 6 ou mais digitos!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //Registrar o usuario no firebase
                fAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(Cadastro.this, "Usuario Cadastrado", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       }else{
                           Toast.makeText(Cadastro.this, "Erro! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }
                    }
                });

            }
        });

        botaoEntreAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Entrar.class));
            }
        });

    }
}
