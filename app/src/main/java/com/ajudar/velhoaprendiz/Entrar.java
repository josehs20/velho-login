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

public class Entrar extends AppCompatActivity {

    EditText emailEntrar, senhaEntrar;
    Button botaoEntrar;
    TextView cadastrarAqui;

    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        emailEntrar = findViewById(R.id.emailEntrar);
        senhaEntrar = findViewById(R.id.senhaEntrar);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        botaoEntrar = findViewById(R.id.botaoEntrar);
        cadastrarAqui = findViewById(R.id.cadastrarAqui);


        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEntrar.getText().toString().trim();
                String senha = senhaEntrar.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailEntrar.setError("Campo obrigatório!");
                    return;
                }
                if(TextUtils.isEmpty(senha)){
                    senhaEntrar.setError("Campo obrigatório!");
                    return;
                }

                if(senha.length() < 6){
                    senhaEntrar.setError("A senha deve ter 6 ou mais digitos!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Autenticar o usuario
                fAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Entrar.this, "Você entrou!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Entrar.this, "Erro! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        cadastrarAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cadastro.class));
            }
        });
    }
}
