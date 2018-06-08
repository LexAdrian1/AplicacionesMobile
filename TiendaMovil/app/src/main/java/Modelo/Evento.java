package Modelo;

import java.io.Serializable;

public class Evento implements Serializable{
    String nombre,lugar,fecha,codigo;
    Evento misEventos [];

    public Evento() {}

    public Evento(String nombre, String lugar, String fecha, String codigo) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.lugar+ " "+ this.fecha ;
    }

    public Evento [] cargarEventos(){
        misEventos = new Evento[] {
                new Evento("Exposicion Arte","Casa de la Cultura", "07/06/2018","001"),
                new Evento("Exposicion Musica","Casa de la Cultura", "08/06/2018","002"),
                new Evento("Exposicion Juegos","Casa de la Cultura", "09/06/2018","003"),
                new Evento("Exposicion Anime","Casa de la Cultura", "10/06/2018","004"),
                new Evento("Exposicion Cultura","Casa de la Cultura", "12/06/2018","005"),

        };
        return misEventos;
    }

}
