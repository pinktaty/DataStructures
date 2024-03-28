import estructuras.*;
import java.util.Iterator;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus nodos, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioBusqueda<T> {

    /**
     * Clase interna protegida para nodos de árboles AVL. La única diferencia
     * con los nodos de árbol binario, es que tienen una variable de clase
     * para la altura del nodo.
     */
    protected class NodoAVL extends ArbolBinario<T>.Nodo<T> {

        /** La altura del nodo. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public NodoAVL(T elemento) {
			super(elemento);
        }

        private boolean equals(NodoAVL v, NodoAVL v2){
			Nodo vNodo = (Nodo) v;
			Nodo v2Nodo = (Nodo) v2;
			return vNodo.equals(v2Nodo);
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link NodoAVL}, su elemento es igual al elemento de éste
         *         nodo, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
			if(o instanceof Nodo){
				Nodo vNodo = (Nodo) this;
				Nodo oNodo = (Nodo) o;
				return vNodo.equals(oNodo);
			}
			return false;
        }

		public String toString(){
			return this.elemento.toString();
		}
    }

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos en DFS in-order. */
		// Dice pila, por lo que cambié la cola por una pila
        private Pila<Nodo<T>> pila;

        /* Construye un iterador con el nodo recibido. */
        public Iterador() {
			pila = new Pila();
			irIzquierda(raiz);
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
			return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
			Nodo actual = pila.pop();
			if(actual.hayDerecho()){
				irIzquierda(actual.derecho);
			}
			return (T) actual.elemento;
        }

		// Función auxiliar
		private void irIzquierda(Nodo próximo){
			Nodo aux = próximo;
			while(aux != null){
				pila.push(aux);
				aux = aux.izquierdo;
			}
		}
    }

    public ArbolAVL(Coleccionable<T> coleccion){
		super(coleccion);
    }

    private void actualizaAltura(NodoAVL v){
		v.altura = v.altura();
		if(v.padre != null) actualizaAltura((NodoAVL) v.padre);
    }

    private void rebalancea(NodoAVL nodo) {
		int altura = getAltura(nodo);
		if(altura > 1){
			if(nodo.derecho.derecho.altura() > nodo.derecho.izquierdo.altura()){
				super.giraIzquierda(nodo);
			} else {
				super.giraDerecha(nodo.derecho);
				super.giraIzquierda(nodo);
			}
		} else if(altura < -1){
			if(nodo.izquierdo.izquierdo.altura() > nodo.izquierdo.derecho.altura()){
				super.giraDerecha(nodo);
			} else {
				super.giraIzquierda(nodo.izquierdo);
				super.giraDerecha(nodo);
			}
		} else {
			rebalancea((NodoAVL) nodo.padre);
		}
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioBusqueda#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     * @param elemento el elemento a agregar.
     */
    @Override public void agregar(T elemento) {
		super.agregar(elemento);
		NodoAVL n = (NodoAVL) buscaNodo(this.raiz, elemento);
		rebalancea(n);
    }

    private Nodo maximoEnSubarbol(Nodo n){
		if(n.derecho != null){
			Nodo aux = n.derecho;
			while(aux.hayDerecho()){
				aux = aux.derecho;
			}
			return aux;
		} else if(n.izquierdo != null){
			Nodo aux = n.izquierdo;
			while(aux.hayDerecho()){
				aux = aux.derecho;
			}
			return aux;
		}
		return n;
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void eliminar(T elemento) {
		Nodo n = buscaNodo(this.raiz, elemento);
		if(n != null){
			eliminarNodo(n);
			rebalancea((NodoAVL) n);
		}
    }

    /**
     * Regresa la altura del nodo AVL.
     * @param nodo el nodo del que queremos la altura.
     * @return la altura del nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    public int getAltura(Nodo<T> nodo) {
		NodoAVL n = (NodoAVL) nodo;
		actualizaAltura(n);
		return n.altura;
    }


    /**
     * Convierte el nodo (visto como instancia de {@link
     * Nodo}) en nodo (visto como instancia de {@link
     * NodoAVL}). Método auxililar para hacer este cast en un único
     * lugar.
     * @param nodo el nodo de árbol binario que queremos como nodo AVL.
     * @return el nodo recibido visto como nodo AVL.
     * @throws ClassCastException si el nodo no es instancia de {@link
     *         NodoAVL}.
     */
    protected NodoAVL nodoAVL(Nodo<T> nodo) {
        return (NodoAVL)nodo;
    }

    public static void main(String[] args) {
        Pila<Integer> pila = new Pila();
        pila.agregar(4);
        pila.agregar(5);
        pila.agregar(2);

        ArbolAVL av = new ArbolAVL((Coleccionable) pila);
        System.out.print(av);

        //*******      Agregar nodos
        System.out.println("\n\nAgregar nodos 8, 10, 9");
        av.agregar(8);
        av.agregar(10);
        av.agregar(9);


        System.out.print(av);

        System.out.println("\n\nAgregar nodos 9, 8, 7, 1");
        av.agregar(9);
        av.agregar(8);
        av.agregar(7);
        av.agregar(1);
        System.out.print(av);

        //*******      Eliminar nodos

        System.out.println("\n\nEliminar nodo 10 y 9");
        av.eliminar(10);
        av.eliminar(9);
        System.out.print(av);

    }
}
