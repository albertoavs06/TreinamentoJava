package br.com.meta.aula2.exercicio1.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaSaida {
	private static Scanner input = new Scanner(System.in);
	
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
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número inteiro e positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número inteiro.");
				}
				
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
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número.");
				}
				
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
				if (positivo) {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número positivo.");
				} else {
					EntradaSaida.escreverMensagem("O valor informado deve ser um número.");
				}
				
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
}
