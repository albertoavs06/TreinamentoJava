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
			System.out.println("N�o � poss�vel informar valores.");
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
				System.out.println("O valor informado deve ser um n�mero inteiro e positivo.");
				Main.limparBuffer();
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static double obterDouble() {
		if (input == null) {
			System.out.println("N�o � poss�vel informar valores.");
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
				System.out.println("O valor informado deve ser um n�mero positivo.");
				Main.limparBuffer();
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static String obterString() {
		if (input == null) {
			System.out.println("N�o � poss�vel informar valores.");
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
		
		System.out.println("Configurando etapa de sele��o...\n");
		
		System.out.println("Informe a etapa de sele��o:");
		int etapa = Main.obterInt();
		etapaSelecao.setEtapa(etapa);
		
		System.out.println("Informe a nota m�nima na etapa anterior para a aprova��o do candidato:");
		double notaMinima = Main.obterDouble();
		etapaSelecao.setNotaMinimaEtapaAnterior(notaMinima);
		
		System.out.println("Informe a experi�ncia m�nima para a aprova��o do candidato:");
		double experienciaMinima = Main.obterDouble();
		etapaSelecao.setExperienciaMinima(experienciaMinima);
		
		System.out.println("Informe a idade m�xima para a aprova��o do candidato:");
		int idadeMaxima = Main.obterInt();
		etapaSelecao.setIdadeMaxima(idadeMaxima);
		
		System.out.println("Informe o m�nimo de horas de qualifica��o para a aprova��o do candidato:");
		int minimoHorasQualificacao = Main.obterInt();
		etapaSelecao.setMinimoHorasQualificacao(minimoHorasQualificacao);
		
		Main.cadastrarCandidatos();
		
		System.out.println("\nEtapa de sele��o configurada.\n");
	}
	
	public static void cadastrarNotas(Candidato candidato) {
		if (candidato == null) {
			System.out.println("N�o � poss�vel cadastrar notas.");
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
			System.out.println("N�o � poss�vel cadastrar candidatos.");
			return;
		}
		
		System.out.println("Cadastrando usu�rio...\n");
		
		Candidato candidato = new Candidato();
		
		System.out.println("Informe o c�digo do candidato:");
		candidato.setCodigo(Main.obterInt());
		
		System.out.println("Informe o ano de nascimento do candidato:");
		candidato.setAnoNascimento(Main.obterInt());
		
		Main.cadastrarNotas(candidato);
		
		System.out.println("Informe o n�mero de horas em cursos de qualifica��o do candidato:");
		candidato.setHorasQualificacao(Main.obterInt());
		
		System.out.println("Informe o tempo de experi�ncia do candidato:");
		candidato.setExperiencia(Main.obterDouble());		
		
		if (etapaSelecao.cadastrarCandidato(candidato)) {
			System.out.println("Usu�rio cadastrado.\n");
		} else {
			System.out.println("N�o foi poss�vel cadastrar o candidato.\n");
		}
	}
	
	public static void cadastrarCandidatos() {
		if (etapaSelecao == null) {
			System.out.println("N�o � poss�vel cadastrar candidatos.");
			return;
		}
		
		do {
			Main.cadastrarCandidato();
			Main.limparBuffer();
			
			System.out.println("Caso deseje encerrar o cadastramento de usu�rios, digite END.");
			System.out.println("Caso deseje cadastrar outro usu�rio, pressione qualquer tecla.");
			
			String resposta = Main.obterString();
			resposta = resposta.toUpperCase();
			
			if (resposta.equals(QUEBRA_LOOP)) {
				break;
			}
		} while (true);
	}
	
	public static void executarEtapaSelecao() {
		if (etapaSelecao == null) {
			System.out.println("N�o � poss�vel avaliar candidatos.");
			return;
		}
		
		String mensagemAprovacao = "Aprovado para a pr�xima etapa.";
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
