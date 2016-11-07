package br.com.meta.aula2.exercicio1.pessoa;

public class Pessoa {
	private String nome;
	private int idade;
	
	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public int getIdade() {
		return this.idade;
	}
	
	public void fazAniversario() {
		this.idade = this.idade + 1;
	}
	
	public String retornaNomeIdade() {
		return ("Nome: " + this.nome + "; Idade: " + this.idade); 
	}
}
