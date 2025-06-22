package espol.grupo_01.Modelo;
import java.io.Serializable;

public class CNode<E> implements Serializable{
    
    private static final long serialVersionUID = 01L;
    
    public E contenido;
    public CNode<E> siguiente;
    public CNode<E> anterior;
        
    public CNode(E contenido) {
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }
}
