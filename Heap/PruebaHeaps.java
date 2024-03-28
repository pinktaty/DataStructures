import estructuras.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 */
public class PruebaHeaps {
    public static void main(String [] args){
        Lista <Integer> l = new Lista<Integer>();
        l.agregarAlFinal(1);
        l.agregarAlFinal(8);
        l.agregarAlFinal(13);
        l.agregarAlFinal(5);
        l.agregarAlFinal(11);
        l.agregarAlFinal(10);
        l.agregarAlFinal(2);
        l.agregarAlFinal(3);
        l.agregarAlFinal(6);
        l.agregarAlFinal(4);
        l.agregarAlFinal(9);
        l.agregarAlFinal(14);
        l.agregarAlFinal(12);
        l.agregarAlFinal(7);
        l.agregarAlFinal(16);
        l.agregarAlFinal(15);
        l.agregarAlFinal(0);
        MinHeap minHeap = new MinHeap<Integer>(l);
        MaxHeap maxHeap = new MaxHeap<Integer>(l);

        System.out.println(minHeap);
        System.out.println(maxHeap);

        Cola <Integer> c = new Cola<Integer>();
        c.queue(1);
        c.queue(8);
        c.queue(13);
        c.queue(5);
        c.queue(11);
        c.queue(10);
        c.queue(2);
        c.queue(3);
        c.queue(6);
        c.queue(4);
        c.queue(9);
        c.queue(14);
        c.queue(12);
        c.queue(7);
        c.queue(16);
        c.queue(15);
        c.queue(0);
        minHeap = new MinHeap<Integer>(c);
        maxHeap = new MaxHeap<Integer>(c);

        System.out.println(minHeap);
        System.out.println(maxHeap);

        l.vaciar();
        l.agregarAlInicio(1);
        l.agregarAlInicio(8);
        l.agregarAlInicio(13);
        l.agregarAlInicio(5);
        l.agregarAlInicio(11);
        l.agregarAlInicio(10);
        l.agregarAlInicio(2);
        l.agregarAlInicio(3);
        l.agregarAlInicio(6);
        l.agregarAlInicio(4);
        l.agregarAlInicio(9);
        l.agregarAlInicio(14);
        l.agregarAlInicio(12);
        l.agregarAlInicio(7);
        l.agregarAlInicio(16);
        l.agregarAlInicio(15);
        l.agregarAlInicio(0);

        Pila <Integer> p = new Pila<Integer>(l);
        minHeap = new MinHeap<Integer>(p);
        maxHeap = new MaxHeap<Integer>(p);

        System.out.println(minHeap);
        System.out.println(maxHeap);
    }
}
