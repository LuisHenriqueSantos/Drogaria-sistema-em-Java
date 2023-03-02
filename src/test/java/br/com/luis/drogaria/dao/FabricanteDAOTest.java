package br.com.luis.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

//import br.com.luis.drogaria.domain.fabricante;
import br.com.luis.drogaria.domain.Fabricante;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("unused")
public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Aché");

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Fabricante fabricante : resultado) {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Nenhum registro encontradro");
		} else {
			fabricanteDAO.excluir(fabricante);
			System.out.println("Registro excluido com sucesso");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}

	}

	// Método de Editar
	@Test
	// @Ignore
	public void editar() {
		Long codigo = 5L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);

		if (fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - Antes:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());

			// fabricante.setCodigo(3);
			fabricante.setDescricao("Corinthians");

			fabricanteDAO.editar(fabricante);

			System.out.println("Registro editado - Depois:");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

}