package br.com.meta.aula12.exercicio1.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaSaida {
	private static Scanner input = new Scanner(System.in);
	private static File file;
	private static FileWriter fileWriter;
	private static PrintWriter printWriter;
	private static String DIRETORIO_PADRAO = "files";
	
	public static int lerInt(boolean positivo) {
		int valor = 0;
		
		do {
			try {
				valor = EntradaSaida.input.nextInt();
				EntradaSaida.input.nextLine();
				
				if (positivo && valor < 0) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException ex) {
				EntradaSaida.input.nextLine();
				
				EntradaSaida.quebrarLinha();
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número inteiro e positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número inteiro.");
				}
				EntradaSaida.quebrarLinha();
				
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static float lerFloat(boolean positivo) {
		float valor = 0.0f;
		
		do {
			try {
				valor = EntradaSaida.input.nextFloat();
				EntradaSaida.input.nextLine();
				
				if (positivo && valor < 0) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException ex) {
				EntradaSaida.input.nextLine();
				
				EntradaSaida.quebrarLinha();
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número.");
				}
				EntradaSaida.quebrarLinha();
				
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static double lerDouble(boolean positivo) {
		double valor = 0.0;
		
		do {
			try {
				valor = EntradaSaida.input.nextDouble();
				EntradaSaida.input.nextLine();
				
				if (positivo && valor < 0) {
					throw new InputMismatchException();
				}
			} catch (InputMismatchException ex) {
				EntradaSaida.input.nextLine();
				
				EntradaSaida.quebrarLinha();
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número.");
				}
				EntradaSaida.quebrarLinha();
				
				continue;
			}
			
			break;
		} while (true);
		
		return valor;
	}
	
	public static String lerString() {
		String valor = "";
		valor = input.nextLine();
		return valor;
	}
	
	public static void quebrarLinha() {
		System.out.println();
	}
	
	public static void escreverMensagem(String mensagem) {
		EntradaSaida.escreverMensagem(mensagem, true);
	}
	
	public static void escreverMensagem(String mensagem, boolean quebraLinha) {
		System.out.print(mensagem);
		if (quebraLinha) {
			EntradaSaida.quebrarLinha();
		}
	}
	
	public static boolean escreverArquivoTexto(String nomeArquivo, String texto) {
		if (nomeArquivo == null || texto == null) {
			return false;
		}
		
		try {
			nomeArquivo = (new File(".")).getCanonicalPath() +
					File.separator +
					EntradaSaida.DIRETORIO_PADRAO +
					File.separator +
					nomeArquivo;
			
			EntradaSaida.file = new File(nomeArquivo);
			if (EntradaSaida.file.exists()) {
				return false;
			}
			
			EntradaSaida.fileWriter = new FileWriter(EntradaSaida.file);
			
			EntradaSaida.printWriter = new PrintWriter(EntradaSaida.fileWriter);
			EntradaSaida.printWriter.println(texto);
			EntradaSaida.printWriter.close();
			
			EntradaSaida.fileWriter.close();
		} catch (SecurityException ex) {
			return false;
		} catch (IOException ex) {
			return false;
		}
		
		return true;
	}
}