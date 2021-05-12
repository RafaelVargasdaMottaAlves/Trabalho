package objetos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import exception.DependenteException;

public class Dependente extends Pessoa {
	private final double descontoDp = 189.59;
	private Parentesco parentesco;

	public Dependente() {
		super();
	}
	
	public Dependente(String nome, String cpf, String dataNascimento, Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return super.toString() + ", Cpf: " + cpf + ", Parentesco: " + parentesco;
	}

	public String getCpf() {
		return cpf;
	}

	public double getDescontoDp() {
		return descontoDp;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public Period geraIdade() {
		LocalDate testeData = LocalDate.parse(dataNascimento, DateTimeFormatter.BASIC_ISO_DATE);
		Period idade = Period.between(testeData, LocalDate.now());
		return idade;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

}
