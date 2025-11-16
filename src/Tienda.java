/* Clase Tienda: */

public class Tienda {

    // Atributos
    private String nombre;
    private ArbolProductos inventario;   // Árbol de productos
    private ColaClientes colaClientes;   // Cola de prioridad de clientes

    // Constructores 

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    // Getters y Setters 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    // Agregar un producto al inventario (árbol)
    public void agregarProductoAlInventario(Producto p) {
        inventario.insertar(p);
        System.out.println("Producto agregado al inventario de la tienda.\n");
    }

    // Mostrar el inventario 
    public void mostrarInventario() {
        inventario.imprimirInOrden();
    }

    // Buscar un producto en el inventario por nombre
    public Producto buscarProductoEnInventario(String nombreProducto) {
        return inventario.buscar(nombreProducto);
    }

    // Registrar un cliente en la cola de atención
    public void registrarCliente(Cliente c) {
        colaClientes.encolar(c);
    }

    // Mostrar los clientes actualmente en la cola
    public void mostrarColaClientes() {
        colaClientes.mostrarCola();
    }

    // Agregar un producto al carrito de un cliente, a partir del inventario.
    public void agregarProductoAlCarrito(Cliente cliente, String nombreProducto, int cantidad) {
        if (cliente == null) {
            System.out.println("No se recibió un cliente válido.\n");
            return;
        }

        Producto enInventario = inventario.buscar(nombreProducto);

        if (enInventario == null) {
            System.out.println("No se encontró el producto '" + nombreProducto + "' en el inventario.\n");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.\n");
            return;
        }

        if (cantidad > enInventario.getCantidad()) {
            System.out.println("No hay suficientes unidades en inventario. Disponible: "
                    + enInventario.getCantidad() + "\n");
            return;
        }

        // Crear una copia del producto para el carrito con la cantidad solicitada
        Producto enCarrito = new Producto(
                enInventario.getNombre(),
                enInventario.getPrecio(),
                enInventario.getCategoria(),
                enInventario.getFechaVencimiento(),
                cantidad
        );

        // Copiar las imágenes 
        for (String ruta : enInventario.getListaImagenes()) {
            enCarrito.agregarImagen(ruta);
        }

        // Agregar al carrito del cliente 
        cliente.getCarrito().insertarFinal(enCarrito);

        // Descontar del inventario
        enInventario.setCantidad(enInventario.getCantidad() - cantidad);

        System.out.println("Producto agregado al carrito de " + cliente.getNombre() + " correctamente.\n");
    }

    // Atender al siguiente cliente y mostrar factura
    public void atenderSiguienteCliente() {
        Cliente c = colaClientes.atenderSiguiente();
        if (c == null) {
            return; 
        }

        System.out.println("--- FACTURA ---");
        System.out.println("Tienda: " + nombre);
        System.out.println("Cliente: " + c.getNombre()
                + " (prioridad " + c.getPrioridad() + ")");
        System.out.println("\nProductos en el carrito:\n");

        ListaProductos carrito = c.getCarrito();

        if (carrito.estaVacia()) {
            System.out.println("(El cliente no tenía productos en el carrito)\n");
        } else {
            carrito.reporteCarrito();
        }

        System.out.println("-------- FIN DE FACTURA --------\n");
    }
}
