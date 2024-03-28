public abstract class Evento{
	protected int boletosVendidos;
	protected double precioBoleto;

	// Constructores de clase
	protected Evento(){}
	protected Evento(double precio){
		this.boletosVendidos = 0;
		this.precioBoleto = precio;
	}

	public int getBoletosVendidos(){
		return boletosVendidos;
	}

	public double getGanancias(){
		return boletosVendidos * precioBoleto;
	}

	public void boletoVendido(){
		boletosVendidos++;
	}

	public abstract TipoEvento getTipoEvento();
	
}
