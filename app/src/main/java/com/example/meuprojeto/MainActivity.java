package com.example.meuprojeto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat.Type;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao; // Objeto FirebaseAuth para autenticação com Firebase
    private EditText campoNome, campoEmail, campoSenha, campoPhone; // Campos de entrada para nome, email e senha
    private Button botaoCadastrar; // Botão para acionar o cadastro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        autenticacao = FirebaseAuth.getInstance();

        inicializar(); // Inicializa os elementos da interface

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos(view);
            }
        });
    }

    // Inicializa os elementos da interface com seus respectivos IDs
    private void inicializar() {
        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.TextLogin);
        campoSenha = findViewById(R.id.textSenha);
        campoPhone = findViewById(R.id.textPhone);
        botaoCadastrar = findViewById(R.id.buttonAcessar);
    }

    // Valida os campos de entrada e, se válidos, chama o método para cadastrar o usuário
    private void validarCampos(View view) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String phone = campoPhone.getText().toString();
        String pass = campoSenha.getText().toString();

        if (!nome.isEmpty()) { // Verifica se o campo nome não está vazio
            if (!email.isEmpty()) { // Verifica se o campo email não está vazio
                if (!pass.isEmpty()) { // Verifica se o campo senha não está vazio

                    // Chama o método para cadastrar o usuário
                    cadastrarUsuario(nome, email, phone, pass);

                } else {
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo senha estiver vazio
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo email estiver vazio
            }
        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo nome estiver vazio
        }
    }

    // Método para cadastrar o usuário utilizando Firebase Authentication
    private void cadastrarUsuario(String nome, String email, String phone, String pass) {
        autenticacao.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sucesso ao Cadastrar o usuário", Toast.LENGTH_SHORT).show(); // Exibe mensagem de sucesso
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao cadastrar usuário: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Exibe mensagem de erro
                        }
                    }
                });
    }
}
