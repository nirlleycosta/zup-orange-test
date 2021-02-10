package com.loteria.crm.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(indexes = @Index(columnList = "numeros, cliente_id", unique = true))
public class Aposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@Column(nullable = false, length = 150)
	private String numeros;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public Aposta() {
		super();
	}

	public Aposta(Cliente cliente, String numeros) {
		this.cliente = cliente;
		this.numeros = numeros;
		this.data = new Date();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getNumeros() {
		return numeros;
	}

	public Date getData() {
		return data;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || getClass() != other.getClass())
			return false;

		final Aposta another = (Aposta) other;

		return Objects.equals(this.numeros, another.numeros);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(numeros);
	}
}
