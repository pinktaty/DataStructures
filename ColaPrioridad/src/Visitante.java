public class Visitante implements Comparable<Visitante>{
	
	private int prioridad;
	private TipoEvento evento;
	private TipoVisitante visitante;

	// Constructor de la clase
	public Visitante(TipoEvento evento, TipoVisitante visitante){
		this.evento = evento;
		this.visitante = visitante;
		switch(visitante){
			case MiembroClub:
				this.prioridad = 0;
				break;
			case EstudianteUNAM:
				this.prioridad = 1;
				break;
			case Estudiante:
				this.prioridad = 2;
				break;
			case VisitanteGeneral:
				this.prioridad = 3;
		}
	}

	public int getPrioridad(){
		return prioridad;
	}

	public TipoEvento getEventoAVisitar(){
		return evento;
	}

	public TipoVisitante getTipoVisitante(){
		return visitante;
	}

	@Override
	public int compareTo(Visitante v){
		if(this.getPrioridad() > v.getPrioridad()){
			return 1;
		} else if(this.getPrioridad() < v.getPrioridad()){
			return -1;
		} else{
			return 0;
		}
	}

}
