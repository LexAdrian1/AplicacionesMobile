package Controlador;

import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Modelo.Evento;
import Modelo.Usuario;

public class controladorArchivoUsuarios implements Serializable {
    private File archivo = Environment.getExternalStorageDirectory();
    private String ruta = archivo.getAbsolutePath()+File.separator;
    private File file;

    ListView miLista;
    ArrayAdapter<Evento> adapter;
    ArrayList<Evento> misEventos;


    public void creacionArchivoUsuarios(Usuario user, String nombreArchivos ){
        file = new File(ruta+nombreArchivos);
        if (file.exists()){
            HashMap<String,Object> miListaUsuarios = new HashMap<String, Object>();
            miListaUsuarios = leerArchivoUsuarios(nombreArchivos);
            miListaUsuarios.put(user.getCorreo(),user);
            escribirArchivoUsuarios(miListaUsuarios,nombreArchivos);
        }else{
            try {
                if (file.createNewFile()){
                    escribirArchivoUsuarios(misUsuarios(user),nombreArchivos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void creacionArchivoEventos(Evento user, String nombreArchivos ){
        file = new File(ruta+nombreArchivos);
        if (file.exists()){
            HashMap<String,Object> miListaUsuarios = new HashMap<String, Object>();
            miListaUsuarios = leerArchivoUsuarios(nombreArchivos);
            miListaUsuarios.put(user.getCodigo(),user);
            escribirArchivoUsuarios(miListaUsuarios,nombreArchivos);
        }else{
            try {
                if (file.createNewFile()){
                    escribirArchivoUsuarios(misEventos(user),nombreArchivos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String,Object> misUsuarios(Usuario usuario){
        HashMap<String,Object> miListaUsuarios=new HashMap();
        miListaUsuarios.put(usuario.getCorreo(),usuario);
        return miListaUsuarios;
    }

    public HashMap<String,Object> misEventos(Evento usuario){
        HashMap<String,Object> miListaEventos=new HashMap();
        miListaEventos.put(usuario.getCodigo(),usuario);
        return miListaEventos;
    }
    public void escribirArchivoUsuarios(HashMap<String,Object> misUsers, String nombreArchivos){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ruta+nombreArchivos);
            out = new ObjectOutputStream(fos);
            out.writeObject(misUsers);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        }
    }


    public HashMap<String,Object> leerArchivoUsuarios(String nombreArchivo) {
        HashMap<String,Object> misUsuarios = new HashMap<String, Object>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta + nombreArchivo);
            in = new ObjectInputStream(fis);
            misUsuarios = (HashMap<String, Object>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo", e.toString());
        } catch (IOException e) {
            Log.e("error io", e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return misUsuarios;
    }

    public HashMap<String,Object> leerArchivoEventos(String nombreArchivo) {
        HashMap<String,Object> misEventos = new HashMap<String, Object>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta + nombreArchivo);
            in = new ObjectInputStream(fis);
            misEventos = (HashMap<String, Object>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo", e.toString());
        } catch (IOException e) {
            Log.e("error io", e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return misEventos;
    }
}

