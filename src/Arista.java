//Clase Arista

public class Arista {

    // Atributos
    private String destino;
    private int peso;

    // Constructor

    public Arista(String destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    // Getters y Setters

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
