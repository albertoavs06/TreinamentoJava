package aula1.exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static Scanner input = null;
	private static EtapaSelecao etapaSelecao = null;
	private static String QUEBRA_LOOP = "END";
	
	public static void inicializarScanner() {
		if (input == null) {
			input = new Scanner(System.in);
		}
	}
	
	public static void limparBuffer() {
		if (input != null && input.hasNextLine()) {
			input.nextLine();
		}
	}
	
	public static int obterInt() {
		if (input == null) {
			System.out.println("Não é possível informar valores.");
			return 0;
		}
		
		int valor = 0;
		
		do {
			try {
				valor = input.nextInt();
				
				if (valor < 0) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException ex) {
				System.out.println("O valor informado deve ser um número inteiro e positivo.");
				Main.limparBuffer();
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static double obterDouble() {
		if (input == null) {
			System.out.println("Não é possível informar valores.");
			return 0.0;
		}
		
		double valor = 0.0;
		
		do {
			try {
				valor = input.nextDouble();
				
				if (valor < 0) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException ex) {
				System.out.println("O valor informado deve ser um número positivo.");
				Main.limparBuffer();
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static String obterString() {
		if (input == null) {
			System.out.println("Não é possível informar valores.");
			return "";
		}
		
		String valor = "";
		valor = input.nextLine();
		return valor;
	}
	
	public static void finalizarScanner() {
		if (input != null) {
			input.close();
		}
	}
	
	public static void inicializarEtapaSelecao() {
		if (etapaSelecao == null) {
			etapaSelecao = new EtapaSelecao();
		}
		
		System.out.println("Configurando etapa de seleção...\n");
		
		System.out.println("Informe a etapa de seleção:");
		int etapa = Main.obterInt();
		etapaSelecao.setEtapa(etapa);
		
		System.out.println("Informe a nota mínima na etapa anterior para a aprovação do candidato:");
		double notaMinima = Main.obterDouble();
		etapaSelecao.setNotaMinimaEtapaAnterior(notaMinima);
		
		System.out.println("Informe a experiência mínima para a aprovação do candidato:");
		double experienciaMinima = Main.obterDouble();
		etapaSelecao.setExperienciaMinima(experienciaMinima);
		
		System.out.println("Informe a idade máxima para a aprovação do candidato:");
		int idadeMaxima = Main.obterInt();
		etapaSelecao.setIdadeMaxima(idadeMaxima);
		
		System.out.println("Informe o mínimo de horas de qualificação para a aprovação do candidato:");
		int minimoHorasQualificacao = Main.obterInt();
		etapaSelecao.setMinimoHorasQualificacao(minimoHorasQualificacao);
		
		Main.cadastrarCandidatos();
		
		System.out.println("\nEtapa de seleção configurada.\n");
	}
	
	public static void cadastrarNotas(Candidato candidato) {
		if (candidato == null) {
			System.out.println("Não é possível cadastrar notas.");
			return;
		}
		
		int etapa = 1;
		while (etapa != etapaSelecao.getEtapa()) {
			System.out.println("Informe a nota do candidato na etapa " + etapa + ":");
			double nota = Main.obterDouble();
			candidato.cadastrarNota(nota);
			etapa++;
		}
	}
	
	public static void cadastrarCandidato() {
		if (etapaSelecao == null) {
			System.out.println("Não é possível cadastrar candidatos.");
			return;
		}
		
		System.out.println("Cadastrando usuário...\n");
		
		Candidato candidato = new Candidato();
		
		System.out.println("Informe o código do candidato:");
		candidato.setCodigo(Main.obterInt());
		
		System.out.println("Informe o ano de nascimento do candidato:");
		candidato.setAnoNascimento(Main.obterInt());
		
		Main.cadastrarNotas(candidato);
		
		System.out.println("Informe o número de horas em cursos de qualificação do candidato:");
		candidato.setHorasQualificacao(Main.obterInt());
		
		System.out.println("Informe o tempo de experiência do candidato:");
		candidato.setExperiencia(Main.obterDouble());		
		
		if (etapaSelecao.cadastrarCandidato(candidato)) {
			System.out.println("Usuário cadastrado.\n");
		} else {
			System.out.println("Não foi possível cadastrar o candidato.\n");
		}
	}
	
	public static void cadastrarCandidatos() {
		if (etapaSelecao == null) {
			System.out.println("Não é possível cadastrar candidatos.");
			return;
		}
		
		do {
			Main.cadastrarCandidato();
			Main.limparBuffer();
			
			System.out.println("Caso deseje encerrar o cadastramento de usuários, digite END.");
			System.out.println("Caso deseje cadastrar outro usuário, pressione qualquer tecla.");
			
			String resposta = Main.obterString();
			resposta = resposta.toUpperCase();
			
			if (resposta.equals(QUEBRA_LOOP)) {
				break;
			}
		} while (true);
	}
	
	public static void executarEtapaSelecao() {
		if (etapaSelecao == null) {
			System.out.println("Não é possível avaliar candidatos.");
			return;
		}
		
		String mensagemAprovacao = "Aprovado para a próxima etapa.";
		String mensagemReprovacao = "Reprovado";
		
		System.out.println("Avaliando candidatos...\n");
		etapaSelecao.avaliarCandidatos(mensagemAprovacao, mensagemReprovacao);
		System.out.println("\nCandidatos avaliados.");
	}

	public static void main(String[] args) {
		Main.inicializarScanner();
		Main.inicializarEtapaSelecao();
		Main.executarEtapaSelecao();
		Main.finalizarScanner();
	}
}
