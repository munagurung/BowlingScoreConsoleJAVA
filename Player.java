
/**
 * @author Muna Gurung <munagrg126@gmail.com>
 *
 */
public class Player {

	// declare variables
	private String playerName;
	private double lifetimeAverageScore;
	private int lifetimeBallsBowled;

	// constructor that creates a player object
	public Player(String playerName) {
		this.setPlayerName(playerName);
	}

	// constructor for player's lifetime game score stats
	public Player(String playerName, double lifetimeAverageScore, int lifetimeBallsBowled) {
		this.setPlayerName(playerName);
		this.setLifetimeAverageScore(lifetimeAverageScore);
		this.setLifetimeBallsBowled(lifetimeBallsBowled);
	}

	// method to get lifetime average score for a player
	public double CalculateLifetimeAverageScore() {
		// calculate this by: total score / total games played
		return 0;
	}

	public int CalculateLifetimeBallsBowled() {
		// calculate this by totalling balls bowled
		return 0;
	}

	// method to get player name
	public String getPlayerName() {
		return playerName;
	}

	private void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the lifetimeBallsBowled
	 */
	private int getLifetimeBallsBowled() {
		return lifetimeBallsBowled;
	}

	/**
	 * @param lifetimeBallsBowled
	 *            the lifetimeBallsBowled to set
	 */
	private void setLifetimeBallsBowled(int lifetimeBallsBowled) {
		this.lifetimeBallsBowled = lifetimeBallsBowled;
	}

	/**
	 * @return the lifetimeAverageScore
	 */
	private double getLifetimeAverageScore() {
		return lifetimeAverageScore;
	}

	/**
	 * @param lifetimeAverageScore
	 *            the lifetimeAverageScore to set
	 */
	private void setLifetimeAverageScore(double lifetimeAverageScore) {
		this.lifetimeAverageScore = lifetimeAverageScore;
	}

}
