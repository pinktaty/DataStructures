/**
 * <p> Clase que modela conjuntos </p> <p>Esta clase sirve para crear
 * conjuntos y hacer operaciones entre ellos</p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Conjunto <T> implements Iterable, Conjuntable<T> {
    /* Arreglo donde guardaremos los elementos de un conjunto */
    private T [] conjunto;
    /* Número de elementos que tiene el conjunto*/
    private int elementos;

    private class IteradorConjunto implements Iterator{
        /* Variable para contar cuantos elementos hay en el conjunto
        no debe tener repeticiones, ni espacios en blanco para que funcione */
	   int contador;
	   public IteradorConjunto(){
	      contador = 0;
	   }
    
	   public boolean hasNext(){
	       return contador < cardinalidad();
	   }
    
	   public Object next(){
	       if (!hasNext()){
	         throw new NoSuchElementException();
	       } else {
	         return conjunto[contador++];
	       }
	   }	
    }
    
    public Conjunto(){
       conjunto = conjunto;
    }
    
    public Conjunto(Conjunto<T> c){
       //Sin terminar, no entendí como aplicarlo
       this. elementos = c.cardinalidad();
    }

    public Conjunto(T[] elementos){
        this.conjunto = elementos;
        this.elementos = elementos.length;
    }
    
    /**
     * Método que nos dice si el conjunto está vacío.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    @Override
    public boolean esVacio(){
       boolean isIt = false;
	    if(cardinalidad() == 0){
	      isIt = true;
	    }
	    return isIt;
    }
    
    /**
     * Método para obtener el tamaño de un conjunto
     * @return número de elementos en el conjunto
     */
    @Override
    public int cardinalidad(){
        return elementos;
    }
    
    /**
     * Método para eliminar todos los elementos de un conjunto
     */
    @Override

    public void vaciar(){
	    //Array auxiliar
       T arr[] = (T[]) new Object[0];
       conjunto = arr;
       elementos = 0;
    }
    
    /**
     * Método para agregar un elemento al conjunto
     * @param elemento Objeto que se incorporara al conjunto
     */
    @Override
    public void agregar(T elemento){
        //Si el conjunto que mandó a llamar al método no contiene al elemento que quiere introducir
        if(contiene(elemento) == false){
            //Intentar meter el elemento al conjunto
            try{
               conjunto[cardinalidad()] = elemento;
            //Si no se logra, atrapar excepción
            } catch (IndexOutOfBoundsException e){
               //Generar una nueva array con un espacio más
               T arr[] = (T[]) new Object[cardinalidad() + 1];
               int index = 0;
               Iterator it = iterator();
               while(it.hasNext()){
                  //Copiar todos los elementos del conjunto al array
                  arr[index] = (T) it.next();
                  index++;
               }
               //Meter el elemento a la array
               arr[index] = elemento;
               //Volver a mi array un conjunto
               conjunto = arr;
            }
            elementos++;
        }
    }
    
    /**
     * Método para eliminar un <code>elemento</code> del conjunto
     * @param elemento Objeto que se eliminara del conjunto
     */
    @Override
    public void eliminar(T elemento){ //POR REVISAR
       //Array auxiliar
       T arr[] = (T[]) new Object[cardinalidad()];
       //Iterador del conjunto que llamó a la función
	    Iterator it = iterator();
	    int index = 0;
	    while(it.hasNext()){
	       T elem = (T) it.next();
          //Si el elemento no coincide con el que se quiere eliminar se agrega a mi array
          if(elem != elemento){
             arr[index] = elem;
             index++;   
          }
       }
       if(contiene(elemento)){
           elementos--;
       }
	    //Volver a mi array un conjunto
       conjunto = arr;
    }
    
    /**
     * Método para ver si un elemento pertenece al conjunto
     * @param elemento Objeto que se va a buscar en el conjunto
     * @return <code>true</code> si el elemento esta en el conjunto,
     * <code>false</code> en otro caso.
     */
    @Override
    public boolean contiene (T elemento){
	    boolean doesIt = false;
       Iterator it = iterator();
       //Mientras el conjunto que mandó a llamar a la función tenga un siguiente elemento
       while(it.hasNext()){
           //Si ese siguiente elemento es igual al elemento que se quiere introducir
           if((T) it.next() == elemento){
              //Es verdad que dicho conjunto contiene al elemento
              doesIt = true;
              //Romper ciclo
              break;
           }
       }
	    return doesIt;
    }
    
    /**
     * Método para calcular la union de dos conjuntos
     * @param c1 conjunto con el que se calculará la unión
     * @return Conjuntable conjunto que contiene la unión
     */
    @Override
    public Conjuntable union(Conjuntable c){
       //Array auxiliar
       T arr[] = (T[]) new Object[0];
       //Volver a mi array un conjunto
       Conjunto<T> k = new Conjunto(arr);
       //Recorrer arreglo que llama al método
       Iterator it1 = iterator();
       while(it1.hasNext()){
           k.agregar((T) it1.next());
       }
       //Recorrer arreglo c
       Iterator it2 = c.iterator();
       while(it2.hasNext()){
           k.agregar((T) it2.next());
       }
       return k;
    }

    /**
     * Método para calcular la intersección de dos conjuntos
     * @param c conjunto con el que se calculará la intersección
     * @return Conjuntable que con tiene la intersección
     */
    @Override
    public Conjuntable interseccion(Conjuntable c){
	    //Array auxiliar
       T arr[] = (T[]) new Object[0];
       //Volver a mi array un conjunto
       Conjunto<T> k = new Conjunto(arr);
       Iterator it = c.iterator();
       //Recorrer conjunto c
       while(it.hasNext()){
           T elem = (T) it.next();
           //Si el conjunto que llamó al método contiene al elemento de c
           if(contiene(elem)){
              k.agregar(elem);
           }
       }
       return k;
    }

    /**
     * Método para calcular la diferencia de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia
     * @return Conjuntable con la diferencia
     */
    @Override
    public Conjuntable diferencia(Conjuntable c){
       //Array auxiliar
       T arr[] = (T[]) new Object[0];
       Conjunto<T> k = new Conjunto(arr);
       //Iterador del conjunto que mandó a llamar al método
       Iterator it1 = iterator();
       //Mientras el conjunto que mandó a llamar al método tenga siguiente
       while(it1.hasNext()){
           T elem = (T) it1.next();
           if(c.contiene(elem) == false){
              //Agregar al array
              k.agregar(elem);
           }
       }
       return k;
    }

    /**
     * Método para calcular la diferencia simétrica de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia simétrica
     * @return Conjuntable con la diferencia simétrica
     */
    @Override
    public Conjuntable diferenciaSimetrica(Conjuntable c){
       //Array auxiliar
       T arr[] = (T[]) new Object[0];
       Conjunto<T> k = new Conjunto(arr);
       //Iterador del conjunto que mandó a llamar al método
       Iterator it1 = iterator();
       //Iterador del conjunto c
       Iterator it2 = c.iterator();
       //Mientras el conjunto que mandó a llamar al método tenga siguiente
       while(it1.hasNext()){
           T elem = (T) it1.next();
           if(c.contiene(elem) == false){
              //Agregar al array
              k.agregar(elem);
           }
       }
       //Mientras c tenga elemento siguiente
       while(it2.hasNext()){
           T elem = (T) it2.next();
           if(contiene(elem) == false){
              //Agregar al array
              k.agregar(elem);
           }
       }
       return k;
    }
    
    /**
     * Método para determinar si un conjunto esta contenido en otro
     * @param c conjunto en se va a probar si el que llama es subconjunto
     * @return boolean true si el conjunto que llama a este metodo es 
     *         subconjunto del parametro y false en otro caso
     */
    @Override
    public boolean subconjunto(Conjuntable c){
       boolean isIt = true;
	    Iterator it = iterator();
       while(it.hasNext()){
          if((c.contiene((T) it.next())) == false){
             isIt = false;
             break;
          }
       }
	    return isIt;
    }

    @Override
    public String toString(){
	    String str = "Los elementos del conjunto son: ";
       Iterator it = iterator();
       while(it.hasNext()){
          str += it.next() + " ";
       }
       return str;
    }
    
    /**
     * Método para crear un iterador sobre un conjunto
     * @return Iterator iterador sobre el conjunto.
     */
    @Override
    public java.util.Iterator iterator(){
         return new IteradorConjunto();
    }
}
