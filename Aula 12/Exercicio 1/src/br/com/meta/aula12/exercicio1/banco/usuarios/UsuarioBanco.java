package br.com.meta.aula12.exercicio1.banco.usuarios;

public class UsuarioBanco {
	private String login;
	private String senha;
	private String nome;
	
	public UsuarioBanco(String login, String senha) {
		this(login, senha, "");
	}
	
	public UsuarioBanco(String login, String senha, String nome) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
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
	
	public boolean compararUsuarioBanco(UsuarioBanco usuarioBanco) {
		if (usuarioBanco == null) {
			return false;
		}
		
		if (!this.login.equals(usuarioBanco.login)) {
			return false;
		}
		
		if (!this.senha.equals(usuarioBanco.senha)) {
			return false;
		}
		
		return true;
	}
}