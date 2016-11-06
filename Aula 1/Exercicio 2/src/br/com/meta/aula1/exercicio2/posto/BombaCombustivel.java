package br.com.meta.aula1.exercicio2.posto;

public class BombaCombustivel {
	private Combustivel combustivel;
	private double quantidade;
	
	public BombaCombustivel(Combustivel combustivel) {
		this(combustivel, 0.0);
	}
	
	private BombaCombustivel(Combustivel combustivel, double quantidade) {
		this.combustivel = combustivel;
		this.quantidade = quantidade;
	}
	
	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}
	
	public Combustivel getCombustivel() {
		return this.combustivel;
	}
	
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getQuantidade() {
		return this.quantidade;
	}
	
	public double calculaValorAPagar() {
		double valorAPagar = 0.0;
		
		if (this.quantidade > combustivel.getQuantidadeMinimaParaDesconto()) {
			valorAPagar = this.quantidade * combustivel.getValorComDesconto();
		} else {
			valorAPagar = this.quantidade * combustivel.getValor();
		}
		
		return valorAPagar;
	}
}
