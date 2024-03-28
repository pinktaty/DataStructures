/*
 * Muy bien :)
 */
package estructuras;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> extends Heap<T>{

    public MinHeap(){
		super();
    }

    public MinHeap(Coleccionable <T> c){
		super(c);
    }

    @Override
    protected void reordena(int indice){
		super.reordena(indice,true);
    }

    @Override
    public T eliminar() {
        if(siguiente==0)
            throw new NoSuchElementException("No hay elementos en el Min-Heap");
        T min = arreglo[0];
        arreglo[0] = arreglo[siguiente-1];
        arreglo[--siguiente] = null;
        super.reordenaParaAbajo(0, true);
        return min;
    }

}
