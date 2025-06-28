package model;

import java.time.LocalDateTime;
import java.util.List;

public class Card {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private boolean bloqueado;
    private String motivoBloqueio;
    private List<HistoricoCard> historico;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}
	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}
	public List<HistoricoCard> getHistorico() {
		return historico;
	}
	public void setHistorico(List<HistoricoCard> historico) {
		this.historico = historico;
	}
	public int getId() {
		return id;
	}

    
}