package grafo;

public class Elemento {
	private Vertice vertice;
	private int valor;
	private int posicao;
	private boolean check;
	
	public Elemento() {
		super();
	}

	public Elemento(Vertice vertice, int valor, int posicao, boolean check) {
		super();
		this.vertice = vertice;
		this.valor = valor;
		this.posicao = posicao;
		this.check = check;
	}

	public Vertice getVertice() {
		return vertice;
	}

	public void setVertice(Vertice vertice) {
		this.vertice = vertice;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "V" + posicao + " (" + valor + ")";
	}
}
