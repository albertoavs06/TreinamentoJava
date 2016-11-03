package br.com.meta.aula6.exercicio1.banco.pessoas;

public class UsuarioBanco {
	private String login;
	private String nome;
	
	public UsuarioBanco(String login) {
		this(login, "");
	}
	
	public UsuarioBanco(String login, String nome) {
		this.login = login;
		this.nome = nome;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean validarUsuarioBanco(UsuarioBanco usuarioBanco) {
		if (this.login != usuarioBanco.login) {
			return false;
		}
		
		return true;
	}
	
	public boolean compararUsuarioBanco(UsuarioBanco usuarioBanco) {
		if (!this.login.equals(usuarioBanco.login)) {
			return false;
		}
		
		return true;
	}
}
