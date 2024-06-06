package com.example.meuprojeto;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBd {
    private static FirebaseAuth firebaseAuth;

    // Método estático para obter a instância de autenticação do Firebase
    public static FirebaseAuth Fireautenticacao() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
}
