package br.com.meta.aula2.exercicio1.ui;

import br.com.meta.aula2.exercicio1.pessoa.Pessoa;
import br.com.meta.aula2.exercicio1.util.EntradaSaida;

public class UI {
	private static String SIM = "S";
	private static String NAO = "N";
	private static Pessoa pessoa;
	
	public static void executar() {
		UI.inicializar();
		UI.atualizar();
		UI.finalizar();
	}
	
	private static void inicializar() {
		EntradaSaida.escreverMensagem("Olá! Informe o seu nome e a sua idade.");
		
		EntradaSaida.escreverMensagem("Nome: ", false);
		String nome = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Idade: ", false);
		int idade = EntradaSaida.lerInt(true);
		
		UI.pessoa = new Pessoa(nome, idade);
	}
	
	private static void atualizar() {
		String resposta = "";
		do {
			EntradaSaida.escreverMensagem("-> Deseja que a pessoa faça aniversário? (S/N)");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			
			if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma opção válida.");
				continue;
			}
			
			if (resposta.equals(UI.NAO)) {
				break;
			}
			
			UI.pessoa.fazAniversario();
		} while (true);
	}
	
	private static void finalizar() {
		EntradaSaida.escreverMensagem("Os dados da pessoa agora são:");
		EntradaSaida.escreverMensagem("-> " + UI.pessoa.retornaNomeIdade());
		EntradaSaida.escreverMensagem("Adeus!");
	}
}
