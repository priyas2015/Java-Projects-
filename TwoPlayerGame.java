//Priya Shah

public interface TwoPlayerGame {

	public abstract void displayRules();
	public abstract String getMove();
	public abstract boolean isValid(String mvoe);
	public abstract void updateBoard();
	public abstract void displayBoard();
	public abstract int determineWinner();

}
