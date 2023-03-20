package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.CidadeDAO;
import br.com.luis.drogaria.dao.EstadoDAO;
import br.com.luis.drogaria.dao.PessoaDAO;
import br.com.luis.drogaria.domain.Cidade;
import br.com.luis.drogaria.domain.Estado;
import br.com.luis.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private Cidade cidade;
	private Estado estado;

	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Pessoa> pessoas;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas!");
			erro.printStackTrace();
		}

	}

	public void novo() {
		try {
			pessoa = new Pessoa();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
			
			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar uma nova pessoa!");
		}

	}

	public void editar(ActionEvent evento) {

	}

	public void salvar() {

	}

	public void excluir(ActionEvent evento) {

	}

}
