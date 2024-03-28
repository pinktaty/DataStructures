import estructuras.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class Sistema{
	//Para mantener registro de los visitantes
	private Lista<Visitante> visitantesRegistro;
	private MinHeap cola;
	private Teatro eventoTeatro;
	private Cine eventoCine;
	private Museo eventoMuseo;

	// Constructor de la clase
	public Sistema(double precioTeatro, double precioCine, double precioMuseo){
		this.visitantesRegistro = new Lista();
		this.cola = new MinHeap();
		this.eventoTeatro = new Teatro(precioTeatro);
		this.eventoCine = new Cine(precioCine);
		this.eventoMuseo = new Museo(precioMuseo);
	}

	/* Método creado sólo para debuggear
	public TipoVisitante heap(){
		Visitante v = (Visitante) cola.raíz();
		return v.getTipoVisitante();
	}
	*/

	public void visitanteHaLlegado(Visitante v){
		cola.agregar(v);
	}

	public void venderBoleto(){
		// Checar que el heap no esté vacío
		if(cola.esVacia()){
			return;
		}
		// Obtener el visitante hasta arriba del heap para venderle el boleto
		Visitante v = (Visitante) cola.eliminar();
		TipoEvento evento = v.getEventoAVisitar();
		switch(evento){
			case Teatro:
				eventoTeatro.boletoVendido();
				break;
			case Cine:
				eventoCine.boletoVendido();
				break;
			case Museo:
				eventoMuseo.boletoVendido();
		}
		visitantesRegistro.agregar(v);
	}

	private void registrarVisitante(Visitante v){
		visitantesRegistro.agregar(v);
	}

	public double gananciasDiarias(){
		return ganaciasTeatro() + gananciasCine() + gananciasMuseo();
	}

	public double ganaciasTeatro(){
		return eventoTeatro.getGanancias();
	}

	public double gananciasCine(){
		return eventoCine.getGanancias();
	}

	public double gananciasMuseo(){
		return eventoMuseo.getGanancias();
	}

	public int boletosVendidosGenerales(){
		return boletosVendidosTeatro() + boletosVendidosCine() + boletosVendidosMuseo();
	}

	public int boletosVendidosTeatro(){
		return eventoTeatro.getBoletosVendidos();
	}

	public int boletosVendidosCine(){
		return eventoCine.getBoletosVendidos();
	}

	public int boletosVendidosMuseo(){
		return eventoMuseo.getBoletosVendidos();
	}

	public TipoEvento eventoMásVendido(){
		Evento ev = eventoTeatro;
		if(ev.getBoletosVendidos() < eventoCine.getBoletosVendidos()){
			ev = eventoCine;
		} 
		if(ev.getBoletosVendidos() < eventoMuseo.getBoletosVendidos()){
			ev = eventoMuseo;
		}
		return ev.getTipoEvento();
	}

	public TipoEvento eventoMenosVendido(){
		Evento ev = eventoTeatro;
		if(ev.getBoletosVendidos() > eventoCine.getBoletosVendidos()){
			ev = eventoCine;
		} 
		if(ev.getBoletosVendidos() > eventoMuseo.getBoletosVendidos()){
			ev = eventoMuseo;
		}
		return ev.getTipoEvento();
	}

	public String visitantesRegistrados(){
		String toReturn = "";
		Iterator it = visitantesRegistro.iterator();
		while(it.hasNext()){
			Visitante v = (Visitante) it.next();
			toReturn += v.getTipoVisitante() + ", ";
		}
		return toReturn;
	}

	public void generaDocumento(){
		try{
			String fecha = DateTimeFormatter.ofPattern("MMM-dd-yyyy").format(LocalDateTime.now());
			File f = new File(fecha + ".txt");
			FileWriter fw = new FileWriter(f);
			fw.write("Resultados obtenidos del día de hoy.\n\n");
			fw.write("Boletos totales vendidos: " + boletosVendidosGenerales() + ".\n");
			fw.write("Ganancias totales: $" + gananciasDiarias() + ".\n\n");
			fw.write("Boletos vendidos en sección museo: " + boletosVendidosMuseo() + ".\n");
			fw.write("Ganancias obtenidas en sección museo: $" + gananciasMuseo() + ".\n\n");
			fw.write("Boletos vendidos en sección cine: " + boletosVendidosCine() + ".\n");
			fw.write("Ganancias obtenidas en sección cine: $" + gananciasCine() + ".\n\n");
			fw.write("Boletos vendidos en sección teatro: " + boletosVendidosTeatro() + ".\n");
			fw.write("Ganancias obtenidas en sección teatro: $" + ganaciasTeatro() + ".\n\n");
			fw.write("Evento más vendido: " + eventoMásVendido() + ".\n");
			fw.write("Evento menos vendido: " + eventoMenosVendido() + ".\n\n");
			fw.write("Visitantes: " + visitantesRegistrados());
			fw.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
