package br.com.luis.drogaria.dao;

import org.junit.Test;

import br.com.luis.drogaria.domain.Estado;
import br.com.luis.drogaria.domain.Fabricante;

public class EstadaoDAOTest {
	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");
	
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
}

