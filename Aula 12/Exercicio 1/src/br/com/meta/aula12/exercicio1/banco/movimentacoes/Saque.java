package br.com.meta.aula12.exercicio1.banco.movimentacoes;

import java.util.Date;

import br.com.meta.aula12.exercicio1.cliente.Cliente;

public class Saque extends Movimentacao {
	public Saque(Cliente cliente, double valor, String descricao, Date data) {
		this.setCliente(cliente);
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setData(data);
	}
	
	@Override
	public String toString() {
		return super.toString() + "D";
	}
}
