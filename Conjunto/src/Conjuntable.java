/**
 * <p> Interfaz para conjuntos </p> <p>Esta clase contiene las
 * operaciones elementales para operaciones entre conjuntos </p>
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 */
public interface Conjuntable <T> {
    /**
     * Método que nos dice si el conjunto está vacío.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacio();
    
    /**
     * Método para obtener el tamaño de un conjunto
     * @return número de elementos en el conjunto
     */
    public int cardinalidad();
    
    /**
     * Método para eliminar todos los elementos de un conjunto
     */
    public void vaciar();
    
    /**
     * Método para agregar un elemento al conjunto
     * @param elemento Objeto que se incorporara al conjunto
     */
    public void agregar(T elemento);
    
    /**
     * Método para eliminar un <code>elemento</code> del conjunto
     * @param elemento Objeto que se eliminara del conjunto
     */
    public void eliminar(T elemento);
    
    /**
     * Método para ver si un elemento pertenece al conjunto
     * @param elemento Objeto que se va a buscar en el conjunto
     * @return <code>true</code> si el elemento esta en el conjunto,
     * <code>false</code> en otro caso.
     */
    public boolean contiene (T elemento);
    
    /**
     * Método para calcular la union de dos conjuntos
     * @param c1 conjunto con el que se calculará la unión
     * @return Conjuntable conjunto que contiene la unión
     */
    public Conjuntable union(Conjuntable c);

    /**
     * Método para calcular la intersección de dos conjuntos
     * @param c conjunto con el que se calculará la intersección
     * @return Conjuntable que con tiene la intersección
     */
    public Conjuntable interseccion(Conjuntable c);

    /**
     * Método para calcular la diferencia de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia
     * @return Conjuntable con la diferencia
     */
    public Conjuntable diferencia(Conjuntable c);

    /**
     * Método para calcular la diferencia simétrica de dos conjuntos
     * @param c conjunto con el que se va a calcular la diferencia simétrica
     * @return Conjuntable con la diferencia simétrica
     */
    public Conjuntable diferenciaSimetrica(Conjuntable c);
    
    /**
     * Método para determinar si un conjunto esta contenido en otro
     * @param c conjunto en se va a probar si el que llama es subconjunto
     * @return boolean true si el conjunto que llama a este metodo es 
     *         subconjunto del parametro y false en otro caso
     */
    public boolean subconjunto(Conjuntable c);
    
    /**
     * Método para crear un iterador sobre un conjunto
     * @return Iterator iterador sobre el conjunto.
     */
    public java.util.Iterator iterator();
}
