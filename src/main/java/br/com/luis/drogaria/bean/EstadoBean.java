package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.EstadoDAO;
import br.com.luis.drogaria.domain.Estado;

/**
 * 
 * @author ljesus
 *
 */

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	@SuppressWarnings("unused")
	private Estado estado;
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public Estado setEstado() {
		return estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao exbir a listagem dos estados!");
			errro.printStackTrace();
		}

	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.salvar(estado);

			novo();

			org.omnifaces.util.Messages.addGlobalInfo("Estado salvo com sucesso");

		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao salvar o estado!");
			errro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Nome " + estado.getNome() + "Sigla " + estado.getSigla());
	}

}