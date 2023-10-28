package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView messageTextView;
    private EditText messageEditText;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextView = findViewById(R.id.messageTextView);
        messageEditText = findViewById(R.id.editTextText);
        Button sendButton = findViewById(R.id.button);

        // Definir el filtro de intención para recibir transmisiones específicas
        IntentFilter filter = new IntentFilter("com.example.APP_ACTION");

        // Crear el receptor de transmisiones
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra("message");
                messageEditText.setText(message);
            }
        };

        // Registrar el receptor
        registerReceiver(receiver, filter);

        // Configurar el boton
        sendButton.setOnClickListener(view -> {
            String message = messageEditText.getText().toString();

            Intent intent = new Intent("com.example.APP_ACTION");
            intent.putExtra("message", message);

            // Enviar
            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desregistrar
        unregisterReceiver(receiver);
    }
}