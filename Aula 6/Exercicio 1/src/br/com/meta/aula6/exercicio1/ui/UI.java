package br.com.meta.aula6.exercicio1.ui;

import java.util.ArrayList;

import br.com.meta.aula6.exercicio1.banco.CaixaEletronico;
import br.com.meta.aula6.exercicio1.banco.pessoas.Cliente;
import br.com.meta.aula6.exercicio1.banco.pessoas.UsuarioBanco;
import br.com.meta.aula6.exercicio1.banco.valores.Cedula;
import br.com.meta.aula6.exercicio1.util.EntradaSaida;

public class UI {
	private static final int TELA_FUNCIONARIO = 1;
	private static final int TELA_CLIENTE = 2;
	private static final int SAIR = 3;
	private static final int ACESSAR_FUNCIONARIO = 1;
	private static final int SAIR_TELA_FUNCIONARIO = 2;
	private static final int REPOR_CEDULAS = 1;
	private static final int SAIR_FUNCIONARIO = 2;
	private static final int ACESSAR_CLIENTE = 1;
	private static final int SAIR_TELA_CLIENTE = 2;
	private static final int SACAR = 1;
	private static final int SAIR_CLIENTE = 2;
	private static final String SAIR_CADASTRO = "S";
	private static int acaoUsuario = 0;
	private static int acaoFuncionario = 0;
	private static int acaoFuncionarioLogado = 0;
	private static int acaoCliente = 0;
	private static int acaoClienteLogado = 0;
	private static CaixaEletronico caixaEletronico;
	
	public static void executar() {
		UI.configurarCaixaEletronico();
		UI.exibirMensagemDeBoasVindas();
		UI.executarCaixaEletronico();
		UI.exibirMensagemDeAdeus();
	}
	
	private static void configurarCaixaEletronico() {
		String senhaAdministracao = "";
		
		EntradaSaida.escreverMensagem("Configurando Caixa Eletr�nico...\n");
		
		EntradaSaida.escreverMensagem("Cadastrando senha de administra��o...\n");
		
		EntradaSaida.escreverMensagem("Informe a senha de administra��o do caixa eletr�nico: ", false);
		senhaAdministracao = EntradaSaida.lerString();
		
		UI.caixaEletronico = new CaixaEletronico(senhaAdministracao);
		
		EntradaSaida.escreverMensagem("\nSenha de administra��o cadastrada!\n");
		
		UI.inicializarCedulas();
		UI.inicializarUsuariosBanco();
		UI.inicializarClientesBanco();
		
		EntradaSaida.escreverMensagem("Caixa Eletr�nico configurado com sucesso!\n");
	}
	
	private static void inicializarCedulas() {
		double valorCedula = 0.0;
		int quantidadeCedula = 0;
		Cedula cedula = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando c�dulas...\n");
		
		do {
			EntradaSaida.escreverMensagem("Informe o valor da c�dula: ", false);
			valorCedula = EntradaSaida.lerDouble(true);
			
			EntradaSaida.escreverMensagem("Informe a quantidade de c�dulas: ", false);
			quantidadeCedula = EntradaSaida.lerInt(true);
			
			cedula = new Cedula(valorCedula);
			cedula.setQuantidade(quantidadeCedula);
			
			if (UI.caixaEletronico.cadastrarCedula(cedula)) {
				EntradaSaida.escreverMensagem("C�dula cadastrada com sucesso!\n");
			} else {
				EntradaSaida.escreverMensagem("N�o foi poss�vel cadastrar a c�dula! � prov�vel que esta c�dula j� tenha sido cadastrada.\n");
			}
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de c�dulas, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de c�dulas, pressione qualquer tecla.");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.escreverMensagem("C�dulas cadastradas com sucesso!\n");
	}
	
	private static void inicializarUsuariosBanco() {
		String login = "";
		String nome = "";
		UsuarioBanco usuario = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando usu�rios...\n");
		
		do {
			EntradaSaida.escreverMensagem("Informe o login do usu�rio: ", false);
			login = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o nome do usu�rio: ", false);
			nome = EntradaSaida.lerString();
			
			usuario = new UsuarioBanco(login, nome);
			if (UI.caixaEletronico.cadastrarUsuario(usuario)) {
				EntradaSaida.escreverMensagem("Usu�rio cadastrado com sucesso!\n");
			} else {
				EntradaSaida.escreverMensagem("N�o foi poss�vel cadastrar o usu�rio! � prov�vel que este usu�rio j� tenha sido cadastrado.\n");
			}
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de usu�rios, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de usu�rios, pressione qualquer tecla.");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.escreverMensagem("Usu�rios cadastrados com sucesso!\n");
	}
	
	private static void inicializarClientesBanco() {
		String agencia = "";
		String conta = "";
		String senha = "";
		String nome = "";
		Cliente cliente = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando clientes...\n");
		
		do {
			EntradaSaida.escreverMensagem("Informe a ag�ncia do cliente: ", false);
			agencia = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe a conta do cliente: ", false);
			conta = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o nome do cliente: ", false);
			nome = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe a senha do cliente: ", false);
			senha = EntradaSaida.lerString(); 
			
			cliente = new Cliente(agencia, conta, senha, nome);
			if (UI.caixaEletronico.cadastrarCliente(cliente)) {
				EntradaSaida.escreverMensagem("Cliente cadastrado com sucesso!\n");
			} else {
				EntradaSaida.escreverMensagem("N�o foi poss�vel cadastrar o cliente! � prov�vel que este cliente j� tenha sido cadastrado.\n");
			}
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de clientes, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de clientes, pressione qualquer tecla.");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.escreverMensagem("Clientes cadastrados com sucesso!\n");
	}
	
	private static void exibirMensagemDeBoasVindas() {
		EntradaSaida.escreverMensagem("Bem-vindo(a) ao Caixa Eletr�nico!");
	}
	
	private static void exibirMensagemDeAdeus() {
		EntradaSaida.escreverMensagem("\nObrigado e volte sempre!\n");
	}
	
	private static void executarCaixaEletronico() {
		while (UI.acaoUsuario != UI.SAIR) {
			UI.exibirMenu();
			UI.executarAcaoUsuario();
		}
		
		UI.acaoUsuario = 0;
	}
	
	private static void exibirMenu() {
		EntradaSaida.escreverMensagem("-> Para exibir a tela de funcion�rios, tecle " + UI.TELA_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para acessar a tela de clientes, tecle " + UI.TELA_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR + ".");
	}
	
	private static void executarAcaoUsuario() {
		UI.acaoUsuario = EntradaSaida.lerInt(true);
		
		switch (UI.acaoUsuario) {
			case UI.TELA_FUNCIONARIO:
				UI.exibirTelaFuncionario();
				break;
			case UI.TELA_CLIENTE:
				UI.exibirTelaCliente();
				break;
			case UI.SAIR:
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma a��o v�lida.");
		}
	}
	
	private static void exibirTelaFuncionario() {
		while (UI.acaoFuncionario != UI.SAIR_TELA_FUNCIONARIO) {
			UI.exibirMenuFuncionario();
			UI.executarAcaoFuncionario();
		}
		
		UI.acaoFuncionario = 0;
	}
	
	private static void exibirMenuFuncionario() {
		EntradaSaida.escreverMensagem("-> Para fazer login como funcion�rio, tecle " + UI.ACESSAR_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_TELA_FUNCIONARIO + ".");
	}
	
	private static void executarAcaoFuncionario() {
		UI.acaoFuncionario = EntradaSaida.lerInt(true);
		
		switch (UI.acaoFuncionario) {
			case UI.ACESSAR_FUNCIONARIO:
				UI.executarCaixaEletronicoFuncionario();
				break;
			case UI.SAIR_TELA_FUNCIONARIO:
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma a��o v�lida.");
		}
	}
	
	private static void executarCaixaEletronicoFuncionario() {
		String login = "";
		String senha = "";
		
		EntradaSaida.escreverMensagem("Informe o login: ", false);
		login = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha: ", false);
		senha = EntradaSaida.lerString();
		
		if (UI.caixaEletronico.logarComoUsuario(login, senha)) {
			UI.exibirMensagemDeBoasVindasFuncionarioLogado();
			UI.executarCaixaEletronicoFuncionarioLogado();
			UI.exibirMensagemDeAdeus();
		} else {
			EntradaSaida.escreverMensagem("N�o foi poss�vel realizar o login. Login e/ou senha inv�lidos.\n");
		}
	}
	
	private static void exibirMensagemDeBoasVindasFuncionarioLogado() {
		UsuarioBanco usuario = UI.caixaEletronico.getUsuarioLogado();
		EntradaSaida.escreverMensagem("\nBem-vindo(a), " + usuario.getNome() + "!\n");
	}
	
	private static void executarCaixaEletronicoFuncionarioLogado() {
		while (UI.acaoFuncionarioLogado != UI.SAIR_FUNCIONARIO) {
			UI.exibirMenuFuncionarioLogado();
			UI.executarAcaoFuncionarioLogado();
		}
		
		UI.acaoFuncionarioLogado = 0;
	}
	
	private static void exibirMenuFuncionarioLogado() {
		EntradaSaida.escreverMensagem("-> Para realizar reposi��o de c�dulas, tecle " + UI.REPOR_CEDULAS + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_FUNCIONARIO + ".");
	}
	
	private static void executarAcaoFuncionarioLogado() {
		UI.acaoFuncionarioLogado = EntradaSaida.lerInt(true);
		
		switch (UI.acaoFuncionarioLogado) {
			case UI.REPOR_CEDULAS:
				UI.reporCedulas();
				break;
			case UI.SAIR_FUNCIONARIO:
				UI.caixaEletronico.deslogarUsuario();
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma a��o v�lida.");
		}
	}
	
	private static void reporCedulas() {
		UI.exibirTotalCedulasDisponiveis();
		UI.efetuarReposicaoCedulas();
		UI.exibirTotalCedulasDisponiveis();
	}
	
	private static void exibirTotalCedulasDisponiveis() {
		double totalDisponivel = 0.0;
		String cedulasDisponiveis = "";
		
		totalDisponivel = UI.caixaEletronico.calcularTotalDisponivel();
		cedulasDisponiveis = UI.caixaEletronico.retornarCedulasDisponiveis(true, true);

		EntradaSaida.escreverMensagem("O novo total dispon�vel em caixa �: " + String.format("%.2f", totalDisponivel));
		EntradaSaida.escreverMensagem("As c�dulas dispon�veis s�o:");
		EntradaSaida.escreverMensagem(cedulasDisponiveis);
	}
	
	private static void efetuarReposicaoCedulas() {
		ArrayList<Cedula> cedulasRepostas = new ArrayList<Cedula>();
		Cedula cedulaReposta = null;
		double valor = 0.0;
		int quantidade = 0;
		
		for (Cedula cedula : UI.caixaEletronico.getCedulasDisponiveis()) {
			valor = cedula.getValor();
			EntradaSaida.escreverMensagem("Informe a quantidade de notas de " + String.format("%.2f", valor) + ": ", false);
			quantidade = EntradaSaida.lerInt(true);
			
			cedulaReposta = new Cedula(valor, quantidade);
			cedulasRepostas.add(cedulaReposta);
		}
		
		if (UI.caixaEletronico.efetuarReposicao(cedulasRepostas)) {
			EntradaSaida.escreverMensagem("Reposi��o de c�dulas efetuada com sucesso!\n");
		} else {
			EntradaSaida.escreverMensagem("N�o foi poss�vel efetuar a reposi��o de c�dulas.\n");
		}
	}
	
	private static void exibirTelaCliente() {
		while (UI.acaoCliente != UI.SAIR_TELA_CLIENTE) {
			UI.exibirMenuCliente();
			UI.executarAcaoCliente();
		}
		
		UI.acaoCliente = 0;
	}
	
	private static void exibirMenuCliente() {
		EntradaSaida.escreverMensagem("-> Para fazer login como cliente, tecle " + UI.ACESSAR_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_TELA_CLIENTE + ".");
	}
	
	private static void executarAcaoCliente() {
		UI.acaoCliente = EntradaSaida.lerInt(true);
		
		switch (UI.acaoCliente) {
			case UI.ACESSAR_CLIENTE:
				UI.executarCaixaEletronicoCliente();
				break;
			case UI.SAIR_TELA_CLIENTE:
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma a��o v�lida.");
		}
	}
	
	private static void executarCaixaEletronicoCliente() {
		String agencia = "";
		String conta = "";
		String senha = "";
		
		EntradaSaida.escreverMensagem("Informe a ag�ncia: ", false);
		agencia = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a conta: ", false);
		conta = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha: ", false);
		senha = EntradaSaida.lerString();
		
		if (UI.caixaEletronico.logarComoCliente(agencia, conta, senha)) {
			UI.exibirMensagemDeBoasVindasClienteLogado();
			UI.executarCaixaEletronicoClienteLogado();
			UI.exibirMensagemDeAdeus();
		} else {
			EntradaSaida.escreverMensagem("N�o foi poss�vel realizar o login. Ag�ncia e/ou conta e/ou senha inv�lidos.\n");
		}
	}
	
	private static void exibirMensagemDeBoasVindasClienteLogado() {
		Cliente cliente = UI.caixaEletronico.getClienteLogado();
		EntradaSaida.escreverMensagem("\nBem-vindo(a), " + cliente.getNome() + "!\n");
	}

	private static void executarCaixaEletronicoClienteLogado() {
		while (UI.acaoClienteLogado != UI.SAIR_CLIENTE) {
			UI.exibirMenuClienteLogado();
			UI.executarAcaoClienteLogado();
		}
		
		UI.acaoClienteLogado = 0;
	}
	
	private static void exibirMenuClienteLogado() {
		EntradaSaida.escreverMensagem("-> Para realizar saque, tecle " + UI.SACAR + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_CLIENTE + ".");
	}

	private static void executarAcaoClienteLogado() {
		UI.acaoClienteLogado = EntradaSaida.lerInt(true);
		
		switch (UI.acaoClienteLogado) {
			case UI.SACAR:
				UI.sacar();
				break;
			case UI.SAIR_CLIENTE:
				UI.caixaEletronico.deslogarCliente();
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado n�o corresponde a uma a��o v�lida.");
		}
	}
	
	private static void sacar() {
		UI.exibirCedulasDisponiveis();
		UI.efetuarSaque();
	}
	
	private static void exibirCedulasDisponiveis() {
		String cedulasDisponiveis = "";
		
		cedulasDisponiveis = UI.caixaEletronico.retornarCedulasDisponiveis(false, false);
		
		if (!cedulasDisponiveis.isEmpty()) {
			EntradaSaida.escreverMensagem("As c�dulas dispon�veis s�o:");
			EntradaSaida.escreverMensagem(cedulasDisponiveis);
			return;
		}
		EntradaSaida.escreverMensagem("N�o h� c�dulas dispon�veis.\n");
	}
	
	private static void efetuarSaque() {
		double valor = 0.0;
		String cedulasSacadas = "";
		
		EntradaSaida.escreverMensagem("Informe o valor do saque: ", false);
		valor = EntradaSaida.lerDouble(true);
		
		if (UI.caixaEletronico.efetuarSaque(valor)) {
			cedulasSacadas = UI.caixaEletronico.retornarCedulasSacadas();
			
			EntradaSaida.escreverMensagem("Saque efetuado com sucesso!\n");
			EntradaSaida.escreverMensagem("As c�dulas sacadas foram:");
			EntradaSaida.escreverMensagem(cedulasSacadas);
		} else {
			EntradaSaida.escreverMensagem("N�o foi poss�vel efetuar o saque.\n");
		}
	}
}
