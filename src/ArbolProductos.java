/* Clase ArbolProductos:*/

public class ArbolProductos {

    // Raíz del árbol
    private Producto raiz;

    // Constructor
    public ArbolProductos() {
        raiz = null;
    }

    // Insertar un producto en el árbol
    public void insertar(Producto nuevo) {
        raiz = insertarRec(raiz, nuevo);
    }

    // Método recursivo para insertar
    private Producto insertarRec(Producto actual, Producto nuevo) {
        if (actual == null) {
            return nuevo;
        }

        String nombreActual = actual.getNombre();
        String nombreNuevo = nuevo.getNombre();

        int cmp = nombreNuevo.compareToIgnoreCase(nombreActual);

        if (cmp < 0) {
            // Va al subárbol izquierdo
            actual.setIzquierdo(insertarRec(actual.getIzquierdo(), nuevo));
        } else if (cmp > 0) {
            // Va al subárbol derecho
            actual.setDerecho(insertarRec(actual.getDerecho(), nuevo));
        } else {
            // Mismo nombre de producto: actualizamos datos en lugar de crear nodo nuevo
            System.out.println("Producto ya existe. Se actualiza la información y se suma la cantidad.\n");

            actual.setPrecio(nuevo.getPrecio());
            actual.setCategoria(nuevo.getCategoria());
            actual.setFechaVencimiento(nuevo.getFechaVencimiento());
            actual.setCantidad(actual.getCantidad() + nuevo.getCantidad());

            for (String ruta : nuevo.getListaImagenes()) {
                actual.agregarImagen(ruta);
            }
        }

        return actual;
    }

    // Búsqueda por nombre 
    public Producto buscar(String nombreBuscado) {
        return buscarRec(raiz, nombreBuscado);
    }

    private Producto buscarRec(Producto actual, String nombreBuscado) {
        if (actual == null) {
            return null;
        }

        int cmp = nombreBuscado.compareToIgnoreCase(actual.getNombre());

        if (cmp == 0) {
            return actual;
        } else if (cmp < 0) {
            return buscarRec(actual.getIzquierdo(), nombreBuscado);
        } else {
            return buscarRec(actual.getDerecho(), nombreBuscado);
        }
    }

    // Para imprimir
    public void imprimirInOrden() {
        if (raiz == null) {
            System.out.println("El inventario está vacío.\n");
        } else {
            System.out.println("\n--- Inventario de productos (inorden) ---");
            imprimirInOrdenRec(raiz);
            System.out.println();
        }
    }

    private void imprimirInOrdenRec(Producto actual) {
        if (actual != null) {
            imprimirInOrdenRec(actual.getIzquierdo());
            System.out.println(actual);
            imprimirInOrdenRec(actual.getDerecho());
        }
    }

    // Getter 
    public Producto getRaiz() {
        return raiz;
    }

    public void setRaiz(Producto raiz) {
        this.raiz = raiz;
    }
}

