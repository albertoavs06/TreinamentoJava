package br.com.meta.aula1.exercicio2.posto;

import java.util.ArrayList;
import java.util.List;

public class Caixa {
	private List<BombaCombustivel> bombasCombustivel;
	private double quantidadeMinimaParaDesconto;
	private double valorMinimoParaDesconto;
	private double desconto;
	
	public Caixa() {
		this(0.0, 0.0, 0.0);
	}
	
	private Caixa(double quantidadeMinimaParaDesconto, double valorMinimoParaDesconto, double desconto) {
		this.bombasCombustivel = new ArrayList<BombaCombustivel>();
		this.quantidadeMinimaParaDesconto = quantidadeMinimaParaDesconto;
		this.valorMinimoParaDesconto = valorMinimoParaDesconto;
		this.desconto = desconto;
	}
	
	public void setBombasCombustivel(ArrayList<BombaCombustivel> bombasCombustivel) {
		this.bombasCombustivel = bombasCombustivel;
	}
	
	public List<BombaCombustivel> getBombasCombustivel() {
		return this.bombasCombustivel;
	}
	
	public void setQuantidadeMinimaParaDesconto(double quantidadeMinimaParaDesconto) {
		this.quantidadeMinimaParaDesconto = quantidadeMinimaParaDesconto;
	}
	
	public double getQuantidadeMinimaParaDesconto() {
		return this.quantidadeMinimaParaDesconto;
	}
	
	public void setValorMinimoParaDesconto(double valorMinimoParaDesconto) {
		this.valorMinimoParaDesconto = valorMinimoParaDesconto;
	}
	
	public double getValorMinimoParaDesconto() {
		return this.valorMinimoParaDesconto;
	}
	
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public boolean adicionarBombaCombustivel(BombaCombustivel bombaCombustivel) {
		return this.bombasCombustivel.add(bombaCombustivel);
	}
	
	public boolean removerBombaCombustivel(BombaCombustivel bombaCombustivel) {
		return this.bombasCombustivel.remove(bombaCombustivel);
	}
	
	public double calcularValorAPagar() {
		double valorAPagar = 0.0;
		
		double quantidadeAbastecida = 0.0;
		for (BombaCombustivel bombaCombustivel : this.bombasCombustivel) {
			valorAPagar += bombaCombustivel.calculaValorAPagar();
			quantidadeAbastecida += bombaCombustivel.getQuantidade();
		}
		
		if (quantidadeAbastecida > this.quantidadeMinimaParaDesconto || valorAPagar > this.valorMinimoParaDesconto) {
			valorAPagar = valorAPagar * (1 - (this.desconto / 100));
		}
		
		return valorAPagar;
	}
}
