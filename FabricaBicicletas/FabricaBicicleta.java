public class FabricaBicicleta{

	private Color color;
	private Llanta llanta1, llanta2;
	private Cuadro cuadro;
	private Manubrio manubrio;
	private Cadena cadena;
	private Freno frenoDelantero, frenoTrasero;
	private Pedal pedalIzquierdo, pedalDerecho;
	private Asiento asiento;
	private double peso;

	public FabricaBicicleta(Color color, Llanta llanta1, Llanta llanta2, Cuadro cuadro, Manubrio manubrio, 
		Cadena cadena, Freno frenoDelantero, Freno frenoTrasero, Pedal pedalIzquierdo, Pedal pedalDerecho
		Asiento asiento, double peso){

		this.color = color;
		this.llanta1 = llanta1;
		this.llanta2 = llanta2;
		this.cuadro = cuadro;
		this.manubrio = manubrio;
		this.cadena = cadena;
		this.frenoDelantero = frenoDelantero;
		this.frenoTrasero = frenoTrasero;
		this.pedalIzquierdo = pedalIzquierdo;
		this.pedalDerecho = pedalDerecho;
		this.asiento = asiento;
		this.peso = peso;
	}

	public double getPeso(){
		return this.peso;
	}

	public void setPeso(double peso){
		this.peso = peso;
	}


	public FabricaBicicleta(Color color, double peso){
		this.color = color;
		this.peso = peso;

	}


}