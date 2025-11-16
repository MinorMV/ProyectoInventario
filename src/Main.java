import java.util.Scanner;

public class Main {

    private static Tienda tienda = new Tienda("Super Data Market");

    public static void main(String[] args) {
        cargarProductosIniciales();
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n======================================");
            System.out.println("  SISTEMA DE INVENTARIO - AVANCE 2");
            System.out.println("======================================");
            System.out.println("1) Insertar PRODUCTO al INVENTARIO");
            System.out.println("2) Mostrar INVENTARIO (inorden)");
            System.out.println("3) Registrar CLIENTE y llenar CARRITO");
            System.out.println("4) Mostrar COLA de clientes");
            System.out.println("5) Atender SIGUIENTE cliente (FACTURA)");
            System.out.println("6) Salir");
            System.out.print("Elija una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    // Insertar producto al inventario (árbol)
                    Producto nuevo = capturarProducto(sc);
                    tienda.agregarProductoAlInventario(nuevo);
                    break;

                case 2:
                    // Mostrar inventario en inorden
                    tienda.mostrarInventario();
                    break;

                case 3:
                    // Registrar cliente y llenar su carrito
                    registrarClienteYCarrito(sc);
                    break;

                case 4:
                    // Mostrar cola de clientes
                    tienda.mostrarColaClientes();
                    break;

                case 5:
                    // Atender siguiente cliente y mostrar factura
                    tienda.atenderSiguienteCliente();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida, intente otra vez.\n");
                    break;
            }
        } while (opcion != 6);

        sc.close();
    }

    // Captura de datos de producto 

    private static Producto capturarProducto(Scanner sc) {
        System.out.println("\n--- Ingrese los datos del producto ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio (use punto): ");
        double precio = leerDouble(sc);

        System.out.print("Categoría: ");
        String categoria = sc.nextLine();

        System.out.print("Fecha de vencimiento (si aplica, sino dejar vacío): ");
        String fecha = sc.nextLine();

        System.out.print("Cantidad (unidades): ");
        int cantidad = leerEntero(sc);

        Producto p;
        if (fecha == null || fecha.trim().isEmpty()) {
            p = new Producto(nombre, precio, categoria, cantidad);
        } else {
            p = new Producto(nombre, precio, categoria, fecha, cantidad);
        }

        System.out.print("¿Agregar imagen ahora? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            System.out.print("Ruta imagen (ej: img/atun_vegetales.png): ");
            String r = sc.nextLine();
            p.agregarImagen(r);
            System.out.println("Imagen agregada.");
        }

        return p;
    }

    // Registrar cliente y llenar carrito desde el inventario 

    private static void registrarClienteYCarrito(Scanner sc) {
        System.out.println("\n--- Registro de cliente ---");

        System.out.print("Nombre del cliente: ");
        String nombreCliente = sc.nextLine();

        int prioridad;
        do {
            System.out.print("Prioridad del cliente (1 = básico, 2 = afiliado, 3 = premium): ");
            prioridad = leerEntero(sc);
            if (prioridad < 1 || prioridad > 3) {
                System.out.println("Prioridad inválida. Debe ser 1, 2 o 3.\n");
            }
        } while (prioridad < 1 || prioridad > 3);

        Cliente cliente = new Cliente(nombreCliente, prioridad);

        System.out.println("\nAhora vamos a llenar el carrito del cliente.");
        String seguir;

        do {
            System.out.print("¿Desea agregar un producto al carrito? (s/n): ");
            seguir = sc.nextLine();

            if (seguir.equalsIgnoreCase("s")) {
                System.out.print("Nombre del producto a agregar al carrito: ");
                String nombreProducto = sc.nextLine();

                Producto enInventario = tienda.buscarProductoEnInventario(nombreProducto);
                if (enInventario == null) {
                    System.out.println("Ese producto no existe en el inventario.\n");
                } else {
                    System.out.println("Producto encontrado: " + enInventario.getNombre()
                            + " | Precio: " + enInventario.getPrecio()
                            + " | Disponible: " + enInventario.getCantidad());

                    System.out.print("Cantidad a agregar al carrito: ");
                    int cant = leerEntero(sc);

                    tienda.agregarProductoAlCarrito(cliente, nombreProducto, cant);
                }
            }

        } while (seguir.equalsIgnoreCase("s"));

        // Validación extra: carrito vacío
        if (cliente.getCarrito().estaVacia()) {
            System.out.println("Aviso: el cliente se registró sin productos en el carrito.\n");
        }

        // Registrar cliente en la cola
        tienda.registrarCliente(cliente);
        System.out.println("Cliente registrado y agregado a la cola de atención.\n");
    }

    // --- Cargar algunos productos iniciales al inventario (árbol) ---

    private static void cargarProductosIniciales() {
        Producto a = new Producto("Aceite de oliva extra virgen", 5200.0,
                "Aceites", "2026-02-15", 3);
        a.agregarImagen("img/aceite_oliva.png");

        Producto b = new Producto("Atún con vegetales", 2150.0,
                "Conservas", "2027-05-30", 6);
        b.agregarImagen("img/atun_vegetales.png");

        Producto c = new Producto("Jabón lavaplatos líquido limón", 1450.0,
                "Limpieza", 4);
        c.agregarImagen("img/jabon_lavaplatos.png");

        Producto d = new Producto("Cereal integral de avena y miel", 3750.0,
                "Desayunos", "2025-11-20", 2);
        d.agregarImagen("img/cereal_avena.png");

        Producto e = new Producto("Yogurt natural bajo en grasa", 890.0,
                "Lácteos", "2025-10-15", 8);
        e.agregarImagen("img/yogurt_natural.png");

        tienda.agregarProductoAlInventario(a);
        tienda.agregarProductoAlInventario(b);
        tienda.agregarProductoAlInventario(c);
        tienda.agregarProductoAlInventario(d);
        tienda.agregarProductoAlInventario(e);
    }

    // Lectura

    private static int leerEntero(Scanner sc) {
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

    private static double leerDouble(Scanner sc) {
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
