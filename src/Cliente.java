/* Clase Cliente: */

public class Cliente {

    // Atributos
    private String nombre;
    private int prioridad;            // 1 = básico, 2 = afiliado, 3 = premium
    private ListaProductos carrito;   

    // Constructores 

    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos(); //  cliente inicia con un carrito vacío
    }

    //  Getters y Setters 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public void setCarrito(ListaProductos carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "nombre='" + nombre + '\''
                + ", prioridad=" + prioridad
                + "}\n";
    }
}
