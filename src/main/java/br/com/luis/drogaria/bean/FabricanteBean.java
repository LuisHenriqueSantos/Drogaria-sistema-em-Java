package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.FabricanteDAO;
import br.com.luis.drogaria.dao.ProdutoDAO;
import br.com.luis.drogaria.domain.Fabricante;
import br.com.luis.drogaria.domain.Produtos;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class FabricanteBean implements Serializable {
	private Fabricante fabricante;
	private Produtos produtos;

	private List<Fabricante> fabricantes;
	private List<Produtos> produto;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public List<Produtos> getProduto() {
		return produto;
	}

	public void setProduto(List<Produtos> produto) {
		this.produto = produto;
	}

	@PostConstruct
	public void listar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();

		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao exbir a listagem dos fabricantes!");
			errro.printStackTrace();
		}

	}

	public void novo() {
		try {
			fabricante = new Fabricante();

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = produtoDAO.listar("descricao");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao incluir um novo fabricante!");
			errro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);

			fabricante = new Fabricante();

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = produtoDAO.listar();

			fabricantes = fabricanteDAO.listar(null);

			Messages.addGlobalInfo("Fabricante salvo com sucesso!");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao incluir um novo fabricante!");
			errro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = produtoDAO.listar();

		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao editar o fabricante!");
			errro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricante);

			fabricantes = fabricanteDAO.listar();

			Messages.addGlobalInfo("Fabricante excluido com sucesso!");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao excluir o fabricante!");
			errro.printStackTrace();
		}
	}

}
