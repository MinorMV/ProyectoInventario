import java.util.Scanner;

/* Clase ListaProductos */

public class ListaProductos {

    // Nodo 
    private Producto cabeza;

    // Constructor
    public ListaProductos() {
        cabeza = null;
    }

    // Insertar al inicio 
    public void insertarInicio(Producto nuevoProducto) {
        nuevoProducto.setSiguiente(cabeza);
        cabeza = nuevoProducto;
        System.out.println("Producto agregado al inicio correctamente.\n");
    }

    // Insertar al final 
    public void insertarFinal(Producto nuevoProducto) {
        if (cabeza == null) {
            cabeza = nuevoProducto;
        } else {
            Producto aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevoProducto);
        }
        nuevoProducto.setSiguiente(null); 
        System.out.println("Producto agregado al final correctamente.\n");
    }

    // Modificar producto por nombre 
    public void modificarProducto(String nombreBuscado, Scanner sc) {
        Producto aux = cabeza;
        boolean encontrado = false;

        while (aux != null) {
            if (aux.getNombre().equalsIgnoreCase(nombreBuscado)) {
                encontrado = true;
                System.out.println("\nProducto encontrado. Ingrese los nuevos datos:");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = sc.nextLine();

                System.out.print("Nuevo precio: ");
                double nuevoPrecio = leerDouble(sc);

                System.out.print("Nueva categoría: ");
                String nuevaCategoria = sc.nextLine();

                System.out.print("Nueva fecha de vencimiento (si aplica, sino dejar vacío): ");
                String nuevaFecha = sc.nextLine();

                System.out.print("Nueva cantidad: ");
                int nuevaCantidad = leerEntero(sc);

                aux.setNombre(nuevoNombre);
                aux.setPrecio(nuevoPrecio);
                aux.setCategoria(nuevaCategoria);
                aux.setFechaVencimiento(nuevaFecha);
                aux.setCantidad(nuevaCantidad);

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
        Producto aux = cabeza;
        boolean agregado = false;

        while (aux != null) {
            if (aux.getNombre().equalsIgnoreCase(nombreBuscado)) {
                aux.agregarImagen(rutaImagen);
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

        Producto aux = cabeza;
        System.out.println("\n--- Productos en lista ---");
        while (aux != null) {
            System.out.println(aux);
            aux = aux.getSiguiente();
        }
    }

    // Reporte de costos totales
    public void reporteCostos() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.\n");
            return;
        }

        Producto aux = cabeza;
        double totalGeneral = 0;

        System.out.println("\n--- Reporte de costos del inventario ---");
        while (aux != null) {
            double costo = aux.getCostoTotal();
            System.out.println(aux.getNombre() + " - " + aux.getCantidad() + " unidades × " 
                    + aux.getPrecio() + " colones = " + costo + " colones");
            totalGeneral += costo;
            aux = aux.getSiguiente();
        }
        System.out.println("\nCosto total del inventario: " + totalGeneral + " colones\n");
    }

    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Reporte especial para FACTURA del carrito
    public void reporteCarrito() {
        if (cabeza == null) {
            System.out.println("(El carrito está vacío)");
            return;
        }

        Producto aux = cabeza;
        double totalGeneral = 0;

        System.out.println("\n--- Detalle del carrito ---");

        while (aux != null) {
            double subtotal = aux.getPrecio() * aux.getCantidad();
            System.out.println(aux.getNombre() + " - " + aux.getCantidad() + " unidades × " 
                    + aux.getPrecio() + " colones = " + subtotal + " colones");
            totalGeneral += subtotal;
            aux = aux.getSiguiente();
        }

        System.out.println("\nTotal a pagar: " + totalGeneral + " colones\n");
    }

    private int leerEntero(Scanner sc) {
        int n;
        while (true) {
            try {
                String linea = sc.nextLine();
                n = Integer.parseInt(linea.trim());
                break;
            } catch (Exception ex) {
                System.out.print("Ingrese un número entero válido: ");
            }
        }
        return n;
    }

    private double leerDouble(Scanner sc) {
        double x;
        while (true) {
            try {
                String linea = sc.nextLine();
                x = Double.parseDouble(linea.trim());
                break;
            } catch (Exception ex) {
                System.out.print("Ingrese un número (use punto) válido: ");
            }
        }
        return x;
    }
}
