package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.ClienteDAO;
import br.com.luis.drogaria.dao.EstadoDAO;
import br.com.luis.drogaria.dao.PessoaDAO;
import br.com.luis.drogaria.domain.Cidade;
import br.com.luis.drogaria.domain.Clientes;
import br.com.luis.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	@SuppressWarnings("unused")
	private Clientes clientes;
	private List<Pessoa> pessoas;
	private List<Clientes> cliente;

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Clientes> getCliente() {
		return cliente;
	}

	public void setCliente(List<Clientes> cliente) {
		this.cliente = cliente;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.listar("dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			clientes = new Clientes();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Ocorrreu um erro ao salvar o cliente!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.listar("pessoa");

			novo();
			cliente = clienteDAO.listar("nome");

			org.omnifaces.util.Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Ocorrreu um erro ao salvar o cliente!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			clientes = (Clientes) evento.getComponent().getAttributes().get("cidadeSelecionada");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Ocorrreu um erro ao editar o cliente!");
			erro.printStackTrace();
		}

	}

	public void excluir() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.listar("pessoa");

			novo();
			cliente = clienteDAO.listar("nome");

			org.omnifaces.util.Messages.addGlobalInfo("Cliente excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Ocorrreu um erro ao excluir o cliente!");
			erro.printStackTrace();
		}

	}

}
