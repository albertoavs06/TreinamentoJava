package br.com.meta.aula12.exercicio1.banco.movimentacoes;

import java.util.Date;

import br.com.meta.aula12.exercicio1.cliente.Cliente;

public class Deposito extends Movimentacao {
	private boolean autorizado;
	
	public Deposito(Cliente cliente, double valor, String descricao, Date data) {
		this.setCliente(cliente);
		this.setValor(valor);
		this.setDescricao(descricao);
		this.setData(data);
		this.autorizado = false;
	}
	
	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	public boolean getAutorizado() {
		return this.autorizado;
	}

	@Override
	public String toString() {
		return super.toString() + "C";
	}
}
