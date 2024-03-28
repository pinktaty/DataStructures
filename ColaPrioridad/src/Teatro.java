public class Teatro extends Evento{
	private TipoEvento tipo = TipoEvento.Teatro;

	// Constructor de clase
	public Teatro(){super();}
	public Teatro(double precio){
		super(precio);
	}
		
	@Override
	public TipoEvento getTipoEvento(){
		return tipo;		
	}
}

