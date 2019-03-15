import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Thread;
import java.util.Random;

/* add option to read from file or genorate list as it's guessing?? */


public class BruteForce {
	
	public static String[] library = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
	"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
	"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};
	public static void main(String[] args) throws IOException {
		
		ListGen();

		int goalLength = 4;
		String goal = null;

		Scanner reader = new Scanner(System.in);
		System.out.print("Would you like to: Generate random string (r) or Pick one yourself (p)?: ");
		String pickOrRand = reader.nextLine();
		boolean again = false;
		do {
			if (pickOrRand.equals("r")) {
				Random r = new Random();
				int Low = 0;
				int High = library.length - 1;
				goal = library[r.nextInt(High-Low)+Low];
				for (int i = goalLength; i > 1; i--) {
					goal = goal + library[r.nextInt(High-Low)+Low];
				}
			} 
			else if (pickOrRand.equals("p")) {
				System.out.printf("Enter any combination of %d letters or digits: ", goalLength);
				goal = reader.nextLine();
			}
			else {
				again = true;
			}
		} while (again);

		reader.close();
		
		System.out.println("Your number is : " + goal);
		
		System.out.print("Current Combination Guess: ");
		
		for (int i = 0; i < goalLength; i++) {
			System.out.print(" ");
		}

		
		boolean notFound = true;
		File file = new File("passwordList");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null && notFound) {
			if (line.toString().equals(goal)) {
				notFound = false;
			}
			//try {Thread.sleep();} 
			//catch(InterruptedException ex){Thread.currentThread().interrupt();}
			for (int i = 0; i < goalLength; i++) {
				System.out.print("\b");
			}
			System.out.print(line.toString());
		}
		fileReader.close();
		System.out.println("\nNumber found!");
	}

	public static void ListGen() {
		
		final FileWriter fileWriter = new FileWriter("passwordList");
		final PrintWriter printWriter = new PrintWriter(fileWriter);
		
		for (int q = 0; q < library.length; q++) {
	    		for (int w = 0; w < library.length; w++) {
		    		for (int e = 0; e < library.length; e++) {
		    			for (int r = 0; r < library.length; r++) {
		    				printWriter.printf("%s%s%s%s\n", library[q], library[w], library[e], library[r]);
		    			}
				}
			}
		}
		System.out.println("Library generated (A-Z, a-z, 0-9).");
		printWriter.close();
	}
}
