package Control;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Modelo.Usuario;

public class ControlArchivoObjeto {
    private File file = Environment.getExternalStorageDirectory();
    private String ruta = file.getAbsolutePath() + File.separator;

    public void escribirArchivo(Usuario p, String nombre) {
        try {
            FileOutputStream fos = new FileOutputStream(ruta + nombre,true);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(p);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("error", e.toString());
        } catch (IOException e) {
            Log.e("error", e.toString());
        }
    }

    public void escribirArchivoArrayList(ArrayList<Usuario> arrayListProductos, String nombre) {
        try {
            FileOutputStream fos = new FileOutputStream(ruta + nombre,true);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(arrayListProductos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Error Archivo", e.toString());
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
    }

    public Usuario leerArchivo(String nombre) {
        Usuario p = null;
        try {
            FileInputStream fis = new FileInputStream(ruta + nombre);
            ObjectInputStream in = new ObjectInputStream(fis);
            p = (Usuario) in.readObject();
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e("Error Archivo", e.toString());
        } catch (IOException e) {
            Log.e("Error IO", e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("Error Persona", e.toString());
        }
        return p;
    }

    public List<Usuario> leerArchivoArrayList(String nombre) {
        //ArrayList<Producto> objectsList = new ArrayList<Producto>();
        List<Usuario> objectsList=null;
        try {
            FileInputStream fis = new FileInputStream(ruta + nombre);
            ObjectInputStream in = new ObjectInputStream(fis);
            objectsList = ((List<Usuario>)in.readObject());
            in.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e("Error Archivo", e.toString());
        } catch (IOException e) {
            Log.e("Error IO", e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("Error Persona", e.toString());
        }
        return  objectsList;
    }
}