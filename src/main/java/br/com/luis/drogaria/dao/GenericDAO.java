package br.com.luis.drogaria.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.luis.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> { // Criação do genérico para o pools

	public void salvar(Entidade entidade) { // Salvar o genérico
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // Criando a sessão para pegar a fábrica de
																	

		Transaction transacao = null; // Declarar objeto de transação

		try {
			transacao = sessao.beginTransaction(); //
			sessao.save(entidade); // Salvando a sessão
			transacao.commit(); // Confirma a transação

		} catch (RuntimeException erro) { // Aqui vai apontar caso der erro e vai desfazer a transação

			if (transacao != null) { // Se a transação é diferente ou igual a null
				transacao.rollback(); // Desfazer a transação
			}
			throw erro; //
		} finally {
			sessao.close(); // Finaliza a sessão
		}
	}
}
