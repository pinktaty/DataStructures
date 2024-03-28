import estructuras.*;
import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * <p>Clase abstracta para modelar la estructura de datos Arbol Binario</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.</p>
 * <p>Puesto que todos los árboles binarios comparten algunas características similares,
 * esta clase sirve perfectamente para modelarlas. Sin embargo no es lo suficientemente
 * específica para modelar algun árbol completamente. Por lo que la implementación
 * final depende de las clases concretas que hereden de ésta.</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */

public abstract class ArbolBinario<T> implements Coleccionable<T> {
    /**
     * Clase interna protegida para nodos.
     */
    protected class Nodo<T> {

        /** El elemento del nodo. */
        public T elemento;
        /** Referencia a los nodos padre, e hijos. */
        public Nodo<T> padre, izquierdo, derecho;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
			this.elemento = elemento;
        }

        /**
         * Nos dice si el nodo tiene un padre.
         * @return <tt>true</tt> si el nodo tiene padre,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayPadre() {
			return this.padre != null;
        }

        /**
         * Nos dice si el nodo tiene un izquierdo.
         * @return <tt>true</tt> si el nodo tiene izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayIzquierdo() {
			return this.izquierdo != null;
        }

        /**
         * Nos dice si el nodo tiene un derecho.
         * @return <tt>true</tt> si el nodo tiene derecho,
         *         <tt>false</tt> en otro caso.
         */
        public boolean hayDerecho() {
			return this.derecho != null;
        }

        /**
         * Regresa la altura del nodo.
         * @return la altura del nodo.
         */
        public int altura() {
			if(this == null) return -1;
			if (!this.hayIzquierdo() && !this.hayDerecho()){
				return 0;
			}
			int alturaIzquierda = 0;
			int alturaDerecha = 0;
			if(this.hayIzquierdo()){
				alturaIzquierda = this.izquierdo.altura();
			}
			if(this.hayDerecho()){
				alturaDerecha = this.derecho.altura();
			}
			return Math.max(alturaIzquierda, alturaDerecha) + 1;
        }

        /**
         * Regresa el elemento al que apunta el nodo.
         * @return el elemento al que apunta el nodo.
         */
        public T get() {
			return elemento;
        }

        /**
         * Compara el nodo con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Nodo} deben
         * sobrecargar el método {@link Nodo#equals}.
         * @param o el objeto con el cual se comparará el nodo.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Nodo}, su elemento es igual al elemento de éste
         *         nodo, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override
	   public boolean equals(Object o) {
		   if(o instanceof Nodo){
			   Nodo oNodo = (Nodo) o;
			   if(this != null && oNodo != null){
				   if(this.elemento == oNodo.elemento){
					   if(checarHijos(this.izquierdo, oNodo.izquierdo) && checarHijos(this.derecho, oNodo.derecho)) return true;
					   return false;
				   }
				   return false;
			   }
			   return false;
			}
			return false;
        }

		// Función auxiliar
		private boolean checarHijos(Nodo n, Nodo o){
			if(n == null && o == null) return true;
			if(n.elemento == o.elemento && checarHijos(n.izquierdo, o.izquierdo) && checarHijos(n.derecho, o.derecho)) return true;
			return false;
		}


        /**
         * Regresa una representación en cadena del nodo.
         * @return una representación en cadena del nodo.
         */
        public String toString() {
	    	return elemento.toString();
        }
    }

    /** La raíz del árbol. */
    protected Nodo<T> raiz;
    /** El número de elementos */
    protected int tamanio;

    /**
     * Constructor sin parámetros.
     */
    public ArbolBinario() {}

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public ArbolBinario(Coleccionable<T> coleccion) {
		// Como es un árbol binario, no tiene regla definida, así que la función añade los elementos a izquierda o derecha de forma random
		if(!coleccion.esVacia()){
			Iterator it = coleccion.iterator();
			raiz.elemento = (T) it.next();
			while(it.hasNext()){
				T elemento = (T) it.next();
				Nodo aux = raiz;
				if(Math.random()*10 > 4){
					while(aux.derecho != null){
						aux = aux.derecho;
					}
					aux.derecho = nuevoNodo(elemento);
				} else {
					while(aux.izquierdo != null){
						aux = aux.izquierdo;
					}
					aux.izquierdo = nuevoNodo(elemento);
				}
			}
		}
    }

    /**
     * Construye un nuevo nodo, usando una instancia de {@link Nodo}. Para
     * crear nodos se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de nodos.
     * @param elemento el elemento dentro del nodo.
     * @return un nuevo nodo con el elemento recibido dentro del mismo.
     */
    protected Nodo<T> nuevoNodo(T elemento) {
		return new Nodo(elemento);
    }


    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     * @return la altura del árbol.
     */
    public int altura() {
		if(raiz == null) return -1;
		return raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    @Override
    public int getTamanio() {
		return tamanio;
    }


    /**
     * Regresa el nodo que contiene la raíz del árbol.
     * @return el nodo que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public Nodo<T> raiz() {
		return raiz;
    }

    /**
     * Nos dice si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean esVacia() {
		return raiz == null;
    }

    /*
     * Método para eliminar todos los elementos en la colección
     */
    public void vaciar(){
		this.raiz = null;
		this.tamanio = 0;
    }


     /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> inOrden(){
		Cola c = new Cola();
		if(this.raiz == null) return c;
		inordenAux(this.raiz, c);
		return c;
    }

    /**
     * Regresa una Cola con el los elementos en inorden del árbol.
     * @return Cola con los elementos del arbol.
     */

    public Lista<T> preOrden(){
		Cola c = new Cola();
		if(this.raiz == null) return c;
		preordenAux(this.raiz, c);
		return c;
    }
    /**
     * Regresa una Lista con el los elementos en inorden del árbol.
     * @return Cola con los elementos del arbol.
     */
    public Lista<T> postOrden(){
		Cola c = new Cola();
		if(this.raiz == null) return c;
		postordenAux(this.raiz, c);
		return c;
    }

    private void inordenAux(Nodo<T> n,Lista<T> l){
		Cola c = (Cola) l;
		if(n != null){
			inordenAux(n.izquierdo, c);
			c.queue(n.elemento);
			inordenAux(n.derecho, c);
		}
    }

    private void preordenAux(Nodo<T> n, Lista<T> l){
		Cola c = (Cola) l;
		if(n != null){
			c.queue(n.elemento);
			preordenAux(n.izquierdo, c);
			preordenAux(n.derecho, c);
		}
    }

    private void postordenAux(Nodo<T> n, Lista<T> l){
		Cola c = (Cola) l;
		if(n != null){
			postordenAux(n.izquierdo, c);
			postordenAux(n.derecho, c);
			c.queue(n.elemento);
		}
    }


    /**
     * Compara el árbol con un objeto.
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o) {
		if(o instanceof ArbolBinario){
			ArbolBinario oArbolBinario = (ArbolBinario) o;
			return this.raiz.equals(oArbolBinario.raiz);
		}
		return false;
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
		if(this.raiz == null) return "";
		boolean[] r = new boolean[this.altura() + 1];
		for(int i = 0; i < this.altura() + 1; i++){
			r[i] = false;
		}
		return cadena(raiz, 0, r);
    }

	private String cadena(Nodo v, int n, boolean[] r){
		String s = v +"\n";
		r[n] = true;
		if(v.izquierdo != null && v.derecho != null){
			s += dibujaEspacios(n, r);
			s += " ├─›";
			s += cadena(v.izquierdo, n+1, r);
			s += dibujaEspacios(n, r);
      s += "└─»";
      r[n] = false;
      s += cadena(v.derecho, n+1, r);
		} else if(v.izquierdo != null){
      s += dibujaEspacios(n, r);
      s += "└─›";
      r[n] = false;
      s += cadena(v.izquierdo, n+1, r);
    } else if(v.derecho != null){
      s += dibujaEspacios(n, r);
      s += "└─»";
      r[n] = false;
      s += cadena(v.derecho, n+1, r);
    }
		return s;
	}

	private String dibujaEspacios(int n, boolean[] r){
		String s = "";
		for(int i = 0; i < n; i++){
			if(r[i]){
				s += "│  ";
			} else {
				s += "   ";
			}
		}
		return s;
	}
}
