package br.com.luis.drogaria.bean;

import br.com.luis.drogaria.dao.ProdutoDAO;
import br.com.luis.drogaria.domain.ItemVenda;
import br.com.luis.drogaria.domain.Produtos;
import org.omnifaces.util.Messages;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class VendaBean implements Serializable {
    private List<Produtos> produtos;
    private List<ItemVenda> itemVendas;

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    @PostConstruct
    public void listar() {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();
            itemVendas = new ArrayList<>();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao carregar a tela de venda!");
            erro.printStackTrace();
        }
    }

    public void adcionarItem(ActionEvent evento) {
        Produtos produto = (Produtos) evento.getComponent().getAttributes().get("produtoSelecionado");
        System.out.println(produto);

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setValorParcial(produto.getPreco());
        itemVenda.setProduto(produto);
        itemVenda.setQuantidade(new Short("1"));

        itemVendas.add(itemVenda);
    }

}
