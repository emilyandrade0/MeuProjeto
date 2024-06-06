package com.example.meuprojeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class telaLogin extends AppCompatActivity {

    EditText campoEmail, campoSenha; // Campos de entrada para email e senha
    Button botaoAcesso; // Botão de acesso
    private FirebaseAuth auth; // Instância do FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login); // Define o layout da atividade

        auth = FirebaseAuth.getInstance(); // Obtém a instância do FirebaseAuth
        inicializarComponente(); // Inicializa os componentes da interface

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Ajusta o padding para suportar áreas de recorte da tela
            return insets;
        });
    }

    // Método para validar a autenticação do usuário
    public void validarAutenticacao(View view) {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty()) { // Verifica se o campo email não está vazio
            if (!senha.isEmpty()) { // Verifica se o campo senha não está vazio
                logar(email, senha); // Chama o método para logar o usuário
            } else {
                Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo senha estiver vazio
            }
        } else {
            Toast.makeText(this, "Preencha o e-mail", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo email estiver vazio
        }
    }

    // Método para logar o usuário utilizando Firebase Authentication
    private void logar(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        abrirHome(); // Se o login for bem-sucedido, abre a tela principal
                    } else {
                        Toast.makeText(telaLogin.this, "Erro ao logar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Exibe a mensagem de erro
                    }
                });
    }

    // Método para abrir a tela principal
    private void abrirHome() {
        Intent intent = new Intent(telaLogin.this, MainActivity.class);
        startActivity(intent);
        finish(); // Fecha a tela de login para evitar que o usuário volte para ela pressionando o botão Voltar
    }

    // Método para inicializar os componentes da interface
    private void inicializarComponente() {
        campoEmail = findViewById(R.id.TextLogin);
        campoSenha = findViewById(R.id.textSenha);
        botaoAcesso = findViewById(R.id.buttonAcessar);
    }
}
