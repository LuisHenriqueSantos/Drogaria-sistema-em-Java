package br.com.luis.drogaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.luis.drogaria.dao.ClienteDAO;
import br.com.luis.drogaria.dao.VendaDAO;
import org.omnifaces.util.Messages;

import br.com.luis.drogaria.dao.FuncionarioDAO;
import br.com.luis.drogaria.dao.ProdutoDAO;
import br.com.luis.drogaria.domain.Clientes;
import br.com.luis.drogaria.domain.Funcionario;
import br.com.luis.drogaria.domain.ItemVenda;
import br.com.luis.drogaria.domain.Produtos;
import br.com.luis.drogaria.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped

public class VendaBean implements Serializable {
    private Venda venda;
    private List<Produtos> produtos;
    private List<ItemVenda> itensVendas;
    private List<Clientes> clientes;
    private List<Funcionario> funcionarios;

    public List<Clientes> getClientes() {
        return clientes;
    }
    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public List<ItemVenda> getItensVendas() {
        return itensVendas;
    }
    public void setItensVendas(List<ItemVenda> itensVendas) {
        this.itensVendas = itensVendas;
    }

    @PostConstruct
    public void novo() {
        try {
            venda = new Venda();
            venda.setValorTotal(new BigDecimal("0.00"));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar();

            itensVendas = new ArrayList<>();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Erro ao carregar a tela de venda!");
            erro.printStackTrace();
        }
    }

    public void adcionarItem(ActionEvent evento) {
        Produtos produto = (Produtos) evento.getComponent().getAttributes().get("produtoSelecionado");
        System.out.println(produto);

        int achou = -1;
        for (int posicao = 0; posicao < itensVendas.size(); posicao++) {
            if (itensVendas.get(posicao).getProduto().equals(produto)) {
                achou = posicao;
            }
        }

        if (achou < 0) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setValorParcial(produto.getPreco());
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(new Short("1"));

            itensVendas.add(itemVenda);
        } else {
            ItemVenda itemVenda = itensVendas.get(achou);
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
            itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
        }
        calculaFinalizacaoVenda();
    }

    public void removerItemCompra(ActionEvent evento) {
        ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("botaoSelecionado");

        int achou = -1;

        for (int i = 0; i < itensVendas.size(); i++) {
            if (itensVendas.get(i).getProduto().equals(itemVenda.getProduto())) {
                achou = i;
            }
        }

        itensVendas.remove(achou);
        calculaFinalizacaoVenda();
    }


    public void diminuirItem(ActionEvent evento) {
        ItemVenda itemVendaEvento = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

        int achou = -1;

        for (int i = 0; i < itensVendas.size(); i++) {
            if (itensVendas.get(i).getProduto().equals(itemVendaEvento.getProduto())) {
                achou = i;
            }
        }

        if (achou >= 0) {
            ItemVenda itemVenda = itensVendas.get(achou);
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1 + ""));
            itemVenda.setValorParcial(itemVenda.getValorParcial().subtract(itemVenda.getProduto().getPreco()));

            if (itemVenda.getQuantidade() <= 0) {
                itensVendas.remove(achou);
            }
        }

        calculaFinalizacaoVenda();
    }
    public void calculaFinalizacaoVenda() {
        venda.setValorTotal(new BigDecimal("0.00"));

        for(int posicao = 0; posicao < itensVendas.size(); posicao++){
            ItemVenda itemvenda = itensVendas.get(posicao);
            venda.setValorTotal(venda.getValorTotal().add(itemvenda.getValorParcial()));
        }
    }

    public void finalizar(){
        try {
            venda.setHorario(new Date());
            venda.setFuncionario(null);
            venda.setCliente(null);

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarios = funcionarioDAO.listarOrdenado();

            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listarOrdenado();
        }catch(RuntimeException erro){
            Messages.addFlashGlobalInfo("Ocorrreu um erro ao finalizar a venda!");
            erro.printStackTrace();
        }
    }
    public void salvar() {
        try {
            if (venda.getValorTotal().signum() == 0){
                Messages.addGlobalInfo("Informe pelo menos um item!");
                return;
            }
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda, itensVendas);

            novo();

            Messages.addGlobalInfo("Venda realizada com sucesso!");
        } catch (RuntimeException erro) {
            Messages.addGlobalInfo("Ocorreu um erro ao salvar a venda!");
            erro.printStackTrace();
        }
    }
}
