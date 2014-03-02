package br.com.luizkowalski.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Lob
	private String descricao;
	
	@Column(scale=2, precision=14)
	private BigDecimal custoCompra;
	
	@Column(scale=2, precision=14)
	private BigDecimal despesasTotais = new BigDecimal(400);
	
	@Column(scale=2, precision=3)
	private BigDecimal margemLucro;
	
	
	
}
