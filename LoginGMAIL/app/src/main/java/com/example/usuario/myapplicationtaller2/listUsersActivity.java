package com.example.usuario.myapplicationtaller2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Control.ControlArchivoObjeto;
import Modelo.Usuario;

public class ListUsersActivity extends AppCompatActivity {
    ListView listUsers;

    ControlArchivoObjeto controladorArchivo = new ControlArchivoObjeto();
    ArrayAdapter<Usuario> adapterUsers;
    List<Usuario> your_array_list = new ArrayList<Usuario>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        listUsers = (ListView)findViewById(R.id.listUsers);

        controladorArchivo.leerArchivoList("Usuarios.txt");
        //your_array_list=controladorArchivo.leerArchivoList("Usuarios.txt");
        /*adapterUsers = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, your_array_list);
        listUsers.setAdapter(adapterUsers);*/
    }
}
