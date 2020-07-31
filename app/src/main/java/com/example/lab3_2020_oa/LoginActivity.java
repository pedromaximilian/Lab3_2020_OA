package com.example.lab3_2020_oa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText etMail, etPass;
    Button btnIngresar;
    //GeneralData uData;
    TextView tvError;
    LoginViewModel loginVM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configView();
    }

    public void configView(){

        loginVM= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        tvError =findViewById(R.id.tvError);
        //uData = new GeneralData();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                loginVM.validar(etMail.getText().toString(), etPass.getText().toString());
            }
        });

        final Observer<String> errorObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvError.setText(s);
            }
        };
        loginVM.getError().observe(this, errorObserver);

        /*////
        final Observer<Usuario> usuarioObserver = new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                tvError.setText("Iniciando sesion...");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mail", usuario.getMail());
                intent.putExtra("nombre", usuario.getNombre());
                startActivity(intent);
            }
        };
        */



        loginVM.getExito().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvError.setText("Iniciando sesion...");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("exito", s);
                startActivity(intent);
            }
        });
    }
}
