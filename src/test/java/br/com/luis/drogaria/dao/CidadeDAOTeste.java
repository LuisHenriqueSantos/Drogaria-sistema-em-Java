package br.com.luis.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Cidade;
import br.com.luis.drogaria.domain.Estado;

//Método de salvar
	public class CidadeDAOTeste{
 
	@Test
	@Ignore
	public void salvar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(1L); 
		
		Cidade cidade = new Cidade();
		cidade.setNome("Rio Grande do Sul");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDao = new  CidadeDAO();
		cidadeDao.salvar(cidade);
	}

	
	public void listar() {


	
	}
	
	
	
	
	
	
}
// Codigo, nome, cidade codigo
// Salvar, Listar, buscar, excluir, editar, 