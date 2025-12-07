//Clase Vertice

public class Vertice implements Comparable<Vertice> {

    // Atributos
    private String nombre;
    private int distancia;

    // Constructor

    public Vertice(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    // Comparar por distancia 

    @Override
    public int compareTo(Vertice otro) {
        return Integer.compare(this.distancia, otro.distancia);
    }
}
