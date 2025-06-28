package model;

import java.time.LocalDateTime;

public class HistoricoCard {
    private int id;
    private int idCard;
    private int idColuna;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String motivoBloqueio;
    private String motivoDesbloqueio;
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public int getIdColuna() {
		return idColuna;
	}
	public void setIdColuna(int idColuna) {
		this.idColuna = idColuna;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}
	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}
	public String getMotivoDesbloqueio() {
		return motivoDesbloqueio;
	}
	public void setMotivoDesbloqueio(String motivoDesbloqueio) {
		this.motivoDesbloqueio = motivoDesbloqueio;
	}
	public int getId() {
		return id;
	}

   
}