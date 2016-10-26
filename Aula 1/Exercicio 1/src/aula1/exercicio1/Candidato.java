package aula1.exercicio1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Candidato {
	private int codigo;
	private int anoNascimento;
	private List<Double> notas;
	private int horasQualificacao;
	private double experiencia;
	
	public Candidato() {
		this.codigo = 0;
		this.anoNascimento = 0;
		this.notas = new ArrayList<Double>();
		this.horasQualificacao = 0;
		this.experiencia = 0.0;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getAnoNascimento() {
		return this.anoNascimento;
	}
	
	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}
	
	public List<Double> getNotas() {
		return this.notas;
	}
	
	public void setNotas(ArrayList<Double> notas) {
		this.notas = notas;
	}
	
	public int getHorasQualificacao() {
		return this.horasQualificacao;
	}
	
	public void setHorasQualificacao(int horasQualificacao) {
		this.horasQualificacao = horasQualificacao;
	}
	
	public double getExperiencia() {
		return this.experiencia;
	}
	
	public void setExperiencia(double experiencia) {
		this.experiencia = experiencia;
	}
	
	public boolean cadastrarNota(double nota) {
		return this.notas.add(nota);
	}
	
	public boolean removerNota(int etapa) {
		Object nota = this.notas.remove(etapa);
		
		if (nota == null) {
			return false;
		}
		
		this.notas.set(etapa, 0.0);
		
		return true;
	}
	
	public double getNota(int etapa) {
		Object nota = this.notas.get(etapa - 1);
		return (nota == null ? 0.0 : (double) nota);
	}
	
	public int calculaIdade() {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		return anoAtual - this.anoNascimento;
	}
	
	public void imprimeDadosBasicos() {
		System.out.println("Candidato de código " + this.codigo + " e idade " + this.calculaIdade());
	}
}
