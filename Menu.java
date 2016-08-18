import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */

/**
 * @author Muna Gurung <munagrg126@gmail.com>
 *
 */
public class Menu {

	// needed for user input
	private static Scanner scanner = new Scanner(System.in);

	// variable that keeps tracks of number of players created
	private static int numberOfPlayerObjects = 0;

	// boolean variable that keeps verification record of whether the user
	// input a maximum of 4 number of players to add in the game
	private static boolean isValidNumberOfPlayersToAdd = true;

	// variable that holds the list of player name in an array
	private static ArrayList<Player> playerList;

	private static ArrayList<Player> scoreBoard;

	// constant variable that defines the maximum number of players that can be
	// added in the game
	private static final int MAX_NUMBER_OF_PLAYER = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// start console by calling the main menu method
		MainMenu();
	}

	// method that displays the main menu of the game
	public static void MainMenu() {
		// Welcome message
		System.out.println("Hello there!\n\n" + "Welcome to 10-pin Bowling Game console which keeps record of"
				+ " the game score.\nHope you enjoy your time here~\n"
				+ "\n***********************************************************" + "\nMain menu\n"
				+ "-----------------------------------------------------------\n" + "1 = New game\n"
				+ "2 = Player history(not implemented yet)\n" + "Please type the appropriate number:");
		int menuOption;

		// retrieve and check user input for menu selection
		try {
			menuOption = scanner.nextInt();

			switch (menuOption) {
			case 1:
				NewGameMenu();
				break;
			case 2:
				PlayerHistoryMenu();
				break;
			default:
				System.out.println("Sorry, that input is not valid!");
				break;
			}
		} catch (Exception ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// method that displays the menu options for a new game
	public static void NewGameMenu() {
		System.out.println("\n***************************************************" + "\nNew game\n"
				+ "----------------------------------------------\n" + "1 = Add new player(Max. 4 players) \n"
				+ "2 = Choose existing player(not implemented yet)\n" + "Please type the appropriate number:");
		int menuOption;

		// retrieve and check user input for menu selection
		try {
			menuOption = scanner.nextInt();
			switch (menuOption) {
			case 1:
				AddPlayerMenu();
				break;
			case 2:
				// select player menu needs to be created and called
				break;
			default:
				MainMenu();
				break;
			}
		} catch (Exception ex) {
			IntegerInputErrorMessage();
			// Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null,
			// ex);
		}
	}

	// method that displays the menu options for player history
	public static void PlayerHistoryMenu() {
		System.out.println("You chose to view player history");
	}

	// method that displays an error message when an incorrect integer is
	// provided by the user
	public static void IntegerInputErrorMessage() {
		System.out.println("Sorry, that input is not valid! Only number" + " is allowed");
	}

	// menu that displays the menu options when adding new players
	public static void AddPlayerMenu() {
		// asks user for number of players to add in the game
		System.out.println("\n***************************************************" + "\nAdd player\n"
				+ "----------------------------------------------\n"
				+ "How many new players would you like to add?(Maximum of 4 " + "players can be added) "
				+ numberOfPlayerObjects + " players exist");

		// retrieve user input
		int numberOfPlayersToAdd = scanner.nextInt();

		// validate user input for number of players to add
		try {
			// check if user has input maximum of 4 players
			isValidNumberOfPlayersToAdd(numberOfPlayersToAdd);

			// create the required number of players if the input is valid
			if (isValidNumberOfPlayersToAdd) {
				// create an arraylist to add player
				playerList = new ArrayList<Player>();

				// counter for while loop
				int i = 1;

				// loop should run until the int i is less than or equal to
				// the required number of players to add
				while (i <= numberOfPlayersToAdd) {

					// message prompting user to enter the player name
					System.out.println("\n***************************************************" + "\nAdd player- "
							+ numberOfPlayerObjects + " player exists\n"
							+ "----------------------------------------------\n" + "Please enter player name: ");

					// get user input for player name
					String playerName = scanner.next();

					// create new player and add it into the player array list
					playerList.add(new Player(playerName));

					// increment the number of player and i count by 1
					numberOfPlayerObjects++;
					i++;
				}
				// call the method to start the game after players are created
				StartGame();
			}

		} catch (Exception ex) {
			System.out.println(isValidNumberOfPlayersToAdd);
			IntegerInputErrorMessage();
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// method that starts the game
	private static void StartGame() {
		// create algorithm for the game
		System.out.println("\n\n**********************************************"
				+ "\n10-pin Bowling Game scoring has started!\n\n" + "The following players has entered the game.\n");

		// loop to show the name of the players that has entered the game
		int i = 0;
		while (i < numberOfPlayerObjects) {
			System.out.println(playerList.get(i).getPlayerName());
			i++;
		}
		// option to end game early
		// implement this by checking if user enters q, MainMenu() should be
		// called so that
		// the user can go back to main menu, thus ending the game
		System.out.println("Please type q to quit the game early when scoring.\n");
		int x;// loop counter
		/*
		 * Game game= new Game(); game.promptUser(); game.CalculateFrameScore();
		 */
		//
		for (x = 0; x < numberOfPlayerObjects; x++) {
			// first frame
			System.out.println("************************************************\n" + playerList.get(x).getPlayerName()
					+ "'s turn\n" + "------------------------------------------------\n");
			// pinsDown = scanner.nextInt(); // get user input for ball 1
			Game game = new Game();
			game.promptUser();
			game.CalculateFrameScore();
		}
	}

	// method that checks whether the user is adding a maximum of 4 players
	public static boolean isValidNumberOfPlayersToAdd(int numberOfPlayersToAdd) {
		return numberOfPlayersToAdd < 5 && numberOfPlayersToAdd > 0;
	}

}
