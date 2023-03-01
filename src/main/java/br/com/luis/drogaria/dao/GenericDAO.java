package br.com.luis.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.luis.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> { // Criação do genérico para o pools
	
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]; // Esse cod ele pega no refactor, qual o tipo da class filha
	}

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
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		}finally {
			sessao.close();
		}
			
	
	
	}
}
