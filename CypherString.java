//Priya Shah
//This program creates an encryption key. It asks the user for a phrase which it then encrypts using a random encryption key.

import java.util.*;

public class CypherString implements Cryptable {	
	
	private String cypherCode;
	
	public CypherString(){
		
		Random rGen=new Random();
		
		String alphabet="abcdefghijklmnopqrstuvwxyz";		
	
		//Creates the encryption for the program. It runs enough to use every letter of the alphabet once and so it creates a 26 letter encryption key for the program to use. 
		for(int i=26;i>0;i--){
			
			int value=rGen.nextInt(i); //creates a new number every time but changes the range as the length of the string decreases.
			cypherCode+=alphabet.charAt(value); //finds the letter at the random value and adds it to the encryption code
			alphabet=alphabet.substring(0, value)+alphabet.substring(value+1); //Deletes the letter at the random position from the alphabet variable in order to use each letter only once. Takes what's before the character and what's after the character and adds it together to make the alphabet string variable.
		}

		
	}
	public static void main(String [] args){
		
		CypherString cypher = new CypherString();
		
		System.out.println("Cypher:  " + cypher.getCypher());
		System.out.println("Encrypt: " + cypher.encrypt("fly"));
		System.out.println("Decrypt: " + cypher.decrypt(cypher.encrypt("fly")));
		
	}

	
	//Encrypts the phrase the user inputed using the encryption key that was randomly generated 
	public String encrypt(String phrase){

		String phraseToCypher="";
		String alphabet="abcdefghijklmnopqrstuvwxyz";	
		
		//Runs through each character in the phrase
		 for(int i = 0; i < phrase.length(); i++){
			 
			 phraseToCypher+= alphabet.charAt(cypherCode.indexOf(phrase.charAt(i))); //First finds the first character of the phrase, then finds that character in the encryption key, and then finds that character in the alphabet. The final character is added to the string that is returned.
		 }
		 
		 return phraseToCypher; //Returns the encrypted phrase
	}
	
	public String decrypt(String phrase){
		
		String cypherToPhrase="";
		String alphabet="abcdefghijklmnopqrstuvwxyz";	
		
		//Runs through each character in the phrase
		 for(int i = 0; i < phrase.length(); i++){
			 
			//First finds the first character of the phrase, then finds that character in the alphabet,
			//and then finds that character in the encrpytion key. 
			//The final character is added to the string that is returned.
			 cypherToPhrase+=cypherCode.charAt(alphabet.indexOf(phrase.charAt(i))); 
		 }

		return cypherToPhrase;
	}
	
	
	public String getCypher(){
		
		return cypherCode;
	}
}















