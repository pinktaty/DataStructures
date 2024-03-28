import estructuras.*;
import java.util.Iterator;

/**
 * <p>Clase para modelar árboles binarios de búsqueda genéricos.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T> {
	// CHECAR EL ITERADOR
    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los nodos en DFS in-order. */
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


    /**
     * Constructor que no recibe parámeteros. {@link ArbolBinario}.
     */
    public ArbolBinarioBusqueda() {

    }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */

    public ArbolBinarioBusqueda(Coleccionable<T> coleccion) {
		// Crea un árbol tomando a la colección como preorden
		if(!coleccion.esVacia()){
			Iterator it = coleccion.iterator();
			raiz.elemento = (T) it.next();
			while(it.hasNext()){
				T elemento = (T) it.next();
				Nodo aux = raiz;
				if((Integer) elemento >= (Integer) aux.elemento){
					while(aux.hayDerecho()){
						aux = aux.derecho;
					}
					aux.derecho = nuevoNodo(elemento);
				} else {
					while(aux.hayIzquierdo()){
						aux = aux.izquierdo;
					}
					aux.izquierdo = nuevoNodo(elemento);
				}
			}
		}
    }
	
    protected void agregarNodo(Nodo<T> n, Nodo<T> nuevo){
		if(raiz == null){
			raiz = nuevo;
		} else {
			if(nuevo.elemento.compareTo(n.elemento) < 0){
				if(n.hayIzquierdo()){
					agregarNodo(n.izquierdo, nuevo);
				} else {
					n.izquierdo = nuevo;
					nuevo.padre = n;
				}
			} else {
				if(n.hayDerecho()){
					agregarNodo(n.derecho, nuevo);
				} else {
					n.derecho = nuevo;
					nuevo.padre = n;
				}
			}
		}
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agregar(T elemento) {
		Nodo nuevo = nuevoNodo(elemento);
		agregarNodo(raiz, nuevo);
		tamanio++;
    }



    protected Nodo<T> eliminarNodo(Nodo<T> n){
		Nodo toReturn = n.padre;
		if(!n.hayIzquierdo() && !n.hayDerecho()){
			if(n != raiz){
				if(n.padre.izquierdo == n){
					n.padre.izquierdo = null;
					return toReturn;
				} else {
					n.padre.derecho = null;
					return n;
				}
			} else {
				raiz = null;
				return toReturn;
			}			
		} else if(!n.hayIzquierdo() || !n.hayDerecho()){
			Nodo hijo = null;
			if(n.hayIzquierdo()){
				hijo = n.izquierdo;
			} else {
				hijo = n.derecho;
			}
			if(n != raiz){
				if(n == n.padre.izquierdo){
					n.padre.izquierdo = hijo;
					return toReturn;
				} else {
					n.padre.derecho = hijo;
					return toReturn;
				}
			} else {
				raiz = hijo;
				raiz.padre = null;
				return toReturn;
			}
		} else {
			Nodo sucesor = maximoEnSubarbolIzquierdo(n);
			T elem = (T) sucesor.elemento;
			eliminarNodo(sucesor);
			n.elemento = elem;
			return toReturn;
		}
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void eliminar(T elemento) {
		eliminarNodo(buscaNodo(raiz, elemento));
		tamanio--;
    }

	// Recibe la raíz del arbol el cuál tiene el subárbol izquierdo
    private Nodo maximoEnSubarbolIzquierdo(Nodo n){
		Nodo aux = n.izquierdo;
		if(aux == null) return aux;
		while(aux.hayDerecho()){
			aux = aux.derecho;
		}
		return aux;
    }

    /**
     * Nos dice si un elemento está contenido en el arbol.
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la arbol.
     * @return <code>true</code> si el elemento está contenido en el arbol,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento){
		if(raiz == null) return false;
		Nodo aux = raiz;
		while(aux != null){
			if(elemento == aux.elemento) return true;
			if((Integer) elemento > (Integer) aux.elemento){
				aux = aux.derecho;
			} else {
				aux = aux.izquierdo;
			}
		}
		return false;
    }

	// Interpreto este método como buscar el nodo que contiene al elemento, el cuál se va a regresar
	// Recibe el elemento a buscar y un nodo a partir del cuál se va a llevar a cabo la búsqueda
    protected Nodo<T> buscaNodo(Nodo<T> n, T elemento){
		if(n.elemento == elemento) return n;
		Nodo aux = n;
		while(aux.elemento != elemento){
			if((Integer) elemento > (Integer) aux.elemento){
				aux = aux.derecho;
			} else {
				aux = aux.izquierdo;
			}
		}
		return aux;
    }


    /**
     * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo no
     * tiene hijo izquierdo, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraDerecha(Nodo<T> nodo) {
		if(nodo.hayIzquierdo()){
			Nodo x = nodo.izquierdo;
			Nodo t2 = x.derecho;

			x.derecho = nodo;
			nodo.padre = x;
			nodo.izquierdo = t2;
			t2.padre = nodo;
		}
    }

    /**
     * Gira el árbol a la izquierda sobre el nodo recibido. Si el nodo no
     * tiene hijo derecho, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    public void giraIzquierda(Nodo<T> nodo) {
		if(nodo.hayDerecho()){
			Nodo y = nodo.derecho;
			Nodo t2 = y.izquierdo;

			y.izquierdo = nodo;
			nodo.padre = y;
			nodo.derecho = t2;
			t2.padre = nodo;
		}
    }


    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

}
