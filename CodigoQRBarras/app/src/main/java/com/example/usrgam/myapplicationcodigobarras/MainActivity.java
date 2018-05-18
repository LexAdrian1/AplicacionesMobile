package com.example.usrgam.myapplicationcodigobarras;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUESTCAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camID = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //verificarPermisos
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);//Poner la camara en todo el view
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){//SDK que corre tu android debe superior a N que es 24
            if(verificarPermisos()){
                //Mensaje
            }else{
                //SolicitarPermisos()
                Toast.makeText(getApplicationContext(),"Permiso Denegado",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean verificarPermisos(){
        //Contexto de Aplicacion
        return ContextCompat.checkSelfPermission(getApplicationContext(),CAMERA_SERVICE) == PackageManager.PERMISSION_GRANTED;
    }

    public void solicitarPermisos(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUESTCAMERA);
    }

    @Override
    public void onResume() {//Reanudar desde el activity
        super.onResume();
        int apiVersion=Build.VERSION.SDK_INT;
        if(apiVersion >= Build.VERSION_CODES.M){
            if(verificarPermisos()){
                if(scannerView==null){
                    scannerView=new ZXingScannerView(this);
                }
            }
            scannerView.setResultHandler(this);
            scannerView.startCamera();
        }else{
            solicitarPermisos();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Varios Permisos
        switch(requestCode){
            case REQUESTCAMERA:
                if(grantResults.length>0){
                    boolean aceptaPermiso = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(aceptaPermiso){
                        //Mensaje
                    }else{
                        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                                //No dio Permisos
                                requestPermissions(new String[]{Manifest.permission.CAMERA},REQUESTCAMERA);
                                onResume();
                            }
                        }
                    }
                }
        }
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(result.getText());
        alertDialog.show();
        Log.e("Resultado",result.getText());
        Log.e("ResultadoBar",result.getBarcodeFormat().toString());
    }
}
