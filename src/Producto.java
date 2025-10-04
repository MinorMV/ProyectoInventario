
import java.util.ArrayList;

/*
 * Clase Producto:
 * Guarda los datos que pide la consigna: nombre, precio, categoria,
 * fechaVencimiento (si aplica), cantidad y una lista de imágenes (rutas).
 */

public class Producto {

    // Atributos 
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento; 
    private int cantidad;            

    // Aquí guardamos rutas como "imagenes/arroz.png"
    private ArrayList<String> listaImagenes;

    // --- Constructores ---

    // Constructor cuando sí hay fecha de vencimiento
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento; // si no aplica, se puede pasar "" directamente
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
    }

    // Constructor cuando no aplica fecha de vencimiento
    public Producto(String nombre, double precio, String categoria, int cantidad) {
        this(nombre, precio, categoria, "", cantidad);
    }

    // --- Getters y Setters básicos ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // sin validaciones adicionales
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        // sin validaciones adicionales
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        // sin validaciones adicionales
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        // sin validaciones adicionales
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        // sin validaciones adicionales
        this.cantidad = cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    // --- Métodos simples solicitados ---

    // Agrega una imagen a la lista (ruta relativa dentro del proyecto)
    public void agregarImagen(String ruta) {
        listaImagenes.add(ruta);
    }

    // Costo total considerando la cantidad
    public double getCostoTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        // salida sencilla para ver el estado del objeto
        return "Producto{"
                + "nombre='" + nombre + '\''
                + ", precio=" + precio
                + ", categoria='" + categoria + '\''
                + ", fechaVencimiento='" + fechaVencimiento + '\''
                + ", cantidad=" + cantidad
                + ", costoTotal=" + getCostoTotal()
                + ", imagenes=" + listaImagenes
                + "}\n";
    }
}
