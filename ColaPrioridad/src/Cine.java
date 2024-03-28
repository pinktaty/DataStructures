public class Cine extends Evento{
	private TipoEvento tipo = TipoEvento.Cine;

	// Constructor de clase
	public Cine(){super();}
	public Cine(double precio){
		super(precio);
	}
		
	@Override
	public TipoEvento getTipoEvento(){
		return tipo;
	}
}

