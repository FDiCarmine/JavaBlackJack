package model;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

	/**
	 * Represents the state of a Blackjack game, managing players, the deck, and 
	 * game logic, including betting and turn resolution.
	 */
public class GameState {

	/** The number of decks used in the game. */
	private int numberOfDecks;

	/** The number of players in the game. */
	private int numberOfPlayers;

	@SuppressWarnings("unused")
	private String humanName;
	/** The deck of cards used in the game. */
	private Deck deck;

	/** The dealer in the game. */
	private Dealer dealer;

	/** The list of players in the game, including bots, human player, and dealer */
	private List<Player> players;

	/** The human player in the game */
	private HumanPlayer human;

	/**
	 */
	private ScoreBoardManager sbm;

	private boolean isHumanReadyToStartRound;
	private boolean isPlayerStand;
	private boolean isSecondHandPlayerStand;

	 /** Constant representing a player win result. */
    public static final String PLAYERWIN = "PLAYERWIN";

    /** Constant representing a player loss result. */
    public static final String PLAYERLOST = "PLAYERLOST";

    /** Constant representing a tie result. */
    public static final String TIE = "TIE";

	private ArrayList<String> roundSolver = new ArrayList<>();

	/**
	 * Constructs a GameState with the specified number of decks and players, and
	 * initializes the human player The first player is always the human, and the
	 * last player is always the dealer
	 *
	 * @param numberOfDecks   the number of decks used in the game
	 * @param numberOfPlayers the number of players in the game
	 * @param humanName       the name of the human player
	 */
	public GameState(int numberOfDecks, int numberOfPlayers, String humanName) {

		this.numberOfDecks = numberOfDecks;
		this.deck = new Deck(numberOfDecks);
		this.dealer = new Dealer();
		players = createBotPlayers(numberOfPlayers);
		players.addLast(dealer); // The last player is always the dealer
		this.humanName = humanName;
		human = new HumanPlayer(humanName);
		players.addFirst(human); // The first player is always the human
		this.sbm = new ScoreBoardManager();
	}

	/**
	 * getter for the class HumanPlayer, return the field of the class GameState
	 *
	 * @return HumanPlayer human
	 */
	public HumanPlayer getHumanPlayer() {
		return human;
	}

	/**
	 * method used to place bets for the human player
	 *
	 * @param chip takes a chip as input and calls the method placeHumanBet by the
	 *             class HumanPlayer
	 */
	public void placeHumanBet(Chips chip) {
		human.placeHumanBet(chip);
	}

	/**
	 * Private method that Creates a list of artificial players to populate the
	 * players list
	 *
	 * @param n the number of artificial players to create
	 * @return a list of Bot players
	 */
	private List<Player> createBotPlayers(int n) {
		return IntStream.range(0, n).mapToObj(i -> new Bot("bot" + ("(" + (i + 1) + ")")))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * deals the initial bets for artificial players this method must be used after
	 * the human player placed is own bet in a game loop
	 */
	protected void dealInitialBets() {
		players.stream().filter(player -> player instanceof Bot).forEach(player -> ((Bot) player).placeBet());
	}

	/**
	 * Deals the initial cards to each player Each player receives two cards from
	 * the deck
	 */
	protected void dealInitialCards() {

		IntStream.range(0, 2).forEach(i -> {
			players.forEach(p -> {
				p.receiveCard(deck.drawCard());
			});
		});
	}
	/**
	 * Deals the initial cards to each player Each player receives two cards from
	 * the deck
	 */
	protected void playBotsTurn() {
		players.stream().forEach(x -> x.playTurn(deck));
	}
	/**
     * Sets the number of decks for the game
     * @param n the number of decks
     */
	protected void setNumberOfDecks(int n) {
		this.numberOfDecks = n;
		deck.setNumberOfDecks(n);

	}
	 /**
     * Gives the human player another card
     */
	protected void humanReceiveCard() {
		human.receiveCard(deck.drawCard());
	}
	/**
     * Gives the human player another card for the second hand
     */
	protected void humanReceiveSecondHandCard() {
		human.receiveSecondHandCard(deck.drawCard());
	}
	/**
    * Checks if the player has chosen to stand
    * @return true if the player has chosen to stand, false otherwise
    */
	protected boolean isPlayerStand() {
		return isPlayerStand;
	}

	/**
     * Splits the human player's hand into two separate hands
     */
	protected void setUpSplitHand() {
		human.getSecondHand().add(human.getHand().removeLast());
		ArrayList<Chips> bets = new ArrayList<Chips>();
		bets.addAll(human.getCurrentBet());
		bets.stream().forEach(x -> human.placeHumanBet(x));

	}
	 /**
     * Doubles the bet for the human player's current round
     */
	protected void setUpDoubleRoundBet() {
		ArrayList<Chips> bets = new ArrayList<Chips>();
		bets.addAll(human.getCurrentBet());
		bets.stream().forEach(x -> human.placeHumanBet(x));

	}
	/**
     * Clears the hands of all players
     */
	protected void clearHands() {
		players.stream().forEach(x -> {
			x.clearHand();
			x.clearSecondHand();
			System.out.println(x.getName() + "    " + x.getHand().size());
		});
	}
	 /**
     * Sets the number of players for the game
     * @param numberOfPlayers the number of players
     */
	protected void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	/**
    * Checks if the player has chosen to stand with their second hand
    * @return true if the player has chosen to stand with their second hand
    */
	protected boolean isSecondHandPlayerStand() {
		return isSecondHandPlayerStand;
	}
	 /**
     * Sets whether the player has chosen to stand with their second hand
     * @param isSecondHandPlayerStand true if the player has chosen to stand
     */
	protected void setSecondHandPlayerStand(boolean isSecondHandPlayerStand) {
		this.isSecondHandPlayerStand = isSecondHandPlayerStand;
	}
	/**
     * Sets whether the player has chosen to stand
     * @param isPlayerStand true if the player has chosen to stand
     */
	protected void setPlayerStand(boolean isPlayerStand) {
		this.isPlayerStand = isPlayerStand;
	}
	/**
     * Checks if the human is ready to start a new round
     * @return true if the human is ready to start
     */
	protected boolean isHumanReadyToStartRound() {
		return isHumanReadyToStartRound;
	}
	/**
     * Sets whether the human is ready to start a new round
     * @param isHumanReadyToStartRound true if the human is ready
     */
	protected void setIsHumanReadyToStartRound(boolean isHumanReadyToStartRound) {
		this.isHumanReadyToStartRound = isHumanReadyToStartRound;
	}
	/**
     * Resolves the bets for the round
     */
	protected void resolveBets() {
		int dealerValue = dealer.calculateHandValue(dealer.getHand());
		boolean dealerBusted = dealer.isBusted();
		HumanPlayer player = getHumanPlayer();

		ArrayList<ArrayList<Card>> hands = new ArrayList<>();
		hands.add(player.getHand());
		if (human.hasChosenToSplit()) {
			hands.add(player.getSecondHand());
		}
		for (ArrayList<Card> hand : hands) {
			int playerValue = player.calculateHandValue(hand);
			if (player.isSplitBusted(hand)) {
				roundSolver.add(PLAYERLOST);
			} else {
				resolveSingleHand(player, hand, playerValue, dealerValue, dealerBusted);
			}
		}

		player.getCurrentBet().clear();

	}
	/**
     * Resolves a single hand for the player, comparing with the dealer
     * @param player       the human player
     * @param hand         the hand to resolve
     * @param playerValue  the value of the player's hand
     * @param dealerValue  the value of the dealer's hand
     * @param dealerBusted true if the dealer is busted
     */
	protected void resolveSingleHand(HumanPlayer player, ArrayList<Card> hand, int playerValue, int dealerValue,
			boolean dealerBusted) {

		int betSum = player.hasChosenToSplit() ? player.getCurrentBet().stream().mapToInt(Chips::getValue).sum() / 2
				: player.getCurrentBet().stream().mapToInt(Chips::getValue).sum();

		if (dealerBusted) {
			if (!player.isSplitBusted(hand)) {
				roundSolver.add(PLAYERWIN);
				player.addChips(betSum * 2);
			} else {
				roundSolver.add(PLAYERLOST);
			}
		} else {
			int comparison = Integer.compare(playerValue, dealerValue);
			switch (comparison) {
			case 1:
				roundSolver.add(PLAYERWIN);
				player.addChips(betSum * 2);
				break;
			case -1:
				roundSolver.add(PLAYERLOST);
				player.getCurrentBet().clear();
				break;
			case 0:
				roundSolver.add(TIE);
				player.addChips(betSum);
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + comparison);
			}
		}
	}
	/**
     * Reinitialize a new deck
     */
	protected void restartDeck() {
		deck.restartDeck();
	}
	/**
     * Returns the results of the current round
     * @return a list of round results
     */
	protected ArrayList<String> getRoundSolver() {
		return roundSolver;
	}
	/**
     * Returns the list of all players in the game
     * @return the list of players
     */
	protected boolean isNicknameToSet() {
		String target = "undef";
		return human.getName().equalsIgnoreCase(target);
	}
	/**
     * Saves the score of the human player
     */
	protected List<Player> getPlayers() {
		return players;
	}
	/**
     * Saves the score of the human player
     */
	protected void saveScore() {
		sbm.saveData(human);
	}
	/**
     * Returns the number of decks in the game
     * @return the number of decks
     */
	protected void resetScore()  {
		try {
			sbm.resetScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
     * Returns the number of decks in the game
     * @return the number of decks
     */
	protected int getNumberOfDecks() {
		return numberOfDecks;
	}
	/**
     * Returns the number of players in the game
     * @return the number of players
     */
	protected int getNumberOfPlayers() {
		return numberOfPlayers;
	}

}
