package com.example.toshibask.myapplicationtiendamovil;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

import Controlador.controladorArchivoUsuarios;
import Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private File archivo = Environment.getExternalStorageDirectory();
    private String ruta = archivo.getAbsolutePath()+File.separator;
    private File file;

    private GoogleApiClient googleApiClient;
    private final int CODERC=9001;
    EditText user;
    EditText pass;
    private controladorArchivoUsuarios registros = new controladorArchivoUsuarios();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)  findViewById(R.id.editTextName);
        pass = (EditText)  findViewById(R.id.editTextPass);
        SignInButton botonDeGoogle = findViewById(R.id.btnGoogle);
        botonDeGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logeoConGoogle();
            }
        });
    }

    public void logeoConGoogle(){
        if (googleApiClient != null)
            googleApiClient.disconnect();

        //Solicitar Correo Inicio de Sesion
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Comprobar Cliente con Logeo
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
        //Abrir ventana de Google
        Intent signIntent = Auth
                .GoogleSignInApi
                .getSignInIntent(googleApiClient);

        startActivityForResult(signIntent,CODERC);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODERC) {
            GoogleSignInResult result = Auth //Resultado de la Sesion
                    .GoogleSignInApi
                    .getSignInResultFromIntent(data);
            if (result.isSuccess()){

                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                GoogleSignInAccount acc=result.getSignInAccount();
                String token = acc.getIdToken();
                intent.putExtra("idUser",acc.getDisplayName());
                intent.putExtra("idPass",acc.getEmail());
                startActivity(intent);
                if (token != null){
                    Toast.makeText(this,token,Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this,"Bienvenido",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openRegistro(View view){
        Intent intent = new Intent(getApplicationContext(),RegistroUsuarioActivity.class);
        startActivity(intent);
    }

    public void openMenu(View view){
        if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
        }
        else {
            existeArchivo("UsuariosRegistrados.txt");
        }
    }

    public void existeArchivo(String nombreArchivo){
        if(registros.leerArchivoUsuarios(nombreArchivo).containsKey(user.getText().toString())) {
            Usuario usuarioRegistrado = (Usuario) registros.leerArchivoUsuarios(nombreArchivo).get(user.getText().toString());
            if (pass.getText().toString().equals(usuarioRegistrado.getPassword())) {
                file = new File(ruta + nombreArchivo);
                if (file.exists()) {
                    Intent intents = new Intent(getApplicationContext(), MenuActivity.class);
                    intents.putExtra("idUser", user.getText().toString());
                    intents.putExtra("idPass", pass.getText().toString());
                    startActivity(intents);
                } else {
                    registros = (controladorArchivoUsuarios) getIntent().getExtras().getSerializable("id");
                    Intent intents = new Intent(getApplicationContext(), MenuActivity.class);
                    intents.putExtra("idUser", user.getText().toString());
                    intents.putExtra("idPass", pass.getText().toString());
                    startActivity(intents);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Password Incorrecto", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Usuario No Registrado", Toast.LENGTH_LONG).show();
        }
    }




}
