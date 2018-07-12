package com.example.usrgam.myapplicationnotificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificacion();
    }

    public void notificacion(){
        NotificationCompat.Builder mBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.alert_light_frame)
                .setContentTitle("Titulo del Mensaje")
                .setContentText("Cuerpo del Mensaje")
                .setTicker("Alerta")
                .setTicker("Alerta!");

        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,mBuilder.build()); // Es el ID del mensaje, deseamos cargando solo ponemos eso


    }


}

