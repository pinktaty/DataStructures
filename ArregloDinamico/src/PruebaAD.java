public class PruebaAD{
    public static void main(String[] args){
        ArregloDinamico primerPrueba = new ArregloDinamico(1.2, 5.8, 9.9);
        ArregloDinamico segundaPrueba = new ArregloDinamico(1, 2, 3, 4, 5, 6, 7, 8);
        
        //Prueba 1
        System.out.println("Primer Prueba");
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        //Añadir 5 elementos
        primerPrueba.add(10.0);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.add(0.0);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.add(6.666);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.add(4.4);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.add(8.0);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        //Eliminar 4 elementos
        primerPrueba.remove(1.2);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.remove(4.4);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.remove(9.9);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        primerPrueba.remove(0.0);
        System.out.println(primerPrueba);
        System.out.println("Numero de elementos: " + primerPrueba.size());
        
        System.out.println(" ");
        
        //Prueba 2
        System.out.println("Segunda Prueba");
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        //Añadir 5 elementos
        segundaPrueba.add(7.777);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.add(1.111);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.add(3.1416);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.add(2.12);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.add(9.0);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        //Eliminar elementos
        segundaPrueba.remove(5.0);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.remove(2.12);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.remove(7.0);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
        segundaPrueba.remove(2.0);
        System.out.println(segundaPrueba);
        System.out.println("Numero de elementos: " + segundaPrueba.size());
    }
}
