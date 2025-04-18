package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Chips;
import model.GameManager;
import view.MainView;

/**
 * The ViewListeners class is responsible for handling user interactions with the GUI.
 * It listens for button presses and responds accordingly by invoking methods in the GameManager
 * or updating the view in the MainView. Implements ActionListener to capture and handle events.
 */
public class ViewListeners implements ActionListener {

    // Instance of GameManager to manage game state and logic
    private GameManager gm = model.GameManager.getInstance();
    
    // Instance of MainView to interact with the UI
    private MainView mv;

    /**
     * Constructor for ViewListeners. Initializes MainView.
     */
    public ViewListeners() {
        try {
            mv = MainView.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action when the deal button is pressed.
     * Ensures that the human player has placed a bet before starting the round.
     */
    private void handleDealButtonPress() {
        if (!gm.getHumanPlayer().getCurrentBet().isEmpty()) {
            gm.setIsHumanReadyToStartRound(true);
        } else {
            gm.noBetPlaced();
        }
    }

    /**
     * Handles the action when the hit button is pressed.
     * Deals a card to the player and checks if they can split or double.
     */
    private void handleHitButtonPress() {
        if (gm.getHumanPlayer().isSpecialRound() && !gm.getHumanPlayer().hasChosenToSplit()) {
            gm.playerSkipSpecialRound();
        }
        if (!gm.getHumanPlayer().hasChosenToSplit()) {
            gm.humanReceiveCard();
            gm.playerSkipDouble();
            gm.determinePlayingStatusNormal();
        } else {
            if (!gm.isPlayerStand()) {
                gm.humanReceiveCard();
                gm.determinePlayingStatusNormal();
            } else {
                if (!gm.getHumanPlayer().isSplitBusted(gm.getHumanPlayer().getSecondHand())
                        || !gm.isSecondHandPlayerStand()) {
                    gm.setPlayingOnSecondHand(true);
                    gm.humanRecevieSecondHandCard();
                }
            }
        }
    }

    /**
     * Handles the action when the stand button is pressed.
     * Signals that the player has chosen to stand, either on the main hand or the split hand.
     */
    private void handleStandButtonPress() {
        if (!gm.getHumanPlayer().hasChosenToSplit()) {
            gm.setPlayerStands(true);
        } else {
            if (!gm.isPlayerStand()) {
                gm.setPlayerStands(true);
            } else if (!gm.isSecondHandPlayerStand()) {
                gm.setSecondHandPlayerStand(true);
            }
        }
    }

    /**
     * Handles the action when the split button is pressed.
     * Attempts to split the player's hand if they have sufficient credits to place a second bet.
     */
    private void handleSplitButtonPress() {
        int chipSum = gm.getHumanPlayer().getCurrentBet().stream().mapToInt(Chips::getValue).sum();
        if (chipSum > gm.getHumanPlayer().getRemainingChips()) {
            mv.notEnoughCreditsForSplit();
        } else {
            gm.getHumanPlayer().setHasChosenToSplit(true);
            gm.setUpSplitHand();
        }
    }

    /**
     * Handles the action when the double button is pressed.
     * Attempts to double the player's bet if they have sufficient credits.
     */
    private void handleDoubleButtonPress() {
        int chipSum = gm.getHumanPlayer().getCurrentBet().stream().mapToInt(Chips::getValue).sum();
        if (chipSum > gm.getHumanPlayer().getRemainingChips()) {
            mv.notEnoughCreditsForDouble();
        } else {
            gm.humanDoubleRound();
            gm.setPlayerStands(true);
        }
    }

    /**
     * Handles the action when the exit button is pressed.
     * Saves the player's score and returns to the main menu if no active bet exists.
     * Otherwise, it denies the exit request.
     */
    private void handleExitButtonPress() {
        if (mv.exitRequest() == 0) {
            if (gm.getHumanPlayer().getCurrentBet().isEmpty()) {
                gm.saveScore();
                gm.setRestarting(true);
                gm.getHumanPlayer().reset();
                mv.switchToMainMenu();
            } else {
                mv.exitRequestDenied();
            }
        }
    }

    /**
     * This method handles all the actions performed based on button presses.
     * It maps the action commands from the view to the appropriate methods.
     *
     * @param e the action event triggered by the view.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Get the command associated with the pressed button
        Commands command = Commands.valueOf(e.getActionCommand());

        // Switch case to handle different commands from the UI
        switch (command) {

            case DEAL_BUTTON_PRESSED -> handleDealButtonPress();
            case STAND_BUTTON_PRESSED -> handleStandButtonPress();
            case HIT_BUTTON_PRESSED -> handleHitButtonPress();
            case SPLIT_BUTTON_PRESSED -> handleSplitButtonPress();
            case DOUBLE_BUTTON_PRESSED -> handleDoubleButtonPress();
            case EXIT_BUTTON_PRESSED -> handleExitButtonPress();
            case CHIPS5_BUTTON_PRESSED -> gm.placeHumanBet(Chips.CHIPS5);
            case CHIPS25_BUTTON_PRESSED -> gm.placeHumanBet(Chips.CHIPS25);
            case CHIPS50_BUTTON_PRESSED -> gm.placeHumanBet(Chips.CHIPS50);
            case CHIPS100_BUTTON_PRESSED -> gm.placeHumanBet(Chips.CHIPS100);
            case PLAY_BUTTON_PRESSED -> mv.switchToPlayPanel(gm.isNicknameToSet());
            case SETTINGS_BUTTON_PRESSED -> gm.switchToSettingsRequest();
            case LEAVE_BUTTON_PRESSED -> System.exit(0);
            case DECK_RADIO_1_PRESSED -> gm.setNumberOfDecks(2);
            case DECK_RADIO_2_PRESSED -> gm.setNumberOfDecks(4);
            case DECK_RADIO_3_PRESSED -> gm.setNumberOfDecks(6);
            case DECK_RADIO_4_PRESSED -> gm.setNumberOfDecks(8);
            case PLAYER_RADIO_1_PRESSED -> gm.setNumberOfPlayers(1);
            case PLAYER_RADIO_2_PRESSED -> gm.setNumberOfPlayers(2);
            case PLAYER_RADIO_3_PRESSED -> gm.setNumberOfPlayers(3);
            case BACK_BUTTON_PRESSED -> mv.switchToMainMenu();
            case SCORE_BUTTON_PRESSED -> mv.showScoreBoard();
            case BACK_TO_MENU_BUTTON_PRESSED -> mv.switchToMainMenu();
            case RESET_SCOREBOARD_BUTTON_PRESSED -> gm.resetScore();
        }
    }
}
