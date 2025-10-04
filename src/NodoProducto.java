
/*
 * Clase NodoProducto:
 * Representa un nodo de la lista enlazada simple.
 * Cada nodo guarda un Producto y una referencia al siguiente nodo.
 */
public class NodoProducto {

    // Atributos
    private Producto dato;          // aquí se guarda el producto
    private NodoProducto siguiente; // referencia al siguiente nodo

    // Constructor
    public NodoProducto(Producto dato) {
        this.dato = dato;
        this.siguiente = null; // al crear el nodo, aún no apunta a nadie
    }

    // Getter y Setter para el producto
    public Producto getDato() {
        return dato;
    }

    public void setDato(Producto dato) {
        this.dato = dato;
    }

    // Getter y Setter para el siguiente nodo
    public NodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoProducto siguiente) {
        this.siguiente = siguiente;
    }
}
