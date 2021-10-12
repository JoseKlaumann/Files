package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ItemsSold;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<ItemsSold> items = new ArrayList<>();
		
	    System.out.print("Enter with number of items: ");
	    int n = sc.nextInt();
		
	    try {
		    for(int i = 0; i < n; i++) {	
		    	System.out.println();
				System.out.print("Name: ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.print("Unity price: ");
				double price = sc.nextDouble();
				System.out.print("Quantity: ");
				int quantity = sc.nextInt();
				
				ItemsSold item = new ItemsSold(name, price, quantity);
				
				items.add(item);
		    }
			
		    System.out.println();
		    System.out.print("Enter file path: ");
		    sc.nextLine();
			String sourceFileStr = sc.nextLine(); //Seleciona o diretorio
			
			new File(sourceFileStr + "\\out").mkdir(); //Cria a pasta com os itens vendidos
			String targetFileStr = 	sourceFileStr + "\\out\\summary.csv";//Cria o arquivo .csv
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr, true))) { //O 'True' garante que ele nao ira deletar, apenas acrescentar novas mensagens
				bw.newLine();//Pula uma linha apos cada cadastro
				for (ItemsSold sold : items) {
					bw.write(sold.toString()); //Escreve dentro do arquivo desejado
					bw.newLine();
				}
				System.out.println(targetFileStr + " - Created!");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println();
			try (BufferedReader br = new BufferedReader(new FileReader(targetFileStr))){
				
				String line = br.readLine();//Vai fazer a impressao daquilo que esta dentro do arquivo selecionado
				while (line != null) {
				    //System.out.println();
					System.out.println(line);
					line = br.readLine();
				}
			}
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
	    }
	    catch (InputMismatchException e) {
	    	//e.printStackTrace();
	    	System.out.println("Enter with numbers!");
	    }
	    catch(RuntimeException e) {
			e.printStackTrace();
		}
	    
		
		sc.close();		
	}

}
