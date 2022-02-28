package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("digite o caminho de arquivo: ");
		String path = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Funcionario> list = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.println("Enter salary:");
			double salary = sc.nextDouble();

			List<String> compSalario = list.stream()
					.filter(p -> p.getSalario() > salary)
					.map(p -> p.getEmail())
					.collect(Collectors.toList());

			compSalario.forEach(System.out::println);

			Double sum = list.stream()
					.filter(p -> p.getNome().charAt(0) == 'M')
					.map(p -> p.getSalario())
					.reduce(0.0,
					(x, y) -> x + y);

			System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}