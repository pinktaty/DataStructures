public class PruebaConjunto{
	
	public static void main(String[] args){
		Integer[] i = {1,2,3,4,5};
		Integer[] j = {2,4,5,6,7,8};
		Conjunto<Integer> c1 = new Conjunto(i);
		Conjunto<Integer> c2 = new Conjunto(j);
		System.out.println("Conjunto c1");
		System.out.println(c1);
		System.out.println("Cardinalidad de c1: " + c1.cardinalidad());
		System.out.println("Conjunto c2");
		System.out.println(c2);
		System.out.println("Cardinalidad de c2: " + c2.cardinalidad());
		System.out.println("Unión");
		Conjunto<Integer> union = (Conjunto<Integer>)(c1.union(c2));
		System.out.println(union);
		System.out.println("Intersección");
		Conjunto<Integer> interseccion = (Conjunto<Integer>)(c1.interseccion(c2));
		System.out.println(interseccion);
		System.out.println("Diferencia c1/c2");
		Conjunto<Integer> diferencia = (Conjunto<Integer>)(c1.diferencia(c2));
		System.out.println(diferencia);
		System.out.println("Diferencia simetrica");
		Conjunto<Integer> diferenciaSimetrica = (Conjunto<Integer>)(c1.diferenciaSimetrica(c2));
		System.out.println(diferenciaSimetrica);
	   System.out.println("Vaciar conjunto c1");
		c1.vaciar();
		System.out.println(c1);
		System.out.println("Cardinalidad de c1: " + c1.cardinalidad());
		System.out.println("¿Es c1 vacío?");
		System.out.println(c1.esVacio());
		System.out.println("Eliminar elemento 4 de c2");
		Integer v = 4;
		c2.eliminar(v);
		System.out.println(c2);
		System.out.println("Cardinalidad de c2: " + c2.cardinalidad());
		System.out.println("Agregar elemento a c1");
		Integer w = 7;
		c1.agregar(w);
		System.out.println(c1);
		System.out.println("Cardinalidad de c1: " + c1.cardinalidad());
		System.out.println("¿Es c1 subconjunto de c2?");
		System.out.println(c1.subconjunto(c2));
	}
}
