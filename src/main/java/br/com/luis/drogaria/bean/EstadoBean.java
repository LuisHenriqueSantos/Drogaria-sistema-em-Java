package br.com.luis.drogaria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import org.jboss.logging.Messages;

import com.mysql.cj.protocol.Message;

/**
 * 
 * @author ljesus
 *
 */

@SuppressWarnings("unused")
@ManagedBean
public class EstadoBean {
	@SuppressWarnings("unused")
	public void salvar() {
		org.omnifaces.util.Messages.addGlobalError("Nome e Sigla sem preencher ");
		
//		String texto = "Teste paro o botão salvar";
//		FacesMessage menssagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto);
//
//		FacesContext contexto = FacesContext.getCurrentInstance();
//		contexto.addMessage(null, menssagem);
	}

}
