package com.example.toshibask.myapplicationtiendamovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Modelo.Evento;

public class ListaActivity extends AppCompatActivity {

    ListView miLista;
    ArrayAdapter<Evento> adapter;
    ArrayList<Evento> misEventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }
}
