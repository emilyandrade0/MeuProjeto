package com.example.meuprojeto;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Index extends AppCompatActivity {

    private EditText entradaDeTexto;
    private ListView ListarProdutos;
    private ArrayAdapter<String> adaptar;
    private List<String> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        entradaDeTexto = findViewById(R.id.entradaDeTexto);
        ListarProdutos = findViewById(R.id.ListarProdutos);

        // Lista de produtos de exemplo
        produtos = new ArrayList<>();
        produtos.add("Produto 1");
        produtos.add("Produto 2");
        produtos.add("Produto 3");
        produtos.add("Produto 4");
        produtos.add("Produto 5");

        adaptar = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);
        ListarProdutos.setAdapter(adaptar);

        // Listar para buscar produtos enquanto digita
        entradaDeTexto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Não utilizado
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Index.this.adaptar.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Não utilizado
            }
        });
    }
}