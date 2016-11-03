package br.com.meta.aula6.exercicio1.banco.pessoas;

public class Cliente {
	private String agencia;
	private String conta;
	private String senha;
	private String nome;
	
	public Cliente(String agencia, String conta, String senha) {
		this(agencia, conta, senha, "");
	}
	
	public Cliente(String agencia, String conta, String senha, String nome) {
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.nome = nome;
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
	
	public boolean validarCliente(Cliente cliente) {
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
		if (!this.agencia.equals(cliente.agencia)) {
			return false;
		}
		
		if (!this.conta.equals(cliente.conta)) {
			return false;
		}
		
		if (!this.senha.equals(cliente.senha)) {
			return false;
		}
		
		return true;
	}
}
