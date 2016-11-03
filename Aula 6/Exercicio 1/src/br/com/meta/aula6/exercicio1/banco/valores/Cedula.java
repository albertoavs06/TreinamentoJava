package br.com.meta.aula6.exercicio1.banco.valores;

public class Cedula {
	private double valor;
	private int quantidade;
	
	public Cedula(double valor) {
		this(valor, 0);
	}
	
	public Cedula(double valor, int quantidade) {
		this.valor = valor;
		this.quantidade = quantidade;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public boolean compararCedula(Cedula cedula) {
		if (this.valor != cedula.valor) {
			return false;
		}
		
		return true;
	}
}
