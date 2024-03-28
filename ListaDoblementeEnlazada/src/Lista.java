import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.1
 */
public class Lista<T> implements Listable<T>, Iterable<T>, Coleccionable<T>{

    /* Clase interna para construir la estructura */
    private class Nodo{
	/* Referencias a los nodos anterior y siguiente */
        public Nodo anterior, siguiente;
        /* El elemento que almacena un nodo */
        public T elemento;

        /* Unico constructor de la clase */
        public Nodo(T elemento){
	    	this.elemento = elemento;
	   		this.siguiente = null;
        }
        
        public boolean equals(Nodo n){
	    //Si el elemento del nodo n es igual al elemento del nodo que manda a llamar a la
	    //función
            if(this == n.elemento){
				return  true;
	   		 }
	    	return false;
        }
    }
    
    private class IteradorLista implements Iterator{
        /* La lista a recorrer*/
        private Lista<T> lista;
        /* Elementos del centinela que recorre la lista*/
        private Lista<T>.Nodo anterior, siguiente;
        
        
        public IteradorLista(Lista<T> lista){
           	this.lista = lista;
	   		siguiente = lista.cabeza;
	   		anterior = null;
        }
        @Override
        public boolean hasNext() {
	    	return siguiente != null;
        }

        @Override
        public T next() {
			T elemTemp = siguiente.elemento;
			siguiente = siguiente.siguiente;
			return elemTemp;
        }

        @Override
        public void remove(){
	    	throw new UnsupportedOperationException("Operacion no valida");    
        }
    }

    /* Atributos de la lista */
    private Nodo cabeza, cola;
    private int longitud;
    
    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacia(){
		if(longitud == 0){
		    return true;
		}
		return false;
    }
    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar(){
		this.cabeza = null;
		this.cola = null;
		longitud = 0;
    }
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    public int getTamanio(){
		return this.longitud;
    }
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento) throws IllegalArgumentException{
		agregarAlInicio(elemento);
    }
        /**
     * Método para agregar al inicio un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento){
		Nodo newNodo = new Nodo(elemento);
		if(esVacia()){
		    cabeza = cola = newNodo;
		} else {
		    cabeza.anterior = newNodo;
		    newNodo.siguiente = cabeza;
		    cabeza = newNodo;
		}
		longitud++;
    }   
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento){
		Nodo newNodo = new Nodo(elemento);
		if(esVacia()){
		    cabeza = cola = newNodo;
		} else {
		    cola.siguiente = newNodo;
		    newNodo.anterior = cola;
		    cola = newNodo;
		}
		longitud++;
    }
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    public boolean contiene(T elemento){
		Iterator it = iterator();
		boolean doesIt = false;
		while(it.hasNext()){
		    T elementoSig  = (T) it.next();
		    if(elemento == elementoSig){
				doesIt = true;
		    }
		 }
		return doesIt;
    }
    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento) throws NoSuchElementException{
		if(contiene(elemento)){
		    if(elemento.equals(cabeza)){
				cabeza = cabeza.siguiente;
		    } else {
				Iterator it = iterator();
				Nodo aux = cabeza;
				while(aux.siguiente.elemento != elemento){
	    			aux = aux.siguiente;
				}
				//Guardar el nodo siguiente al nodo a eliminar
				Nodo siguienteNodo = aux.siguiente.siguiente;
				//Enlazar el nodo anterior al que se va a eliminar con el siguiente del que se va a
				//elimianar
				aux.siguiente = siguienteNodo;
	    	}
	    	longitud--;
		} else {
			throw new NoSuchElementException();
		}
    }
    
    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento){
		int indice = 0;
		Iterator it = iterator();
        while(it.hasNext()){
	    	T elemSig = (T) it.next();
            if(elemento != elemSig){
				indice++;
	    	} else {
				return indice;
	    	}
		}
		return -1;
    }
    
    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista, 
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException{
		if(this.longitud <= i || i < 0){
		    throw new IndexOutOfBoundsException();
		}
		int indice = 0;
		T elemSig = null;
		Iterator it = iterator();
		while(indice < i && it.hasNext()){
		    elemSig = (T) it.next();
		}
		return elemSig;
    }
    
    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    public Lista<T> reversa(){
		Lista<T> nuevaLista = new Lista<T>();
		Nodo actual = this.cola; 
		while(actual != null){
		    nuevaLista.agregarAlFinal(actual.elemento);
		    actual = actual.anterior;
		}
		return nuevaLista;
    }
    
    /**
     * Método que devuelve una copi exacta de la lista
     * @return la copia de la lista.
     */
    public Lista<T> copia(){
		Lista<T> nuevaLista = new Lista<T>();
		Nodo actual = this.cabeza;
		while(actual != null){
	    	nuevaLista.agregar(actual.elemento);
	    	actual = actual.siguiente;
		}
		return nuevaLista;
    }
    
    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
		boolean isIt = true;
		Lista<T> listaO = (Lista<T>) o;
		if(getTamanio() != listaO.getTamanio()){
		    isIt = false;
		} else {
		    Iterator it1 = listaO.iterator();
		    Iterator it2 = iterator();
		    while(it1.hasNext() && it2.hasNext()){
			if(it1.next() != it2.next()){
			    isIt= false;
			    break;
			}
	    	}
		}
		return isIt;
    }
    
    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator iterator(){
        return new IteradorLista(this);
    }
    
      /**
     * Método que devuelve una copia de la lista.
     * @param <T> Debe ser un tipo que extienda Comparable, para poder distinguir
     * el orden de los elementos en la lista.
     * @param l La lista de elementos comparables.
     * @return copia de la lista ordenada.
     */
    public static <T extends Comparable<T>> Lista <T> mergesort(Lista<T>l){
		if(l.longitud == 1){
			return l.copia();
		}
		Lista<T> l1 = new Lista<T>();
		Lista<T> l2 = new Lista<T>();
		int contador = 0;
		int mitad = l.longitud/2;
		for(T elemento: l){
			if(contador < mitad){
				l1.agregar(elemento);
			} else {
				l2.agregar(elemento);
			}
			contador++;
		}
		l1 = mergesort(l1);
		l2 = mergesort(l2);
		return merge(l1, l2);
    }

    public static <T extends Comparable<T>> Lista <T> merge(Lista<T> l1,Lista<T> l2){
		Lista<T> nuevaLista = new Lista<T>();
		Iterator it1 = l1.iterator();
		Iterator it2 = l2.iterator();
		T elem1 = (T) it1.next();
		T elem2 = (T) it2.next();
		while(it1.hasNext() && it2.hasNext()){
			if(elem1.compareTo(elem2) < 0){
				nuevaLista.agregarAlFinal(elem1);
				elem1 = (T) it1.next();
			} else {
				nuevaLista.agregarAlFinal(elem2);
				elem2 = (T) it2.next();
			}
		}
		while(it1.hasNext()){
			nuevaLista.agregar(elem1);
			elem1 = (T) it1.next();
		}
		while(it2.hasNext()){
			nuevaLista.agregar(elem2);
			elem2 = (T) it2.next();
		}
		return nuevaLista;
	} 
	
    @Override
    public String toString(){
		String str = "Lista: ";
		Iterator it = iterator();
		while(it.hasNext()){
		    str += it.next() + " ";
		}
		return str;
    }
}
