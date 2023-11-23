package br.com.iniflex.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.iniflex.model.entities.Funcionario;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
		listaFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
		listaFuncionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"));
		listaFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"));
		listaFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
		listaFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68), "Recepcionista"));
		listaFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
		listaFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"));
		listaFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"));
		listaFuncionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85), "Eletricista"));
		listaFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2799.93), "Gerente"));
		
	
		//Estou criando novas instâncias diretamente no método add do ArrayList de funcionários.
		//Também seria possível declarar as variáveis e adicionar posteriormente
		
		
		// Mostrando como ficou a lista
		System.out.println("LISTA DE FUNCIONÁRIOS SEM MODIFICAÇÕES: ");
		for(Funcionario i : listaFuncionarios) {
			System.out.println(i.toString());
		}
		
		System.out.println();

		// 3.2 – Remover o funcionário “João” da lista.
		listaFuncionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		
		System.out.println();
		
		//Mostrando a lista após remover João
		System.out.println("LISTA DE FUNCIONÁRIOS APÓS DEMISSÃO DE JOÃO: ");
			for(Funcionario i : listaFuncionarios) {
			System.out.println(i.toString());
		}
			
			System.out.println();
			
		//3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
			for(Funcionario i : listaFuncionarios) {
				BigDecimal novoSalario = i.getSalario().multiply(new BigDecimal(1.10));
				i.setSalario(novoSalario);
			}
		
		//Mostrando a lista após aumento de 10%
			System.out.println("LISTA DE FUNCIONÁRIOS APÓS AUMENTO DE 10% EM TODOS OS SALÁRIOS: ");
			for(Funcionario i : listaFuncionarios) {
			System.out.println(i.toString());
		}
			
		System.out.println();
		
		//3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
		
		Map<String, List<Funcionario>> funcionarioChave = new HashMap<>();
		// Vou percorrer a lista já existente num For pra não precisar fazer tudo várias vezes
		
			for(Funcionario i : listaFuncionarios) {
				String cargo = i.getFuncao();
				funcionarioChave.computeIfAbsent(cargo, k -> new ArrayList<>()).add(i);
			}
		
		System.out.println("LISTA DE FUNCIONÁRIOS AGRUPADO POR FUNÇÃO:");
		// Imprimindo agrupado por função
			for (Map.Entry<String, List<Funcionario>> entry : funcionarioChave.entrySet()) {
			    String cargo = entry.getKey();
			    List<Funcionario> funcionarios = entry.getValue();

			    System.out.println("FUNCIONÁRIOS COM A FUNÇÃO DE " + cargo.toUpperCase() + ":");
			    for (Funcionario funcionario : funcionarios) {
			        System.out.println(funcionario);
			       			    } // For encadeado
			}	    
				
			System.out.println();

			
		// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
		System.out.println("LISTA DE FUNCIONÁRIOS QUE FAZEM ANIVERSÁRIO EM OUTUBRO (10) OU DEZEMBRO (12):");
		for (Funcionario i:listaFuncionarios) {
			int outubro = 10;
			int dezembro = 12;
			LocalDate dataTemporal = i.getDataNascimento();
			int mesNascimento = dataTemporal.getMonthValue();
			
			if(mesNascimento == outubro || mesNascimento == dezembro) {
				System.out.println(i);
			}
		}
		
		
		System.out.println();
		
		// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
		int maiorIdade = 0;
		Funcionario maisExperiente = null; //Declarando por fora pois será declarado via for e acessado via if e a variável nesse contexto só existe no escopo
		for (Funcionario i : listaFuncionarios) {
			LocalDate dataAtual = LocalDate.now();
			LocalDate dataNascimento = i.getDataNascimento();
			int idadeFuncionario = Period.between(dataNascimento, dataAtual).getYears();
			
			if (idadeFuncionario > maiorIdade) {
				maiorIdade = idadeFuncionario;
				maisExperiente = i;
			}
		}
		
		if (maisExperiente != null) {
			System.out.println("FUNCIONÁRIO COM A MELHOR IDADE: ");
			System.out.println("Nome: " + maisExperiente.getNome() + " | Idade: " + maiorIdade);
		}
		
		System.out.println();
		
		// 3.10 – Imprimir a lista de funcionários por ordem alfabética.
		Collections.sort(listaFuncionarios, Comparator.comparing(Funcionario::getNome));
		System.out.println("LISTA DE FUNCIONÁRIOS ORDENADA POR ORDEM ALFABÉTICA: ");
		for (Funcionario funcionario : listaFuncionarios) {
		    System.out.println(funcionario);
		}
		
		System.out.println();
		
		
		// 3.11 – Imprimir o total dos salários dos funcionários.
		BigDecimal somaSalarios = BigDecimal.ZERO;

		for (Funcionario i : listaFuncionarios) {
		    somaSalarios = somaSalarios.add(i.getSalario());
		}

		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');

		DecimalFormat decimalFormat = new DecimalFormat("###,###.##", symbols);
		String somaSalariosFormatada = decimalFormat.format(somaSalarios);

		System.out.println("TOTAL DO SALÁRIO DE FUNCIONÁRIOS == " + somaSalariosFormatada);
		
		// Aqui usei o mesmo formato que foi pedido no item 3.3. Acredito que vocês tenham solicitado dessa forma pelo padrão brasileiro de R$X.XXX,XX.
		
		System.out.println();
		
		// 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
		System.out.println("QUANTIDADE DE SALÁRIOS MÍNIMOS POR COLABORADOR: ");
		BigDecimal salarioMinimo = new BigDecimal("1212.00");

		for (Funcionario funcionario : listaFuncionarios) {
		    BigDecimal salario = funcionario.getSalario();
		    BigDecimal salariosMinimos = salario.divide(salarioMinimo, 2, RoundingMode.DOWN); // Usei 2 casas decimais para o resultado por conta de precisão, mas também poderia ser 0 pra ficar o valor redondinho
		    
		    System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
		}
		
		
		// O código acima foi feito utilizando a IDE Eclipse.
		// Agradeço pelo tempo dispensado até aqui.
	}
}

