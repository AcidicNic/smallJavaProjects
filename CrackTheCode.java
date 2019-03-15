import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Thread;

public class CrackTheCode {
	
	public static String[] library = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	
	public static void main(String[] args) throws IOException {
		
		final FileWriter fileWriter = new FileWriter("passwordList");
		final PrintWriter printWriter = new PrintWriter(fileWriter);
		
	    for (int q = 0; q < library.length; q++) {
	    	for (int w = 0; w < library.length; w++) {
		    	for (int e = 0; e < library.length; e++) {
			    	for (int x = 0; x < library.length; x++) {
			    		printWriter.printf("%s%s%s%s\n", library[q], library[w], library[e], library[x]);
			    	}
		    	}
	    	}
	    }
	    System.out.println("list generated.");
	    printWriter.close();
	    
		String goal;
		boolean notFound = true;
		Scanner reader = new Scanner(System.in);
		System.out.print("\nEnter any combination of four characters (a-z and 0-9): ");
		goal = reader.nextLine();
		reader.close();
		
		System.out.print("\n   ");
		
		File file = new File("passwordList");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null && notFound) {
			if (line.toString().equals(goal)) {
				notFound = false;
			}
			//Thread.sleep(1000);
			System.out.printf("\b\b\b\b%s",line.toString());
		}
		fileReader.close();
		System.out.println("\nCombination found!");

	}
}
