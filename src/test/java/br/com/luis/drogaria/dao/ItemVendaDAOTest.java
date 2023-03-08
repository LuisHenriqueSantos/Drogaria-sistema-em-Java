package br.com.luis.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.ItemVenda;
import br.com.luis.drogaria.domain.Produtos;
import br.com.luis.drogaria.domain.Venda;
import br.com.luis.drogaria.domain.Fabricante;
import br.com.luis.drogaria.domain.Funcionario;

@SuppressWarnings("unused")
public class ItemVendaDAOTest {

	@Test
	// @Ignore
	
	// Tem que ver esse aqui
	
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO(); 
		Fabricante fabricante = fabricanteDAO.buscar(2L);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produtos produtos = produtoDAO.buscar(1L);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda vendas = vendaDAO.buscar(4L);

		ItemVenda itemVenda = new ItemVenda();

		itemVenda.setProduto(produtos);
		itemVenda.setQuantidade(new Short("12"));
		itemVenda.setValorParcial(new BigDecimal("130.50"));

		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itemVendaDAO.salvar(itemVenda);

		System.out.println("Itens da venda salvo com sucesso!");

	}

}
