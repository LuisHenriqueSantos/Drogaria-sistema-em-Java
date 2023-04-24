package br.com.luis.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemVenda extends GenericDomain {
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal valorParcial;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produtos produto;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda venda;

	public Short getQuantidade() {
		return quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
	}
}
