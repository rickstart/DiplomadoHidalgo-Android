public class Carrera{

	private Color negro = new Color("#000000");
	private Color blanco = new Color("#FFFFFF");
	//private Color gris = new Color("#CCCCCC");

	private FabricaBicicleta bicicleta1 = new FabricaBicicleta(negro, 80.00);
	private FabricaBicicleta bicicleta2 = new FabricaBicicleta(blanco, 120.50);
	private FabricaBicicleta bicicleta3 = new FabricaBicicleta(new Color("#CCCCCC"), 60.20);

	private Persona competidor1 = new Persona(75.50,bicicleta1);
	private Persona competidor2 = new Persona(105.50,bicicleta2);
	private Persona competidor3 = new Persona(60.50,bicicleta3);

	FabricaBicicleta b1 = new FabricaBicicleta();
	b1.color;
	private Color color = FabricaBicicleta.color;
	public Persona competencia(Persona competidor1, Persona competidor2, Persona competidor3){
		int d1 = 0, d2 = 0, d3 = 0;
		while(d1 <= 100 || d2 <= 100 || d3 <= 100 ){}
	
			//d1 = d1 + competidor1.velocidad;
			d1 += competidor1.velocidad;
			d2 += competidor2.velocidad;
			d3 += competidor3.velocidad;

			if( d1 >= 100 )
				return competidor1;
			if( d2 >= 100)
				return competidor2;
			if( d3 >= 100 )
				return competidor3;

		}
	}
}