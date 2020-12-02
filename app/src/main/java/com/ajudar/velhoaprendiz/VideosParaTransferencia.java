package com.ajudar.velhoaprendiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class VideosParaTransferencia extends AppCompatActivity {

    private ImageView iconeSair, iconeAjuda, telaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_para_transferencia);

        iconeSair = findViewById(R.id.iconeSair);
        iconeAjuda = findViewById(R.id.iconeAjuda);
        telaPrincipal = findViewById(R.id.iconeTelaPrincipal);




        telaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        iconeSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(VideosParaTransferencia.this, "Você saiu!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Entrar.class));
                finish();
            }
        });


        iconeAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg = new AlertDialog.Builder(VideosParaTransferencia.this);
                msg.setTitle("Ajuda");
                msg.setIcon(android.R.drawable.ic_menu_help);
                msg.setMessage(" Neste aplicativo você terá vídeos explicativos sobre o que você deseja fazer.\n" +
                        " \n Aprenda a pagar boletos, realizar transferências, etc.\n" +
                        "\n Quando acessar o que quer aprender, terá videos de diferentes instituições bancárias. \n" +
                        "\n Escolha o video de acordo com seu banco.");
                msg.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeContextMenu();
                    }
                });
                msg.show();
            }
        });
    }
}
