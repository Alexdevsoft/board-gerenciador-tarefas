package model;

import java.util.List;

public class Board {
    private int id;
    private String nome;
    private List<Coluna> colunas;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Coluna> getColunas() {
		return colunas;
	}
	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}
	public int getId() {
		return id;
	}

    
}