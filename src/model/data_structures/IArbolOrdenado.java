package model.data_structures;

public interface IArbolOrdenado<T extends Comparable<? super T>> extends IArbol<T>  
{

    /**
     * Inserta un nuevo elemento en el �rbol.
     * 
     * @param elem Elemento a insertar.
     * @throws ElementoExisteException Si el elemento a insertar ya se encuentra en el �rbol
     */
    public void insertar( T elem ) throws Exception;

    /**
     * Eliminar un elemento del �rbol.
     * 
     * @param elem Elemento a eliminar del �rbol.
     * @throws ElementoNoExisteException Si el elemento a eliminar no es encontrado en el �rbol.
     */
    public void eliminar( T elem ) throws Exception;

}
