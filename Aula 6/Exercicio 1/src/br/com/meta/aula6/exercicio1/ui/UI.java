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
		
		EntradaSaida.escreverMensagem("Configurando Caixa Eletrônico...\n");
		
		EntradaSaida.escreverMensagem("Cadastrando senha de administração...\n");
		
		EntradaSaida.escreverMensagem("Informe a senha de administração do caixa eletrônico: ", false);
		senhaAdministracao = EntradaSaida.lerString();
		
		UI.caixaEletronico = new CaixaEletronico(senhaAdministracao);
		
		EntradaSaida.escreverMensagem("\nSenha de administração cadastrada!\n");
		
		UI.inicializarCedulas();
		UI.inicializarUsuariosBanco();
		UI.inicializarClientesBanco();
		
		EntradaSaida.escreverMensagem("Caixa Eletrônico configurado com sucesso!\n");
	}
	
	private static void inicializarCedulas() {
		double valorCedula = 0.0;
		int quantidadeCedula = 0;
		Cedula cedula = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando cédulas...\n");
		
		do {
			EntradaSaida.escreverMensagem("Informe o valor da cédula: ", false);
			valorCedula = EntradaSaida.lerDouble(true);
			
			EntradaSaida.escreverMensagem("Informe a quantidade de cédulas: ", false);
			quantidadeCedula = EntradaSaida.lerInt(true);
			
			cedula = new Cedula(valorCedula);
			cedula.setQuantidade(quantidadeCedula);
			
			if (UI.caixaEletronico.cadastrarCedula(cedula)) {
				EntradaSaida.escreverMensagem("Cédula cadastrada com sucesso!\n");
			} else {
				EntradaSaida.escreverMensagem("Não foi possível cadastrar a cédula! É provável que esta cédula já tenha sido cadastrada.\n");
			}
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de cédulas, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de cédulas, pressione qualquer tecla.");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.escreverMensagem("Cédulas cadastradas com sucesso!\n");
	}
	
	private static void inicializarUsuariosBanco() {
		String login = "";
		String nome = "";
		UsuarioBanco usuario = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando usuários...\n");
		
		do {
			EntradaSaida.escreverMensagem("Informe o login do usuário: ", false);
			login = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o nome do usuário: ", false);
			nome = EntradaSaida.lerString();
			
			usuario = new UsuarioBanco(login, nome);
			if (UI.caixaEletronico.cadastrarUsuario(usuario)) {
				EntradaSaida.escreverMensagem("Usuário cadastrado com sucesso!\n");
			} else {
				EntradaSaida.escreverMensagem("Não foi possível cadastrar o usuário! É provável que este usuário já tenha sido cadastrado.\n");
			}
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de usuários, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de usuários, pressione qualquer tecla.");
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.escreverMensagem("Usuários cadastrados com sucesso!\n");
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
			EntradaSaida.escreverMensagem("Informe a agência do cliente: ", false);
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
				EntradaSaida.escreverMensagem("Não foi possível cadastrar o cliente! É provável que este cliente já tenha sido cadastrado.\n");
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
		EntradaSaida.escreverMensagem("Bem-vindo(a) ao Caixa Eletrônico!");
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
		EntradaSaida.escreverMensagem("-> Para exibir a tela de funcionários, tecle " + UI.TELA_FUNCIONARIO + ";");
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
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
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
		EntradaSaida.escreverMensagem("-> Para fazer login como funcionário, tecle " + UI.ACESSAR_FUNCIONARIO + ";");
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
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
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
			EntradaSaida.escreverMensagem("Não foi possível realizar o login. Login e/ou senha inválidos.\n");
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
		EntradaSaida.escreverMensagem("-> Para realizar reposição de cédulas, tecle " + UI.REPOR_CEDULAS + ";");
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
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
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

		EntradaSaida.escreverMensagem("O novo total disponível em caixa é: " + String.format("%.2f", totalDisponivel));
		EntradaSaida.escreverMensagem("As cédulas disponíveis são:");
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
			EntradaSaida.escreverMensagem("Reposição de cédulas efetuada com sucesso!\n");
		} else {
			EntradaSaida.escreverMensagem("Não foi possível efetuar a reposição de cédulas.\n");
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
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
		}
	}
	
	private static void executarCaixaEletronicoCliente() {
		String agencia = "";
		String conta = "";
		String senha = "";
		
		EntradaSaida.escreverMensagem("Informe a agência: ", false);
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
			EntradaSaida.escreverMensagem("Não foi possível realizar o login. Agência e/ou conta e/ou senha inválidos.\n");
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
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
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
			EntradaSaida.escreverMensagem("As cédulas disponíveis são:");
			EntradaSaida.escreverMensagem(cedulasDisponiveis);
			return;
		}
		EntradaSaida.escreverMensagem("Não há cédulas disponíveis.\n");
	}
	
	private static void efetuarSaque() {
		double valor = 0.0;
		String cedulasSacadas = "";
		
		EntradaSaida.escreverMensagem("Informe o valor do saque: ", false);
		valor = EntradaSaida.lerDouble(true);
		
		if (UI.caixaEletronico.efetuarSaque(valor)) {
			cedulasSacadas = UI.caixaEletronico.retornarCedulasSacadas();
			
			EntradaSaida.escreverMensagem("Saque efetuado com sucesso!\n");
			EntradaSaida.escreverMensagem("As cédulas sacadas foram:");
			EntradaSaida.escreverMensagem(cedulasSacadas);
		} else {
			EntradaSaida.escreverMensagem("Não foi possível efetuar o saque.\n");
		}
	}
}
