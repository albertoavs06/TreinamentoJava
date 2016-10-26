package aula1.exercicio1;

import java.util.ArrayList;
import java.util.List;

public class EtapaSelecao {
	private List<Candidato> candidatos;
	private double notaMinimaEtapaAnterior;
	private double experienciaMinima;
	private int idadeMaxima;
	private int minimoHorasQualificacao;
	private int etapa;
	
	public EtapaSelecao() {
		this.candidatos = new ArrayList<Candidato>();
		this.notaMinimaEtapaAnterior = 0.0;
		this.experienciaMinima = 0.0;
		this.idadeMaxima = 0;
		this.minimoHorasQualificacao = 0;
		this.etapa = 0;
	}
	
	public List<Candidato> getCandidatos() {
		return this.candidatos;
	}
	
	public void setCandidatos(ArrayList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}
	
	public double getNotaMinimaEtapaAnterior() {
		return this.notaMinimaEtapaAnterior;
	}
	
	public void setNotaMinimaEtapaAnterior(double notaMinimaEtapaAnterior) {
		this.notaMinimaEtapaAnterior = notaMinimaEtapaAnterior;
	}
	
	public double getExperienciaMinima() {
		return this.experienciaMinima;
	}
	
	public void setExperienciaMinima(double experienciaMinima) {
		this.experienciaMinima = experienciaMinima;
	}
	
	public int getIdadeMaxima() {
		return this.idadeMaxima;
	}
	
	public void setIdadeMaxima(int idadeMaxima) {
		this.idadeMaxima = idadeMaxima;
	}
	
	public int getMinimoHorasQualificacao() {
		return this.minimoHorasQualificacao;
	}
	
	public void setMinimoHorasQualificacao(int minimoHorasQualificacao) {
		this.minimoHorasQualificacao = minimoHorasQualificacao;
	}
	
	public int getEtapa() {
		return this.etapa;
	}
	
	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}
	
	public boolean candidatoCadastrado(Candidato candidato) {
		return this.candidatos.contains(candidato);
	}
	
	public boolean cadastrarCandidato(Candidato candidato) {
		if (this.candidatoCadastrado(candidato)) {
			return false;
		}
		
		return this.candidatos.add(candidato);
	}
	
	public boolean removerCandidato(Candidato candidato) {
		if (!this.candidatoCadastrado(candidato)) {
			return false;
		}
		
		return this.candidatos.remove(candidato);
	}
	
	public boolean avaliarCandidato(Candidato candidato) {
		if (candidato.getNota(this.etapa - 1) >= this.notaMinimaEtapaAnterior) {
			return true;
		}
		
		if (candidato.getExperiencia() >= this.experienciaMinima && candidato.calculaIdade() <= this.idadeMaxima) {
			return true;
		}
		
		if (candidato.getHorasQualificacao() >= this.minimoHorasQualificacao) {
			return true;
		}
		
		return false;
	}
	
	public void avaliarCandidatos(String mensagemAprovacao, String mensagemReprovacao) {
		for (Candidato candidato : this.candidatos) {
			candidato.imprimeDadosBasicos();
			if (this.avaliarCandidato(candidato)) {
				System.out.println("-> " + mensagemAprovacao);
			} else {
				System.out.println("-> " + mensagemReprovacao);
			}
		}
	}
}
