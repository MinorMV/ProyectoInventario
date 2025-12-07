//Clase Cliente

public class Cliente {

    // Atributos
    private String nombre;
    private int prioridad; // 1 = básico, 2 = afiliado, 3 = premium
    private ListaProductos carrito;
    private String ubicacion;

    // Constructores

    public Cliente(String nombre, int prioridad) {
        this(nombre, prioridad, "Sin ubicación");
    }

    // Constructor con ubicacion
    public Cliente(String nombre, int prioridad, String ubicacion) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
        this.ubicacion = ubicacion;
    }

    // Getters y Setters

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

    // Ubicacion de cada cliente

    public String getUbicacion() {
        return ubicacion;
    }

    // Modificar ubicacion
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "nombre='" + nombre + '\''
                + ", prioridad=" + prioridad
                + ", ubicacion='" + ubicacion + '\''
                + "}\n";
    }
}
