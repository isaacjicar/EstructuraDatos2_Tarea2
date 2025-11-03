package org.example.model;

public class TennisPlayer {
    private String nombre;
    private String pais;
    private int edad;
    private int puntaje;

    public TennisPlayer(String nombre, String pais, int edad, int puntaje) {
        this.nombre = nombre.trim();
        this.pais = pais.trim();
        this.edad = edad;
        this.puntaje = puntaje;
    }
    public String getNombre() { return nombre; }
    public String getPais()    { return pais; }
    public int getEdad()       { return edad; }
    public int getPuntaje()    { return puntaje; }
}
