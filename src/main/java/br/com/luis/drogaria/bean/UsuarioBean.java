package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.EstadoDAO;
import br.com.luis.drogaria.dao.PessoaDAO;
import br.com.luis.drogaria.dao.UsuarioDAO;
import br.com.luis.drogaria.domain.Cidade;
import br.com.luis.drogaria.domain.Pessoa;
import br.com.luis.drogaria.domain.Usuario;

@SuppressWarnings({ "unused", "serial" })
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private Pessoa pessoa;

	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			usuarios = usuarioDAO.listar("tipo");
		} catch (RuntimeException erro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao exibir a listagem dos usuários!");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro!");
			errro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuario = new Usuario();
			usuarios = usuarioDAO.listar();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			usuarios = usuarioDAO.listar();
			Messages.addGlobalInfo("Usuário salvo com sucesso");
		} catch (RuntimeException errro) {
			org.omnifaces.util.Messages.addGlobalInfo("Ocorrreu um erro ao salvar o usuário!");
		}

	}

	public void editar() {

	}

	public void excluir() {

	}

}
