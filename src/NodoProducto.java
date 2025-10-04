// Clase NodoProducto
public class NodoProducto {

    // Atributos
    private Producto dato;          
    private NodoProducto siguiente; 

    // Constructor
    public NodoProducto(Producto dato) {
        this.dato = dato;
        this.siguiente = null; 
    }

    // Getter y Setter
    public Producto getDato() {
        return dato;
    }

    public void setDato(Producto dato) {
        this.dato = dato;
    }

    // Getter y Setter 
    public NodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}
