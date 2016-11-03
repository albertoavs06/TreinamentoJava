package br.com.meta.aula6.exercicio1.banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.meta.aula6.exercicio1.banco.pessoas.Cliente;
import br.com.meta.aula6.exercicio1.banco.pessoas.UsuarioBanco;
import br.com.meta.aula6.exercicio1.banco.valores.Cedula;

public class CaixaEletronico {
	private List<Cedula> cedulasDisponiveis;
	private String senhaAdministracao;
	private List<UsuarioBanco> usuariosCadastrados;
	private List<Cliente> clientesCadastrados;
	private UsuarioBanco usuarioLogado;
	private Cliente clienteLogado;
	private List<Cedula> cedulasSacadas;
	
 	public CaixaEletronico(String senhaAdministracao) {
		this(
			new ArrayList<Cedula>(),
			senhaAdministracao,
			new ArrayList<UsuarioBanco>(),
			new ArrayList<Cliente>(),
			null,
			null,
			null
		);
	}
	
	public CaixaEletronico(
			ArrayList<Cedula> cedulasDisponiveis,
			String senhaAdministracao,
			ArrayList<UsuarioBanco> usuariosCadastrados,
			ArrayList<Cliente> clientesCadastrados) {
		
		this(
			cedulasDisponiveis,
			senhaAdministracao,
			usuariosCadastrados,
			clientesCadastrados,
			null,
			null,
			null
		);
	}
	
	private CaixaEletronico(
			ArrayList<Cedula> cedulasDisponiveis,
			String senhaAdministracao,
			ArrayList<UsuarioBanco> usuariosCadastrados,
			ArrayList<Cliente> clientesCadastrados,
			UsuarioBanco usuarioLogado,
			Cliente clienteLogado,
			ArrayList<Cedula> cedulasSacadas) {
		
		this.cedulasDisponiveis = cedulasDisponiveis;
		this.senhaAdministracao = senhaAdministracao;
		this.usuariosCadastrados = usuariosCadastrados;
		this.clientesCadastrados = clientesCadastrados;
		this.usuarioLogado = usuarioLogado;
		this.clienteLogado = clienteLogado;
		this.cedulasSacadas = cedulasSacadas;
	}
	
	public List<Cedula> getCedulasDisponiveis() {
		return this.cedulasDisponiveis;
	}
	
	public void setCedulasDisponiveis(ArrayList<Cedula> cedulasDisponiveis) {
		this.cedulasDisponiveis = cedulasDisponiveis;
	}
	
	public String getSenhaAdministracao() {
		return this.senhaAdministracao;
	}
	
	public void setSenhaAdministracao(String senhaAdministracao) {
		this.senhaAdministracao = senhaAdministracao;
	}
	
	public List<UsuarioBanco> getUsuariosCadastrados() {
		return this.usuariosCadastrados;
	}
	
	public void setUsuariosCadastrados(ArrayList<UsuarioBanco> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
	}
	
	public List<Cliente> getClientesCadastrados() {
		return this.clientesCadastrados;
	}
	
	public void setClientesCadastrados(ArrayList<Cliente> clientesCadastrados) {
		this.clientesCadastrados = clientesCadastrados;
	}
	
	public UsuarioBanco getUsuarioLogado() {
		return this.usuarioLogado;
	}
	
	public void setUsuarioLogado(UsuarioBanco usuarioBanco) {
		this.usuarioLogado = usuarioBanco;
	}
	
	public Cliente getClienteLogado() {
		return this.clienteLogado;
	}
	
	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
	public boolean cedulaCadastrada(double valor) {
		return this.cedulaCadastrada(new Cedula(valor));
	}
	
	public boolean cedulaCadastrada(Cedula cedula) {
		for (Cedula c : this.cedulasDisponiveis) {
			if (c.compararCedula(cedula)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cadastrarCedula(Cedula cedula) {
		if (this.cedulaCadastrada(cedula)) {
			return false;
		}
		
		return this.cedulasDisponiveis.add(cedula);
	}
	
	public boolean removerCedula(Cedula cedula) {
		if (!this.cedulaCadastrada(cedula)) {
			return false;
		}
		
		return this.cedulasDisponiveis.remove(cedula);
	}

	public Cedula buscarCedula(Cedula cedula) {
		for (Cedula c : this.cedulasDisponiveis) {
			if (c.compararCedula(cedula)) {
				return c;
			}
		}
		
		return null;
	}
	
	public void ordenarCedulasDisponiveis(boolean crescente) {
		Collections.sort(this.cedulasDisponiveis, new Comparator<Cedula>() {
			@Override
			public int compare(Cedula c1, Cedula c2) {
				if (crescente) {
					return ((int) (c1.getValor() - c2.getValor()));
				}
				return (-1) * ((int) (c1.getValor() - c2.getValor()));
			}
		});
	}
	
	public Cedula buscarCedula(double valor) {
		return this.buscarCedula(new Cedula(valor));
	}
	
	public boolean usuarioCadastrado(String login, String nome) {
		return this.usuarioCadastrado(new UsuarioBanco(login, nome));
	}
	
	public boolean usuarioCadastrado(UsuarioBanco usuarioBanco) {
		for (UsuarioBanco usuario : this.usuariosCadastrados) {
			if (usuario.compararUsuarioBanco(usuarioBanco)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cadastrarUsuario(UsuarioBanco usuarioBanco) {
		if (this.usuarioCadastrado(usuarioBanco)) {
			return false;
		}
		
		return this.usuariosCadastrados.add(usuarioBanco);
	}
	
	public boolean removerUsuario(UsuarioBanco usuarioBanco) {
		if (!this.usuarioCadastrado(usuarioBanco)) {
			return false;
		}
		
		return this.usuariosCadastrados.remove(usuarioBanco);
	}
	
	public UsuarioBanco buscarUsuario(UsuarioBanco usuarioBanco) {
		for (UsuarioBanco u : this.usuariosCadastrados) {
			if (u.compararUsuarioBanco(usuarioBanco)) {
				return u;
			}
		}
		
		return null;
	}
	
	public UsuarioBanco buscarUsuario(String login) {
		return this.buscarUsuario(new UsuarioBanco(login));
	}
	
	public boolean clienteCadastrado(String agencia, String conta, String senha, String nome) {
		return this.clienteCadastrado(new Cliente(agencia, conta, senha, nome));
	}
	
	public boolean clienteCadastrado(Cliente cliente) {
		for (Cliente cl : this.clientesCadastrados) {
			if (cl.compararCliente(cliente)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		if (this.clienteCadastrado(cliente)) {
			return false;
		}
		
		return this.clientesCadastrados.add(cliente);
	}
	
	public boolean removerCliente(Cliente cliente) {
		if (!this.clienteCadastrado(cliente)) {
			return false;
		}
		
		return this.clientesCadastrados.remove(cliente);
	}
	
	public Cliente buscarCliente(Cliente cliente) {
		for (Cliente c : this.clientesCadastrados) {
			if (c.compararCliente(cliente)) {
				return c;
			}
		}
		
		return null;
	}
	
	public Cliente buscarCliente(String agencia, String conta, String senha) {
		return this.buscarCliente(new Cliente(agencia, conta, senha));
	}
	
	public boolean logarComoUsuario(String login, String senhaAdministracao) {
		if (!this.senhaAdministracao.equals(senhaAdministracao)) {
			return false;
		}
		
		UsuarioBanco usuario = this.buscarUsuario(login);
		if (usuario == null) {
			return false;
		}
		
		this.usuarioLogado = usuario;
		
		return true;
	}
	
	public void deslogarUsuario() {
		this.usuarioLogado = null;
	}
	
	public boolean logarComoCliente(String agencia, String conta, String senha) {
		Cliente cliente = this.buscarCliente(agencia, conta, senha);
		if (cliente == null) {
			return false;
		}
		
		this.clienteLogado = cliente;
		
		return true;
	}
	
	public void deslogarCliente() {
		this.clienteLogado = null;
	}
	
	public double calcularTotalDisponivel() {
		double total = 0.0;
		
		for (Cedula cedula : this.cedulasDisponiveis) {
			total += ((double) cedula.getQuantidade()) * cedula.getValor();
		}
		
		return total;
	}
	
	public String retornarCedulasDisponiveis(boolean mostrarZeradas, boolean mostrarQuantidades) {
		String retorno = "";
		
		for (Cedula cedula : this.cedulasDisponiveis) {
			if (!mostrarZeradas && cedula.getQuantidade() == 0) {
				continue;
			}
			
			retorno += "-> " + String.format("%.2f", cedula.getValor());
			
			if (mostrarQuantidades) {
				retorno += " - " + cedula.getQuantidade() + " cédula(s) disponível(is)";
			}
			
			retorno += "\n";
		}
		
		return retorno;
	}
	
	private boolean validarCedulasRepostas(ArrayList<Cedula> cedulasRepostas) {
		if (cedulasRepostas == null) {
			return false;
		}
		
		if (cedulasRepostas.size() != this.cedulasDisponiveis.size()) {
			return false;
		}
		
		int indice = 0;
		Cedula cedula = null;
		
		for (Cedula c : cedulasRepostas) {
			cedula = this.cedulasDisponiveis.get(indice);
			
			if (!c.compararCedula(cedula)) {
				return false;
			}
			
			indice++;
		}
		
		return true;
	}
	
	public boolean efetuarReposicao(ArrayList<Cedula> cedulasRepostas) {
		if (this.usuarioLogado == null) {
			return false;
		}
		
		if (!this.validarCedulasRepostas(cedulasRepostas)) {
			return false;
		}
		
		int indice = 0;
		Cedula cedulaReposta = null;
		
		for (Cedula cedula : this.cedulasDisponiveis) {
			cedulaReposta = cedulasRepostas.get(indice);
			cedula.setQuantidade(cedula.getQuantidade() + cedulaReposta.getQuantidade());
			indice++;
		}
		
		return true;
	}
	
	private void inicializarCedulasSacadas() {
		Cedula cedulaSacada = null;
		
		this.ordenarCedulasDisponiveis(false);
		
		this.cedulasSacadas = new ArrayList<Cedula>();
		for (Cedula cedula : this.cedulasDisponiveis) {
			cedulaSacada = new Cedula(cedula.getValor());
			this.cedulasSacadas.add(cedulaSacada);
		}
	}
	
	public String retornarCedulasSacadas() {
		String retorno = "";
		
		for (Cedula cedula : this.cedulasSacadas) {
			if (cedula.getQuantidade() == 0) {
				continue;
			}
			retorno += "-> " + String.format("%.2f", cedula.getValor()) + " - " + cedula.getQuantidade() + " nota(s)\n";
		}
		this.inicializarCedulasSacadas();
		
		return retorno;
	}
	
	private int obterIndiceMaiorCedulaImpar() {
		int indice = 0;
		
		for (Cedula cedula : this.cedulasDisponiveis) {
			if ((cedula.getValor() % 2) != 0) {
				if (cedula.getQuantidade() == 0) {
					continue;
				}
				break;
			}
			
			indice++;
		}
		
		return indice;
	}
	
	private boolean descontaMaiorNotaImpar() {
		int indiceCedulaImpar = 0;
		
		indiceCedulaImpar = this.obterIndiceMaiorCedulaImpar();
		
		if (indiceCedulaImpar == 0) {
			return false;
		}
		
		if (this.cedulasDisponiveis.get(indiceCedulaImpar).getQuantidade() == 0) {
			return false;
		}
		
		this.cedulasSacadas.get(indiceCedulaImpar).setQuantidade(
			this.cedulasSacadas.get(indiceCedulaImpar).getQuantidade() + 1
		);
		
		return true;
	}
	
	private boolean descontaNotas(double valor) {
		int notas = 0;
		double valorASacar = 0.0;
		
		int indice = 0;
		for (Cedula cedula : this.cedulasDisponiveis) {
			if (valor == 0.0) {
				break;
			}
			
			notas = ((int) Math.floor(valor / cedula.getValor()));
			
			if (notas > cedula.getQuantidade()) {
				notas = cedula.getQuantidade();
			}
			
			valorASacar = ((double) notas) * cedula.getValor();
			
			if (((valor - valorASacar) % 2) != 0) {
				indice++;
				continue;
			}
			
			this.cedulasSacadas.get(indice).setQuantidade(
				this.cedulasSacadas.get(indice).getQuantidade() + notas
			);
			
			valor = valor - valorASacar;
			indice++;
		}
		
		if (valor == 0) {
			indice = 0;
			for (Cedula cedula : this.cedulasSacadas) {
				this.cedulasDisponiveis.get(indice).setQuantidade(
					this.cedulasDisponiveis.get(indice).getQuantidade() - cedula.getQuantidade()
				);
				
				indice++;
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean efetuarSaque(double valor) {
		if (this.clienteLogado == null) {
			return false;
		}
		
		this.inicializarCedulasSacadas();

		int indiceCedulaImpar = 0;
		
		// se o valor solicitado for ímpar, obrigatoriamente deve sacar ao menos uma nota de valor ímpar
		if ((valor % 2) != 0) {
			indiceCedulaImpar = this.obterIndiceMaiorCedulaImpar();
			
			if (indiceCedulaImpar == 0) {
				return false;
			}
			
			if (this.descontaMaiorNotaImpar()) {
				valor = valor - this.cedulasDisponiveis.get(indiceCedulaImpar).getValor();
			}
		}
		
		return this.descontaNotas(valor);
	}
}
