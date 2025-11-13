import java.util.Scanner;

public class Main {

    private static ListaProductos lista = new ListaProductos();

    public static void main(String[] args) {
        cargarProductosIniciales();
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println("  SISTEMA DE INVENTARIO");
            System.out.println("==============================");
            System.out.println("1) Insertar producto al INICIO");
            System.out.println("2) Insertar producto al FINAL");
            System.out.println("3) Modificar producto por NOMBRE");
            System.out.println("4) Agregar IMAGEN a un producto");
            System.out.println("5) Imprimir LISTA completa");
            System.out.println("6) Reporte de COSTOS");
            System.out.println("7) Salir");
            System.out.print("Elija una opción: ");

            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    Producto p1 = capturarProducto(sc);
                    lista.insertarInicio(p1);
                    break;
                case 2:
                    Producto p2 = capturarProducto(sc);
                    lista.insertarFinal(p2);
                    break;
                case 3:
                    System.out.print("Nombre del producto a MODIFICAR: ");
                    String buscado = sc.nextLine();
                    lista.modificarProducto(buscado, sc);
                    break;
                case 4:
                    System.out.print("Nombre del producto para agregar imagen: ");
                    String buscadoImg = sc.nextLine();
                    System.out.print("Ruta de imagen (ej: img/jabon_lavaplatos.png): ");
                    String ruta = sc.nextLine();
                    lista.agregarImagenAProducto(buscadoImg, ruta);
                    break;
                case 5:
                    lista.imprimirLista();
                    break;
                case 6:
                    lista.reporteCostos();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida, intente otra vez.\n");
                    break;
            }
        } while (opcion != 7);

        sc.close();
    }

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

    private static void cargarProductosIniciales() {
        Producto a = new Producto("Aceite de oliva extra virgen", 5200.0, "Aceites", "2026-02-15", 3);
        a.agregarImagen("img/aceite_oliva.png");

        Producto b = new Producto("Atún con vegetales", 2150.0, "Conservas", "2027-05-30", 6);
        b.agregarImagen("img/atun_vegetales.png");

        Producto c = new Producto("Jabón lavaplatos líquido limón", 1450.0, "Limpieza", 4);
        c.agregarImagen("img/jabon_lavaplatos.png");

        Producto d = new Producto("Cereal integral de avena y miel", 3750.0, "Desayunos", "2025-11-20", 2);
        d.agregarImagen("img/cereal_avena.png");

        Producto e = new Producto("Yogurt natural bajo en grasa", 890.0, "Lácteos", "2025-10-15", 8);
        e.agregarImagen("img/yogurt_natural.png");

        lista.insertarInicio(a);
        lista.insertarFinal(b);
        lista.insertarFinal(c);
        lista.insertarInicio(d);
        lista.insertarFinal(e);
    }

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
