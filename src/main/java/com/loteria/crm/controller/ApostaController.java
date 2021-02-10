package com.loteria.crm.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loteria.crm.generator.GeradorNumeros;
import com.loteria.crm.model.Aposta;
import com.loteria.crm.model.Cliente;
import com.loteria.crm.repository.ApostaRepository;
import com.loteria.crm.repository.ClienteRepository;

@RestController
@RequestMapping("/apostas")
public class ApostaController {

	@Autowired
	private ApostaRepository apostaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Aposta> listar(@RequestParam Map<String, String> queryString) {
		if (queryString.containsKey("email")) {
			// ?email=john@example.com
			return pesquisarPorEmail(queryString.get("email"));
		} else if (queryString.containsKey("numeros")) {
			// ?numeros=1, 2, 3, 4, 5, 6
			return pesquisarPorNumeros(queryString.get("numeros"));
		} else {
			return Collections.<Aposta>emptyList();
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aposta apostar(@RequestBody Cliente apostador) {
		Set<Integer> numeros = new GeradorNumeros().gerarAposta();

		Cliente cliente = criarCliente(apostador);
		Aposta aposta = new Aposta(cliente, numeros.toString());

		return apostaRepository.save(aposta);
	}

	private List<Aposta> pesquisarPorEmail(String email) {
		return apostaRepository.findByClienteEmail(email);
	}

	private List<Aposta> pesquisarPorNumeros(String numeros) {
		final String osNumeros = "[" + String.join(", ", numeros.trim().replace(" ", "").split(",")) + "]";

		return apostaRepository.findByNumeros(osNumeros);
	}

	private Cliente criarCliente(Cliente cliente) {
		return clienteRepository.findClienteByEmail(cliente.getEmail())
				.orElseGet(() -> clienteRepository.save(cliente));
	}
}
