//Clase Grafo

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Grafo {

    // Lista de adyacencia
    private Map<String, List<Arista>> listaAdyacencia;

    // Constructor

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    // Agregar vertice

    public void agregarVertice(String nombre) {
        if (!listaAdyacencia.containsKey(nombre)) {
            listaAdyacencia.put(nombre, new ArrayList<>());
        }
    }

    // Agregar arista no dirigida y ponderada

    public void agregarArista(String origen, String destino, int peso) {
        agregarVertice(origen);
        agregarVertice(destino);

        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
    }

    // Mostrar grafo

    public void mostrarGrafo() {
        System.out.println("\n--- Grafo de ubicaciones ---");
        for (String v : listaAdyacencia.keySet()) {
            System.out.print(v + " -> ");
            List<Arista> adyacentes = listaAdyacencia.get(v);
            for (Arista a : adyacentes) {
                System.out.print(a.getDestino() + "(" + a.getPeso() + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Verificar si un vertice existe

    public boolean contieneVertice(String nombre) {
        return listaAdyacencia.containsKey(nombre);
    }

    // Algoritmo de Dijkstra

    public void algoritmoDijkstra(String inicio, Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {

        // Inicializar distancias
        for (String v : listaAdyacencia.keySet()) {
            distancias.put(v, Integer.MAX_VALUE);
            predecesores.put(v, null);
        }

        distancias.put(inicio, 0);

        PriorityQueue<Vertice> cola = new PriorityQueue<>();
        cola.add(new Vertice(inicio, 0));

        while (!cola.isEmpty()) {
            Vertice actual = cola.poll();
            String nombreActual = actual.getNombre();
            int distanciaActual = actual.getDistancia();

            if (distanciaActual > distancias.get(nombreActual)) {
                continue;
            }

            List<Arista> adyacentes = listaAdyacencia.get(nombreActual);
            for (Arista a : adyacentes) {
                String vecino = a.getDestino();
                int peso = a.getPeso();

                int nuevaDistancia = distanciaActual + peso;
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, nombreActual);
                    cola.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    // Reconstruir camino 

    public List<String> reconstruirCamino(String inicio, String destino,
                                          Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();

        if (!predecesores.containsKey(destino) && !inicio.equals(destino)) {
            return camino;
        }

        String actual = destino;
        while (actual != null && !actual.equals(inicio)) {
            camino.add(0, actual);
            actual = predecesores.get(actual);
        }

        if (actual == null) {
            camino.clear();
            return camino;
        }

        camino.add(0, inicio);
        return camino;
    }
}
