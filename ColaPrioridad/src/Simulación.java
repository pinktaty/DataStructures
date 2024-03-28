public class Simulación{
	public static void main(String args[]){  

		double precioTeatro = 20;
		double precioCine = 40.5;
		double precioMuseo = 15;

		TipoEvento teatro = TipoEvento.Teatro;
		TipoEvento cine = TipoEvento.Cine;
		TipoEvento museo = TipoEvento.Museo;

		TipoVisitante miembroClub = TipoVisitante.MiembroClub;
		TipoVisitante estudianteUNAM = TipoVisitante.EstudianteUNAM;
		TipoVisitante estudiante = TipoVisitante.Estudiante;
		TipoVisitante visitanteGeneral = TipoVisitante.VisitanteGeneral;

		Sistema s = new Sistema(precioTeatro, precioCine, precioMuseo);

		Visitante v0 = new Visitante(cine, estudiante);
		Visitante v1 = new Visitante(teatro, estudiante);
		Visitante v2 = new Visitante(cine, miembroClub);
		Visitante v3 = new Visitante(teatro, visitanteGeneral);
		Visitante v4 = new Visitante(cine, miembroClub);
		Visitante v5 = new Visitante(museo, estudiante);
		Visitante v6 = new Visitante(teatro, estudiante);
		Visitante v7 = new Visitante(cine, estudianteUNAM);
		Visitante v8 = new Visitante(museo, estudianteUNAM);
		Visitante v9 = new Visitante(teatro, miembroClub);
		Visitante v10 = new Visitante(museo, estudianteUNAM);
		Visitante v11 = new Visitante(cine, estudianteUNAM);

		// Para poder usar los System.out.println descomentar el método heap() en la clase Sistema y el método raíz() en la clase Heap

		s.visitanteHaLlegado(v1);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v3);
		// System.out.println("Visitante a la cabeza de fila: " + s.heap());
		s.visitanteHaLlegado(v2);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v4);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v5);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());

		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();

		s.visitanteHaLlegado(v6);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v7);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v0);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());

		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();

		s.visitanteHaLlegado(v8);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v9);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v10);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());
		s.visitanteHaLlegado(v11);
		// System.out.println("Visitante a la cabeza de la fila: " + s.heap());

		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();
		// System.out.println("Visitante al que se le está vendiendo boleto: " + s.heap());
		s.venderBoleto();

		s.generaDocumento();
	}  
}
