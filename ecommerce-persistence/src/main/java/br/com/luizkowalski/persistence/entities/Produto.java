package br.com.luizkowalski.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=50)
	private String nome;
	
	@Column(length=200)
	private String descricao;
	
	@Column
	private String codigoInterno;
	
	private String caminhoFoto;
	
	@Column(scale=2, precision=14)
	private BigDecimal custoCompra;
	
	@Column(scale=2, precision=14)
	private BigDecimal despesasTotais = new BigDecimal(400);
	
	@Column(scale=2, precision=4)
	private BigDecimal margemLucro;
	
	@Transient
	private BigDecimal valorVenda;
	
	@Transient
	private Integer quantidade;
	
	public Produto() {}
	
	public Produto(String nome, String descricao, String codigoInterno,
			String caminhoFoto, BigDecimal custoCompra,
			BigDecimal despesasTotais, BigDecimal margemLucro) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.codigoInterno = codigoInterno;
		this.caminhoFoto = caminhoFoto;
		this.custoCompra = custoCompra;
		this.despesasTotais = despesasTotais;
		this.margemLucro = margemLucro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getCodigoInterno() {
		return codigoInterno;
	}
	
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCustoCompra() {
		return custoCompra;
	}

	public void setCustoCompra(BigDecimal custoCompra) {
		this.custoCompra = custoCompra;
	}

	public BigDecimal getDespesasTotais() {
		return despesasTotais;
	}

	public void setDespesasTotais(BigDecimal despesasTotais) {
		this.despesasTotais = despesasTotais;
	}

	public BigDecimal getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(BigDecimal margemLucro) {
		this.margemLucro = margemLucro;
	}
	
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void calcularValorVenda(BigDecimal rateioDespesas) {
		// Se existe margem de lucro, e ela é maior que 0, calcula
		if(margemLucro != null && margemLucro.compareTo(BigDecimal.ZERO) > 0){
			BigDecimal precoVenda = custoCompra.add(rateioDespesas);
			BigDecimal taxa = new BigDecimal(1).add(margemLucro.divide(new BigDecimal(100)));
			precoVenda = precoVenda.multiply(taxa);
			setValorVenda(precoVenda);
		} else {
			// O exercicio nao diz o que fazer caso a margem seja 0
		}
		
	}

	
}
