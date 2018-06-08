package com.example.toshibask.myapplicationtiendamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Controlador.controladorArchivoUsuarios;
import Modelo.Usuario;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText correo;
    private EditText password;
    private EditText repeatPassword;
    controladorArchivoUsuarios miregistroUsuario = new controladorArchivoUsuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nombre=(EditText)findViewById(R.id.nombre);
        correo=(EditText)findViewById(R.id.correo);
        password=(EditText)findViewById(R.id.password);
        repeatPassword=(EditText)findViewById(R.id.repeatPassword);
    }

    public void registrarUsuario(View view){
        if(password.getText().toString().equals(repeatPassword.getText().toString())){
            miregistroUsuario.creacionArchivoUsuarios(new Usuario(nombre.getText().toString(),correo.getText().toString(),password.getText().toString()),"UsuariosRegistrados.txt");
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("id",miregistroUsuario);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Contrasenias deben ser Iguales",Toast.LENGTH_SHORT).show();
        }
    }
}
