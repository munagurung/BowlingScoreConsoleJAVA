import java.util.Scanner;

/**
 * @author Muna Gurung <munagrg126@gmail.com>
 *
 */

public class Game {

	private int currentFrameNumber = 1; // define and intialize the
	// current frame number
	private int score = 0; // score always starts from 0 and increases
	// by the amount of pins knocked down
	private int frameBalls[][] = new int[12][2]; // 12 rows and 2 columns for
													// frameballs
	private int frameScore[] = new int[10]; // array to hold total score per
											// frame
	private Scanner scanner = new Scanner(System.in);

	/**
	 * @return the currentFrameNumber
	 */
	private int getCurrentFrameNumber() {
		return currentFrameNumber;
	}

	/**
	 * @param currentFrameNumber
	 *            the currentFrameNumber to set
	 */
	private void setCurrentFrameNumber(int currentFrameNumber) {
		this.currentFrameNumber = currentFrameNumber;
	}

	/**
	 * @return the frameBalls
	 */
	private int[][] getFrameBalls() {
		return frameBalls;
	}

	/**
	 * @param frameBalls
	 *            the frameBalls to set
	 */
	private void setFrameBalls(int frameBalls[][]) {
		this.frameBalls = frameBalls;
	}

	/**
	 * @return the frameScore
	 */
	private int[] getFrameScore() {
		return frameScore;
	}

	/**
	 * @param frameScore
	 *            the frameScore to set
	 */
	private void setFrameScore(int frameScore[]) {
		this.frameScore = frameScore;
	}

	public void promptUser() {
		int frame = 0;
		int ball = 0;
		// game should run through 10 frames
		for (frame = 0; frame < 10; frame++) {
			int frameNumber = frame + 1;
			System.out.println("FRAME " + frameNumber + "\n");
			// player should be allowed to throw two balls per frame
			for (ball = 0; ball < 2; ball++) {
				int ballNumber = ball + 1;
				System.out.println("Ball " + ballNumber);
				// keep track of pins knocked down by the player
				int pinsDown;
				// retrieve player input
				pinsDown = scanner.nextInt();

				// different code for last frame because of 2 or 1 extra ball if
				// strike or spare
				if (frame != 9) {
					// check if player did a strike/ spare/ open frame/ any
					// number
					// of pins
					if (pinsDown == 10 && ball == 0) // if strike
					{
						frameBalls[frame][ball] = pinsDown; // save the pinned
															// down balls number
															// in the frameBalls
															// array
						System.out.println("Great ! It is a strike!!\n");
						ball = 3; // change counter of ball to 3 as the player
									// needs
									// to move onto next frame
					}
					// if any number 0-10
					else if (pinsDown >= 0 && pinsDown <= 10 && ball == 1) {
						frameBalls[frame][ball] = pinsDown;
						System.out.println("Nice.\n");
					} else if (pinsDown >= 0 && pinsDown < 10 && ball == 0) {
						frameBalls[frame][ball] = pinsDown;
						System.out.println("Nice.\n");
					}
				} else {
					// in last frame, player can strike for 2 more balls, can
					// score spare
					// number 0-9, last frame
					if (pinsDown >= 0 && pinsDown < 10) {
						frameBalls[frame][ball] = pinsDown;
						System.out.println("Nice.\n");
					}
					// spare at last frame
					else if (pinsDown == 10 && ball == 1) {

						frameBalls[frame][ball] = pinsDown;
						System.out.println("Frameballs score is " + frameBalls[frame][ball]);
					} else if (pinsDown == 10 && ball == 0) // if strike
					{
						frameBalls[frame][ball] = pinsDown;
						boolean extra = true;
						while (extra) {
							System.out.println("Extra Ball 1:");
							// retrieve player input
							pinsDown = scanner.nextInt();
							frameBalls[10][ball] = pinsDown;
							System.out.println("Extra Ball 2:");
							pinsDown = scanner.nextInt();
							frameBalls[10][1] = pinsDown;
							extra = false;
						}
						ball = 3;
					}
				}
			}
		}
	}

	// calculate the total score per frame
	public void CalculateFrameScore() {
		int frameBallsIndex; // frame index number
		int ball = 0; // ball value will be either 0 or 1,0 being 1st ball and 1
						// being second ball thrown

		// frameBalls[x][] where x = startIndex
		// needed to use inside the loop as the original index counter must not
		// be changed
		int startIndex;

		// temporary variables for frameBallsIndex
		int frameBallsIndexTemp;
		int tempFrame;

		int spareBonus, ball1, ball2, strikeBonus1, strikeBonus2; // holds
																	// scores

		// Go through the frameBalls, calculate the frame score and save them
		// inside frameScore array
		for (frameBallsIndex = 0; frameBallsIndex < 10; frameBallsIndex++) {
			// assign frameBallsIndex to startIndex so that we can start using
			// the index number without altering the loop counter number
			// do the same with other temporary variables
			startIndex = frameBallsIndex;
			frameBallsIndexTemp = frameBallsIndex; // temporary frameBallsIndex
			tempFrame = frameBallsIndex;

			// increment and decrement, temporary variables created as the
			// original loop index
			// must not be changed when used inside
			int incrementFrameBallsIndexTemp = frameBallsIndexTemp + 1;
			int incrementFrameBallsIndexTempByTwo = frameBallsIndexTemp + 2;
			int decrementTempFrame = tempFrame - 1;
			int incrementStartIndex = startIndex + 1;
			int incrementStartIndexByTwo = startIndex + 2;

			// if strike
			if (frameBalls[startIndex][ball] == 10) {
				ball1 = frameBalls[frameBallsIndexTemp][ball];
				// three/ two strike
				if (((frameBalls[incrementStartIndex][ball] == 10)
						&& (frameBalls[incrementStartIndexByTwo][ball] == 10))
						|| ((frameBalls[incrementStartIndex][ball] == 10)
								&& ((frameBalls[incrementStartIndexByTwo][ball] != 10)))) {
					strikeBonus1 = frameBalls[incrementFrameBallsIndexTemp][ball];
					strikeBonus2 = frameBalls[incrementFrameBallsIndexTempByTwo][ball];
					// strike at first frame
					if (frameBallsIndex == 0) {
						frameScore[frameBallsIndex] = ball1 + strikeBonus1 + strikeBonus2;
						/*
						 * System.out.println("frame score for frame 1 or " +
						 * frameBallsIndexTemp + " is " +
						 * frameScore[frameBallsIndex]);
						 */
					} else if (frameBallsIndex != 9 && frameBallsIndex > 0 && frameBallsIndex < 12) {
						int scoreIndex = frameScore[decrementTempFrame];
						frameScore[frameBallsIndex] = scoreIndex + ball1 + strikeBonus1 + strikeBonus2;
						/*
						 * System.out.
						 * println("3 strike after first time for frame " +
						 * frameBallsIndex + " is " +
						 * frameScore[frameBallsIndex]);
						 */
					} // strike at last frame
					else if (frameBallsIndex == 9) {
						strikeBonus2 = frameBalls[incrementFrameBallsIndexTemp][1];
						frameScore[frameBallsIndex] = frameScore[decrementTempFrame] + ball1 + strikeBonus1
								+ strikeBonus2;
						/*
						 * System.out.println("frame score strike last frame " +
						 * frameBallsIndexTemp + " is " +
						 * frameScore[frameBallsIndex]);
						 */ }
				}

				// one strike
				else if (frameBalls[incrementStartIndex][ball] != 10 || frameBallsIndex == 9) {
					strikeBonus1 = frameBalls[incrementFrameBallsIndexTemp][ball];
					strikeBonus2 = frameBalls[incrementFrameBallsIndexTemp][1];
					if (frameBallsIndex == 0) {
						frameScore[frameBallsIndex] = ball1 + strikeBonus1 + strikeBonus2;
						/*
						 * System.out.println("frame score for frame 1 or " +
						 * frameBallsIndexTemp + " is " +
						 * frameScore[frameBallsIndex]);
						 */
					} else if (frameBallsIndex > 0 && frameBallsIndex < 12) {
						frameScore[frameBallsIndex] = frameScore[decrementTempFrame] + ball1 + strikeBonus1
								+ strikeBonus2;
						/*
						 * System.out.println("frame score for last frame " +
						 * frameBallsIndexTemp + " is " +
						 * frameScore[frameBallsIndex]);
						 */
					}
				}
			}
			// if spare
			else if (frameBalls[frameBallsIndex][0] + frameBalls[frameBallsIndex][1] == 10
					&& frameBalls[frameBallsIndex][0] != 10) {
				ball1 = frameBalls[frameBallsIndex][0];
				ball2 = frameBalls[frameBallsIndex][1];
				spareBonus = frameBalls[incrementFrameBallsIndexTemp][0];
				// first frame spare
				if (frameBallsIndex == 0) {
					frameScore[frameBallsIndex] = ball1 + ball2 + spareBonus;
					/*
					 * System.out.println("Spare score first time for frame " +
					 * frameBallsIndex + " is " + frameScore[frameBallsIndex]);
					 */
				} else if (frameBallsIndex > 0 && frameBallsIndex < 12) {
					frameScore[frameBallsIndex] = frameScore[decrementTempFrame] + ball1 + ball2 + spareBonus;
					/*
					 * System.out.
					 * println("Spare score after first time for frame " +
					 * frameBallsIndex + " is " + frameScore[frameBallsIndex]);
					 */
				}

			}
			// if no strike and spare
			else if (frameBalls[frameBallsIndex][0] + frameBalls[frameBallsIndex][1] != 10) {
				ball1 = frameBalls[frameBallsIndex][0];
				ball2 = frameBalls[frameBallsIndex][1];
				// first frame
				if (frameBallsIndex == 0) {
					frameScore[frameBallsIndex] = ball1 + ball2;
					/*
					 * System.out.
					 * println("no spare or strike first frame for frame " +
					 * frameBallsIndex + " is " + frameScore[frameBallsIndex]);
					 */
				} else if (frameBallsIndex > 0 && frameBallsIndex < 12) {
					frameScore[frameBallsIndex] = frameScore[decrementTempFrame] + ball1 + ball2;
					/*
					 * System.out.
					 * println("no spare or strike first frame for frame " +
					 * frameBallsIndex + " is " + frameScore[frameBallsIndex]);
					 */
				}
			}
			// loop through frame score to display
		}

		for (int i = 0; i < frameScore.length; i++) {
			int frame = i + 1;
			System.out.println("Total frame score for frame " + frame + " is " + frameScore[i]);
		}
		System.out.println("\n");
	}
}
