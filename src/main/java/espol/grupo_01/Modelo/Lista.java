/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.grupo_01.Modelo;

/**
 *
 * @author Grupo 1
 */
public interface Lista<E> {
    
    //Metodos que seran obligados a implementar por la clase que implemente la interfaz
    public boolean add(E element, int index);
    public ListaCircular<E> getByIndex(int index);
    public boolean addFirst(E element); 
    public boolean addLast(E element);
    public ListaCircular<E> getPrev(ListaCircular<E> nodo);
    public ListaCircular<E> getSig(ListaCircular<E> nodo);
    public boolean delete(E content);
}
