/*Todd Schleicher, Aaron Green 
 * Period 8
 * This class is a game of battleship. Handles most user interactions
 */
import java.util.*;


public class BattleShip implements TwoPlayerGame{
	
	private static final Scanner keyboard=new Scanner(System.in);
	private BattlePlayer[] players=new BattlePlayer[2];
	private int boardSize;
	private int currentPlayer=(int)(Math.random()*2+1);
	private int row; 
	private int col;
	
	public BattleShip(String p1, String p2){
		
		boardSize=10;
		
		players[0]=new BattlePlayer(p1, boardSize);
		players[1]=new BattlePlayer(p2, boardSize);
		
	}
	
	//displays rules of the game
	public void displayRules(){
		
		System.out.println("You and your opponent have a board of five ships.");
		System.out.println("You must guess the locations of your opponent's ship.");
		System.out.println("We will give you a picture each turn of where you have guessed before- white pegs for");
		System.out.println("a missed guess, and red pegs for hits.");
		System.out.println("You must guess by entering a capital letter and number coordinate combination");
		System.out.println("The first player to sink all the other's ships wins!");
		
	}
	
	//determines if a move entered by user is valid
	public boolean isValid(String s){
		
		if(s.equals(""))
			return false;
		
		row=s.charAt(0)-65;
		
		if(s.length()<2||row>boardSize-1||row<0)
			
			return false;
		
		try{
			
			col=Integer.parseInt(s.substring(1))-1;
			
		}catch(NumberFormatException e){

			return false;
		}
		
		if(col>=boardSize||col<0)

			return false;
		
		
		if(!players[currentPlayer-1].isEmpty(row, col))
			
			return false;
		
		
		return true;
		
	}
	
	//returns the move the user makes
	public String getMove(){
		
		System.out.print(players[currentPlayer-1].getPlayerName()+" enter a move: ");
		return keyboard.nextLine(); 
		
	}
	
	//determines if the game is over
	// if not, switches player for next turn 
	public int determineWinner(){
		
		if(players[currentPlayer-1].empty()){
			
			return currentPlayer;
			
		}
		
		currentPlayer=currentPlayer%2+1;
		
		return -1;
		
	}
	
	//returns which player is currently up
	public int getPlayer(){
		
		return currentPlayer;
		
	}
	
	//allows player to make a move
	public void updateBoard(){
		
		players[currentPlayer-1].move(row, col);
		
	}
	
	//shows a player his red/white pegs
	public void displayBoard(){
		
		players[currentPlayer-1].showPegs();
		
	}
	
}
