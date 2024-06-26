package br.com.luis.drogaria.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.FabricanteDAO;
import br.com.luis.drogaria.dao.ProdutoDAO;
import br.com.luis.drogaria.domain.Fabricante;
import br.com.luis.drogaria.domain.Produtos;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class ProdutoBean implements Serializable {
    private Produtos produto;
    private List<Produtos> produtos;
    private List<Fabricante> fabricantes;

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    @PostConstruct
    public void listar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            produto = new Produtos();

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar("descricao");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            produto = (Produtos) evento.getComponent().getAttributes().get("produtoSelecionado");
            produto.setCaminho("C:/EclipseJDK/workspace/upload/img_produtos/" + produto.getCodigo() + ".png");

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            if (produto.getCaminho() == null) {
                Messages.addGlobalError("O campo foto é obrigatório");
                return;
            }

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produtos produtoRetorno = produtoDAO.merge(produto);

            Path origem = Paths.get(produto.getCaminho());
            Path destino = Paths.get("C:/EclipseJDK/workspace/upload/img_produtos/" + produtoRetorno.getCodigo() + ".png");
            Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

            produto = new Produtos();

            FabricanteDAO fabricanteDAO = new FabricanteDAO();
            fabricantes = fabricanteDAO.listar();

            produtos = produtoDAO.listar();

            Messages.addGlobalInfo("Produto salvo com sucesso");
        } catch (RuntimeException | IOException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            produto = (Produtos) evento.getComponent().getAttributes().get("produtoSelecionado");

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.excluir(produto);

            Path arquivo = Paths.get("C:/EclipseJDK/workspace/upload/img_produtos/" + produto.getCodigo() + ".png");
            Files.deleteIfExists(arquivo);

            produtos = produtoDAO.listar();

            Messages.addGlobalInfo("Produto removido com sucesso");
        } catch (RuntimeException | IOException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
            erro.printStackTrace();
        }
    }

    public void uploadFotos(FileUploadEvent evento) {
        try {
            UploadedFile arquivoUpload = evento.getFile();
            Path arquivoTemporario = Files.createTempFile(null, null);
            Files.copy(arquivoUpload.getInputstream(), arquivoTemporario, StandardCopyOption.REPLACE_EXISTING);
            produto.setCaminho(arquivoTemporario.toString());
            Messages.addGlobalInfo(produto.getCaminho());
        } catch (IOException erro) {
            Messages.addGlobalInfo("Erro ao fazer o upload da imagem!");
            erro.printStackTrace();
        }
    }
}