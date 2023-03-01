package br.com.luis.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Estado;
import br.com.luis.drogaria.domain.Fabricante;

public class EstadaoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}

	@Test
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		// Pode usar esse for aqui para mostar os resultados
		// for(Estado estado : resultado) {
		// System.out.println("Total de registros encontrados: " + resultado.size());
		// }

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + estado.getSigla() + " - " + estado.getNome());
			;
		}
	}
}
