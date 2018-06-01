package com.example.usrgam.dialogos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrar(View view){
        //dialogoAlert();
        //dialogoLista();
        //dialogoSingleChoose();
        //dialogoMultiChoose();
        dialogoPersonalizado();
    }


    public void dialogoAlert(){
        AlertDialog.Builder alertdialogo = new AlertDialog.Builder(this);

        alertdialogo.setTitle("Mensaje - Titulo");
        alertdialogo.setMessage("cuerpo del mensaje");

        //int i valor del boton -1,0,1
        alertdialogo.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste si",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste no",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setCancelable(true); //true se da la flecha de atras la,  // false obli9garcion a dar tap al boton

        alertdialogo.create();
        alertdialogo.show();



    }


    //
    public void dialogoLista(){
      final CharSequence[] items = {"ar1","ar2","ar3","ar4"};
        AlertDialog.Builder alertdialogo = new AlertDialog.Builder(this);

        alertdialogo.setTitle("Mensaje - Titulo");
        alertdialogo.setMessage("cuerpo del mensaje");

        alertdialogo.setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setCancelable(true); //true se da la flecha de atras la,  // false obli9garcion a dar tap al boton


        //evento al dar tap a un elementode la lista
        alertdialogo.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.create();
        alertdialogo.show();

    }


    public void dialogoSingleChoose(){
        final CharSequence[] items = {"ar1","ar2","ar3","ar4"};
        AlertDialog.Builder alertdialogo = new AlertDialog.Builder(this);

        alertdialogo.setTitle("Mensaje - Titulo");
        alertdialogo.setMessage("cuerpo del mensaje");

        //int i valor del boton -1,0,1
        alertdialogo.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste si",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste no",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setCancelable(true); //true se da la flecha de atras la,  // false obli9garcion a dar tap al boton

        alertdialogo.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),items[which],Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.create();
        alertdialogo.show();
    }

    public void dialogoMultiChoose(){
        final CharSequence[] items = {"ar1","ar2","ar3","ar4"};
        final ArrayList selecionados = new ArrayList();

        AlertDialog.Builder alertdialogo = new AlertDialog.Builder(this);

        alertdialogo.setTitle("Mensaje - Titulo");
        alertdialogo.setMessage("cuerpo del mensaje");

        //int i valor del boton -1,0,1
        alertdialogo.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String salida="";
                for (int i=0;i<selecionados.size();i++){
                    salida+=items[Integer.parseInt(selecionados.get(i).toString())].toString();
                }
                Toast.makeText(getApplicationContext(),salida,Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste no",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setNegativeButton("Cancelar ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"diste cancelar",Toast.LENGTH_LONG).show();
            }
        });

        alertdialogo.setCancelable(true);   //true se da la flecha de atras la,  // false obli9garcion a dar tap al boton

        alertdialogo.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    selecionados.add(isChecked);

                }else {
                    selecionados.remove(isChecked);
                }
            }
        });

        alertdialogo.create();
        alertdialogo.show();


    }


    public void dialogoPersonalizado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater =  this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogo_personalizado,null));

        
        builder.setCancelable(true);
        builder.create();
        builder.show();

    }


}
