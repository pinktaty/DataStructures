public class Museo extends Evento{
	private TipoEvento tipo = TipoEvento.Museo;

	// Constructor de clase
	public Museo(){super();}
	public Museo(double precio){
		super(precio);
	}
		
	@Override
	public TipoEvento getTipoEvento(){
		return tipo;
	}
}

