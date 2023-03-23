package br.com.luis.drogaria.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.ClienteDAO;
import br.com.luis.drogaria.domain.Clientes;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	@SuppressWarnings("unused")
	private List<Clientes> cliente;
	
	public List<Clientes> getCliente() {
		return cliente;
	}
	
	public void setCliente(List<Clientes> cliente) {
		this.cliente = cliente;
	}
	
	
	@PostConstruct
	public void listar(){
		try{
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.listar("dataCadastro");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
		}
	}

	public void novo() {

	}

	public void salvar() {

	}

	public void editar() {

	}

	public void excluir() {

	}

}
