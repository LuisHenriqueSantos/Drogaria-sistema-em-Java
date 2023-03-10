package br.com.luis.drogaria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

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
		String texto = "Teste paro o botão salvar";
		FacesMessage menssagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, texto);

		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, menssagem);
	}

}
