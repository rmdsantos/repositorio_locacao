package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Veiculo {
	@Id
	
	private String placa;
	private String fabricante;
	private String modelo;
	private int kmAtual;
	private float taxaDiaria;
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getKmAtual() {
		return kmAtual;
	}
	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}
	public float getTaxaDiaria() {
		return taxaDiaria;
	}
	public void setTaxaDiaria(float taxaDiaria) {
		this.taxaDiaria = taxaDiaria;
	}
	
	
	
	
	

}
