package com.example.usuario.myapplicationtaller2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import Control.ControlArchivoObjeto;
import Modelo.Usuario;

public class MainActivity extends AppCompatActivity {
    Button registrarse;
    SignInButton signInGoogle;
    Button ingreso;
    TextView mail;
    TextView pass;
    ControlArchivoObjeto controladorArchivo = new ControlArchivoObjeto();
    List<Usuario> your_array_list = new ArrayList<Usuario>();

    //Crear un cliente del API de google
    //Codigo de Respuesta es 9001
    private GoogleApiClient googleApiClient;
    private final int CODERC=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrarse = (Button)findViewById(R.id.buttonRegistrarse);
        ingreso = (Button)findViewById(R.id.buttonInicio);
        mail = (TextView) findViewById(R.id.editTextMail);
        pass = (TextView) findViewById(R.id.editTextPass);
        signInGoogle = (SignInButton) findViewById(R.id.buttonSignInGoogle);
        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logeoGmail();
            }
        });
    }

    public void openRegistro(View view){
        Intent intents = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(intents);
    }

    public void openList(View view){

        your_array_list=controladorArchivo.leerArchivoArrayList("Usuarios.txt");
        for(Usuario d : your_array_list){
            if(d.getMail() != null && d.getMail().contains(mail.getText().toString())){
                Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
                Intent intents = new Intent(getApplicationContext(), ListUsersActivity.class);
                startActivity(intents);
            }
        }
    }

    public void logeoGmail(){
        if (googleApiClient != null) {
            //Desconectado
            googleApiClient.disconnect();
        }
        //Solicitar correo para Iniciar Sesion
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        //Igualar al Cliente con el Logeo
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        //Abrir ventana de google
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signIntent,CODERC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODERC) {
            GoogleSignInResult result = Auth .GoogleSignInApi.getSignInResultFromIntent(data);//Resultado de la Sesion
            if (result.isSuccess()){
                GoogleSignInAccount acc=result.getSignInAccount();
                String token = acc.getIdToken();
                Log.i("Correo",acc.getEmail());
                Log.i("Nombre",acc.getDisplayName());
                Log.i("ID",acc.getId());
                Intent intents = new Intent(getApplicationContext(), ListUsersActivity.class);
                startActivity(intents);
                if (token != null){
                    Toast.makeText(this,token,Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
