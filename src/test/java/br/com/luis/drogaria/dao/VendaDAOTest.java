package br.com.luis.drogaria.dao;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Clientes;
import br.com.luis.drogaria.domain.Funcionario;
import br.com.luis.drogaria.domain.Produtos;
import br.com.luis.drogaria.domain.Venda;

@SuppressWarnings("unused")
public class VendaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);

		ClienteDAO clienteDAO = new ClienteDAO();
		Clientes clientes = clienteDAO.buscar(2L);

		Venda vendas = new Venda();

		vendas.setHorario(new Date(14));
		vendas.setValorTotal(new BigDecimal("15.60"));
		vendas.setCliente(clientes);
		vendas.setFuniconario(funcionario);

		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(vendas);

		System.out.println("Venda salva com sucesso!");
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);
		ClienteDAO clienteDAO = new ClienteDAO();
		Clientes clientes = clienteDAO.buscar(2L);

		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();

		System.out.println("Vendas encontrados: ");

		for (Venda vendas : resultado) {
			System.out.println(vendas.getHorario() + " - " + vendas.getValorTotal() + " - " + vendas.getCliente()
					+ " - " + vendas.getFuniconario().getPessoa().getNome());
		}

	}

	@Test
	@Ignore

	public void buscar() {
		Long codigo = 2L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda vendas = vendaDAO.buscar(codigo);

		if (vendas == null) {
			System.out.println("Nenhuma venda encontrada!");
		} else {
			System.out.println("Venda encontrada!");
			System.out.println(vendas.getCodigo() + " - " + vendas.getValorTotal() + vendas.getHorario()
					+ vendas.getCliente() + " - " + vendas.getFuniconario().getPessoa().getNome());
		}

	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda vendas = vendaDAO.buscar(codigo);

		if (vendas == null) {
			System.out.println("Nunhuma venda encontrada");
		} else {
			vendaDAO.excluir(vendas);
			System.out.println("Venda excluida");
			System.out.println(vendas.getCliente() + " - " + vendas.getHorario() + " - " + vendas.getValorTotal()
					+ " - " + vendas.getFuniconario());
		}
	}

	@Test
	// @Ignore
	public void editar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(1L);

		ClienteDAO clienteDAO = new ClienteDAO();
		Clientes clientes = clienteDAO.buscar(2L);

		Long codigo = 2L;
		VendaDAO vendaDAO = new VendaDAO();
		Venda vendas = vendaDAO.buscar(codigo);
		
		if (vendas == null) {
			System.out.println("Nenhuma venda encontrada!");
		} else {
			System.out.println(vendas.getHorario() + " - " + vendas.getValorTotal() + " - " + vendas.getCliente()
					+ " - " + vendas.getFuniconario().getPessoa().getNome());

			vendas.setHorario(new Date(14));
			vendas.setValorTotal(new BigDecimal("15.60"));
			vendas.setCliente(clientes);
			vendas.setFuniconario(funcionario);
			vendaDAO.editar(vendas);

			System.out.println("Registro editado - Depois:");
			System.out.println(vendas.getHorario() + " - " + vendas.getValorTotal() + " - " + vendas.getCliente()
					+ " - " + vendas.getFuniconario().getPessoa().getNome());
		}

	}
}
