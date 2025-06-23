package espol.grupo_01.Controlador;

import espol.grupo_01.Modelo.Dato;
import espol.grupo_01.Modelo.LinkedListSimpleCircular;
import espol.grupo_01.Modelo.Nodo;

    public class NavegadorDato {
        private Nodo<Dato> nodoActual;

        public NavegadorDato(LinkedListSimpleCircular<Dato> lista) {
            this.nodoActual = lista.getPrimero();
        }

        public String siguiente() {
            nodoActual = nodoActual.siguiente;
            return nodoActual.valor.toString();
        }

        public String actual() {
            return nodoActual.valor.toString();
        }
    }
