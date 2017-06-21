package grafo;

public class Aresta {
	public int elemento;
	public Vertice verticeOrigem;
	public Vertice verticeDestino;
	
	/*
	 * Construtores
	 * */
	
	public Aresta(int elemento) {
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


	public Vertice getVerticeOrigem() {
		return verticeOrigem;
	}


	public void setVerticeOrigem(Vertice verticeOrigem) {
		this.verticeOrigem = verticeOrigem;
	}


	public Vertice getVerticeDestino() {
		return verticeDestino;
	}


	public void setVerticeDestino(Vertice verticeDestino) {
		this.verticeDestino = verticeDestino;
	}
	
	
	/*
	 * Auxiliares
	 * */
	
	public Vertice oposto(Vertice vertice) {
		if (verticeOrigem.getElemento() == vertice.getElemento()) {
			return verticeDestino;
		}
		else if (verticeDestino.getElemento() == vertice.getElemento()) {
			return verticeOrigem;
		}
		return null;
	}
}
