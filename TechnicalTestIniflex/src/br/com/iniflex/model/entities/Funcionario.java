package br.com.iniflex.model.entities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
	
	
	private BigDecimal salario;
	
	private String funcao;
	
	public Funcionario() {
		
	}

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getSalarioFormatado() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(getSalario());
        
        }
	
		@Override
		public String toString() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("Funcionário [");
		    sb.append("Nome = ").append(getNome()).append(", ");
		    sb.append("Salário = ").append(getSalarioFormatado()).append(", ");
		    sb.append("Função = ").append(funcao).append(", ");
		    sb.append("Data de Nascimento = ").append(getDataFormatada());
		    sb.append("]");
		    return sb.toString();
		}

	

	
}
