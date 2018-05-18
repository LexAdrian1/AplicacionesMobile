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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Modelo.Usuario;

public class ControlArchivoObjeto {
    private File file = Environment.getExternalStorageDirectory();
    private String ruta = file.getAbsolutePath() + File.separator;

    public void escribirArchivoArrayList(ArrayList<Usuario> arrayListProductos, String nombre) {
        try {
            FileOutputStream fos = new FileOutputStream(ruta + nombre);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(arrayListProductos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("Error Archivo", e.toString());
        } catch (IOException e) {
            Log.e("Error", e.toString());
        }
    }

    public List<Usuario> leerArchivoArrayList(String nombre) {
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