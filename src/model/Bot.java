package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class represents an artificial player in a card game, inheriting from the Player class,can place bets and play its turn according to specific rules.
 */
public class Bot extends Player {

    /** The current bet placed , represented as a list of Chips. */
    private ArrayList<Chips> currentBet;

    /**
     * Constructs a Bot with a given name.
     *
     * @param name
     */
    public Bot(String name) {
        super(name);
        currentBet = new ArrayList<>();
    }

    /**
     * Places a bet by randomly selecting a Chips value and adding it to the current bet
     */
    public void placeBet() {
        Chips[] bets = Chips.values();
        Random random = new Random();
        int betIndex = random.nextInt(bets.length);
        currentBet.add(bets[betIndex]);
    }

    /**
     * plays its turn by drawing cards from the deck until the value of its hand is at least 17.
     *
     * @param deck
     */
    public void playTurn(Deck deck) {
        while (calculateHandValue(getHand()) < 17) {
            super.receiveCard(deck.drawCard());
        }
    }

    /**
     * Returns a string representation, name and current bet
     *
     * @return a string in the format "name:currentBet".
     */
    @Override
    public String toString() {
        return getName() + ":" + currentBet;
    }

    /**
     * Retrieves the name
     *
     * @return String name
     */
    @Override
    public String getName() {
        return super.name;
    }

    /**
     * Retrieves the current bet
     * @return the current bet as a list of Chips.
     */
    public ArrayList<Chips> getCurrentBet() {
        return currentBet;
    }
}
