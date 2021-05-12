package testes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import objetos.Dependente;
import objetos.Funcionario;
import objetos.Parentesco;
import validator.DoubleValidator;

public class TesteReadFile {

	public static void main(String[] args) {

		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		String linha = new String();
		DoubleValidator validador = new DoubleValidator();

		try (BufferedReader buffReader = new BufferedReader(new FileReader("C:\\Aula\\trabalhofinal.csv"))) {
			Funcionario funcionario = null;
			while (linha.isEmpty() || funcionario == null) {
				funcionario = new Funcionario();
				while ((linha = buffReader.readLine()) != null) {

					if (linha.trim().length() == 0) {
						continue;
					}

					String[] values = linha.split(";");

					if (validador.isDouble(values[3].toString())) {
						System.out.println(values[0].toString());
						funcionario.setNome(values[0].toString());
						funcionario.setCpf(values[1].toString());
						funcionario.setDataNascimento(values[2].toString());
						double salarioBruto = Double.valueOf(values[3]).floatValue();
						funcionario.setSalarioBruto(salarioBruto);
						listaFuncionarios.add((funcionario));

					} else {
						Dependente dependente = new Dependente();
						dependente.setNome(values[0].toString());
						dependente.setCpf(values[1].toString());

						LocalDate dataNasc = LocalDate.parse(values[2].toString(), DateTimeFormatter.BASIC_ISO_DATE);
						Period idade = Period.between(dataNasc, LocalDate.now());
						System.out.println(idade.getYears());

						if (idade.getYears() < 18) {
							dependente.setDataNascimento(values[2].toString());
							dependente.setParentesco(Parentesco.valueOf(values[3]));
							funcionario.adicionaDependentes(dependente);

						}
					}
				}
				
			}
			System.out.println(funcionario);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(listaFuncionarios.get(1).toString());
		FileWriter file;
		try {
			file = new FileWriter("saida.csv");
			BufferedWriter buffer = new BufferedWriter(file);
			for (int j = 0; j < listaFuncionarios.size(); j++) {
				buffer.write(listaFuncionarios.get(j).getNome() + ";" + listaFuncionarios.get(j).getCpf() + ";"
						+ listaFuncionarios.get(j).calcInss() + ";" + listaFuncionarios.get(j).calcIR() + ";"
						+ listaFuncionarios.get(j).calcSalarioLiquido() + listaFuncionarios.get(j).getDependentes()
						+ "\n");
			}
			buffer.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}