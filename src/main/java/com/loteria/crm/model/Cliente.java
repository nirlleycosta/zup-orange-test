package com.loteria.crm.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	public Cliente() {
		super();
	}

	public Cliente(String email) {
		// TODO: validação do e-mail.
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || getClass() != other.getClass())
			return false;

		final Cliente another = (Cliente) other;

		return Objects.equals(this.email, another.email);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(email);
	}
}
