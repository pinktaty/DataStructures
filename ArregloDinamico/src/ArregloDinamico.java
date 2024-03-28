public class ArregloDinamico<T>{

    private double[] arreglo;
    private int tam;
     
    //Como en telegram se dijo que se podía modificar los constructores me tomé la libertad de modificar este para generar los objetos
    public ArregloDinamico(double... elem){
        arreglo = new double[elem.length];
        int index = 0;
        for(double i : elem){
            arreglo[index] = i;
            index++;
        }
        tam = elem.length;
    }
    
    public void add(double f){
        //¿Cabe en el arreglo?
        try {
            arreglo[tam] = f;
        //Si no cabe atrapar excepción
        } catch (IndexOutOfBoundsException e){
            //Arreglo temporal que expandirá el anterior
            double arr[] = new double[arreglo.length + 5];
            //Copiar los elementos del arreglo anterior al nuevo
            for(int i = 0; i < arreglo.length; i++){
               arr[i] = arreglo[i];
            }
            arreglo = arr;
            //Añadir elemento
            arreglo[tam] = f;
        }
        tam++;
    }

    public boolean isEmpty(){
        boolean isIt = false;
        if(tam == 0){
            isIt = true;
        }
        return isIt; 
    }

    public double remove(double f){  
        //Recorre el arreglo
        for(int i = 0; i < arreglo.length; i++){
            //Busca coincidencias en el arreglo con el número ingresado
            if(arreglo[i] == f){
               //Elimina el elemento en la posición i y acomoda los elementos sin dejar espacios vacíos
               for(int j = i; j < arreglo.length; j++){
                   //si index = tam, entonces arreglo[index] = 0.0
                   if(j == arreglo.length-1){
                       arreglo[j] = 0.0;
                   } else {
                       try {
                          arreglo[j] = arreglo[j+1];
                       } catch (IndexOutOfBoundsException e){
                          arreglo[j] = 0.0;
                       }
                   }
               }
               //Rompe el bucle
               break;
            }
        }
        tam--;
        //Si el número de elementos es menor a la mitad del arreglo 
        if(tam < arreglo.length/2){
            //Arreglo temporal que decrecerá el anterior
            double arr[] = new double[arreglo.length/2];
            //Copiar los elementos del arreglo anterior al nuevo
            for(int i = 0; i < arreglo.length; i++){
               arr[i] = arreglo[i];
            }
            arreglo = arr;
        }
        return f;
    }
    
    //Punto extra, remueve el valor en la posición i, no acabado, checar
    public void remove(int i){
         //Borrar el elemento
         arreglo[i] = arreglo[i+1];
        //Recorre el arreglo a partir del siguiente elemento del que ya se borró
        for(int j = i+1; j < arreglo.length; j++){
            //En caso del último elemento hacerlo 0
            if(j == arreglo.length-1){
                       arreglo[j] = 0.0;
            } else {
                 try {
                    //Recorrer los elementos para no dejar espacios
                    arreglo[j] = arreglo[j+1];
                 } catch (IndexOutOfBoundsException e){
                    arreglo[j] = 0.0;
                 }
            }
        }
        tam--;
    }

    public int size(){
        return tam;
    }

    public String toString(){
        String str = "Los elementos del arreglo son: ";
        for(double element: arreglo){  
            str += element + " ";
        }
        return str;
    }
    
}
