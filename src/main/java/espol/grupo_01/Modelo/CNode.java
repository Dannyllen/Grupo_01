package espol.grupo_01.Modelo;

public class CNode<E>{
    public E contenido;
    public CNode<E> siguiente;
    public CNode<E> anterior;
        
    public CNode(E contenido) {
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }
}
