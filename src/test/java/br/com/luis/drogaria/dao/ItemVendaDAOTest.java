package br.com.luis.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Funcionario;
import br.com.luis.drogaria.domain.ItemVenda;
import br.com.luis.drogaria.domain.Produtos;
import br.com.luis.drogaria.domain.Venda;

@SuppressWarnings("unused")
public class ItemVendaDAOTest {

	@Test
	// @Ignore
	public void salvar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produtos produtos = produtoDAO.buscar(1L);
		VendaDAO vendaDAO = new VendaDAO();
		//Venda venda = vendaDAO.buscar(1L);

		ItemVenda itemVenda = new ItemVenda();

		itemVenda.setCodigo(1L);
		itemVenda.setProduto(produtos);
		itemVenda.setQuantidade(new Short("7"));
		itemVenda.setValorParcial(new BigDecimal("15.50"));

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.salvar(itemVenda);

		System.out.print("Produto salvo com sucesso");

	}

}
