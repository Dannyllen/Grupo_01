package espol.grupo_01.Modelo;
import java.io.Serializable;

public class Dato<T> implements Serializable{
    
    private static final long serialVersionUID = 01L;
    private T valor;
    private T identificador;

    public Dato(T valor, T identificador) {
        this.valor = valor;
        this.identificador= identificador;
    }
    
    @Override
    public String toString() {
        return identificador + ":" + valor;
    }
}
