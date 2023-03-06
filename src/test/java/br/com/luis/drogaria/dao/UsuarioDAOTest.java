package br.com.luis.drogaria.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.luis.drogaria.domain.Pessoa;
import br.com.luis.drogaria.domain.Usuario;

@SuppressWarnings("unused")

public class UsuarioDAOTest {

	@Test
	@Ignore

	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);

		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());

		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenha("q1w2e3r4");
		usuario.setTipo('A');

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		System.out.println("Usuário salvo com sucesso.");
	}

	@Test
	@Ignore

	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		System.out.println("Total de registros encontrados!");

		for (Usuario usuario : resultado) {
			System.out.println(usuario.getSenha() + " - " + usuario.getAtivo() + " - " + usuario.getTipo() + " - "
					+ usuario.getPessoa().getNome());
		}
	}

	@Test
	@Ignore

	public void buscar() {
		Long codigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Nenhuma pessoa encontrada!");
		} else {
			System.out.println("Pessoa localizada!");
			System.out.println(pessoa.getNome());
		}

	}

	@Test
	//@Ignore

	public void excluir() {
		Long codigo = 1L;
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);

		if (pessoa == null) {
			System.out.println("Nenhuma pessoa encontrada!");
		} else {
			pessoaDAO.excluir(pessoa);
			System.out.println("Pessoa excluida com sucesso");
			System.out.println(pessoa.getNome() + " - " + pessoa.getEmail() + " - " + pessoa.getCodigo());

		}

	}

}
