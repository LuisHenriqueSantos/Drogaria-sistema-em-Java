package br.com.luis.drogaria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.management.RuntimeErrorException;

import org.jboss.logging.Messages;

import com.mysql.cj.protocol.Message;

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

	public Estado getEstado() {
		return estado;
	}

	public Estado setEstado() {
		return estado;
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

			throw new RuntimeException("Erro forçado");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro!");
			errro.printStackTrace();
		}
//		String texto = "Teste paro o botão salvar";
//		FacesMessage menssagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto);
//
//		FacesContext contexto = FacesContext.getCurrentInstance();
//		contexto.addMessage(null, menssagem);
	}

}
