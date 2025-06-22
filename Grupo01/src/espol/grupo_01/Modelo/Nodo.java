package espol.grupo_01.Modelo;
import java.io.Serializable;

public class Nodo<T> implements Serializable{
    
    private static final long serialVersionUID = 01L;
    public T valor;
    public Nodo<T> siguiente;

    public Nodo(T valor) {
        this.valor = valor;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public T getValor() {
        return valor;
    }
    
}