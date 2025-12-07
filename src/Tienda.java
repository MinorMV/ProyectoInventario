//Clase Tienda

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tienda {

    // Atributos
    private String nombre;
    private ArbolProductos inventario;   
    private ColaClientes colaClientes;   

    // Ubicacion y rutas
    private String ubicacion;            
    private Grafo grafoUbicaciones;     

    // Constructor

    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();

        this.ubicacion = "San Pedro";
        this.grafoUbicaciones = new Grafo();
        inicializarGrafoUbicaciones();
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Grafo getGrafoUbicaciones() {
        return grafoUbicaciones;
    }

    // Metodos

    public void agregarProductoAlInventario(Producto p) {
        inventario.insertar(p);
        System.out.println("Producto agregado al inventario de la tienda.\n");
    }

    public void mostrarInventario() {
        inventario.imprimirInOrden();
    }

    public Producto buscarProductoEnInventario(String nombreProducto) {
        return inventario.buscar(nombreProducto);
    }


    public void registrarCliente(Cliente c) {
        colaClientes.encolar(c);
    }

    public void mostrarColaClientes() {
        colaClientes.mostrarCola();
    }

    // Agregar producto al carrito

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


        Producto enCarrito = new Producto(
                enInventario.getNombre(),
                enInventario.getPrecio(),
                enInventario.getCategoria(),
                enInventario.getFechaVencimiento(),
                cantidad
        );

        for (String ruta : enInventario.getListaImagenes()) {
            enCarrito.agregarImagen(ruta);
        }

        cliente.getCarrito().insertarFinal(enCarrito);

        enInventario.setCantidad(enInventario.getCantidad() - cantidad);

        System.out.println("Producto agregado al carrito de " + cliente.getNombre() + " correctamente.\n");
    }

    // Atender cliente, factura + ruta

    public void atenderSiguienteCliente() {
        Cliente c = colaClientes.atenderSiguiente();
        if (c == null) {
            return;
        }

        System.out.println("--- FACTURA ---");
        System.out.println("Tienda: " + nombre);
        System.out.println("Cliente: " + c.getNombre()
                + " (prioridad " + c.getPrioridad() + ")");
        System.out.println("Ubicacion del cliente: " + c.getUbicacion());
        System.out.println("Productos en el carrito:\n");

        ListaProductos carrito = c.getCarrito();

        if (carrito.estaVacia()) {
            System.out.println("(El cliente no tenía productos en el carrito)\n");
        } else {
            carrito.reporteCarrito();
        }

        // Ruta de entrega 

        System.out.println("--- RUTA DE ENTREGA (camino más corto) ---");

        if (c.getUbicacion() == null || c.getUbicacion().trim().isEmpty()) {
            System.out.println("El cliente no tiene una ubicación definida. No se puede calcular la ruta.\n");
        } else if (!grafoUbicaciones.contieneVertice(c.getUbicacion())
                || !grafoUbicaciones.contieneVertice(ubicacion)) {
            System.out.println("No existe información de rutas para alguna de las ubicaciones.\n");
        } else {
            Map<String, Integer> distancias = new HashMap<>();
            Map<String, String> predecesores = new HashMap<>();

            grafoUbicaciones.algoritmoDijkstra(c.getUbicacion(), distancias, predecesores);

            List<String> camino = grafoUbicaciones.reconstruirCamino(c.getUbicacion(), ubicacion, predecesores);

            if (camino.isEmpty()) {
                System.out.println("No se encontró un camino entre " + c.getUbicacion()
                        + " y " + ubicacion + ".\n");
            } else {
                System.out.println("Ubicación de la tienda: " + ubicacion);
                System.out.println("Camino más corto: " + String.join(" -> ", camino));
                Integer distanciaTotal = distancias.get(ubicacion);
                System.out.println("Distancia total aproximada: " + distanciaTotal + " km\n");
            }
        }

        System.out.println("-------- FIN DE FACTURA --------\n");
    }

    // Inicializar el grafo 

    private void inicializarGrafoUbicaciones() {
        grafoUbicaciones.agregarVertice(ubicacion); 
        grafoUbicaciones.agregarVertice("Curridabat");
        grafoUbicaciones.agregarVertice("Montes de Oca");
        grafoUbicaciones.agregarVertice("Sabanilla");
        grafoUbicaciones.agregarVertice("Zapote");
        grafoUbicaciones.agregarVertice("Guadalupe");

        grafoUbicaciones.agregarArista(ubicacion, "Montes de Oca", 2);
        grafoUbicaciones.agregarArista(ubicacion, "Guadalupe", 3);
        grafoUbicaciones.agregarArista(ubicacion, "Zapote", 4);

        grafoUbicaciones.agregarArista("Montes de Oca", "Sabanilla", 2);
        grafoUbicaciones.agregarArista("Guadalupe", "Sabanilla", 2);
        grafoUbicaciones.agregarArista("Zapote", "Curridabat", 3);
        grafoUbicaciones.agregarArista("Curridabat", "Montes de Oca", 4);
        grafoUbicaciones.agregarArista("Guadalupe", "Curridabat", 5);
    }

    // Mostrar grafo 

    public void mostrarRutasEntrega() {
        grafoUbicaciones.mostrarGrafo();
    }
}
