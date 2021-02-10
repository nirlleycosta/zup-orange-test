package com.loteria.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loteria.crm.model.Aposta;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, String> {
	public List<Aposta> findByClienteEmail(String email);

	public List<Aposta> findByNumeros(String numeros);
}
