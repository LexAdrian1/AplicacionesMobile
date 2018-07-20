package com.example.usrgam.mapas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleMap.OnPolygonClickListener,GoogleMap.OnPolylineClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private GoogleMap mMap;


    public MapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getMapAsync(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //progamar permisos control de mapa

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},177);
            }

            return;
        }else{
            mMap.setMyLocationEnabled(true);
            dibujar();
            agregarMarcacion(0.206361,-78.491336);
            moverCamara(-0.206361,-78.491335);
            mMap.setOnPolygonClickListener(this);

            mMap.getUiSettings().setAllGesturesEnabled(false);

            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
    }



    public void agregarMarcacion(double lat, double lng){
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).title("Mi marcacion").title("Mi Marcacion").snippet("Contenido")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

    }
    public void moverCamara (double lat, double lng){
        CameraPosition cameraPosition=CameraPosition.builder().target(new LatLng(lat,lng)).zoom(16).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    public void dibujar(){
        Polygon polyline = mMap.addPolygon(new PolygonOptions()
        .clickable(true)
        .add(new LatLng(-0.2099507,-78.4908745),
                new LatLng(-0.208528, -78.485771),
                new LatLng(-0.212230, -78.489532),
                new LatLng(-0.210159, -78.493577),
                new LatLng(-0.2099507,-78.4908745))
        .fillColor(Color.RED)
        .strokeColor(Color.BLUE)
        );
        polyline.setTag("ID:001:EPN - Aplicaciones Moviles");
       //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-0.2099507,-78.4908745),16));
    }




    @Override
    public void onPolygonClick(Polygon polygon) {
        Toast.makeText(getContext(),polygon.getTag()+polygon.getId(),Toast.LENGTH_LONG).show();
        //polygon.getTag();


    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
