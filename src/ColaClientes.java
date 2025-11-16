import java.util.ArrayList;

/* Clase ColaClientes */

public class ColaClientes {

    // Atributos
    private ArrayList<Cliente> clientes;

    // Constructor
    public ColaClientes() {
        clientes = new ArrayList<>();
    }

    // Encolar un cliente (se agrega al final)
    public void encolar(Cliente c) {
        clientes.add(c);
        System.out.println("Cliente agregado a la cola correctamente.\n");
    }

    // Verificar si la cola está vacía
    public boolean estaVacia() {
        return clientes.isEmpty();
    }

    // Cantidad de clientes en la cola
    public int tamano() {
        return clientes.size();
    }

    // Atender al siguiente cliente según la prioridad
    public Cliente atenderSiguiente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes en la cola.\n");
            return null;
        }

        int indiceGanador = 0;
        Cliente ganador = clientes.get(0);

        // Buscamos el cliente con mayor prioridad
        for (int i = 1; i < clientes.size(); i++) {
            Cliente actual = clientes.get(i);

            // Si tiene prioridad mayor, pasa a ser el nuevo ganador
            if (actual.getPrioridad() > ganador.getPrioridad()) {
                ganador = actual;
                indiceGanador = i;
            }
            // Si la prioridad es igual, se mantiene el que está más cerca del frente
        }

        // Eliminamos al ganador de la cola y lo retornamos
        clientes.remove(indiceGanador);
        System.out.println("Atendiendo a: " + ganador.getNombre()
                + " (prioridad " + ganador.getPrioridad() + ")\n");
        return ganador;
    }

    // Mostrar el contenido de la cola
    public void mostrarCola() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes en la cola.\n");
            return;
        }

        System.out.println("\n--- Clientes en la cola ---");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.println((i + 1) + ") " + c.getNombre()
                    + " - prioridad " + c.getPrioridad());
        }
        System.out.println();
    }
}
