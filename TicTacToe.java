//Priya Shah

import java.util.*;
import java.util.Scanner;

public class TicTacToe implements TwoPlayerGame {
	
	private int[][] board;
	private String[] players = new String[2];
	private int currentPlayer;
	private int row;
	private int col;
	
	Scanner keyboard = new Scanner(System.in);

	public TicTacToe(String p1, String p2) {
		
		board = new int[3][3];
		players[0] = p1;
		players[1] = p2;
		currentPlayer = (int) (Math.random() * 2);
	}

	//Displays the rules of the game
	public void displayRules() {

		System.out.println("Tic-tac-toe game for two players, X and O, who take turns marking the spaces in a 3◊3 grid");
		System.out.println("The player who succeeds in placing three respective marks in a horizontal, vertical, or diagonal row wins the game.");

	}

	//gets the players move
	public String getMove() {
		
		System.out.println(players[currentPlayer] + " enter cell number: ");
		
		
		return keyboard.nextLine();
	}
	
	//checks to see if the players move is valid
	public boolean isValid(String move) {

		int choice;
		
		//makes sure their move is a number
		try {
			
			choice = Integer.parseInt(move);
			
		} catch (InputMismatchException e) {
			
			return false;
		}
		
		//makes sure the move is on the board
		if (choice < 1 || choice > 9){
			
			return false;
		}
		
		//turns the move into a row and column
		row = (choice - 1) / 3;
		col = (choice - 1) % 3;

		//makes sure the space is empty
		if (board[row][col] != 0){
			
			return false;
		}
		
		return true;
	}

	//updates the tic tac toe board
	public void updateBoard() {
		
		board[row][col] = currentPlayer + 1;
	}

	//displays the board 
	public void displayBoard() {

		//prints out the numbers and spacingfor the board so the user knows where they are able to move. puts an X or an O based if a player went there and a number there if it is empty
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {

				if (board[r][c] == 0) {
					
					System.out.print(r * 3 + c + 1);
				} 
				
				else if (board[r][c] == 1){
					
					System.out.print("X");
				}
				
				else{
					
					System.out.print("O");
				}

				if (c < 2){
					
					System.out.print("|");
				}
			}
			
			System.out.println("\n_ _ _");
		}

	}

	//determines the winner of the game
	public int determineWinner() {

		int winner = -1;

		//checks the rows
		if (board[row][0] == currentPlayer && board[row][1] == currentPlayer&& board[row][2] == currentPlayer) {
			
			System.out.println("col");
			winner = currentPlayer;
		}

		//checks the columns
		else if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
			
			System.out.println("row");
			winner = currentPlayer;
		}

		//checks the first diagonal
		else if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
			
			System.out.println("diag1");
			winner = currentPlayer;
		}

		//checks the second diagonal
		else if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
			
			System.out.println("diag2");
			winner = currentPlayer;
		}

		currentPlayer = (currentPlayer + 1) % 2;

		return winner;
	}

}
