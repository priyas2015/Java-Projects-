/*Todd Schleicher, Aaron Green 
 * Period 8
 * This class maintains a board of ships and pegs
 */
import java.util.Random;


public class BattleBoard {
	
	private static final String[] SHIPTYPES={"Submarine", "Destroyer", "Cruiser", "Battleship", "Aircraft Carrier"};
	private char[][] shipLocs;
	private char[][] pegs;
	private GamePiece[] pieces;
	private int shipsLeft;
	
	public BattleBoard(int boardSize){
		
		shipLocs=new char[boardSize][boardSize];//location of ships on board
		pegs=new char[boardSize][boardSize];//location of moves made on board
		pieces=new GamePiece[SHIPTYPES.length];//holds ships on the board
		shipsLeft=SHIPTYPES.length;//number of ships left
		
		Random randGen=new Random();
		int shipRow;
		int shipCol;
		int vertical;
		int shipLength;
		
		
		for(int i=0; i<SHIPTYPES.length; i++){
			
			shipLength=i+2;
			
			pieces[i]=new GamePiece(shipLength);
			
			//generate new coordinates until there are no ships in the way
			do{
				
				vertical=randGen.nextInt(2);//1 signifies a vertical placement
				shipRow=randGen.nextInt(shipLocs.length-(shipLength-1)* vertical);
				shipCol=randGen.nextInt(shipLocs.length - (shipLength-1) * (1- vertical));
				
			}while(!isValidPlacement(shipRow, shipCol, shipLength, vertical));
			
			for(int n=0; n<shipLength; n++)
				
				shipLocs[shipRow+n*vertical][shipCol+n*(1-vertical)]=SHIPTYPES[i].charAt(0);
	
		}
	
	}
	
	//returns the board of red and white pegs
	public String showPegs(){
		
		return matrixShow(pegs);
		
	}
	
	//returns number of ships left to sink
	public int getShipsLeft(){
		
		return shipsLeft;
		
	}
	
	//returns true if a peg already exists at a location
	public boolean existPeg(int row, int col){
		
		return pegs[row][col]!=0;
		
	}
	
	//returns true if no ships in the way of potential placement
	private boolean isValidPlacement(int shipRow, int shipCol, int shipLength, int vertical){
		
		if(shipLength==2)
			return true;
		
		for(int n=0; n<shipLength; n++){
			
			if(shipLocs[shipRow+n*vertical][shipCol+n*(1-vertical)]!=0)
			return false;
			
		}
		
		return true;
		
	}
	
	//adds white/red peg to board, possibly sinks ship
	public void hit(int row, int col){
		
		if(shipLocs[row][col]==0){
			
			System.out.println("You missed");
			pegs[row][col]='w';//white peg for miss
			return;
			
		}
		
		System.out.print("You hit ");
		
		for(int i=0; i<SHIPTYPES.length; i++){
			
			if(SHIPTYPES[i].charAt(0)==shipLocs[row][col]){
				
				pieces[i].hit();
				pegs[row][col]='r';//red peg for hit
				
				if(pieces[i].sunk()){
					
					shipsLeft--;
					System.out.print("and sunk ");
					
				}
				
				System.out.println("the "+SHIPTYPES[i]);
				
			}
			
		}
		
	}
	
	//returns a matrix parameter in string form
	private String matrixShow(char[][] board){
		
		String toReturn="";
		
		for(int i=1; i<=board.length; i++)
			toReturn+=("\t"+i);
		
		toReturn+=("\n");
		
		for(int row=0; row<board.length; row++){
			
			toReturn+=((char)(row+65)+"\t");
			
			for(int col=0; col<board.length; col++)
				
				toReturn+=(board[row][col]+"\t");
			
			toReturn+=("\n\n");
		}
		
		return toReturn;
		
	}
	
	//returns locations of ships in string form
	public String toString(){
		
		return matrixShow(shipLocs);
		
	}

	
}
