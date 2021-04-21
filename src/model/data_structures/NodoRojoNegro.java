package model.data_structures;

import java.io.Serializable;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class NodoRojoNegro<T extends Comparable<? super T>> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
		
	
    /**
     * Constante que representa el color negro de los nodos
     */
    public static final int NEGRO = 1;

    /**
     * Constante que representa el color rojo de los nodos
     */
    public static final int ROJO = 0;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Hijo derecho del nodo
     */
    private NodoRojoNegro<T> hijoDerecho;

    /**
     * Hijo izquierdo del nodo
     */
    private NodoRojoNegro<T> hijoIzquierdo;

    /**
     * Información guardada en el nodo
     */
    private T elem;

    /**
     * Color del nodo
     */
    private int color;

    /**
     * Padre del nodo
     */
    private NodoRojoNegro<T> padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo.
     * </p>
     * Cronstruye un nodo no-hoja de color rojo sin padre y con hijos hojas.
     * 
     * @param elem
     */
    protected NodoRojoNegro( T elem )
    {
        this.elem = elem;
        color = ROJO;
        cambiarHijoDerecho( new NodoRojoNegro<T>( ) );
        cambiarHijoIzquierdo( new NodoRojoNegro<T>( ) );
        padre = null;
    }

    /**
     * Constructor por defecto de la clase.
     * </p>
     * Construye un nodo hoja (nodo negro sin información).
     */
    private NodoRojoNegro( )
    {
        this.elem = null;
        color = NEGRO;
        padre = null;
    }

    // -----------------------------------------------------------------
    // Métodos consultores básicos
    // -----------------------------------------------------------------

    /**
     * Retorna el padre del nodo.
     * 
     * @return el padre del nodo.
     */
    public NodoRojoNegro<T> darPadre( )
    {
        return padre;
    }

    /**
     * Retorna el tio del nodo.
     * 
     * @return El hermano del padre del nodo, o <code>null</code> si no tiene padre o tio
     */
    public NodoRojoNegro<T> darTio( )
    {
        if( padre == null || padre.padre == null )
        {
            return null;
        }
        else
        {
            if( padre.padre.esHijoDerecho( padre ) )
                return padre.padre.hijoIzquierdo;
            else
                return padre.padre.hijoDerecho;
        }

    }

    /**
     * Retorna el color del nodo.
     * 
     * @return El color del nodo.
     */
    public int darColor( )
    {
        return color;
    }

    /**
     * Retorna el hijo derecho del nodo.
     * 
     * @return Hijo derecho del nodo, o <code>null</code> en caso de que el nodo sea hoja.
     */
    public NodoRojoNegro<T> darHijoDerecho( )
    {
        return hijoDerecho;
    }

    /**
     * Verifica si el nodo ingresado por parametro es hijo derecho de <code>this</code>
     * 
     * @param nodo Nodo a comparar.
     * @return <code>true</code> si el nodo ingresado como parametro es hijo derecho de <code>this</code> o <code>false</code> en caso contrario.
     */
    public boolean esHijoDerecho( NodoRojoNegro<T> nodo )
    {
        return hijoDerecho == nodo;
    }

    /**
     * Retorna el hijo izquierdo del nodo.
     * 
     * @return Hijo izquierdo del nodo, o <code>null</code> en caso de que el nodo sea hoja.
     */
    public NodoRojoNegro<T> darHijoIzquierdo( )
    {
        return hijoIzquierdo;
    }

    /**
     * Verifica si el nodo ingresado por parametro es hijo izquierdo de <code>this</code>
     * 
     * @param nodo Nodo a comparar.
     * @return <code>true</code> si el nodo ingresado como parametro es hijo izquierdo de <code>this</code> o <code>false</code> en caso contrario.
     */
    public boolean esHijoIzquierdo( NodoRojoNegro<T> nodo )
    {
        return hijoIzquierdo == nodo;
    }

    /**
     * Verifica si el hijo derecho del nodo es una hoja.
     * 
     * @return <code>true</code> si el hijo derecho del nodo es una hoja o <code>false</code> en caso contrario.
     */
    public boolean hijoDerechoHoja( )
    {
        return hijoDerecho.elem == null;
    }

    /**
     * Verifica si el hijo izquierdo del nodo es una hoja.
     * 
     * @return <code>true</code> si el hijo izquierdo del nodo es una hoja o <code>false</code> en caso contrario.
     */
    public boolean hijoIzquierdoHoja( )
    {
        return hijoIzquierdo.elem == null;
    }

    /**
     * Retorna el mayor de los descendientes del nodo.
     * 
     * @return El mayor de los descendientes del nodo.
     */
    public NodoRojoNegro<T> darMayor( )
    {
        return hijoDerechoHoja( ) ? this : hijoDerecho.darMayor( );
    }

    /**
     * Retorna el menor de los descendientes del nodo.
     * 
     * @return El menor de los descendientes del nodo.
     */
    public NodoRojoNegro<T> darMenor( )
    {
        return hijoIzquierdoHoja( ) ? this : hijoIzquierdo.darMenor( );
    }

    /**
     * Construye una lista con los elementos descendientes del nodo en preorden
     * 
     * @param preorden La lista en donde se agregarán los descendientes del nodo en preorden.
     */
    public void darPreorden( ArregloDinamico preorden )
    {
        preorden.addLast(elem);;
        if( !hijoIzquierdoHoja( ) )
            hijoIzquierdo.darPreorden( preorden );
        if( !hijoDerechoHoja( ) )
            hijoDerecho.darPreorden( preorden );
    }

    /**
     * Verifica si el nodo es una hoja.
     * 
     * @return <code>true</code> si el nodo es una hoja o <code>false</code> en caso contrario.
     * @see NodoRojoNegro#NodoRojoNegro()
     */
    public boolean esHoja( )
    {
        return elem == null;
    }

    /**
     * Retorna el nÃºmero de descendientes del nodo.
     * 
     * @return El nÃºmero de descendientes del nodo.
     */
    public int darPeso( )
    {
        return esHoja( ) ? 0 : 1 + hijoDerecho.darPeso( ) + hijoIzquierdo.darPeso( );
    }

    /**
     * Construye una lista con los descendientes hoja del nodo.
     * 
     * @param hojas La lista en donde se agregarán los descendientes hoja del nodo.
     */
    public void darHojas( ArregloDinamico hojas )
    {
        if( esHoja( ) )
            hojas.addLast((Comparable) this);
        else
        {
            if( !hijoDerechoHoja( ) )
                hijoDerecho.darHojas( hojas );
            if( !hijoIzquierdoHoja( ) )
                hijoIzquierdo.darHojas( hojas );
        }
    }

    /**
     * Retorna la altura del nodo.
     * 
     * @return La altura del nodo.
     */
    public int darAltura( )
    {
        if( esHoja( ) )
            return 0;
        int a1 = hijoIzquierdo.darAltura( );
        int a2 = hijoDerecho.darAltura( );
        return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
    }

    /**
     * Verifica si el nodo tiene un descendiente con la información ingresada por parametro.
     * 
     * @param e Información a buscar.
     * @return <code>true</code> si existe un descendiente del nodo con la información ingresada por parametro o <code>false</code> en caso contrario.
     */
    public boolean existe( T e )
    {
        try
        {
            darNodo( e );
            return true;
        }
        catch( Exception c )
        {
            return false;
        }

    }

    /**
     * Busca el nodo que contiene la información ingresada como parametro.
     * 
     * @param elem Información buscada en el nodo.
     * @return El nodo que contiene la información ingresada por parametro.
     * @throws NoExisteException En caso de no encontrar la información buscada.
     */
    public NodoRojoNegro<T> darNodo( T elem ) throws Exception
    {
        int comp = elem.compareTo( this.elem );
        if( comp == 0 )
            return this;
        else if( comp < 0 )
        {
            if( !hijoIzquierdoHoja( ) )
                return hijoIzquierdo.darNodo( elem );
            else
                return null;
               // throw new ElementoNoExisteException( "El elemento buscado no existe" );
        }
        else
        {
            if( !hijoDerechoHoja( ) )
                return hijoDerecho.darNodo( elem );
            else
                return null;
               // throw new ElementoNoExisteException( "El elemento buscado no existe" );
        }

    }

    /**
     * Retorna la información del nodo.
     * 
     * @return La información guardada en el nodo.
     */
    public T darInfoNodo( )
    {
        return elem;
    }

    /**
     * Verifica si el hijo derecho del nodo es negro.
     * 
     * @return <code>true</code> si el hijo derecho es negro o <code>false</code> en caso contrario.
     */
    public boolean hijoDerechoNegro( )
    {
        return hijoDerecho.color == NEGRO;
    }

    /**
     * Verifica si el hijo izquierdo del nodo es negro.
     * 
     * @return <code>true</code> si el hijo izquierdo es negro o <code>false</code> en caso contrario.
     */
    public boolean hijoIzquierdoNegro( )
    {
        return hijoIzquierdo.color == NEGRO;
    }

    /**
     * Verifica si los hijos del nodo son negro.
     * 
     * @return <code>true</code> si los hijos son negros o <code>false</code> en caso contrario.
     */
    public boolean hijosNegros( )
    {
        return hijoDerechoNegro( ) && hijoIzquierdoNegro( );
    }

    /**
     * Retorna el hermano del nodo.
     * 
     * @return El hermano del nodo o <code>null</code> en caso de no tener hermano.
     */
    public NodoRojoNegro<T> darHermano( )
    {
        if( padre == null )
            return null;
        else
            return padre.esHijoDerecho( this ) ? padre.hijoIzquierdo : padre.hijoDerecho;
    }

    // -----------------------------------------------------------------
    // Modificadores básicos
    // -----------------------------------------------------------------

    /**
     * Modifica el padre del nodo
     * </p>
     * Este método NO mantiene encadenamientos del nuevo padre al nodo.
     * 
     * @param padre Nuevo padre del nodo
     */
    private void cambiarPadre( NodoRojoNegro<T> padre )
    {
        this.padre = padre;
    }

    /**
     * Cambia el color del nodo.
     * 
     * @param color Nuevo color del nodo.
     */
    protected void cambiarColor( int color )
    {
        this.color = color;
    }

    /**
     * Modifica el hijo derecho del nodo.
     * </p>
     * Este mÃ©todo mantiene el encadenamiento de los nodos en ambos sentidos.
     * 
     * @param hijo Nuevo hijo derecho del nodo.
     */
    private void cambiarHijoDerecho( NodoRojoNegro<T> hijo )
    {
        if( hijo != null )
            hijo.cambiarPadre( this );
        hijoDerecho = hijo;
    }

    /**
     * Modifica el hijo izquierdo del nodo.
     * </p>
     * Este método mantiene el encadenamiento de los nodos en ambos sentidos.
     * 
     * @param hijo Nuevo hijo izquierdo del nodo.
     */
    private void cambiarHijoIzquierdo( NodoRojoNegro<T> hijo )
    {
        if( hijo != null )
            hijo.cambiarPadre( this );
        hijoIzquierdo = hijo;
    }

    /**
     * Intercambia la información de dos nodos. Este metodo no implica cambios de encadenamientos.
     * 
     * @param nodo Nodo con el que se quiere intercambiar la información.
     */
    private void cambiarElem( NodoRojoNegro<T> nodo )
    {
        if( nodo.elem != null )
        {
            T aux = elem;
            elem = nodo.elem;
            nodo.elem = aux;
        }
        else
        {
            elem = null;
            color = NEGRO;
            hijoDerecho = hijoIzquierdo = null;
        }
    }

    /**
     * Rota a la derecha el nodo.
     * 
     * @return El nodo rotado a la derecha.
     */
    private NodoRojoNegro<T> rotarIzquierda( )
    {
        if( hijoDerechoHoja( ) )
            return this;
        else
        {
            NodoRojoNegro<T> hijoDerechoAux = hijoDerecho;
            cambiarHijoDerecho( hijoDerechoAux.darHijoIzquierdo( ) );
            hijoDerechoAux.cambiarPadre( padre );
            hijoDerechoAux.cambiarHijoIzquierdo( this );
            return hijoDerechoAux;
        }
    }

    /**
     * Rota a la izquierda el nodo.
     * 
     * @return El nodo rotado a la izquierda.
     */
    private NodoRojoNegro<T> rotarDerecha( )
    {
        if( hijoIzquierdoHoja( ) )
            return this;
        else
        {
            NodoRojoNegro<T> hijoIzquierdoAux = hijoIzquierdo;
            cambiarHijoIzquierdo( hijoIzquierdoAux.darHijoDerecho( ) );
            hijoIzquierdoAux.cambiarPadre( padre );
            hijoIzquierdoAux.cambiarHijoDerecho( this );
            return hijoIzquierdoAux;
        }
    }

    // -----------------------------------------------------------------
    // Métodos de inserción
    // -----------------------------------------------------------------

    /**
     * Inserta un nodo en la raiz de un árbol rojo negro.
     * 
     * @param nodo Nodo a ingresar.
     * @return La nueva raiz del árbol o <code>null</code> si no hubo cambio de raiz.
     * @throws ExisteException Si el no ya existe en el árbol.
     */
    protected NodoRojoNegro<T> insertar( NodoRojoNegro<T> nodo ) throws Exception
    {
        insertarNormal( nodo );
        Retorno r = new Retorno( null );
        nodo.balanceoRojoNegroCaso1( r );
        return r.respuesta;
    }

    /**
     * Inserta un nuevo nodo a partir de <code>this</code> como si el árbol fuera un árbol binario ordenado. El utilizar unicamente este método NO conserva las propiedades
     * del árbol rojo-negro.
     * 
     * @param nodo Nuevo nodo a insertar.
     * @throws ExisteException Si ya existe un nodo con la misma información del nuevo nodo.
     * @see NodoRojoNegro#balanceoRojoNegroCaso1()
     */
    private void insertarNormal( NodoRojoNegro<T> nodo ) throws Exception
    {
        if( elem.compareTo( nodo.darInfoNodo( ) ) == 0 )
        {
            //throw new ElementoExisteException( "El elemento " + nodo.darInfoNodo( ).toString( ) + " ya existe en el árbol" );
        }
        else if( elem.compareTo( nodo.darInfoNodo( ) ) < 0 )
        {
            if( hijoDerechoHoja( ) )
            {
                hijoDerecho = nodo;
                nodo.cambiarPadre( this );
            }
            else
            {
                hijoDerecho.insertarNormal( nodo );
            }
        }
        else
        {
            if( hijoIzquierdoHoja( ) )
            {
                hijoIzquierdo = nodo;
                nodo.cambiarPadre( this );
            }
            else
            {
                hijoIzquierdo.insertarNormal( nodo );
            }
        }
    }

    /**
     * Primer caso de balanceo en inserción. Este caso arranca el balanceo de un nuevo nodo agregado en el árbol.
     * </p>
     * Consiste en agregar un nodo en un árbol de peso 1.
     */
    private NodoRojoNegro<T> balanceoRojoNegroCaso1( Retorno r )
    {
        if( padre == null )
        {
            color = NEGRO;
            r.respuesta = this;
        }
        else
        {
            balanceoRojoNegroCaso2( r );
        }
        return r.respuesta;
    }

    /**
     * Segundo caso de balanceo en inserción.
     * </p>
     * Si el padre del nodo agregado es negro no se debe hacer nada ya que reemplazar una hoja con un nodo rojo no afecta las propiedades del árbol.
     */
    private void balanceoRojoNegroCaso2( Retorno r )
    {
        if( padre.darColor( ) == ROJO )
            balanceoRojoNegroCaso3( r );
        else
            r.respuesta = null;

    }

    /**
     * Tercer caso de balanceo en inserción.
     * 
     * El nodo agregado tiene un padre y un tio rojos.
     */
    private void balanceoRojoNegroCaso3( Retorno r )
    {
        NodoRojoNegro<T> tio = darTio( );
        NodoRojoNegro<T> abuelo = padre.darPadre( );
        r.respuesta = null;

        if( !tio.esHoja( ) && tio.darColor( ) == ROJO )
        {
            darPadre( ).cambiarColor( NEGRO );
            tio.cambiarColor( NEGRO );
            abuelo.cambiarColor( ROJO );
            abuelo.balanceoRojoNegroCaso1( r );
        }
        else
        {
            balanceoRojoNegroCaso4( r );
        }
    }

    /**
     * Cuarto caso de inserción.
     * </p>
     * El nodo ingresado es hijo derecho, su padre es hijo izquierdo y ambos son rojos. El caso reflejado también se cubre.
     */
    private void balanceoRojoNegroCaso4( Retorno r )
    {
        NodoRojoNegro<T> abuelo = padre.darPadre( );
        r.respuesta = null;

        if( padre.esHijoDerecho( this ) && abuelo.esHijoIzquierdo( padre ) )
        {
            abuelo.cambiarHijoIzquierdo( padre.rotarIzquierda( ) );
            hijoIzquierdo.balanceoRojoNegroCaso5( r );
        }
        else if( padre.esHijoIzquierdo( this ) && abuelo.esHijoDerecho( padre ) )
        {
            abuelo.cambiarHijoDerecho( padre.rotarDerecha( ) );
            hijoDerecho.balanceoRojoNegroCaso5( r );
        }
        else
        {
            balanceoRojoNegroCaso5( r );
        }
    }

    /**
     * Quinto caso de inserción
     * </p>
     * El nodo y su padre son hijos izquierdos, son rojos y el tio es negro. El caso reflejado también se cubre.
     */
    private void balanceoRojoNegroCaso5( Retorno r )
    {
        NodoRojoNegro<T> abuelo = padre.darPadre( );

        padre.cambiarColor( NEGRO );
        abuelo.cambiarColor( ROJO );

        if( padre.esHijoIzquierdo( this ) && abuelo.esHijoIzquierdo( padre ) )
        {
            if( abuelo.darPadre( ) == null )
                abuelo.rotarDerecha( );
            else if( abuelo.darPadre( ).esHijoDerecho( abuelo ) )
                abuelo.darPadre( ).cambiarHijoDerecho( abuelo.rotarDerecha( ) );
            else
                abuelo.darPadre( ).cambiarHijoIzquierdo( abuelo.rotarDerecha( ) );

        }
        else
        {
            if( abuelo.darPadre( ) == null )
                abuelo.rotarIzquierda( );
            else if( abuelo.darPadre( ).esHijoDerecho( abuelo ) )
                abuelo.darPadre( ).cambiarHijoDerecho( abuelo.rotarIzquierda( ) );
            else
                abuelo.darPadre( ).cambiarHijoIzquierdo( abuelo.rotarIzquierda( ) );
        }
        r.respuesta = padre;
    }

    // -----------------------------------------------------------------
    // Métodos de eliminación
    // -----------------------------------------------------------------

    /**
     * Elimina el nodo del árbol. El utilizar unicamente este método NO conserva las propiedades del árbol rojo-negro.
     * 
     * @see NodoRojoNegro#eliminarRojoNegro()
     */
    protected NodoRojoNegro<T> eliminar( )
    {
        // Encontrar el mínimo de la derecha o el máximo de la izquierda
        NodoRojoNegro<T> reemplazo = !hijoIzquierdoHoja( ) ? hijoIzquierdo.darMayor( ) : this.darMenor( );

        // Hacer el cambio de los elementos en this y en reemplazo
        cambiarElem( reemplazo );

        // Ahora tenemos que eliminar reemplazo, el cual tiene como maximo un
        // hijo
        Retorno r = new Retorno( null );
        reemplazo.eliminarRojoNegro( r );

        return r.respuesta;
    }

    /**
     * Cambia los hijos de un nodo por hojas
     */
    private void eliminarHijos( )
    {
        hijoDerecho = new NodoRojoNegro<T>( );
        hijoIzquierdo = new NodoRojoNegro<T>( );
    }

    /**
     * Mantiene las propiedades del árbol cuando se va a eliminar un nodo.
     * </p>
     * Para este método se asume que el nodo a eliminar tiene maximo un hijo no-hoja.
     * 
     * @see NodoRojoNegro#eliminar()
     */
    private void eliminarRojoNegro( Retorno r )
    {
        // Encontrar el hijo del nodo a eliminar. Este hijo es el unico hijo del
        // nodo
        NodoRojoNegro<T> hijo = !hijoDerechoHoja( ) ? hijoDerecho : hijoIzquierdo;

        int colorBorrar = darColor( );
        int colorHijo = hijo.darColor( );

        cambiarElem( hijo );
        eliminarHijos( );

        if( colorHijo == ROJO )
        {
            // Eliminar una hoja roja no efecta las propiedades del árbol;
            r.respuesta = this;
            return;
        }
        else if( colorHijo == NEGRO && colorBorrar == ROJO )
        {
            // El nodo a eliminar es negro y su hijo es una hoja roja
            r.respuesta = this;
            cambiarColor( NEGRO );
        }
        else
        {
            // El nodo a eliminar y su hijo son negros
            eliminarCaso1( r );
        }
    }

    /**
     * Primer caso de eliminar.
     * </p>
     * El nodo a eliminar es la raiz y esta no tiene hijos.
     */
    private void eliminarCaso1( Retorno r )
    {
        if( padre != null )
            this.eliminarCaso2( r );
        else
            r.respuesta = null;
    }

    /**
     * El nodo a eliminar es negro y tiene un hermano rojo.
     */
    private void eliminarCaso2( Retorno r )
    {
        // Por la estructura del árbol, este hermano nunca sera null
        NodoRojoNegro<T> hermano = darHermano( );

        if( hermano.color == ROJO )
        {
            padre.color = ROJO;
            hermano.color = NEGRO;

            r.respuesta = hermano;

            NodoRojoNegro<T> abuelo = padre.padre;
            if( padre.esHijoDerecho( this ) )
            {
                if( abuelo != null )
                {
                    if( abuelo.esHijoDerecho( padre ) )
                        abuelo.cambiarHijoDerecho( padre.rotarDerecha( ) );
                    else
                        abuelo.cambiarHijoIzquierdo( padre.rotarDerecha( ) );
                }
                else
                    padre.rotarDerecha( );
            }
            else
            {
                if( abuelo != null )
                {
                    if( abuelo.esHijoDerecho( padre ) )
                        abuelo.cambiarHijoDerecho( padre.rotarIzquierda( ) );
                    else
                        abuelo.cambiarHijoIzquierdo( padre.rotarIzquierda( ) );
                }
                else
                    padre.rotarIzquierda( );
            }
        }
        eliminarCaso3( r );
    }

    /**
     * Tercer caso de eliminación.
     * </p>
     * El nodo a eliminar es negro y tiene un hermano negro.
     */
    private void eliminarCaso3( Retorno r )
    {
        // Por la estructura del árbol, este hermano nunca sera null
        NodoRojoNegro<T> hermano = darHermano( );

        if( padre.color == NEGRO && hermano.color == NEGRO && hermano.hijosNegros( ) )
        {
            hermano.cambiarColor( ROJO );
            padre.eliminarCaso1( r );
        }
        else
        {
            eliminarCaso4( r );
        }
    }

    /**
     * Cuarto caso de eliminación.
     * </p>
     * El nodo a eliminar es negro, su padre es rojo, su hermano es negro y sus sobrinos son negros.
     */
    private void eliminarCaso4( Retorno r )
    {
        // Por la estructura del árbol, este hermano nunca sera null
        NodoRojoNegro<T> hermano = darHermano( );

        if( padre.color == ROJO && hermano.color == NEGRO && hermano.hijosNegros( ) )
        {
            hermano.cambiarColor( ROJO );
            padre.cambiarColor( NEGRO );
        }
        else
        {
            eliminarCaso5( r );
        }
    }

    /**
     * Quinto caso de eliminación.
     * </p>
     * El nodo a eliminar es negro y es hijo izquierdo, su hermano es negro, su sobrino izquierdo es rojo y su sobrino derecho es negro. El caso reflejado también se cubre.
     */
    private void eliminarCaso5( Retorno r )
    {
        // Por la estructura del árbol, este hermano nunca sera null
        NodoRojoNegro<T> hermano = darHermano( );

        if( padre.esHijoIzquierdo( this ) && hermano.color == NEGRO && !hermano.hijoIzquierdoNegro( ) && hermano.hijoDerechoNegro( ) )
        {
            hermano.color = ROJO;
            hermano.hijoIzquierdo.color = NEGRO;
            padre.cambiarHijoDerecho( hermano.rotarDerecha( ) );
        }
        else if( padre.esHijoDerecho( this ) && hermano.color == NEGRO && !hermano.hijoDerechoNegro( ) && hermano.hijoIzquierdoNegro( ) )
        {
            hermano.color = ROJO;
            hermano.hijoDerecho.color = NEGRO;
            padre.cambiarHijoIzquierdo( hermano.rotarIzquierda( ) );
        }
        eliminarCaso6( r );
    }

    /**
     * Sexto caso de eliminación.
     * </p>
     * El nodo a eliminar es negro y es hijo izquierdo, su hermano es negro y su sobrino derecho es rojo. El caso reflejado también se cubre.
     */
    private void eliminarCaso6( Retorno r )
    {
        // Por la estructura del árbol, este hermano nunca sera null
        NodoRojoNegro<T> hermano = darHermano( );

        hermano.color = padre.color;
        padre.color = NEGRO;
        NodoRojoNegro<T> abuelo = padre.padre;

        r.respuesta = hermano;

        if( padre.esHijoIzquierdo( this ) )
        {
            hermano.hijoDerecho.color = NEGRO;

            if( abuelo != null )
            {
                if( abuelo.esHijoDerecho( padre ) )
                    abuelo.cambiarHijoDerecho( padre.rotarIzquierda( ) );
                else
                    abuelo.cambiarHijoIzquierdo( padre.rotarIzquierda( ) );
            }
            else
                padre.rotarIzquierda( );
        }
        else
        {
            hermano.hijoIzquierdo.color = NEGRO;

            if( abuelo != null )
            {
                if( abuelo.esHijoDerecho( padre ) )
                    abuelo.cambiarHijoDerecho( padre.rotarDerecha( ) );
                else
                    abuelo.cambiarHijoIzquierdo( padre.rotarDerecha( ) );
            }
            else
                padre.rotarDerecha( );
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString( )
    {
        return ( elem != null ? elem.toString( ) : "null" ) + ( color == ROJO ? " red" : " black" );
    }
    
    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------    

    /**
     * Estructura para retornar la raíz del árbol rojo-negro resultado de un proceso de modificación.
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------

        /**
         * Raíz del árbol de respuesta
         */
        private NodoRojoNegro<T> respuesta;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * Método constructor de la clase Retorno<br>
         * <b>post: </b> Se construyó en objeto retorno con los valores especificados<br>
         * 
         * @param pRespuesta Raíz del árbol de respuesta
         */
        private Retorno( NodoRojoNegro<T> pRespuesta )
        {
            respuesta = pRespuesta;
        }
    }
}
