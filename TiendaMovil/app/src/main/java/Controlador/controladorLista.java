package Controlador;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Modelo.Evento;

public class controladorLista implements Serializable {

    private File archivo = Environment.getExternalStorageDirectory();
    private String ruta = archivo.getAbsolutePath()+File.separator;
    private File file;

    public void creacionArchivoEventos(Evento decks, String nombreArchivos ){
        file = new File(ruta+nombreArchivos);
        if (file.exists()){
            ArrayList<Evento> miListaArticulos = new ArrayList<>();
            escribirArchivoEventos(miListaArticulos,nombreArchivos);
        }else{
            try {
                if (file.createNewFile()){
                    //escribirArchivoEventos(escribirArchivoEventos(eventos),nombreArchivos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void escribirArchivoEventos(ArrayList<Evento> misEventos, String nombreArchivos){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(ruta+nombreArchivos);
            out = new ObjectOutputStream(fos);
            out.writeObject(misEventos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        }
    }

    public ArrayList<Evento> leerArchivoEventos(String nombreArchivo){
        ArrayList<Evento> misEventos=new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(ruta+nombreArchivo);
            in = new ObjectInputStream(fis);
            misEventos = (ArrayList<Evento>) in.readObject();
        } catch (FileNotFoundException e) {
            Log.e("error archivo",e.toString());
        } catch (IOException e) {
            Log.e("error io",e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("error de lista",e.toString());
        }
        return misEventos;
    }
}
