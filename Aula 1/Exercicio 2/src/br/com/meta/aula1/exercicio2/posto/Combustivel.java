package br.com.meta.aula1.exercicio2.posto;

public class Combustivel {
	private String nome;
	private double valor;
	private double valorComDesconto;
	private double quantidadeMinimaParaDesconto;
	
	public Combustivel(String nome, double valor) {
		this(nome, valor, 0.0, 0.0);
	}
	
	private Combustivel(
			String nome, double valor, double valorComDesconto,
			double quantidadeMinimaParaDesconto) {
		
		this.nome = nome;
		this.valor = valor;
		this.valorComDesconto = valorComDesconto;
		this.quantidadeMinimaParaDesconto = quantidadeMinimaParaDesconto;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public void setValorComDesconto(double valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}
	
	public double getValorComDesconto() {
		return this.valorComDesconto;
	}
	
	public void setQuantidadeMinimaParaDesconto(double quantidadeMinimaParaDesconto) {
		this.quantidadeMinimaParaDesconto = quantidadeMinimaParaDesconto;
	}
	
	public double getQuantidadeMinimaParaDesconto() {
		return this.quantidadeMinimaParaDesconto;
	}
}
