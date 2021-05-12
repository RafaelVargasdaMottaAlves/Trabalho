package objetos;


import java.util.HashSet;

import java.util.Set;

public class Funcionario extends Pessoa implements Salario {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private Set<Dependente> dependentes = new HashSet<>(3);

	public Funcionario() {
		super();
	}
	
	public Funcionario(String nome, String cpf, String dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		
	}

	@Override
	public String toString() {
		return "Funcionario [Nome = " + this.nome + ", salarioBruto =" + this.salarioBruto + ", descontoInss =" + this.descontoInss + ", descontoIR="
				+ this.descontoIR + ", dependentes =" + dependentes+ "]";
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}



	public double getDescontoInss() {
		return descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}
	
	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public void adicionaDependentes(Dependente dependente) {
		this.dependentes.add(dependente);
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	

	public double calcInss() {
		if (salarioBruto <= 1100 && salarioBruto >= 0) {
			return descontoInss = salarioBruto * 0.075;
		} else if (salarioBruto >= 1100.01 && salarioBruto <= 2203.48) {
			return descontoInss = salarioBruto * 0.009 - 16.50;
		} else if (salarioBruto >= 2203.49 && salarioBruto <= 3305.22) {
			return descontoInss = salarioBruto * 0.12 - 82.61;
		} else if (salarioBruto >= 3305.23 && salarioBruto <= 6433.57) {
			return descontoInss = salarioBruto * 0.14 - 148.72;
		} else {
			return descontoInss = 6433.57 * 0.14 - 148.72;
		}
	}

	public double calcIR() {
		if ((salarioBruto - abatimentoDP() - calcInss()) < 1903.98
				&& (salarioBruto - abatimentoDP() - calcInss()) >= 0) {
			return descontoIR = ((salarioBruto - abatimentoDP() - calcInss()));
		} else if ((salarioBruto - abatimentoDP() - calcInss())>= 1903.98 
				&&(salarioBruto - abatimentoDP() - calcInss()) <= 2826.65) {  
			 return descontoIR = (((salarioBruto - abatimentoDP() - calcInss()) * 0.075) - 142.80);
		} else if ((salarioBruto - abatimentoDP() - calcInss()) >= 2826.66
				&& (salarioBruto - abatimentoDP() - calcInss()) <= 3751.05) {
			return descontoIR = (((salarioBruto - abatimentoDP() - calcInss()) * 0.15) - 354.80);
		} else if ((salarioBruto - abatimentoDP() - calcInss()) >= 3751.06
				&& (salarioBruto - abatimentoDP() - calcInss()) <= 4664.68) {
			return descontoIR = (((salarioBruto - abatimentoDP() - calcInss()) * 0.225) - 636.13);
		} else {
			return descontoIR = (((salarioBruto - abatimentoDP() - calcInss()) * 0.275) - 869.36);
		}

	}

	public double abatimentoDP() {
		return (dependentes.size() * dependentes.iterator().next().getDescontoDp());
	}

	@Override
	public double calcSalarioLiquido() {

		return salarioBruto - calcInss() - calcIR();
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	
	
	
}
