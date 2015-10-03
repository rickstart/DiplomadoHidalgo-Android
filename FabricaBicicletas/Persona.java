public class Persona{
	private int edad;
	private double peso;
	private String sexo;
	private double estatura;
	private FabricaBicicleta bicicleta;

	public Persona(int edad, double peso, String sexo, double estatura, Bicicleta bicicleta){
		this.edad = edad;
		this.peso = peso;
		this.sexo = sexo;
		this.estatura = estatura;
		this.bicicleta = bicicleta; 
	}

	public Persona(double peso, Bicicleta bicicleta){
		this.peso = peso;
		this.bicicleta = bicicleta; 
	}

	public int velocidad(){
		if(this.peso < bicicleta.getPeso()){
			return 2;
		}else{
			return 1;
		}


	}

}