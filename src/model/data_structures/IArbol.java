package model.data_structures;

public interface IArbol<T> 
{
    
    /**
     * Retorna la altura del �rbol.
     * @return La altura del �rbol.
     */
    public int darAltura();
    
    /**
     * Retorna el n�mero de elementos del �rbol.
     * @return El n�mero de elementos del �rbol.
     */
    public int darPeso();
    
    /**
     * Busca un elemento en el �rbol dado su modelo.
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden.
     * @return T elemento del �rbol que corresponde al modelo o <code>null</code> si este no existe.
     */
    public T buscar(T modelo);
    
}