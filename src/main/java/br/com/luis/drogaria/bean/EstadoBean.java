package br.com.luis.drogaria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import org.jboss.logging.Messages;

import com.mysql.cj.protocol.Message;

import br.com.luis.drogaria.domain.Estado;

/**
 * 
 * @author ljesus
 *
 */

@SuppressWarnings("unused")
@ManagedBean
@ViewScoped  
public class EstadoBean {
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
		org.omnifaces.util.Messages.addGlobalInfo("Nome: " + estado.getNome() + "Sigla: " + estado.getSigla());
		
//		String texto = "Teste paro o botão salvar";
//		FacesMessage menssagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto);
//
//		FacesContext contexto = FacesContext.getCurrentInstance();
//		contexto.addMessage(null, menssagem);
	}

}
