package br.com.meta.aula12.exercicio1.ui;

import java.util.ArrayList;

import br.com.meta.aula12.exercicio1.cliente.Cliente;
import br.com.meta.aula12.exercicio1.banco.CaixaEletronico;
import br.com.meta.aula12.exercicio1.banco.movimentacoes.Deposito;
import br.com.meta.aula12.exercicio1.banco.movimentacoes.Movimentacao;
import br.com.meta.aula12.exercicio1.banco.usuarios.UsuarioBanco;
import br.com.meta.aula12.exercicio1.banco.valores.Cedula;
import br.com.meta.aula12.exercicio1.util.EntradaSaida;

public class UI {
	private static final int TELA_FUNCIONARIO = 1;
	private static final int TELA_CLIENTE = 2;
	private static final int SAIR = 3;
	private static final int ACESSAR_FUNCIONARIO = 1;
	private static final int SAIR_TELA_FUNCIONARIO = 2;
	private static final int CONSULTAR_EXTRATO_FUNCIONARIO = 1;
	private static final int ESVAZIAR_CEDULAS = 2;
	private static final int REPOR_CEDULAS = 3;
	private static final int CONSULTAR_SALDO_FUNCIONARIO = 4;
	private static final int APROVAR_DEPOSITOS = 5;
	private static final int SAIR_FUNCIONARIO = 6;
	private static final int ACESSAR_CLIENTE = 1;
	private static final int SAIR_TELA_CLIENTE = 2;
	private static final int CONSULTAR_SALDO_CLIENTE = 1;
	private static final int CONSULTAR_EXTRATO_CLIENTE = 2;
	private static final int DEPOSITAR = 3;
	private static final int SACAR = 4;
	private static final int SAIR_CLIENTE = 5;
	private static final String SAIR_CADASTRO = "S";
	private static final String SIM = "S";
	private static final String NAO = "N";
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
	
	// Início da configuração do Caixa Eletrônico
	
	private static void configurarCaixaEletronico() {
		String agenciaAdministracao = "";
		String contaAdministracao = "";
		String senhaAdministracao = "";
		
		EntradaSaida.escreverMensagem("Configurando Caixa Eletrônico...");
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Cadastrando agência, conta e senha de administração...");
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Informe a agência de administração do caixa eletrônico: ", false);
		agenciaAdministracao = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a conta de administração do caixa eletrônico: ", false);
		contaAdministracao = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha de administração do caixa eletrônico: ", false);
		senhaAdministracao = EntradaSaida.lerString();
		
		UI.caixaEletronico = new CaixaEletronico(
			agenciaAdministracao,
			contaAdministracao,
			senhaAdministracao
		);
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Agência, conta e senha de administração cadastradas!");
		EntradaSaida.quebrarLinha();
		
		UI.inicializarCedulas();
		UI.inicializarUsuariosBanco();
		UI.inicializarClientesBanco();
		
		EntradaSaida.escreverMensagem("Caixa Eletrônico configurado com sucesso!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void inicializarCedulas() {
		double valorCedula = 0.0;
		int quantidadeCedula = 0;
		Cedula cedula = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando cédulas...");
		EntradaSaida.quebrarLinha();
		
		do {
			EntradaSaida.escreverMensagem("Informe o valor da cédula (em R$): ", false);
			valorCedula = EntradaSaida.lerDouble(true);
			
			EntradaSaida.escreverMensagem("Informe a quantidade de cédulas: ", false);
			quantidadeCedula = EntradaSaida.lerInt(true);
			
			cedula = new Cedula(valorCedula);
			cedula.setQuantidade(quantidadeCedula);
			
			EntradaSaida.quebrarLinha();
			if (UI.caixaEletronico.cadastrarCedula(cedula)) {
				EntradaSaida.escreverMensagem("Cédula cadastrada com sucesso!");
			} else {
				EntradaSaida.escreverMensagem("Não foi possível cadastrar a cédula! É provável que esta cédula já tenha sido cadastrada.");
			}
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de cédulas, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de cédulas, pressione qualquer tecla.");
			
			EntradaSaida.quebrarLinha();
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Cédulas cadastradas com sucesso!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void inicializarUsuariosBanco() {
		String login = "";
		String nome = "";
		String senha = "";
		UsuarioBanco usuario = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando usuários...");
		EntradaSaida.quebrarLinha();
		
		do {
			EntradaSaida.escreverMensagem("Informe o login do usuário: ", false);
			login = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o nome do usuário: ", false);
			nome = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe a senha do usuário: ", false);
			senha = EntradaSaida.lerString();
			
			EntradaSaida.quebrarLinha();
			usuario = new UsuarioBanco(login, senha, nome);
			if (UI.caixaEletronico.cadastrarUsuario(usuario)) {
				EntradaSaida.escreverMensagem("Usuário cadastrado com sucesso!");
			} else {
				EntradaSaida.escreverMensagem("Não foi possível cadastrar o usuário! É provável que este usuário já tenha sido cadastrado.");
			}
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de usuários, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de usuários, pressione qualquer tecla.");
			
			EntradaSaida.quebrarLinha();
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Usuários cadastrados com sucesso!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void inicializarClientesBanco() {
		String agencia = "";
		String conta = "";
		String senha = "";
		String nome = "";
		Cliente cliente = null;
		String resposta = "";
		
		EntradaSaida.escreverMensagem("Cadastrando clientes...");
		EntradaSaida.quebrarLinha();
		
		do {
			EntradaSaida.escreverMensagem("Informe a agência do cliente: ", false);
			agencia = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe a conta do cliente: ", false);
			conta = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe o nome do cliente: ", false);
			nome = EntradaSaida.lerString();
			
			EntradaSaida.escreverMensagem("Informe a senha do cliente: ", false);
			senha = EntradaSaida.lerString(); 
			
			EntradaSaida.quebrarLinha();
			cliente = new Cliente(agencia, conta, senha, nome);
			if (UI.caixaEletronico.cadastrarCliente(cliente)) {
				EntradaSaida.escreverMensagem("Cliente cadastrado com sucesso!");
			} else {
				EntradaSaida.escreverMensagem("Não foi possível cadastrar o cliente! É provável que este cliente já tenha sido cadastrado.");
			}
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("-> Para interromper o cadastramento de clientes, tecle " + UI.SAIR_CADASTRO + ";");
			EntradaSaida.escreverMensagem("-> Para continuar com o cadastramento de clientes, pressione qualquer tecla.");
			
			EntradaSaida.quebrarLinha();
			
			resposta = EntradaSaida.lerString();
			resposta = resposta.toUpperCase();
			if (resposta.equals(UI.SAIR_CADASTRO)) {
				break;
			}
		} while (true);
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Clientes cadastrados com sucesso!");
		EntradaSaida.quebrarLinha();
	}
	
	// Fim da configuração do Caixa Eletrônico
	
	// Início da tela principal
	
	private static void exibirMensagemDeBoasVindas() {
		EntradaSaida.escreverMensagem("Bem-vindo(a) ao Caixa Eletrônico!");
	}
	
	private static void exibirMensagemDeAdeus() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Obrigado e volte sempre!");
		EntradaSaida.quebrarLinha();
	}
	
	private static void executarCaixaEletronico() {
		while (UI.acaoUsuario != UI.SAIR) {
			UI.exibirMenu();
			UI.executarAcaoUsuario();
		}
		
		UI.acaoUsuario = 0;
	}
	
	private static void exibirMenu() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Para exibir a tela de funcionários, tecle " + UI.TELA_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para acessar a tela de clientes, tecle " + UI.TELA_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR + ".");
		EntradaSaida.quebrarLinha();
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
	
	// Fim da tela principal
	
	// Início da tela de funcionário
	
	private static void exibirTelaFuncionario() {
		while (UI.acaoFuncionario != UI.SAIR_TELA_FUNCIONARIO) {
			UI.exibirMenuFuncionario();
			UI.executarAcaoFuncionario();
		}
		
		UI.acaoFuncionario = 0;
	}
	
	private static void exibirMenuFuncionario() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Para fazer login como funcionário, tecle " + UI.ACESSAR_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_TELA_FUNCIONARIO + ".");
		EntradaSaida.quebrarLinha();
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
		String agenciaAdministracao = "";
		String contaAdministracao = "";
		String senhaAdministracao = "";
		String loginUsuario = "";
		String senhaUsuario = "";
		
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Informe a agência de administração do caixa eletrônico: ", false);
		agenciaAdministracao = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a conta de administração do caixa eletrônico: ", false);
		contaAdministracao = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha de administração do caixa eletrônico: ", false);
		senhaAdministracao = EntradaSaida.lerString();
		
		if (!UI.caixaEletronico.acessarCaixaEletronico(agenciaAdministracao, contaAdministracao, senhaAdministracao)) {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Não foi possível acessar o painel de administração do caixa eletrônico. Agência e/ou conta e/ou senha inválidas.");
			return;
		}
		
		EntradaSaida.escreverMensagem("Informe o login: ", false);
		loginUsuario = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha: ", false);
		senhaUsuario = EntradaSaida.lerString();
		
		EntradaSaida.quebrarLinha();
		if (UI.caixaEletronico.logarComoUsuario(loginUsuario, senhaUsuario)) {
			UI.exibirMensagemDeBoasVindasFuncionarioLogado();
			UI.executarCaixaEletronicoFuncionarioLogado();
			UI.exibirMensagemDeAdeusFuncionarioLogado();
		} else {
			EntradaSaida.escreverMensagem("Não foi possível realizar o login. Login e/ou senha inválidos.");
		}
	}
	
	// Fim da tela de funcionário
	
	// Início da tela de funcionário logado
	
	private static void exibirMensagemDeBoasVindasFuncionarioLogado() {
		UsuarioBanco usuario = UI.caixaEletronico.getUsuarioLogado();
		EntradaSaida.escreverMensagem("Bem-vindo(a), " + usuario.getNome() + "!");
	}
	
	private static void exibirMensagemDeAdeusFuncionarioLogado() {
		UsuarioBanco usuario = UI.caixaEletronico.getUsuarioLogado();
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Adeus, " + usuario.getNome() + "!");
		UI.caixaEletronico.deslogarUsuario();
	}
	
	private static void executarCaixaEletronicoFuncionarioLogado() {
		while (UI.acaoFuncionarioLogado != UI.SAIR_FUNCIONARIO) {
			UI.exibirMenuFuncionarioLogado();
			UI.executarAcaoFuncionarioLogado();
		}
		
		UI.acaoFuncionarioLogado = 0;
	}
	
	private static void exibirMenuFuncionarioLogado() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Para realizar consulta de movimentações, tecle " + UI.CONSULTAR_EXTRATO_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para realizar descarga de cédulas, tecle " + UI.ESVAZIAR_CEDULAS + ";");
		EntradaSaida.escreverMensagem("-> Para realizar reposição de cédulas, tecle " + UI.REPOR_CEDULAS + ";");
		EntradaSaida.escreverMensagem("-> Para realizar consultar o valor disponível no caixa, tecle " + UI.CONSULTAR_SALDO_FUNCIONARIO + ";");
		EntradaSaida.escreverMensagem("-> Para realizar aprovação de depósitos, tecle " + UI.APROVAR_DEPOSITOS + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_FUNCIONARIO + ".");
		EntradaSaida.quebrarLinha();
	}
	
	private static void executarAcaoFuncionarioLogado() {
		UI.acaoFuncionarioLogado = EntradaSaida.lerInt(true);
		
		switch (UI.acaoFuncionarioLogado) {
			case UI.CONSULTAR_EXTRATO_FUNCIONARIO:
				UI.consultarExtratoClientes();
				break;
			case UI.ESVAZIAR_CEDULAS:
				UI.esvaziarCedulas();
				break;
			case UI.REPOR_CEDULAS:
				UI.reporCedulas();
				break;
			case UI.CONSULTAR_SALDO_FUNCIONARIO:
				UI.consultarSaldoCaixaEletronico();
				break;
			case UI.APROVAR_DEPOSITOS:
				UI.aprovarDepositos();
				break;
			case UI.SAIR_FUNCIONARIO:
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
		}
	}
	
	private static void consultarExtratoClientes() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Deseja salvar o extrato em um arquivo texto? (S/N)");
		EntradaSaida.quebrarLinha();
		
		String resposta = EntradaSaida.lerString();
		resposta = resposta.toUpperCase();
		
		if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
			UI.consultarExtratoClientes();
		} else {
			String movimentacoes = UI.caixaEletronico.retornarExtratoClientes();
			
			EntradaSaida.quebrarLinha();
			if (!movimentacoes.isEmpty()) {
				EntradaSaida.escreverMensagem("As movimentações realizadas são:");
			} else {
				movimentacoes = "Não houve movimentações neste caixa eletrônico.";
			}
			EntradaSaida.escreverMensagem(movimentacoes);
			
			if (resposta.equals(UI.SIM)) {
				EntradaSaida.quebrarLinha();
				String agencia = UI.caixaEletronico.getAgenciaAdministracao();
				String conta = UI.caixaEletronico.getContaAdministracao();
				String nomeArquivo = "Extrato_" + agencia + "_" + conta + "_" + System.currentTimeMillis() + ".txt";
				if (EntradaSaida.escreverArquivoTexto(nomeArquivo, movimentacoes)) {
					EntradaSaida.escreverMensagem("As movimentações realizadas foram salvas em " + nomeArquivo);
				} else {
					EntradaSaida.escreverMensagem("Não foi possível salvar as movimentações realizadas em arquivo texto.");
				}
			}
		}
	}
	
	private static void esvaziarCedulas() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Deseja fazer a descarga total de cédulas? (S/N)");
		EntradaSaida.quebrarLinha();
		
		String resposta = EntradaSaida.lerString();
		resposta = resposta.toUpperCase();
		if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
			UI.esvaziarCedulas();
		} else {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Descarregando cédulas...");
			EntradaSaida.quebrarLinha();
			
			if (resposta.equals(UI.NAO)) {
				EntradaSaida.escreverMensagem("Informe a cédula que deve ser esvaziada (em R$): ", false);
				double valor = EntradaSaida.lerDouble(true);
				
				EntradaSaida.quebrarLinha();
				Cedula cedula = UI.caixaEletronico.buscarCedula(valor);
				if (cedula != null) {
					if (UI.caixaEletronico.esvaziarCedulas(valor)) {
						EntradaSaida.escreverMensagem("Descarga parcial de cédulas efetuada com sucesso!");
					} else {
						EntradaSaida.escreverMensagem("Não foi possível realizar a descarga parcial de cédulas.");
					}
				} else {
					EntradaSaida.escreverMensagem("Não foi possível realizar a descarga parcial de cédulas.");
				}
			} else {
				if (UI.caixaEletronico.esvaziarCedulas()) {
					EntradaSaida.escreverMensagem("Descarga total de cédulas efetuada com sucesso!");
				} else {
					EntradaSaida.escreverMensagem("Não foi possível realizar a descarga total de cédulas.");
				}
			}
		}
	}
	
	private static void reporCedulas() {
		UI.exibirTotalCedulasDisponiveis();
		UI.efetuarReposicaoCedulas();
		UI.exibirTotalCedulasDisponiveis();
	}
	
	private static void consultarSaldoCaixaEletronico() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("O saldo disponível no caixa é: R$ " + String.format("%.2f", UI.caixaEletronico.calcularTotalDisponivel()));
	}
	
	private static void aprovarDepositos() {
		UI.exibirDepositosPendentes();
		UI.aprovarDepositosPendentes();
	}
	
	private static void exibirDepositosPendentes() {
		ArrayList<Movimentacao> depositosPendentes = UI.caixaEletronico.retornarDepositosPendentes();
		
		if (depositosPendentes == null) {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Não existem depósitos pendentes.");
			return;
		}
		
		String descricao = "";
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Os depósitos pendentes são:");
		for (Movimentacao movimentacao : depositosPendentes) {
			descricao = "-> " + ((Deposito) movimentacao).toString() + "\n";
			EntradaSaida.escreverMensagem(descricao, false);
		}
	}
	
	private static void aprovarDepositosPendentes() {
		ArrayList<Movimentacao> depositosPendentes = UI.caixaEletronico.retornarDepositosPendentes();
		
		if (depositosPendentes == null) {
			return;
		}
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Aprovando depósitos pendentes...");
		EntradaSaida.quebrarLinha();
		
		for (Movimentacao movimentacao : depositosPendentes) {
			do {
				Deposito deposito = (Deposito) movimentacao;
				
				EntradaSaida.escreverMensagem(deposito.toString());
				EntradaSaida.escreverMensagem("-> Deseja aprovar esta movimentação? (S/N)");
				EntradaSaida.quebrarLinha();
				
				String resposta = EntradaSaida.lerString();
				resposta = resposta.toUpperCase();
				if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
					EntradaSaida.quebrarLinha();
					EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
					EntradaSaida.quebrarLinha();
					continue;
				}
				
				UI.caixaEletronico.aprovarDeposito(deposito, resposta.equals(UI.SIM));
				
				break;
			} while (true);
		}
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Depósitos pendentes aprovados!");
	}
	
	private static void exibirTotalCedulasDisponiveis() {
		double totalDisponivel = 0.0;
		String cedulasDisponiveis = "";
		
		totalDisponivel = UI.caixaEletronico.calcularTotalDisponivel();
		cedulasDisponiveis = UI.caixaEletronico.retornarCedulasDisponiveis(true, true);

		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("O total disponível em caixa é: R$ " + String.format("%.2f", totalDisponivel));
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("As cédulas disponíveis são:");
		EntradaSaida.escreverMensagem(cedulasDisponiveis);
		EntradaSaida.quebrarLinha();
	}
	
	private static void efetuarReposicaoCedulas() {
		ArrayList<Cedula> cedulasRepostas = new ArrayList<Cedula>();
		Cedula cedulaReposta = null;
		double valor = 0.0;
		int quantidade = 0;
		
		for (Cedula cedula : UI.caixaEletronico.getCedulasDisponiveis()) {
			valor = cedula.getValor();
			EntradaSaida.escreverMensagem("Informe a quantidade de notas de R$ " + String.format("%.2f", valor) + ": ", false);
			quantidade = EntradaSaida.lerInt(true);
			
			cedulaReposta = new Cedula(valor, quantidade);
			cedulasRepostas.add(cedulaReposta);
		}
		
		EntradaSaida.quebrarLinha();
		if (UI.caixaEletronico.efetuarReposicao(cedulasRepostas)) {
			EntradaSaida.escreverMensagem("Reposição de cédulas efetuada com sucesso!");
		} else {
			EntradaSaida.escreverMensagem("Não foi possível efetuar a reposição de cédulas.");
		}
	}
	
	// Fim da tela de funcionário logado
	
	// Início da tela de cliente
	
	private static void exibirTelaCliente() {
		while (UI.acaoCliente != UI.SAIR_TELA_CLIENTE) {
			UI.exibirMenuCliente();
			UI.executarAcaoCliente();
		}
		
		UI.acaoCliente = 0;
	}
	
	private static void exibirMenuCliente() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Para fazer login como cliente, tecle " + UI.ACESSAR_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_TELA_CLIENTE + ".");
		EntradaSaida.quebrarLinha();
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
		
		EntradaSaida.quebrarLinha();
		
		EntradaSaida.escreverMensagem("Informe a agência: ", false);
		agencia = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a conta: ", false);
		conta = EntradaSaida.lerString();
		
		EntradaSaida.escreverMensagem("Informe a senha: ", false);
		senha = EntradaSaida.lerString();
		
		EntradaSaida.quebrarLinha();
		if (UI.caixaEletronico.logarComoCliente(agencia, conta, senha)) {
			UI.exibirMensagemDeBoasVindasClienteLogado();
			UI.executarCaixaEletronicoClienteLogado();
			UI.exibirMensagemDeAdeusClienteLogado();
		} else {
			EntradaSaida.escreverMensagem("Não foi possível realizar o login. Agência e/ou conta e/ou senha inválidos.");
		}
	}
	
	// Fim da tela de cliente
	
	// Início da tela de cliente logado
	
	private static void exibirMensagemDeBoasVindasClienteLogado() {
		Cliente cliente = UI.caixaEletronico.getClienteLogado();
		EntradaSaida.escreverMensagem("Bem-vindo(a), " + cliente.getNome() + "!");
	}
	
	private static void exibirMensagemDeAdeusClienteLogado() {
		Cliente cliente = UI.caixaEletronico.getClienteLogado();
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Adeus, " + cliente.getNome() + "!");
		UI.caixaEletronico.deslogarCliente();
	}

	private static void executarCaixaEletronicoClienteLogado() {
		while (UI.acaoClienteLogado != UI.SAIR_CLIENTE) {
			UI.exibirMenuClienteLogado();
			UI.executarAcaoClienteLogado();
		}
		
		UI.acaoClienteLogado = 0;
	}
	
	private static void exibirMenuClienteLogado() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Para realizar consulta de saldo, tecle " + UI.CONSULTAR_SALDO_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para realizar consulta de extrato, tecle " + UI.CONSULTAR_EXTRATO_CLIENTE + ";");
		EntradaSaida.escreverMensagem("-> Para realizar depósito, tecle " + UI.DEPOSITAR + ";");
		EntradaSaida.escreverMensagem("-> Para realizar saque, tecle " + UI.SACAR + ";");
		EntradaSaida.escreverMensagem("-> Para sair, tecle " + UI.SAIR_CLIENTE + ".");
		EntradaSaida.quebrarLinha();
	}

	private static void executarAcaoClienteLogado() {
		UI.acaoClienteLogado = EntradaSaida.lerInt(true);
		
		switch (UI.acaoClienteLogado) {
			case UI.CONSULTAR_SALDO_CLIENTE:
				UI.consultarSaldoCliente();
				break;
			case UI.CONSULTAR_EXTRATO_CLIENTE:
				UI.consultarExtratoCliente();
				break;
			case UI.DEPOSITAR:
				UI.depositar();
				break;
			case UI.SACAR:
				UI.sacar();
				break;
			case UI.SAIR_CLIENTE:
				break;
			default:
				EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
		}
	}
	
	private static void consultarSaldoCliente() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("O saldo disponível é: " + UI.caixaEletronico.retornaSaldoCliente());
	}
	
	private static void consultarExtratoCliente() {
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("-> Deseja salvar o extrato em um arquivo texto? (S/N)");
		EntradaSaida.quebrarLinha();
		
		String resposta = EntradaSaida.lerString();
		resposta = resposta.toUpperCase();

		if (!resposta.equals(UI.SIM) && !resposta.equals(UI.NAO)) {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("O valor informado não corresponde a uma ação válida.");
			UI.consultarExtratoCliente();
		} else {
			String movimentacoes = UI.caixaEletronico.retornaExtratoCliente();
			
			EntradaSaida.quebrarLinha();
			if (!movimentacoes.isEmpty()) {
				EntradaSaida.escreverMensagem("As movimentações realizadas são:");
			} else {
				movimentacoes = "Não houve movimentações nesta conta.";
			}
			EntradaSaida.escreverMensagem(movimentacoes);
			
			if (resposta.equals(UI.SIM)) {
				EntradaSaida.quebrarLinha();
				Cliente cliente = UI.caixaEletronico.getClienteLogado();
				String nomeArquivo = "Extrato_" + cliente.getAgencia() + "_" + cliente.getConta() + "_" + System.currentTimeMillis() + ".txt";
				if (EntradaSaida.escreverArquivoTexto(nomeArquivo, movimentacoes)) {
					EntradaSaida.escreverMensagem("As movimentações realizadas foram salvas em " + nomeArquivo);
				} else {
					EntradaSaida.escreverMensagem("Não foi possível salvar as movimentações realizadas em arquivo texto.");
				}
			}
		}
	}
	
	private static void depositar() {
		double valor = 0.0;
		
		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Informe o valor do depósito (em R$): ", false);
		valor = EntradaSaida.lerDouble(true);
		
		EntradaSaida.quebrarLinha();
		if (UI.caixaEletronico.efetuarDeposito(valor)) {
			EntradaSaida.escreverMensagem("Depósito efetuado com sucesso!");
			EntradaSaida.escreverMensagem("O valor será disponibilizado em sua conta após a aprovação do banco.");
		} else {
			EntradaSaida.escreverMensagem("Não foi possível efetuar o depósito.");
		}
	}
	
	private static void sacar() {
		UI.exibirCedulasDisponiveis();
		UI.efetuarSaque();
	}
	
	private static void exibirCedulasDisponiveis() {
		String cedulasDisponiveis = "";
		
		cedulasDisponiveis = UI.caixaEletronico.retornarCedulasDisponiveis(false, false);
		
		EntradaSaida.quebrarLinha();
		if (!cedulasDisponiveis.isEmpty()) {
			EntradaSaida.escreverMensagem("As cédulas disponíveis são:");
			EntradaSaida.escreverMensagem(cedulasDisponiveis);
			return;
		}
		EntradaSaida.escreverMensagem("Não há cédulas disponíveis.");
		EntradaSaida.quebrarLinha();
	}
	
	private static void efetuarSaque() {
		double valor = 0.0;
		String cedulasSacadas = "";

		EntradaSaida.quebrarLinha();
		EntradaSaida.escreverMensagem("Informe o valor do saque: ", false);
		valor = EntradaSaida.lerDouble(true);
		
		if (UI.caixaEletronico.efetuarSaque(valor)) {
			cedulasSacadas = UI.caixaEletronico.retornarCedulasSacadas();
			
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Saque efetuado com sucesso!");
			EntradaSaida.quebrarLinha();
			
			EntradaSaida.escreverMensagem("As cédulas sacadas foram:");
			EntradaSaida.escreverMensagem(cedulasSacadas);
		} else {
			EntradaSaida.quebrarLinha();
			EntradaSaida.escreverMensagem("Não foi possível efetuar o saque.");
		}
	}

	// Fim da tela de cliente logado
}