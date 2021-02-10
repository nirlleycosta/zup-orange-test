package com.loteria.crm.generator;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GeradorNumeros {

	private static Integer LOTERIA_APOSTA_MINIMA = 6;
	private static Integer LOTERIA_NUMERO_MAXIMO = 60;

	public Set<Integer> gerarAposta() {
		return gerarApostaCom(LOTERIA_APOSTA_MINIMA);
	}

	public Set<Integer> gerarApostaCom(Integer numeros) {
		if (numeros < 6) {
			throw new IllegalArgumentException("A aposta requer no mínimo seis números.");
		}

		return gerarApostaCom(numeros, LOTERIA_NUMERO_MAXIMO);
	}

	private Set<Integer> gerarApostaCom(Integer numeros, Integer numeroMaximo) {
		if (numeros < 1) {
			throw new IllegalArgumentException("A aposta requer no mínimo um número.");
		}

		Set<Integer> aposta = new TreeSet<Integer>();
		Random gerador = new Random();

		// Gerar números até ter a quantidade necessária. O TreeSet garante a ordenação
		// e que teremos números únicos.
		while (true) {
			aposta.add(gerador.nextInt(numeroMaximo) + 1);

			if (aposta.size() == numeros) {
				break;
			}
		}

		return aposta;
	}
}
