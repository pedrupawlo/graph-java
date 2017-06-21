package grafo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GrafoSimples {
	private ArrayList<Vertice> vertices;
	private Aresta[][] arestas;
	private int qtdVertices;
	private int size = 6;
	
	public GrafoSimples(int size) {
		this.vertices = new ArrayList<Vertice>();
		this.qtdVertices = 0;
		this.arestas = new Aresta[1][1];
		this.size = 6;
	}
	
	public ArrayList<Elemento> getMenorCaminho() {
		if (vertices.size() < 2) {
			return null;
		}
		else {
			ArrayList<Elemento> S = new ArrayList<Elemento>(); // S
			Vertice primeiro = vertices.get(0); // Vertice inicial
			S.add(new Elemento(primeiro, 0, 0, true)); // Seta elemento com false para que não seja visitado
			int posicao = -1; // Posição do Vertice de referencia
			int valor = 99999999; // Valor auxiliar para verificar elemento que vai para S
			int qtdElementos = vertices.size() - 1; // Regula o fim da interação sobre os elementos
			
			
			/*
			 * Passo 1
			 * */
			
			/*
			 * Verifica se existe uma Aresta ligando os vertices, se ouver
			 * adiciona novo elemento na coleção. Caso seja o com menor valor,
			 * ele se torna referencia e é adicionado a S
			 * */
			for (Vertice vertice : vertices) {
				Aresta a = arestas[vertices.indexOf(primeiro)][vertices.indexOf(vertice)];
				
				if (vertices.indexOf(vertice) != vertices.indexOf(primeiro) && a != null) {
					int temp = a.getElemento();
					S.add(new Elemento(vertice, temp, vertices.indexOf(primeiro), false));
					
					// Atualiza referencia para elemento com menor custo
					if (temp < valor) {
						posicao = vertices.indexOf(vertice);
					}
				}
				else {
					S.add(new Elemento(vertice, 99999999, 0, false));
				}
			}
			S.get(posicao).setCheck(true); // Seta elemento como visitado
			-- qtdElementos;
			
			/*
			 * FIM Passo 1
			 * */
			
			
			/*
			 * Passo 2
			 * */
			
			Elemento anterior = S.get(posicao); // W
			valor = 99999999; // Valor auxiliar para verificar elemento que vai para S
			
			/*
			 * Enquanto ouverem elementos não visitados em S, eles terão seus
			 * valores atualizados, como o custo para que se chegue ao Vertice e
			 * por qual Vertice se deve passar caso seja necessário.
			 * */
			while (qtdElementos > 0) {
				for (Elemento elemento : S) {
					if (!elemento.isCheck()) {
						Aresta a = arestas[vertices.indexOf(primeiro)][vertices.indexOf(anterior.getVertice())];
						Aresta b = arestas[vertices.indexOf(anterior.getVertice())][vertices.indexOf(elemento.getVertice())];
						
						// Verifica se existem Arestas ligando os Vertices
						if (vertices.indexOf(elemento.getVertice()) != vertices.indexOf(primeiro) && a != null && b != null) {
							// D[v]=min(D[v],d[w]+L(w,v))
							if (elemento.getValor() > (a.getElemento() + b.getElemento())) {
								elemento.setPosicao(vertices.indexOf(anterior.getVertice()));
								elemento.setValor(a.getElemento() + b.getElemento());
								
								// Atualiza referencia para elemento com menor custo
								if (elemento.getValor() < valor) {
									posicao = vertices.indexOf(elemento.getVertice());
								}
							}
						}
					}
				}
				S.get(posicao).setCheck(true); // Seta elemento como visitado
				-- qtdElementos;
				valor = 99999999;
			}
			return S;
			
			/*
			 * FIM Passo 2
			 * */
		}
	}
	
	
	/*
	 * Acessadores
	 * */
	
	public Vertice[] finalVertices(Aresta aresta) {
		return new Vertice[] {aresta.getVerticeOrigem(), aresta.getVerticeDestino()};
	}
	
	public Vertice oposto(Vertice vertice, Aresta aresta) {
		return aresta.oposto(vertice);
	}
	
	public boolean isAdjacente(Vertice vertice1, Vertice vertice2) {
		int indiceVertice1 = vertices.indexOf(vertice1);
		int indiceVertice2 = vertices.indexOf(vertice2);
		
		if (arestas[indiceVertice1][indiceVertice2] != null) {
			return true;
		}
		return false;
	}
	
	public void substituir(Vertice vertice, int elemento) {
		vertice.setElemento(elemento);
	}
	
	public void substituir(Aresta aresta, int elemento) {
		aresta.setElemento(elemento);
	}
	
	public int ordem() {
		return vertices.size();
	}
	
	
	/*
	 * Atualizadores
	 * */
	
	public Vertice inserirVertice(int elemento) {
		Vertice vertice = new Vertice(elemento);
		vertices.add(vertice);
		
		qtdVertices++;
		Aresta[][] arestasTemp = new Aresta[qtdVertices][qtdVertices];
		for (int i = 0; i < qtdVertices - 1; i++) {
			for (int j = 0; j < qtdVertices - 1; j++) {
				arestasTemp[i][j] = arestas[i][j];
			}
		}
		arestas = arestasTemp;

		return vertice;
	}
	
	public Aresta inserirAresta(Vertice vertice1, Vertice vertice2, int elemento, boolean readFile) {		
		int indiceVertice1 = vertices.indexOf(vertice1);
		int indiceVertice2 = vertices.indexOf(vertice2);
		
		Aresta aresta = new Aresta(elemento);
		if (readFile) {
			arestas[indiceVertice1][indiceVertice2] = aresta;
		}
		else {
			arestas[indiceVertice1][indiceVertice2] = arestas[indiceVertice2][indiceVertice1] = aresta;
		}
		
		return aresta;
	};
	
	public int removerVertice(Vertice vertice) {
		int indiceVertice = vertices.indexOf(vertice);
	
		Aresta[][] arestasTemp = new Aresta[qtdVertices - 1][qtdVertices - 1];
		for (int i = 0; i < qtdVertices; i++) {
			// Pula a linha do Vertice na matriz de adjacencia
			if (i == indiceVertice) {
				i++;
				continue;
			}
			
			for (int j = 0; j < qtdVertices; j++) {
				// Pula a coluna do Vertice na matriz de adjacencia
				if (j == indiceVertice) {
					j++;
					continue;
				}
				
				if (i > indiceVertice && j > indiceVertice) {
					arestasTemp[i-1][j-1] = arestas[i][j];
				}
				else if (i > indiceVertice) {
					arestasTemp[i-1][j] = arestas[i][j];
				}
				else if (j > indiceVertice) {
					arestasTemp[1][j-1] = arestas[i][j];
				}
				else {
					arestasTemp[i][j] = arestas[i][j];
				}
			}
		}
		arestas = arestasTemp;
		qtdVertices--;
		
		return vertice.getElemento();
	}
	
	public int removerAresta(Aresta aresta) {
		int indiceOrigem = vertices.indexOf(aresta.getVerticeOrigem());
		int indiceDestino = vertices.indexOf(aresta.getVerticeDestino());
		
		Aresta arestaTemp = arestas[indiceOrigem][indiceDestino];
		arestaTemp.setVerticeOrigem(null);
		arestaTemp.setVerticeDestino(null);
		
		arestas[indiceOrigem][indiceDestino] = arestas[indiceDestino][indiceOrigem] = null;
		
		return arestaTemp.getElemento();
	}
	
	
	/*
	 * Interadores
	 * */
	
	public void lerMatriz() {
		String matriz = "";
		
		for (int i = 0; i < qtdVertices - 1; i++) {
			for (int j = 0; j < qtdVertices - 1; j++) {
				if (arestas[i][j] != null) {
					matriz += String.valueOf(arestas[i][j].getElemento()) + ", ";
					continue;
				}
				matriz += "_, ";
			}
			matriz += "\n";
		}
		
		System.out.println(matriz);
	}

	public ArrayList<Aresta> arestasIncidentes(Vertice vertice) {
		ArrayList<Aresta> arestasTemp = new ArrayList<Aresta>();
		int indiceVertice = vertices.indexOf(vertice);
		
		for (int i = 0; i < qtdVertices + 1; i++) {
			if (arestas[indiceVertice][i] != null) {
				arestasTemp.add(arestas[indiceVertice][i]);
			}
		}
		
		return arestasTemp;
	}
	
	public ArrayList<Vertice> vertices() {
		return vertices;
	}
	
	public ArrayList<Aresta> arestas() {
		ArrayList<Aresta> arestasTemp = new ArrayList<Aresta>();
		
		for (int i = 0; i < qtdVertices; i++) {
			for (int j = 0; j < qtdVertices; j++) {
				arestasTemp.add(arestas[i][j]);
			}
		}
		
		return arestasTemp;
	}
	
	
	/*
	 * Menor caminho
	 * */
	
	public void lerArquivo(String file){
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        String line;
        int row = 0;
        
        try {
		    for (int i = 0; i < this.size; i++) {
		    	inserirVertice(0);
		    }
        	
			while ((line = buffer.readLine()) != null) {
			    String[] vals = line.trim().split("\\s+");
			    this.size = vals.length;
			 
			    for (int col = 0; col < this.size; col++) {
			        inserirAresta(vertices.get(row), vertices.get(col), Integer.parseInt(vals[col]), true);
			    }
			    row++;
			    lerMatriz();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}