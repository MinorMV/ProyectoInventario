
import java.util.ArrayList;

/* Clase Producto:*/

public class Producto {

    // Atributos 
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento; 
    private int cantidad;            

 
    private ArrayList<String> listaImagenes;

    // --- Constructores ---

    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento; 
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
    }


    public Producto(String nombre, double precio, String categoria, int cantidad) {
        this(nombre, precio, categoria, "", cantidad);
    }

    // --- Getters y Setters 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }


    // Agregar una imagen a la lista 
    public void agregarImagen(String ruta) {
        listaImagenes.add(ruta);
    }

    public double getCostoTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
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
