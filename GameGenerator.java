//Priya Shah

import java.util.Scanner;

public class GameGenerator {
	
	public static void main(String[] args){
		
		Scanner keyboard=new Scanner(System.in);
		
		
		String name1;
		String name2;
		int gameChoice;
		
		System.out.println("Player 1 enter name: ");
		name1 = keyboard.nextLine();
		
		System.out.println("Player 2 enter name: ");
		name2 = keyboard.nextLine();
		
		keyboard.nextLine();
		
		System.out.println("What game would you like to to plau? Enter 1 for BattleShip and enter 2 for TicTacToe: ");
		gameChoice = keyboard.nextInt();
		
		if(gameChoice == 1)
			playGame(new BattleShip(name1, name2));	
		
		else if(gameChoice == 2)
			playGame(new TicTacToe(name1, name2));	
		
		else
			System.out.println("Not a valid game choice.");
	}
	
	
	public static void playGame(TwoPlayerGame game){

		game.displayRules();
				
		do{
			
			game.displayBoard();
			String move;
			
			//gets a valid move input
			do{
					
				move=game.getMove();
					
			}while(!game.isValid(move));
			
			game.updateBoard();
			
		}while(game.determineWinner() == -1);
		
		if(game.determineWinner() == 1 || game.determineWinner() == 0){
			
		}
	}
}

