import java.util.Scanner;

public class ListaProductos {

    // Nodo 
    private NodoProducto cabeza;

    // Constructor
    public ListaProductos() {
        cabeza = null;
    }

    // --- Insertar al inicio ---
    public void insertarInicio(Producto nuevoProducto) {
        NodoProducto nuevo = new NodoProducto(nuevoProducto);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        System.out.println("Producto agregado al inicio correctamente.\n");
    }

    // --- Insertar al final ---
    public void insertarFinal(Producto nuevoProducto) {
        NodoProducto nuevo = new NodoProducto(nuevoProducto);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoProducto aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        System.out.println("Producto agregado al final correctamente.\n");
    }

    // Modificar producto por nombre 
    public void modificarProducto(String nombreBuscado, Scanner sc) {
        NodoProducto aux = cabeza;
        boolean encontrado = false;

        while (aux != null) {
            if (aux.getDato().getNombre().equalsIgnoreCase(nombreBuscado)) {
                encontrado = true;
                System.out.println("\nProducto encontrado. Ingrese los nuevos datos:");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = sc.nextLine();

                System.out.print("Nuevo precio: ");
                double nuevoPrecio = sc.nextDouble();
                sc.nextLine();

                System.out.print("Nueva categoría: ");
                String nuevaCategoria = sc.nextLine();

                System.out.print("Nueva fecha de vencimiento (si aplica, sino dejar vacío): ");
                String nuevaFecha = sc.nextLine();

                System.out.print("Nueva cantidad: ");
                int nuevaCantidad = sc.nextInt();
                sc.nextLine();

                aux.getDato().setNombre(nuevoNombre);
                aux.getDato().setPrecio(nuevoPrecio);
                aux.getDato().setCategoria(nuevaCategoria);
                aux.getDato().setFechaVencimiento(nuevaFecha);
                aux.getDato().setCantidad(nuevaCantidad);

                System.out.println("\nProducto modificado correctamente.\n");
                break;
            }
            aux = aux.getSiguiente();
        }

        if (!encontrado) {
            System.out.println("No se encontró un producto con ese nombre.\n");
        }
    }

    // Agregar imagen a un producto 
    public void agregarImagenAProducto(String nombreBuscado, String rutaImagen) {
        NodoProducto aux = cabeza;
        boolean agregado = false;

        while (aux != null) {
            if (aux.getDato().getNombre().equalsIgnoreCase(nombreBuscado)) {
                aux.getDato().agregarImagen(rutaImagen);
                System.out.println("Imagen agregada al producto correctamente.\n");
                agregado = true;
                break;
            }
            aux = aux.getSiguiente();
        }

        if (!agregado) {
            System.out.println("No se encontró un producto con ese nombre.\n");
        }
    }

    // Imprimir los productos 
    public void imprimirLista() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.\n");
            return;
        }

        NodoProducto aux = cabeza;
        System.out.println("\n--- Productos en inventario ---");
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    // Reporte de costos totales 
    public void reporteCostos() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.\n");
            return;
        }

        NodoProducto aux = cabeza;
        double totalGeneral = 0;

        System.out.println("\n--- Reporte de costos del inventario ---");
        while (aux != null) {
            Producto p = aux.getDato();
            double costo = p.getCostoTotal();
            System.out.println(p.getNombre() + " - " + p.getCantidad() + " unidades × " 
                    + p.getPrecio() + " colones = " + costo + " colones");
            totalGeneral += costo;
            aux = aux.getSiguiente();
        }
        System.out.println("\nCosto total del inventario: " + totalGeneral + " colones\n");
    }
}
