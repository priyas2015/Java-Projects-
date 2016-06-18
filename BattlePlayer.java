/*Todd Schleicher, Aaron Green 
 * Period 8
 * This class maintains properties of a player with a board
 */
public class BattlePlayer {
	
	private BattleBoard myBoard;
	private String name;
	
	public BattlePlayer(String n, int boardSize){
		
		name=n;
		myBoard=new BattleBoard(boardSize);
		
	}
	
	//returns name of player
	public String getPlayerName(){
		
		return name;
		
	}
	
	//returns true if the board has no ships left to sink
	public boolean empty(){
		
		return myBoard.getShipsLeft()==0;
		
	}
	
	//shows user his red/white pegs
	public void showPegs(){
		
		System.out.println(myBoard.showPegs());
		
	}
	
	//makes a move according to coordinates entered
	public void move(int row, int col){
		
		myBoard.hit(row, col);
		
	}
	
	//checks if the move has already been made
	public boolean isEmpty(int row, int col){
		
		return !myBoard.existPeg(row, col);
		
	}
	

}
