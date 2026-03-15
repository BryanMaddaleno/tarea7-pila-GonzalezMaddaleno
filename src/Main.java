import java.util.EmptyStackException;

/**
 * Parte 1 — Definición del TDA BitacoraStack
 * Las pilas almacenan elementos donde las inserciones y borrados 
 * ocurren en el "tope".
 */
class BitacoraStack {
    private Nodo tope;
    private int tamaño;

    // Estructura de Nodo basada en la implementación de listas enlazadas
    private class Nodo {
        String evento;
        Nodo siguiente;

        Nodo(String evento) {
            this.evento = evento;
        }
    }

    public BitacoraStack() {
        this.tope = null;
        this.tamaño = 0;
    }

    // Operación Push: Agrega un evento a la cima
    public void registrar(String evento) {
        Nodo nuevoNodo = new Nodo(evento);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamaño++;
    }

    // Operación Top/Peek: Consulta el último sin borrar
    public String consultarUltimo() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }
        return tope.evento;
    }

    // Operación Pop: Elimina y devuelve el último
    public String eliminarUltimo() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }
        String evento = tope.evento;
        tope = tope.siguiente;
        tamaño--;
        return evento;
    }

    // Operación Vacía? 
    public boolean estaVacia() {
        return tope == null;
    }

    public int totalEventos() {
        return tamaño;
    }
}

public class Main {
    public static void main(String[] args) {
        BitacoraStack bitacora = new BitacoraStack();

        // 1. Registro de eventos de la misión
        bitacora.registrar("Motor de estribor encendido");
        bitacora.registrar("Velocidad warp alcanzada");
        bitacora.registrar("Senal de comunicacion estable");
        bitacora.registrar("Anomalia detectada en sector 7");
        bitacora.registrar("Escudos al 40%");
        bitacora.registrar("ERROR CRITICO: fallo en sistema de navegacion");

        try {
            // 2. Consultar último evento
            String ultimo = bitacora.consultarUltimo();
            System.out.println("Ultimo evento registrado: " + ultimo);

            // 3. Protocolo de revisión por ERROR
            if (ultimo.contains("ERROR")) {
                System.out.println("\n--- PROTOCOLO DE REVISION ACTIVADO ---");
                for (int i = 0; i < 3; i++) {
                    if (!bitacora.estaVacia()) {
                        System.out.println("Removiendo para analisis: " + bitacora.eliminarUltimo());
                    }
                }
            }

            // 4. Estado actual de la bitácora
            System.out.println("\n--- ESTADO ACTUAL DE LA BITÁCORA ---");
            System.out.println("Total de eventos restantes: " + bitacora.totalEventos());
            if (!bitacora.estaVacia()) {
                System.out.println("Evento actual en la cima: " + bitacora.consultarUltimo());
            }

        } catch (EmptyStackException e) {
            System.err.println("ERROR DE SISTEMA: Intento de acceso a bitacora vacia.");
        }
    }

    
}