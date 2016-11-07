package br.com.meta.aula12.exercicio1.banco.movimentacoes;

import java.text.DateFormat;
import java.util.Date;

import br.com.meta.aula12.exercicio1.cliente.Cliente;

public abstract class Movimentacao {
	private Cliente cliente;
	private String descricao;
	private double valor;
	private Date data;
	
	public final void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public final Cliente getCliente() {
		return this.cliente;
	}
	
	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public final String getDescricao() {
		return this.descricao;
	}
	
	public final void setValor(double valor) {
		this.valor = valor;
	}
	
	public final double getValor() {
		return this.valor;
	}
	
	public final void setData(Date data) {
		this.data = data;
	}
	
	public final Date getData() {
		return this.data;
	}
	
	public boolean compararMovimentacao(Movimentacao movimentacao) {
		if (movimentacao == null) {
			return false;
		}
		
		if (this.getClass() != movimentacao.getClass()) {
			return false;
		}
		
		if (!this.cliente.compararCliente(movimentacao.cliente)) {
			return false;
		}
		
		if (!this.descricao.equals(movimentacao.descricao)) {
			return false;
		}
		
		if (this.valor != movimentacao.valor) {
			return false;
		}
		
		if (this.data.compareTo(movimentacao.data) != 0) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		String retorno = "";
		
		Cliente cliente = this.getCliente();
		retorno += DateFormat.getInstance().format(this.getData()) + " - ";
		
		if (cliente != null) {
			retorno += cliente.getAgencia() + " " + cliente.getConta() + " " + cliente.getNome() + " - ";
		}
		
		retorno += this.getDescricao() + " - ";
		retorno += String.format("%.2f", this.getValor());

		return retorno;
	}
}
