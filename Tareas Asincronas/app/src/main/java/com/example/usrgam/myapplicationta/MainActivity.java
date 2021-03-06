package com.example.usrgam.myapplicationta;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnEjecutar;
    private ProgressBar progressBar;
    TareaAsincrona tareaAsincrona = new TareaAsincrona();
    //TareaAsyncDialogo tareaAsyncDialogo = new TareaAsyncDialogo();
    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEjecutar = (Button) findViewById(R.id.buttonEjecutar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void btnEjecutar(View view){
        progressBar.setMax(100);
        progressBar.setProgress(0);
        for (int i=0;i<=100;i++){
            ejecutarTarea();
            progressBar.incrementProgressBy(1);
        }
        Toast.makeText(MainActivity.this, "Finalizo",Toast.LENGTH_LONG).show();
    }

    public void btnEjecutarHilo(final View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Accion
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(0);
                            }
                        });
                    }
                });

                //Aqui
                for (int i=0;i<=100;i++){
                    ejecutarTarea();
                    progressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.incrementProgressBy(1);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Finalizo",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }

    public void ejecutarTarea(){
        try{
            Thread.sleep(175);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void Asincrono(View view) {
        tareaAsincrona.execute();
    }

    public void Cancelar(View view) {
        tareaAsincrona.cancel(true);
    }

    /*public void Dialogo(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Conectando...");
        progressDialog.setCancelable(true);
        progressDialog.setMax(100);


        //tareaAsyncDialogo.execute();

    }*/

    public class TareaAsincrona extends AsyncTask<Void,Integer,Boolean>{
            @Override
            protected Boolean doInBackground(Void... voids) {
                for(int i=1;i<=100;i++){
                    ejecutarTarea();
                    publishProgress(i);
                    if(isCancelled()){
                        //aqui en vez de un break un intent o un dialogo
                        break;
                    }
                }
                return true;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                int progreso=values[0].intValue();
                progressBar.setProgress(progreso);

            }

            @Override
            protected void onPreExecute() {
                progressBar.setMax(100);
                progressBar.setProgress(0);

            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(MainActivity.this,"FinalizoHiloK",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onCancelled() {
                Toast.makeText(MainActivity.this,"Se Cancelo",Toast.LENGTH_LONG).show();
            }
        }

    /*public class TareaAsyncDialogo extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i =1;i<=100;i++) {
                ejecutarTarea();
                publishProgress(i);
                if (isCancelled()){
                    break;
                }
            }

            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            progressDialog.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    TareaAsyncDialogo.this.cancel(true);
                }
            });
            progressDialog.setProgress(0);
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Finalizo",Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this,"Cancelado",Toast.LENGTH_LONG).show();

        }
    }*/

}
