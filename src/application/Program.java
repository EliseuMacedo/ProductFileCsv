package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Produto;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		String dir = "C:\\Users\\eliseu\\Desktop\\ONG\\teste.csv";
		List<Produto> listProd = new ArrayList<Produto>();

		// Recebe a classe arquivo na clase leitura
		try (BufferedReader br = new BufferedReader(new FileReader(dir))) {

			// receber o arquivo file numa string e verificar se é diferente de null
			String line = br.readLine();

			while (line != null) {

				System.out.println(line);
				String[] vet = new String[3];
				vet = line.toString().split(",");
				// Armazenar vetor com tipos especificos
				String nome = vet[0];
				double preco = Double.parseDouble(vet[1]);
				int quantidade = Integer.parseInt(vet[2]);

				// instanciar produto com dados do construtor
				Produto prod = new Produto(nome, preco, quantidade);
				// adicionar a lista de produtos
				listProd.add(prod);
				// ler a proxima linha
				line = br.readLine();
			}
			
			System.out.print("\nDiretorio do novo arquivo: ");
			String dirArquivo = sc.nextLine();
			// criar novo arquivo
			Boolean success = new File(dirArquivo + "\\out").mkdir();
			String pathArquivo = dirArquivo + "\\out\\summary.csv";

			// criar novo arquivo na variável BufferdWriter
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathArquivo))) {

				for (Produto p : listProd) {
					bw.write(p.getNome() + "," + p.total());
					bw.newLine();
				}

				if(success == true) {
					System.out.println("Arquivo criado com sucesso");	
				}
				else {
					System.out.println("Erro ao criar arquivo");
				}
				
			} 
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}

		finally {

			sc.close();
		}
	}
}
