package br.com.luis.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Estado;
import br.com.luis.drogaria.domain.Fabricante;
import br.com.luis.drogaria.domain.Produtos;

@SuppressWarnings("unused")
public class ProdutoDAOTest {

	@Test
	@Ignore

	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(5L);

		Produtos produtos = new Produtos();

		produtos.setDescricao("Agua mineral");
		produtos.setQuantidade(new Short("7"));
		produtos.setPreco(new BigDecimal("15.50"));
		produtos.setFabricante(fabricante);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produtos);

		System.out.print("Produto salvo com sucesso");

	}

	@Test
	@Ignore
	public void listar() {

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(3L);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produtos> resultado = produtoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Produtos produtos : resultado) {
			System.out.println(produtos.getDescricao() + " - " + produtos.getQuantidade() + " - " + produtos.getPreco()
					+ " - " + produtos.getFabricante().getDescricao());
		}

	}

	@Test
	//@Ignore

	public void buscar() {
		Long codigo = 4L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produtos produtos = produtoDAO.buscar(codigo);

		if (produtos == null) {
			System.out.println("Nenhm registro encontrado!");
		} else {
			System.out.println("Registro encontrado");
			System.out.println(produtos.getDescricao() + " - " + produtos.getQuantidade() + " - " + produtos.getPreco() + " - " + produtos.getFabricante().getDescricao());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 3L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produtos produtos = produtoDAO.buscar(codigo);

		if (produtos == null) {
			System.out.println("Nenhm registro encontrado!");
		} else {
			produtoDAO.excluir(produtos);
			System.out.println("Registro excluido com sucesso");
			System.out.println(produtos.getDescricao() + " - " + produtos.getQuantidade() + " - " + produtos.getPreco());

		}

	}

	@Test
	@Ignore

	public void editar() {
		Long codigo = 2L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produtos produtos = produtoDAO.buscar(codigo);

		if (produtos == null) {
			System.out.println("Nenhum registro encontrado!");
		} else {
			System.out.println("Registro editado - Antes:");
			// System.out.println(produtos.getDescricao() + " - " + produtos.getQuantidade()
			// + " - " + produtos.getPreco());
			System.out
					.println(produtos.getDescricao() + " - " + produtos.getQuantidade() + " - " + produtos.getPreco());

			produtos.setDescricao("Arroz");
			produtos.setQuantidade(new Short("7"));
			produtos.setPreco(new BigDecimal("30.50"));
			produtoDAO.editar(produtos);

			System.out.println("Registro editado - Depois:");
			System.out
					.println(produtos.getDescricao() + " - " + produtos.getQuantidade() + " - " + produtos.getPreco());
		}
	}

}

// Descrição, quantidade, preço, fabricante
