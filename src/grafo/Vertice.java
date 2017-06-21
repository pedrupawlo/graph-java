package grafo;

public class Vertice {
	public int elemento;
	
	/*
	 * Construtores
	 * */
	
	public Vertice(int elemento) {
		super();
		this.elemento = elemento;
	}
	
	
	/*
	 * Acessadores
	 * */
	
	public int getElemento() {
		return elemento;
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}
}
