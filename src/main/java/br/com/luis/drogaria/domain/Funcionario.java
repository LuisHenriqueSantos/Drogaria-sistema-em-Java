package br.com.luis.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain{
	
	@Column (length = 15, nullable = false)
	private String ScarteiraTrabalho;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAdimissao;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

	public String getScarteiraTrabalho() {
		return ScarteiraTrabalho;
	}

	public void setScarteiraTrabalho(String scarteiraTrabalho) {
		ScarteiraTrabalho = scarteiraTrabalho;
	}

	public Date getDataAdimissao() {
		return dataAdimissao;
	}

	public void setDataAdimissao(Date dataAdimissao) {
		this.dataAdimissao = dataAdimissao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	} 

}
