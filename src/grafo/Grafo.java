package grafo;

/**
 * @author decio
 *
 */
public interface Grafo {
	
	/*
	 * Métodos Acessadores
	 * */
	
	/**
	 * Retorna um array armazenando os vértices finais da aresta passada.
	 * 
	 * @param aresta
	 * 
	 * @return Tipo: Vertice[]
	 */ 
	public Vertice[] finalVertices(Aresta aresta);
	
	/**
	 * Retorna o vértice oposto da aresta.
	 * 
	 * @param vertice
	 * @param aresta
	 * 
	 * @return Tipo: Vertice
	 */
	public Vertice oposto(Vertice vertice, Aresta aresta);
	
	/**
	 * Verifica se os vertices passados são adjacentes
	 * 
	 * @param verticeInicial
	 * @param verticeFinal
	 * 
	 * @return Tipo: booelan
	 */
	public boolean ehAdjacente(Vertice verticeInicial, Vertice verticeFinal);
	
	/**
	 * Substitui o valor armazenado no vértice
	 * 
	 * @param vertice
	 * @param valor
	 * 
	 * @return Tipo: Sem retorno
	 */
	public void substituir(Vertice vertice, double valor);
	
	/**
	 * Substitui o valor armazenado na aresta
	 * 
	 * @param aresta
	 * @param valor
	 * 
	 * @return Tipo: Sem retorno
	 */
	public void substituir(Aresta aresta, double valor);
	
	/**
	 * Retorna a ordem do grafo
	 * 
	 * @return Tipo: int
	 */
	public int ordem();
	
	
	/*
	 * Métodos Atualizadores
	 * */
	
	/**
	 * Insere um novo vertice com o valor passado
	 * 
	 * @param valor
	 * 
	 * @return Tipo: Vertice
	 */
	public Vertice inserirVertice(double valor);
	
	/**
	 * Insere uma nova aresta que liga os vertices e armazena o valor
	 * 
	 * @param verticeIncial
	 * @param verticeFinal
	 * @param valor
	 * 
	 * @return Tipo: Aresta
	 */
	public Aresta inserirAresta(Vertice verticeIncial, Vertice verticeFinal, double valor);
	
	/**
	 * Remove o vertice e todas as arestas incidentes
	 * 
	 * @param vertice
	 * 
	 * @return Tipo: double
	 */
	public double removerVertice(Vertice vertice);
	
	/**
	 * Remove a aresta retornando o valor armazenado
	 * 
	 * @param aresta
	 * 
	 * @return Tipo: double
	 */
	public double removerAresta(Aresta aresta);
	
	
	/*
	 * Métodos Interadores
	 * */
	
	/**
	 * Retorna um array com as arestas incidentes no vertice
	 * 
	 * @param vertice
	 * 
	 * @return Tipo: Aresta[]
	 */
	public Aresta[] arestasIncidentes(Vertice vertice);
	
	/**
	 * Retorna um array com todas os vertices do grafo
	 * 
	 * @return Tipo: Vertice[]
	 */
	public Vertice[] vertices();
	
	/**
	 * Retorna um array com todas as arestas do grafo
	 * 
	 * @return Tipo: Aresta[]
	 */
	public Aresta[] arestas();
}
