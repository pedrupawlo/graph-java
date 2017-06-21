package grafo;

public class Main {

	public static void main(String[] args) {	
		GrafoSimples gs = new GrafoSimples(6);
		gs.lerArquivo("grafo.txt");
		gs.lerMatriz();
	}
}
