package br.com.meta.aula1.exercicio2.ui;

import br.com.meta.aula1.exercicio2.posto.BombaCombustivel;
import br.com.meta.aula1.exercicio2.posto.Caixa;
import br.com.meta.aula1.exercicio2.posto.Combustivel;
import br.com.meta.aula1.exercicio2.util.EntradaSaida;

public class UI {
	private static String SIM = "S";
	private static String NAO = "N";
	
	private static Caixa caixa;
	
	public static void executar() {
		UI.inicializar();
		UI.exibirMensagemDeBoasVindas();
		UI.executarCaixaPosto();
		UI.exibirMensagemDeAdeus();
	}
	
	private static void inicializar() {
		EntradaSaida.escreverMensagem("Configurando caixa do posto...");
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Informe a quantidade m�nima de combust�vel para obter desconto no pagamento (em litros): ", false);
		double quantidadeMinimaParaDesconto = EntradaSaida.lerDouble(true);
		
		EntradaSaida.escreverMensagem("Informe o valor m�nimo da nota para obter desconto no pagamento (em R$): ", false);
		double valorMinimoParaDesconto = EntradaSaida.lerDouble(true);
		
		EntradaSaida.escreverMensagem("Informe o desconto (em %): ", false);
		double desconto = EntradaSaida.lerDouble(true);

		UI.caixa = new Caixa();
		
		caixa.setDesconto(desconto);
		caixa.setQuantidadeMinimaParaDesconto(quantidadeMinimaParaDesconto);
		caixa.setValorMinimoParaDesconto(valorMinimoParaDesconto);
		
		UI.inicializarBombasCombustivel();
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Caixa do posto configurado com sucesso!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void inicializarBombasCombustivel() {
		BombaCombustivel bombaCombustivel = null;
		Combustivel combustivel = null;
		String resposta = "";
		
		do {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Cadastrando bomba de combust�vel...");
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("Informe o nome do combust�vel: ", false);
			String nome = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o valor do combust�vel (em R$): ", false);
			double valor = EntradaSaida.lerDouble(true);
			
			EntradaSaida.escreverMensagem("Informe o valor com desconto do combust�vel (em R$): ", false);
			double valorComDesconto = EntradaSaida.lerDouble(true);
			
			EntradaSaida.escreverMensagem("Informe a quantidade m�nima para pagar o valor com desconto (em litros): ", false);
			double quantidadeMinimaParaDesconto = EntradaSaida.lerDouble(true);
			
			combustivel = new Combustivel(nome, valor);
			combustivel.setValorComDesconto(valorComDesconto);
			combustivel.setQuantidadeMinimaParaDesconto(quantidadeMinimaParaDesconto);
			
			bombaCombustivel = new BombaCombustivel(combustivel);
			
			EntradaSaida.quebrarLinha();
			if (UI.caixa.adicionarBombaCombustivel(bombaCombustivel)) {
				EntradaSaida.escreverMensagem("Bomba de combust�vel cadastrada com sucesso!");
			} else {
				EntradaSaida.escreverMensagem("N�o foi poss�vel cadastrar a bomba de combust�vel.");
			}
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("-> Deseja continuar a cadastrar bombas de combust�vel? (S/N)");
			resposta = EntradaSaida.lerString();
			
			resposta = resposta.toUpperCase();
			if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma op��o v�lida.");
				continue;
			}
			
			if (resposta.equals(UI.NAO)) {
				break;
			}
		} while (true);
	}
	
	private static void exibirMensagemDeBoasVindas() {
		EntradaSaida.escreverMensagem("Bem-vindo(a) ao Posto de Combust�vel!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void executarCaixaPosto() {
		Combustivel combustivel = null;
		double quantidade = 0.0;
		double valorAPagar = 0.00;
		
		for (BombaCombustivel bombaCombustivel : UI.caixa.getBombasCombustivel()) {
			combustivel = bombaCombustivel.getCombustivel();
			
			EntradaSaida.escreverMensagem("Informe a quantidade abastecida de " + combustivel.getNome() + " (em litros): ", false);
			quantidade = EntradaSaida.lerDouble(true);
			bombaCombustivel.setQuantidade(quantidade);
		}
		
		valorAPagar = UI.caixa.calcularValorAPagar();
		EntradaSaida.escreverMensagem("O valor a pagar � R$ " + String.format("%.2f", valorAPagar) + ".");
	}
	
	private static void exibirMensagemDeAdeus() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Obrigado e volte sempre!");
	}
}
