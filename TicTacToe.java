import java.util.Scanner;

/**
*
*This program will allow a user to play Tic-Tac-Toe against the computer
*
*@author Ethan Muir
*@version Thursday, June 6
* 
*/

public class TicTacToeMuir
{
	static int userScore = 0;
	static int compScore = 0;
	
	public static void main(String[]args)
	{
		//Scanner object for getting user input
		Scanner in = new Scanner(System.in);
		boolean repeatChoice = false; //Becomes true if user decides to play again
		printProgIntro();
		do
		{
			//Declaration of variables
			char[][] userPlaces = {{' ', ' ', ' '}, {' ', ' ', ' '},{' ', ' ', ' '}};//Two dimensional array to store each user spot on the tic-tac-toe grid
			char[][] compPlaces = {{' ', ' ', ' '}, {' ', ' ', ' '},{' ', ' ', ' '}};//Two dimensional array to store each copmuter spot on the tic-tac-toe grid
			int userRow = 0; //User's chosen row
			int userColumn = 0; //User's chosen column
			boolean userWin = false; //Becomes true when the user wins
			boolean compWin = false; //Becomes true when the computer wins 
			boolean tieGame = false; //Becomes true when there is a tie
			
			printGrid(userPlaces, compPlaces, userWin, tieGame);
			do
			{
				userPlaces = getUserPlace(compPlaces, userPlaces);
				printGrid(userPlaces, compPlaces, userWin, tieGame);
				userWin = checkUserWin(userPlaces);
				tieGame = checkTie(userPlaces, compPlaces, userWin, compWin, tieGame);
				compPlaces = getCompPlace(compPlaces, userPlaces, userWin, tieGame);
				tieGame = checkTie(userPlaces, compPlaces, userWin, compWin, tieGame);
				printGrid(userPlaces, compPlaces, userWin, tieGame);
				compWin = checkCompWin(compPlaces, tieGame);
			}
			while(userWin == false && compWin == false && tieGame == false); //Program executes until someone wins or a tie occurs
			System.out.println("");
			System.out.println("\tThe computer has " + compScore + " wins");
			System.out.println("\tYou have " + userScore + " wins");
			System.out.println("");
			repeatChoice = repeatProgram();
		}
		while(repeatChoice == true); //Program will repeat until user decides to stop
		System.out.println("\t~Thanks for playing!~");
	}
	
	/**Method to print the program intro
	*/
	public static void printProgIntro()
	{
		System.out.println("Tic-Tac-Toe");
		System.out.println("===========");
		System.out.println("\nWelcome, the object of the game is to get 3 O's in a row.");
		System.out.println("You will be facing the computer to win. Good Luck!");
		System.out.println("========================================================\n");
	}
	
	/**Method for the user to choose a spot for their O
	* 
	* @param compPlaces 2 dimensional array with all the copmuter's places in it
	* @param userPlaces 2 dimensional array with all the user's places in it
	* 
	* @return Updated array with user's places in it
	*/
	public static char[][] getUserPlace(char[][] compPlaces, char[][] userPlaces)
	{
		//Scanner object for getting user input
		Scanner in = new Scanner(System.in);
		int row; //The user's chosen row
		int column; //The user's chosen column
		boolean repeat = false; //Will become true if the user needs to choose a different place
		do
		{
			System.out.println("\nWhat row would you like? (1-3)");
			row = in.nextInt();
			System.out.println("What column would you like? (1-3)");
			column = in.nextInt();
			if(row > 3 || column > 3 || row < 1 || column < 1) //Will make sure the user does not choose a number higher than 3 or less than 1
			{
				System.out.println("Invalid value, please choose another.");
				repeat = true;
			}	
			else if(userPlaces[row - 1][column - 1] == 'O' || compPlaces[row - 1][column - 1] == 'X') //Will make sure user does not choose an already taken space
			{
				System.out.println("\nThat space is already taken, please choose another.");
				repeat = true;
			}
			else
			{
				userPlaces[row - 1][column - 1] = 'O'; //Assigns an 'O' to the user's chosen place
				repeat = false;
			}
		}
		while(repeat == true);
		return userPlaces;
	}
	
	/**Method for the computer to generate a spot or place it's X
	* 
	* @param compPlaces 2 dimensional array with all the copmuter's places in it
	* @param userPlaces 2 dimensional array with all the user's places in it
	* @param userWin boolean variable to know if the user has won or not
	* @param tieGame boolean variable to know if there has been a tie or not 
	* 
	* @return Updated array with computer's places in it
	*/
	public static char[][] getCompPlace(char[][] compPlaces, char[][] userPlaces, boolean userWin, boolean tieGame)
	{
		int row; //Computer's chosen row
		int column; //Computer's chosen column
		//This if statement is to make sure the computer does not try to generate random numbers in a tie
		if(tieGame == false)
		{
			do
			{
				row = (int)((Math.random()* 3) + 1); //Generates a random row from 1-3
				column = (int)((Math.random()* 3) + 1); //Generates a random column from 1-3
			}
			while(userPlaces[row - 1][column - 1] == 'O' || compPlaces[row - 1][column - 1] == 'X'); //This will make it so the computer will contiunue to find a spot until it finds a free spot
		}
		else
		{
			row = 1;
			column = 1;
		}
		//If statement to make sure this doesn't print if the user has already won
		if(userWin == false && tieGame == false)
		{
			System.out.println("\nThe computer chose column: " + column);
			System.out.println("The computer chose row: " + row + "\n");
		}
		else
		{
			//Do not print computer choices
		}
		compPlaces[row - 1][column - 1] = 'X';
		return compPlaces;
	}
	
	/**Method to generate the grid to the screen with user and computer's places
	* 
	* @param compPlaces 2 dimensional array with all the copmuter's places in it
	* @param userPlaces 2 dimensional array with all the user's places in it
	* @param userWin boolean variable to know if the user has won or not
	* @param tieGame boolean variable to know if there has been a tie or not 
	*/
	public static void printGrid(char[][] userPlaces, char[][] compPlaces, boolean userWin,boolean tieGame)
	{
		if(userWin == false && tieGame == false) //If statement to make sure this doesn't execute if user has already won or a tie has occured
		{
			System.out.println("=======================================\n");
			System.out.print("\t");
			
			//Loop to print cells on first row of grid
			for(int i = 0; i < 2; i++)
			{
				System.out.print("  " + userPlaces[0][i] + compPlaces[0][i] + "  |");
			}
			System.out.print("  " + userPlaces[0][2] + compPlaces[0][2] + "  ");
			System.out.println("");
			
			System.out.print("\t");
			//Loop to print horizontal line of grid
			for(int i = 0; i < 20; i++)
			{
				System.out.print("-");
			}
			System.out.println("");
			
			System.out.print("\t");
			//Loop to print cells on second row of grid
			for(int i = 0; i < 2; i++)
			{
				System.out.print("  " + userPlaces[1][i] + compPlaces[1][i] + "  |");
			}
			System.out.print("  " + userPlaces[1][2] + compPlaces[1][2] + "  ");
			System.out.println("");
			
			System.out.print("\t");
			//Loop to print horizontal line of grid
			for(int i = 0; i < 20; i++)
			{
				System.out.print("-");
			}
			System.out.println("");
			
			System.out.print("\t");
			//Loop to print cells on third row of grid
			for(int i = 0; i < 2; i++)
			{
				System.out.print("  " + userPlaces[2][i] + compPlaces[2][i] + "  |");
			}
			System.out.print("  " + userPlaces[2][2] + compPlaces[2][2] + "  ");
			System.out.println("");
			System.out.println("\n=======================================");
		}
		else
		{
			//Dont print grid
		}
	}
	
	/**Method to check if user has won
	* @param userPlaces 2 dimensional array with all the user's places in it
	* 
	* @return boolean return to know if user has won or not
	*/
	public static boolean checkUserWin(char[][] userPlaces)
	{
		boolean win = false;
		//Loop to check if user has any vertical or horizontal lines
		for(int i = 0; i < 3; i++)
		{
			if(userPlaces[0][i] == 'O' && userPlaces[1][i] == 'O' && userPlaces[2][i] == 'O')
			{
				userScore++;
				System.out.println("\t\t**You Win!**");
				win = true;
			}
			else if(userPlaces[i][0] == 'O' && userPlaces[i][1] == 'O' && userPlaces[i][2] == 'O')
			{
				userScore++;
				System.out.println("\t\t**You Win!**");
				win = true;
			}
		}
		//If statement to check if user has any diagonal lines
		if(userPlaces[0][0] == 'O' &&  userPlaces[1][1] == 'O' && userPlaces[2][2] == 'O')
		{
			userScore++;
			System.out.println("\t**You Win!**");
			win = true;
		}
		else if(userPlaces[0][2] == 'O' &&  userPlaces[1][1] == 'O' && userPlaces[2][0] == 'O')
		{
			userScore++;
			System.out.println("\t**You Win!**");
			win = true;
		}
		return win;
	}
	
	/**Method to check in computer has won
	* 
	* @param compPlaces 2 dimensional array with all the copmuter's places in it
	* @param tieGame boolean variable to know if there has been a tie or not 
	* 
	* @return boolean return to know if computer has won or not
	*/
	public static boolean checkCompWin(char[][] compPlaces, boolean tieGame)
	{
		boolean win = false; //Will become true is computer has won
		//Loop to check if computer has any vertical or horizontal lines
		if(tieGame == false)
		{
			for(int i = 0; i < 3; i++)
			{
				if(compPlaces[0][i] == 'X' && compPlaces[1][i] == 'X' && compPlaces[2][i] == 'X') //Horizontal lines
				{
					compScore++;
					System.out.println("\t\tThe Computer Won!");
					win = true;
				}
				else if(compPlaces[i][0] == 'X' && compPlaces[i][1] == 'X' && compPlaces[i][2] == 'X') //Vertical lines
				{
					compScore++;
					System.out.println("\t\tThe Computer Won!");
					win = true;
				}
			}
			//If statement to check if computer has any diagonal lines
			if(compPlaces[0][0] == 'X' &&  compPlaces[1][1] == 'X' && compPlaces[2][2] == 'X')
			{
				compScore++;
				System.out.println("\t\tThe Computer Won!");
				win = true;
			}
			else if(compPlaces[0][2] == 'X' &&  compPlaces[1][1] == 'X' && compPlaces[2][0] == 'X')
			{
				compScore++;
				System.out.println("\t\tThe Computer Won!");
				win = true;
			}
		}
		else
		{
			win = false;
		}
		return win;
	}
	
	
	/**Method to check if a tie has happened
	* 
	* @param compPlaces 2 dimensional array with all the copmuter's places in it
	* @param userPlaces 2 dimensional array with all the user's places in it
	* @param userWin boolean variable to know if the user has won or not
	* @param compWin boolean variable to know if the copmuter has won or not
	* @param tieGame boolean variable to know if there has been a tie or not 
	* 
	* @return boolean return to know if there has been a tie or not
	*/
	public static boolean checkTie(char[][] userPlaces, char[][] compPlaces, boolean userWin, boolean compWin, boolean tieGame)
	{
		int takenPlaces = 0; //Variable to keep track of how many spacs are taken
		//The following code will check to see if all the spaces are taking, resulting in a tie
		if(userWin == false && compWin == false)
		{
			//Nested loop to check each cell if it is taken or not
			for(int i = 0; i < userPlaces.length; i++)
			{
				for(int j = 0; j < userPlaces[i].length; j++)
				{
					if(userPlaces[i][j] == 'O' || compPlaces[i][j] == 'X')
					{
						takenPlaces++;
					}
				}
			}
		}
		if(takenPlaces >= 9) //If there are 9 taken cells, and nobody has won, it is a tie
		{
			if(tieGame == false) //This will make sure the tie game message won't print if it already has
			{
				System.out.println("\t\tTie Game!");
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**Method to ask the user if they would like to play again
	* 
	* @return boolean variable to see if user wants to repeat or not
	*/
	public static boolean repeatProgram()
	{
		//Scanner object for getting user input
		Scanner in = new Scanner(System.in);
		boolean askAgain; //will become true if user puts an invalid answer 
		char userAns; //Variable to store user's answer
		boolean repeatChoice = false;
		do
		{
			askAgain = false; //Will become true if user decides to repeat program
			System.out.println("Would you like to play again? (y) or (n)");
			userAns = in.next().charAt(0);
			if(userAns == 'y')
			{
				repeatChoice = true;
			}
			else if(userAns == 'n')
			{
				repeatChoice = false;
			}
			else
			{
				System.out.println("Invalid answer");
				askAgain = true;
			}
		}
		while(askAgain == true);
		return repeatChoice;
	}
}
