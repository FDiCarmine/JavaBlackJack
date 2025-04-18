package model;

import java.util.*;

@SuppressWarnings("deprecation")
public class GameManager extends Observable {

	private static GameManager instance;
	private GameState gameState;
	private boolean restartBool;
	/**
	 * Private constructor for Singleton pattern.
	 * Initializes the GameState with 8 decks and 4 players.
	 */
	private GameManager() {
		//INIZIALIZZAZIONE DI BASE DEL GAMESTATE 8 MAZZI E  4 GIOCATORI
		this.gameState = new GameState(8, 4,"UNDEF");

	}
	
	/**
	 * Returns the singleton instance of the GameManager.
	 * @return the GameManager instance
	 */
	
	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	/**
	 * Gets the current human player.
	 * @return the HumanPlayer instance
	 */
	public HumanPlayer getHumanPlayer() {
		return gameState.getHumanPlayer();
	}

	/**
	 * Enum representing observer messages.
	 */
	public enum Message {
		
		INITIAL_CARDS_DEALT,
		BOTS_PLACED_INITIAL_BETS,
		HUMAN_PLACED_BET,
		INITIAL_CARD_DEALT,
		PLAYER_FIRST_HAND_UPDATE,
		BOTS_PLAYED_TURN,
		PLAYER_SPLIT_CARDS,
		PLAYER_SECOND_HAND_UPDATE,
		PLAYER_BET_MISSING,
		EXIT_REQUEST,
		GO_SETTINGS,
		PLAYER_SKIP_SPECIAL_ROUND,
		PLAYER_SKIP_DOUBLE_ROUND,
		PRINT_ROUND_SOLVER,
		UPDATE_SCORE_BOARD, 
		RESET_SCORE_BOARD, 
		
	}
	// --METODI INCAPSULATI DA GAMESTATE
	
	/**
	 * Places the human player's bet.
	 * @param chip the chip to bet
	 */
	public void placeHumanBet(Chips chip)  {
		gameState.placeHumanBet(chip);
		setChanged();
		notifyObservers(Message.HUMAN_PLACED_BET);
	}
	/**
	 * Causes all bot players to place their initial bets.
	 */
	public void dealInitialBets() {
		gameState.dealInitialBets();
		setChanged();
		notifyObservers(Message.BOTS_PLACED_INITIAL_BETS);

	}
	/**
	 * Deals initial cards to all players.
	 */
	public void dealInitialCards() {
		gameState.dealInitialCards();
		setChanged();
		notifyObservers(Message.INITIAL_CARDS_DEALT);

	}


	/**
	 * Prepares the human player's hand for splitting.
	 */
	public void setUpSplitHand() {
		gameState.setUpSplitHand();
		setChanged();
		notifyObservers(Message.PLAYER_SPLIT_CARDS);

	}
	/**
	 * Executes all bot players' turns.
	 */
	public void playBotTurns() {
		gameState.playBotsTurn();
		setChanged();
		notifyObservers(Message.BOTS_PLAYED_TURN);
	}
	/**
	 * Checks whether the human player is ready to start the next round.
	 * @return true if ready, false otherwise
	 */
	public boolean isHumanReadyToStartRound() {
		return gameState.isHumanReadyToStartRound();
	}

	public void setIsHumanReadyToStartRound(boolean isHumanReadyToStartRound) {
		gameState.setIsHumanReadyToStartRound(isHumanReadyToStartRound);
	}
	/**
	 * Sets whether the player chooses to stand.
	 * @param status true to stand, false otherwise
	 */
	public void setPlayerStands(boolean status) {
		gameState.setPlayerStand(status);
	}

	/**
	 * Determines if the round should end after the player's first hand.
	 */
	public void determinePlayingStatusNormal() {
		if (gameState.getHumanPlayer().isBusted() || isPlayerStand()) {
			setPlayerStands(true);
		}
	}
	/**
	 * Determines if the round should end after the second (split) hand.
	 */
	public void determinePlayingStatusSplit() {
		if (gameState.getHumanPlayer().isSplitBusted(getHumanPlayer().getSecondHand()) || isPlayerStand()) {

		}
	}
	/**
	 * Checks if the player has stood on the second hand.
	 * @return true if stood, false otherwise
	 */
	public boolean isSecondHandPlayerStand() {
		return gameState.isSecondHandPlayerStand();
	}
	/**
	 * Sets whether the player has stood on the second hand.
	 * @param isSecondHandPlayerStand status of standing
	 */
	public void setSecondHandPlayerStand(boolean isSecondHandPlayerStand) {
		gameState.setSecondHandPlayerStand(isSecondHandPlayerStand);
	}
	/**
	 * Checks if the player has stood on the first hand.
	 * @return true if stood, false otherwise
	 */
	public boolean isPlayerStand() {
		return gameState.isPlayerStand();
	}
	/**
	 * Deals a card to the human player's first hand.
	 */
	public void humanReceiveCard() {
		gameState.humanReceiveCard();
		setChanged();
		notifyObservers(Message.PLAYER_FIRST_HAND_UPDATE);

	}
	/**
	 * Resolves all bets at the end of a round.
	 */
	public void resolveBets() {
		gameState.resolveBets();

	}
	/**
	 * Clears all players' hands.
	 */
	public void HandCleaner() {
		gameState.clearHands();
	}
	/**
	 * Sets the number of decks used and restarts the deck.
	 * @param n number of decks
	 */
	public void setNumberOfDecks(int n) {
		gameState.setNumberOfDecks(n);
		restartDeck();
	}
	/**
	 * Performs the double bet move for the human player.
	 */
	public void humanDoubleRound() {

		humanReceiveCard();
		gameState.setUpDoubleRoundBet();

	}
	
	/**
	 * Deals a card to the human player's second hand.
	 */
	public void humanRecevieSecondHandCard() {
		gameState.humanReceiveSecondHandCard();
		setChanged();
		notifyObservers(Message.PLAYER_SECOND_HAND_UPDATE);
	}
	/**
	 * Sets whether the human is currently playing the second hand.
	 * @param status true if playing second hand
	 */
	public void setPlayingOnSecondHand(boolean status) {
		gameState.getHumanPlayer().setPlayingOnSecondHand(status);

	}
	/**
	 * Notifies observers that the player attempted to start without placing a bet.
	 */
	public void noBetPlaced() {
		setChanged();
		notifyObservers(Message.PLAYER_BET_MISSING);
	}
	/**
	 * Notifies observers that the exit button was pressed.
	 */
	public void exitButtonRequest() {
		setChanged();
		notifyObservers(Message.EXIT_REQUEST);
	}
	
	/**
	 * Restarts the deck with current settings.
	 */
	public void restartDeck() {
		gameState.restartDeck();
	}
	
	
	
	
	//*********************************************************
	//CHE CAZZO E
/*
	public void switchToPlayRequest() {
		setChanged();
		notifyObservers(Message.SWITCH_TO_PLAY);
	}
*/
	//************************************************************
	
	
	
	
	
	
	
	/**
	 * Changes the number of players and reinitializes the game state.
	 * @param numberOfPlayers the number of players
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		
		int nod = gameState.getNumberOfDecks();
		String hn = gameState.getHumanPlayer().getName();
		gameState.setNumberOfPlayers(numberOfPlayers);
		gameState = new  GameState(nod,numberOfPlayers,hn);

	}

	public void switchToSettingsRequest() {
		setChanged();
		notifyObservers(Message.GO_SETTINGS);
	}
	/**
	 * Notifies observers to skip the split hand round.
	 */
	public void playerSkipSpecialRound() {
		setChanged();
		notifyObservers(Message.PLAYER_SKIP_SPECIAL_ROUND);

	}
	/**
	 * Notifies observers to skip the double bet round.
	 */
	public void playerSkipDouble() {
		setChanged();
		notifyObservers(Message.PLAYER_SKIP_DOUBLE_ROUND);
	}
	
	/**
	 * Notifies observers to update and display round results.
	 */
	public void updateRoundSolver() {
		setChanged();
		notifyObservers(Message.PRINT_ROUND_SOLVER);

	}
	/**
	 * Returns whether the game is in a restart state.
	 * @return true if restarting, false otherwise
	 */
	public boolean isRestarting() {
		return restartBool;
	}
	/**
	 * Sets whether the game is in a restart state.
	 * @param status true if restarting
	 */
	public void setRestarting(boolean status) {
		this.restartBool = status;
	}
	/**
	 * Gets a list of all players in the game.
	 * @return list of Player objects
	 */
	public List<Player> getPlayers() {
		return gameState.getPlayers();
	}
	/**
	 * Checks if a  user nickname must be set.
	 * @return true if nickname is needed
	 */
	public boolean isNicknameToSet() {
		return gameState.isNicknameToSet();
	}
	/**
	 * Gets the outcome of the current round for all players.
	 * @return list of result strings
	 */
	public List<String> getRoundSolver() {
		return gameState.getRoundSolver();
	}
	
	/**
	 * Saves the human player's score.
	 */
	public void saveScore() {
		gameState.saveScore();
		setChanged();
		notifyObservers(Message.UPDATE_SCORE_BOARD);
	}
	
	/**
	 * Resets the scoreboard.
	 */
	public void resetScore() {
		gameState.resetScore();
		setChanged();
		notifyObservers(Message.RESET_SCORE_BOARD);
		
	}
	

}
