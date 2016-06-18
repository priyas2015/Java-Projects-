import java.util.Scanner;

//Priya Shah

public class Driver {
	
	public static void main(String[] args){
		
		Scanner keyboard = new Scanner(System.in);
		
		String cryptObject;
		String phrase;
		
		Cryptable cypherPhrase = null;
		
		System.out.println("Enter which cryptography oject you want to use (Enter A for CypherString or B for Unstoppable Crypt): ");
		cryptObject = keyboard.nextLine();
		
		
		System.out.println("Enter the phrase you want to encrpt: ");
		phrase = keyboard.nextLine();
		
		if(cryptObject.equals("A")){
			
			cypherPhrase = new CypherString();
		}
				
		
		if(cryptObject.equals("B")){
			
			cypherPhrase = new UnstoppableCrypt();
			
		}		
	
		System.out.println("Crypted Message: " + cypherPhrase.encrypt(phrase));

						
		
	}
	
}
