package controller;

/**
 * The Commands enum defines all the possible action commands triggered by the UI buttons.
 * Each constant represents a specific user interaction that is handled by the {@link ViewListeners}
 * class to update the game logic or the interface.
 */
public enum Commands {

    /** Triggered when the "Deal" button is pressed to start a new round. */
    DEAL_BUTTON_PRESSED,
    /** Triggered when the "Stand" button is pressed to hold the current hand. */
    STAND_BUTTON_PRESSED,
    /** Triggered when the "Hit" button is pressed to draw another card. */
    HIT_BUTTON_PRESSED,
    /** Triggered when the "Double" button is pressed to double the current bet. */
    DOUBLE_BUTTON_PRESSED,
    /** Triggered when the "Split" button is pressed to split the hand into two. */
    SPLIT_BUTTON_PRESSED,
    /** Triggered when the chip worth 5 credits is selected for betting. */
    CHIPS5_BUTTON_PRESSED,
    /** Triggered when the chip worth 100 credits is selected for betting. */
    CHIPS100_BUTTON_PRESSED,
    /** Triggered when the chip worth 25 credits is selected for betting. */
    CHIPS25_BUTTON_PRESSED,
    /** Triggered when the chip worth 50 credits is selected for betting. */
    CHIPS50_BUTTON_PRESSED,
    /** Triggered when the "Exit" button is pressed to exit the current game. */
    EXIT_BUTTON_PRESSED,
    /** Triggered when the "Leave" button is pressed to exit the application. */
    LEAVE_BUTTON_PRESSED,
    /** Triggered when the "Play" button is pressed to begin a new game. */
    PLAY_BUTTON_PRESSED,
    /** Triggered when selecting 2 decks in the settings menu. */
    DECK_RADIO_1_PRESSED,
    /** Triggered when selecting 4 decks in the settings menu. */
    DECK_RADIO_2_PRESSED,
    /** Triggered when selecting 6 decks in the settings menu. */
    DECK_RADIO_3_PRESSED,
    /** Triggered when selecting 8 decks in the settings menu. */
    DECK_RADIO_4_PRESSED,
    /** Triggered when selecting 1 player in the settings menu. */
    PLAYER_RADIO_1_PRESSED,
    /** Triggered when selecting 2 players in the settings menu. */
    PLAYER_RADIO_2_PRESSED,
    /** Triggered when selecting 3 players in the settings menu. */
    PLAYER_RADIO_3_PRESSED,
    /** Triggered when the "Back" button is pressed to return to the main menu. */
    BACK_BUTTON_PRESSED,
    /** Triggered when the "Settings" button is pressed to access game settings. */
    SETTINGS_BUTTON_PRESSED,
    /** Triggered when the "Scoreboard" button is pressed to view the score history. */
    SCORE_BUTTON_PRESSED,
    /** Triggered when returning to the main menu from the scoreboard. */
    BACK_TO_MENU_BUTTON_PRESSED,
    /** Triggered when the scoreboard reset button is pressed. */
    RESET_SCOREBOARD_BUTTON_PRESSED
}
