package estructuras;
import java.util.Iterator;

/**
 * Clase para heaps mínimos (<i>min heaps</i>). Podemos crear un heap
 * mínimo con <em>n</em> elementos en tiempo <em>O</em>(<em>n</em>), y podemos
 * agregar y actualizar elementos en tiempo <em>O</em>(log <em>n</em>). Eliminar
 * el elemento mínimo también nos toma tiempo <em>O</em>(log <em>n</em>).
 * @param <T>
 */
public abstract class Heap<T extends Comparable<T>> implements Coleccionable<T> {

    /* Clase privada para iteradores de heaps. */
    private class Iterador<T extends Comparable<T>> implements Iterator<T> {

        /* Índice del iterador. */
        private int actual;

        /* Construye un nuevo iterador, auxiliándose del heap mínimo. */
        public Iterador() {
			this.actual = 0;
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
			if(actual == arreglo.length) return false;
			if(arreglo[actual] == null) return false;
			return true;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
			return (T) arreglo[actual++];
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* El siguiente índice dónde agregar un elemento. */
    protected int siguiente;
    /* El arreglo que guarda los datos con la estructura de árbol ue requiere el heap.*/
    protected T[] arreglo;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new Comparable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #HeapMinimo(Lista)}, pero se ofrece este constructor por completez.
     */
    public Heap() {
		this.arreglo = creaArregloGenerico(14);
    }

    /**
     * Constructor para heap mínimo que recibe un coleccionable. Es más barato
     * construir un heap con todos sus elementos de antemano (tiempo
     * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
     * <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param coleccionable la lista a partir de la cuál queremos construir el
     * heap.
     */
    public Heap(Coleccionable<T> coleccionable) {
		this.arreglo = creaArregloGenerico(coleccionable.getTamanio());
		Iterator it = coleccionable.iterator();
		while(it.hasNext()){
			agregar((T) it.next());
		}
    }

    /*
     * Métodos auxiliares.
	 * Cambia a i por j.
     */
    private void intercambia(int i, int j){
		T auxElem = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = auxElem;
    }

    /**
     * Método auxiliar que devuelve la posición del hijo izquierdo,
     * si no tiene hijo izquiero devuelve -1.
     */
    private int izquierdo(int i){
		int toReturn = (2*i) + 1;
		if(arreglo[toReturn] == null){
			return -1;
		}
		return toReturn;
    }

    /**
     * Método auxiliar que devuelve la posición del hijo derecho,
     * si no tiene hijo derecho devuelve -1.
     */
    private int derecho(int i){
		int toReturn = (2*i) + 2;
		if(arreglo[toReturn] == null){
			return -1;
		}
		return toReturn;
    }

    /**
     * Método auxiliar que devuelve la posición del padre.
     * Si no tiene padre, devuelve -1;
     */
    private int padre(int i){
		if(i == 0){
			return -1;
		}
		return (int) (i-1) / 2;
    }

    /**
     * Agrega un nuevo elemento en el heap.
     * @param elemento el elemento a agregar en el heap.
     */
    @Override public void agregar(T elemento) {
		if(siguiente == arreglo.length){
			T[] aux = creaArregloGenerico(arreglo.length * 2);
			for(int i = 0; i < arreglo.length; i++){
				aux[i] = arreglo[i];
			}
			arreglo = aux;
		}
		arreglo[siguiente] = elemento;
		reordena(siguiente++);
    }

    /**
     * Método que reordena hacia abajo un elemento en un heap.
     * Si el elemento que queremos está en un orden incorrecto,
     * se intercambia con el hijo correspondiente para que el heap sea
     * válido. Es importante considerar si el heap es mínimo o máximo.
     * @param n
     */
    protected void reordenaParaAbajo(int n, boolean esMin){
		if(esMin){
			int aux = getMenor(n, izquierdo(n), derecho(n));
			if(aux != n){
				intercambia(aux, n);
				reordenaParaAbajo(aux, true);
			}
		} else {
			int aux = getMayor(n, izquierdo(n), derecho(n));
			if(aux != n){
				intercambia(aux, n);
				reordenaParaAbajo(aux, false);
			}
		}
    }

    /**
     * Elimina el elemento hasta arriba del heap.
     * @return el elemento mínimo o máximo del heap.
     * @throws IllegalStateException si el heap es vacío.
     */
    public abstract T eliminar();

    /**
     * Puesto que no deberíamos eliminar un elemento que
     * no sea el mínimo, lanzamos una excepción. En una
     * implementación más aplicada es posible implementar
     * esta operación con los métodos ya implementados sin mucho
     * esfuerzo.
     * @param elemento a eliminar del heap.
     * @throws IllegalStateException. Esta operación no debería ser posible
     * en un Heap
     */
    @Override public void eliminar(T elemento) {
		throw new IllegalStateException("Esta operación no debería ser válida para Heaps");
    }

    /**
     * Nos dice si un elemento está contenido en el heap.
     * @param elemento el elemento que queremos saber si está contenido.
     * @return <code>true</code> si el elemento está contenido,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
		Iterator it = this.iterator();
		while(it.hasNext()){
			if(it.next() == elemento) return true;
		}
		return false;
    }

    /**
     * Nos dice si el heap es vacío.
     * @return <tt>true</tt> si ya no hay elementos en el heap,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacia() {
		if(siguiente == 0) return true;
		return false;
    }


    @Override
    /**
     * Elimina todos los elementos del heap.
     * pone todos los espacios del arreglo en <code>null</code>
     */
    public void vaciar(){
		for(int i = 0; i < siguiente; i++){
			arreglo[i] = null;
		}
		siguiente = 0;
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     * Recibe tres índices de elementos en el arreglo.
     */
    private int getMenor(int elemento, int i, int d){
		int aux = elemento;
		if(i != -1){
			if(arreglo[i].compareTo(arreglo[aux]) <= 0){
				aux = i;
			}
		}
		if(d != -1){
			if(arreglo[d].compareTo(arreglo[aux]) <= 0){
				aux = d;
			}
		}
		return aux;
    }

    /* Método auxiliar que regresa el índice en el arreglo que
     * tiene al elemento menor de tres.
     * Recibe tres índices de elementos en el arreglo.
     */
    private int getMayor(int elemento, int i, int d){
		int aux = elemento;
		if(i != -1){
			if(arreglo[i].compareTo(arreglo[aux]) >= 0){
				aux = i;
			}
		}
		if(d != -1){
			if(arreglo[d].compareTo(arreglo[aux]) >= 0){
				aux = d;
			}
		}
		return aux;
    }

    /**
     * Reordena un elemento en el árbol.
     * La implementación se deja a la clase concreta, pues el reordenamiento
     * depende de si es un heap mínimo o máximo.
     * @param elemento el elemento que hay que reordenar.
     */
    protected abstract void reordena(int elemento);

    /**
     * Este método hace la chamba, dependiendo de si
     * el heap es mínimo o máximo.
     * @param elemento el elemento a reordenar.
     * @param esMin nos dice si el heap que manda a llamar esta operación es mínimo.
     */
    protected void reordena(int elemento, boolean esMin){
		int aux = padre(elemento);
		if(esMin){
			while(aux != -1){
				if(arreglo[aux].compareTo(arreglo[elemento]) > 0){
					intercambia(aux, elemento);
					elemento = aux;
					aux = padre(elemento);
				} else {
					break;
				}
			}
		} else {
			while(aux != -1){
				if(arreglo[aux].compareTo(arreglo[elemento]) < 0){
					intercambia(aux, elemento);
					elemento = aux;
					aux = padre(elemento);
				} else {
					break;
				}
			}
		}
    }


    /**
     * Regresa el número de elementos en el heap.
     * @return el número de elementos en el heap.
     */
    @Override public int getTamanio() {
		return siguiente;
    }

	/* Método agregado para debuguear
	public T raíz(){
		return arreglo[0];
	}
	*/

    @Override
    /**
     * Regresa la representación en cadena del heap.
     * @return la representación en cadena del árbol.
     */
    public String toString(){
		String str = "Heap en forma de array: ";
		Iterator it = iterator();
		while(it.hasNext()){
			str += it.next() + "  ";
		}
		return str;
    }

    /**
     * Regresa un iterador para recorrer el heap.
     * @return un iterador para recorrer el heap.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador<T>();
    }
}
