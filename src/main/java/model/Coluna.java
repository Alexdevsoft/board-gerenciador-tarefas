package model;

import java.util.List;

public class Coluna {
    private int id;
    private String nome;
    private int ordem;
    private TipoColuna tipo;
    private List<Card> cards;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public TipoColuna getTipo() {
		return tipo;
	}
	public void setTipo(TipoColuna tipo) {
		this.tipo = tipo;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public int getId() {
		return id;
	}

    
}