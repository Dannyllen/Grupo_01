package espol.grupo_01.Modelo;

public class Nodo<T> {
    public T valor;
    public T identificador;
    public Nodo<T> siguiente;

    public Nodo(T valor, T identificador) {
        this.valor = valor;
        this.identificador = identificador;
    }
    
    @Override
    public String toString() {
        return identificador + ":" + valor;
    }
}
