package br.com.luis.drogaria.bean;

import br.com.luis.drogaria.dao.ProdutoDAO;
import br.com.luis.drogaria.domain.Produtos;
import org.omnifaces.util.Messages;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class VendaBean implements Serializable {
    private List<Produtos> produtos;

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    @PostConstruct
    public void listar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao carregar a tela de venda!");
            erro.printStackTrace();
        }
    }
}
