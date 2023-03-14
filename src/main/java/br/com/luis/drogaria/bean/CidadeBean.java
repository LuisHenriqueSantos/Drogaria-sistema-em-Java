package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.CidadeDAO;
import br.com.luis.drogaria.dao.EstadoDAO;
import br.com.luis.drogaria.domain.Cidade;
import br.com.luis.drogaria.domain.Estado;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> cidades;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao exbir a listagem das cidades!");
			errro.printStackTrace();
		}
	}

	public void novo() {
		cidade = new Cidade();
	}

	public void salvar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);

			novo();
			cidades = cidadeDAO.listar();

			org.omnifaces.util.Messages.addGlobalInfo("Estado salvo com sucesso");

		} catch (RuntimeException erro) {
			Messages.addGlobalInfo("Ocorrreu um erro ao salvar o estado!");
			erro.printStackTrace();
		}
	}

}
