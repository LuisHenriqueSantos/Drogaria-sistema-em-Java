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
		Pessoa pessoa = pessoaDAO.buscar(2L);

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
		Long codigo = 2L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhuma pessoa encontrada!");
		} else {
			System.out.println("Pessoa localizada!");
			System.out
					.println(usuario.getPessoa().getNome() + " - " + usuario.getCodigo() + " - " + usuario.getAtivo());
		}

	}

	@Test
	@Ignore

	public void excluir() {
		Long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhum usuário encontrado!");
		} else {
			usuarioDAO.excluir(usuario);
			System.out.println("Pessoa excluida com sucesso");
			System.out.println(usuario.getCodigo() + " - " + usuario.getSenha() + " - " + usuario.getTipo());

		}

	}

	@Test
	@Ignore

	public void editar() {
		Long codigo = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);

		if (usuario == null) {
			System.out.println("Nenhuma pessoa encontrada");
		} else {
			System.out.println("Registro encontrado");

			System.out.println(usuario.getCodigo() + " - " + usuario.getSenha() + " - " + usuario.getTipo() + " - "
					+ usuario.getAtivo() + " - " + usuario.getPessoa().getNome());

			usuario.setCodigo(1L);
			usuario.setSenha("123456");
			usuario.setAtivo(true);
			usuarioDAO.editar(usuario);

			System.out.println("Registro editado - Depois:");
			System.out.println(usuario.getCodigo() + " - " + usuario.getSenha() + " - " + usuario.getTipo() + " - "
					+ usuario.getAtivo());

		}

	}

}
