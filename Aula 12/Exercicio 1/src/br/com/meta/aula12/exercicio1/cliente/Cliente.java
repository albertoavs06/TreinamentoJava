package br.com.meta.aula12.exercicio1.cliente;

import java.util.ArrayList;
import java.util.List;

import br.com.meta.aula12.exercicio1.banco.movimentacoes.Movimentacao;

public class Cliente {
	private String agencia;
	private String conta;
	private String senha;
	private String nome;
	private double saldo;
	private List<Movimentacao> movimentacoes;
	
	public Cliente(String agencia, String conta, String senha) {
		this(agencia, conta, senha, "");
	}
	
	public Cliente(String agencia, String conta, String senha, String nome) {
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.nome = nome;
		this.saldo = 0.0;
		this.movimentacoes = new ArrayList<Movimentacao>();
	}
	
	public String getAgencia() {
		return this.agencia;
	}
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public String getConta() {
		return this.conta;
	}
	
	public void setConta(String conta) {
		this.conta = conta;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return this.movimentacoes;
	}
	
	public void setMovimentacoes(ArrayList<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public boolean existeMovimentacao(Movimentacao movimentacao) {
		for (Movimentacao mov : this.movimentacoes) {
			if (mov.compararMovimentacao(movimentacao)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Movimentacao buscarMovimentacao(Movimentacao movimentacao) {
		for (Movimentacao mov : this.movimentacoes) {
			if (mov.compararMovimentacao(movimentacao)) {
				return mov;
			}
		}
		
		return null;
	}
	
	public boolean adicionarMovimentacao(Movimentacao movimentacao) {
		if (this.existeMovimentacao(movimentacao)) {
			return false;
		}
		
		return this.movimentacoes.add(movimentacao);
	}
	
	public boolean removerMovimentacao(Movimentacao movimentacao) {
		if (!this.existeMovimentacao(movimentacao)) {
			return false;
		}
		
		return this.movimentacoes.remove(movimentacao);
	}
	
	public boolean validarCliente(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		
		if (this.agencia != cliente.agencia) {
			return false;
		}
		
		if (this.conta != cliente.conta) {
			return false;
		}
		
		if (this.senha != cliente.senha) {
			return false;
		}
		
		return true;
	}
	
	public boolean compararCliente(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		
		if (!this.agencia.equals(cliente.agencia)) {
			return false;
		}
		
		if (!this.conta.equals(cliente.conta)) {
			return false;
		}
		
		return true;
	}
}