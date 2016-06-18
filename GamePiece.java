/*Todd Schleicher, Aaron Green 
 * Period 8
 * This class represents a ship on the board
 */
public class GamePiece {
	
	private int spotsLeft;
	
	public GamePiece(int size){
		
		spotsLeft=size;
		
	}
	
	//returns true if ship has no more spots to hit
	public boolean sunk(){
		
		return spotsLeft==0;
		
	}
	
	//updates number of spots left to hit on ship
	public void hit(){
		
		spotsLeft--;
		
	}
}
