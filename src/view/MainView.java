package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import controller.Commands;
import model.*;


@SuppressWarnings({ "deprecation", "serial" })
public class MainView extends JFrame implements Observer {

	private static MainView instance;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private PlayPanel pp = new PlayPanel();
	private MenuPanel mp = new MenuPanel();
	private SettingsPanel sp = new SettingsPanel();
	private ScoreBoardPanel sbp = new ScoreBoardPanel();
	

	private MainView() throws IOException {

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		mainPanel.add(mp, "MenuPanel");
		mainPanel.add(pp, "PlayPanel");
		mainPanel.add(sp, "SettingsPanel");
		mainPanel.add(sbp, "ScoreBoardPanel");

		this.setUndecorated(true);
		
		add(mainPanel);
		setTitle("JBlackJack");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(850, 650);
		setResizable(true);

		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	public static MainView getInstance() throws IOException {
		if (instance == null) {
			instance = new MainView();
		}
		return instance;
	}

	private void drawInitialBotsBets(List<Player> list) {
		list.stream().filter(x -> x instanceof Bot).forEach(x -> {
			try {
				Thread.sleep(500);
				pp.drawPlayerChips(x, false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

	}


	private void drawPlayerBet(HumanPlayer data) {
		
		pp.drawPlayerChips(data, false);
	}

	private void drawInitialHands(List<Player> data) {
		pp.drawInitialHands(data);
	}

	private void playerTakesCard(HumanPlayer data) {
		pp.drawPlayerCard(data, data.getHand().size() - 1);
	}

	private void playerTakesCardOnSecondHand(HumanPlayer data) {
		pp.drawPlayerCard(data, data.getSecondHand().indexOf(data.getSecondHand().getLast()));
	}

	private void drawPlayersTurn(List<Player> data) {

		data.stream().filter(x -> !(x instanceof HumanPlayer)).forEach(p -> {

			IntStream.range(2, p.getHand().size()).forEach(i -> {
				try {
					Thread.sleep(500);
					if (p instanceof Dealer) {
						pp.drawPlayerCard(p, i);
						pp.showDealerHiddenCard();
					} else {
						pp.drawPlayerCard(p, i);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
		pp.showDealerHiddenCard();
	}

	private void splitCardAction(HumanPlayer data) {
		pp.splitPlayerCards();
		pp.drawPlayerChips(data, true);
	}

	public void chipsButtonsStatus(boolean status) {
		pp.chipsButtonsStatus(status);
	}

	public void setHitButtonStatus(boolean status) {
		pp.setHitButtonStatus(status);
	}

	public void setStandButtonStatus(boolean status) {
		pp.setStandButtonStatus(status);
	}

	public void setDoubleButtonStatus(boolean status) {
		pp.setDoubleButtonStatus(status);
	}

	public void setSplitButtonStatus(boolean status) {
		pp.setSplitButtonStatus(status);
	}

	public void drawDoubleRound() {
		pp.setHitButtonStatus(false);
		pp.setStandButtonStatus(false);
		pp.setDoubleButtonStatus(false);
		pp.setSplitButtonStatus(false);

	}

	public void reset() throws IOException, InterruptedException {
		Thread.sleep(3000);
		pp = new PlayPanel();
		mainPanel.remove(pp);
		mainPanel.add(pp, "PlayPanel");
		mainPanel.revalidate();
		mainPanel.repaint();
		cardLayout.show(mainPanel, "PlayPanel");

		

	}


	public void setDealButtonStatus(boolean status) {
		pp.setDealButtonStatus(status);
	}

	public void noBetForPlayer() {
		pp.noBetForPlayer();
	}

	public void updateCreditLabel(String updatedText) {
		pp.updateCreditLabel(updatedText);
	}

	public void showDealerHiddencard() {
		pp.showDealerHiddenCard();
	}

	// ##############################################################################################################
	// METODI PER AGGIUNGERE I BOTTONI AI LISTENERS
	public void addDealButtonListener(ActionListener al) {
		pp.getDealButton().addActionListener(al);
		pp.getDealButton().setActionCommand(Commands.DEAL_BUTTON_PRESSED.name());
	}

	public void addChip5ButtonListener(ActionListener al) {
		pp.getChips5Button().addActionListener(al);
		pp.getChips5Button().setActionCommand(Commands.CHIPS5_BUTTON_PRESSED.name());
	}

	public void addChip25ButtonListener(ActionListener al) {
		pp.getChips25Button().addActionListener(al);
		pp.getChips25Button().setActionCommand(Commands.CHIPS25_BUTTON_PRESSED.name());
	}

	public void addChip50ButtonListener(ActionListener al) {
		pp.getChips50Button().addActionListener(al);
		pp.getChips50Button().setActionCommand(Commands.CHIPS50_BUTTON_PRESSED.name());
	}

	public void addChip100ButtonListener(ActionListener al) {
		pp.getChips100Button().addActionListener(al);
		pp.getChips100Button().setActionCommand(Commands.CHIPS100_BUTTON_PRESSED.name());
	}

	public void addHitButtonListener(ActionListener al) {
		pp.getHitButton().addActionListener(al);
		pp.getHitButton().setActionCommand(Commands.HIT_BUTTON_PRESSED.name());
	}

	public void addStandButtonListener(ActionListener al) {
		pp.getStandButton().addActionListener(al);
		pp.getStandButton().setActionCommand(Commands.STAND_BUTTON_PRESSED.name());
	}

	public void addDoubleButtonListener(ActionListener al) {
		pp.getDoubleButton().addActionListener(al);
		pp.getDoubleButton().setActionCommand(Commands.DOUBLE_BUTTON_PRESSED.name());
	}

	public void addSplitButtonListener(ActionListener al) {
		pp.getSplitButton().addActionListener(al);
		pp.getSplitButton().setActionCommand(Commands.SPLIT_BUTTON_PRESSED.name());
	}

	public void addExitButtonListener(ActionListener al) {
		pp.getExitButton().addActionListener(al);
		pp.getExitButton().setActionCommand(Commands.EXIT_BUTTON_PRESSED.name());

	}

	public void addLeaveButtonListener(ActionListener al) {
		mp.getLeaveButton().addActionListener(al);
		mp.getLeaveButton().setActionCommand(Commands.LEAVE_BUTTON_PRESSED.name());

	}

	public void addPlayButtonListener(ActionListener al) {
		mp.getPlayButton().addActionListener(al);
		mp.getPlayButton().setActionCommand(Commands.PLAY_BUTTON_PRESSED.name());

	}

	public void addScoreButtonListener(ActionListener al) {
		mp.getScoreButton().addActionListener(al);
		mp.getScoreButton().setActionCommand(Commands.SCORE_BUTTON_PRESSED.name());

	}

	public void switchPlayPanel(boolean status) throws IOException {
		boolean nicknameNeedToSet = status;
		if (nicknameNeedToSet) {
			dataInsertPanel();
		} else {
			cardLayout.show(mainPanel, "PlayPanel");
		}
	}

	public void addCheckBox1Listener(ActionListener al) {
		sp.getDeckCheckBox1().addActionListener(al);
		sp.getDeckCheckBox1().setActionCommand(Commands.DECK_RADIO_1_PRESSED.name());
	}

	public void addCheckBox2Listener(ActionListener al) {
		sp.getDeckCheckBox2().addActionListener(al);
		sp.getDeckCheckBox2().setActionCommand(Commands.DECK_RADIO_2_PRESSED.name());
	}

	public void addCheckBox3Listener(ActionListener al) {
		sp.getDeckCheckBox3().addActionListener(al);
		sp.getDeckCheckBox3().setActionCommand(Commands.DECK_RADIO_3_PRESSED.name());
	}

	public void addCheckBox4Listener(ActionListener al) {
		sp.getDeckCheckBox4().addActionListener(al);
		sp.getDeckCheckBox4().setActionCommand(Commands.DECK_RADIO_4_PRESSED.name());
	}

	public void addPlayersBox1Listener(ActionListener al) {
		sp.getPlayersCheckBox1().addActionListener(al);
		sp.getPlayersCheckBox1().setActionCommand(Commands.PLAYER_RADIO_1_PRESSED.name());
	}

	public void addPlayersBox2Listener(ActionListener al) {
		sp.getPlayersCheckBox2().addActionListener(al);
		sp.getPlayersCheckBox2().setActionCommand(Commands.PLAYER_RADIO_2_PRESSED.name());
	}

	public void addPlayersBox3Listener(ActionListener al) {
		sp.getPlayersCheckBox3().addActionListener(al);
		sp.getPlayersCheckBox3().setActionCommand(Commands.PLAYER_RADIO_3_PRESSED.name());
	}

	public void addBackButtonListener(ActionListener al) {
		sp.getBackButton().addActionListener(al);
		sp.getBackButton().setActionCommand(Commands.BACK_BUTTON_PRESSED.name());
	}

	public void addSetingsButtonListener(ActionListener al) {
		mp.getSettingsButton().addActionListener(al);
		mp.getSettingsButton().setActionCommand(Commands.SETTINGS_BUTTON_PRESSED.name());
	}

	public void addBackToMenuButtonListener(ActionListener al) {
		sbp.getBackToMenuButton().addActionListener(al);
		sbp.getBackToMenuButton().setActionCommand(Commands.BACK_TO_MENU_BUTTON_PRESSED.name());
	}

	public void addResetScoreBoardButton(ActionListener al) {
		sp.getResetScoreBoardButton().addActionListener(al);
		sp.getResetScoreBoardButton().setActionCommand(Commands.RESET_SCOREBOARD_BUTTON_PRESSED.name());
	}
	
	private void playerSkipSpecialRound() {
		pp.getSplitButton().setEnabled(false);

	}

	private void playerSkipDoubleRound() {
		pp.getDoubleButton().setEnabled(false);

	}
	public void switchToPlayPanel(boolean data) {
		try {
			switchPlayPanel(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int exitRequest() {
		String message = "Are you sure to leave the table? Your score will be saved in scoreboard!";
		int response = JOptionPane.showConfirmDialog(null, message, "Exit Game", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		return response;
	}
	
	public void exitRequestDenied() {
		String message = "you must play this round before leave the table";
		JOptionPane.showMessageDialog(mp, message);
	}

	// ###########################################################################################################

	@Override
	public void update(Observable o, Object arg) {

		GameManager gm = GameManager.getInstance();

		if (o instanceof GameManager && arg instanceof GameManager.Message) {
			GameManager.Message msg = (GameManager.Message) arg;

			switch (msg) {
			case BOTS_PLACED_INITIAL_BETS -> drawInitialBotsBets(gm.getPlayers());
			case HUMAN_PLACED_BET -> drawPlayerBet(gm.getHumanPlayer());
			case INITIAL_CARDS_DEALT -> drawInitialHands(gm.getPlayers());
			case BOTS_PLAYED_TURN -> drawPlayersTurn(gm.getPlayers());
			case PLAYER_SPLIT_CARDS -> {
				splitCardAction(gm.getHumanPlayer());
				this.setSplitButtonStatus(false);
				this.setDoubleButtonStatus(false);
			}
			case PLAYER_FIRST_HAND_UPDATE -> playerTakesCard(gm.getHumanPlayer());
			case PLAYER_SECOND_HAND_UPDATE -> playerTakesCardOnSecondHand(gm.getHumanPlayer());
			case PLAYER_BET_MISSING -> pp.noBetForPlayer();
			case PLAYER_SKIP_SPECIAL_ROUND -> playerSkipSpecialRound();
			case PLAYER_SKIP_DOUBLE_ROUND -> playerSkipDoubleRound();
			case EXIT_REQUEST -> exitRequest();
			case GO_SETTINGS -> switchToSettings();
			case PRINT_ROUND_SOLVER -> switchSolverLabel(gm.getRoundSolver());
			case UPDATE_SCORE_BOARD ->  sbp.updateScoreBoardPanel();
			case RESET_SCORE_BOARD -> reloadSbp();
			default -> throw new IllegalArgumentException("Unexpected value: " + msg);

			}
		}
	}

	public void dataInsertPanel() throws IOException {
		pp.dataInsertPanel();
	}

	private void switchSolverLabel(List<String> roundSolver) {
		pp.printRoundLabel(roundSolver);

	}

	private void switchToSettings() {
		cardLayout.show(mainPanel, "SettingsPanel");
	}

	public void switchToMainMenu() {
		cardLayout.removeLayoutComponent(mp);
		mp = new MenuPanel();
		cardLayout.addLayoutComponent(mp, "MenuPanel");
		mainPanel.revalidate();
		mainPanel.repaint();
		cardLayout.show(mainPanel, "MenuPanel");
	}

	public void printGameOver() throws InterruptedException, IOException {

		JLabel gameOver = new JLabel();
		AudioManager.getInstance().play(getClass().getResource("/Audio/notify.wav").getPath());// suono sconfitta
		int width = pp.getWidth();
		int height = 350;
		pp.removeAll();
		pp.setBackground(Color.BLACK.darker().darker());
		String imagePath = "/RoundImg/GAMEOVER.png";
		Image ii = CustomSwingUtilities.resizeImage(imagePath, width, height);
		gameOver.setIcon(new ImageIcon(ii));
		pp.setOpaque(true);
		pp.add(gameOver);
		pp.repaint();
		pp.revalidate();
		revalidate();
		repaint();
		Thread.sleep(3000);

	}
	public void notEnoughCreditsForSplit() {
		pp.notEnoghMoneyForSplitDialog();
	}

	public void showScoreBoard() {
		cardLayout.show(mainPanel, "ScoreBoardPanel");
	}

	public void notEnoughCreditsForDouble() {
		pp.notEnoughMoneyForDouble();

	}
	private void reloadSbp() {
		sbp.clearTextArea();
		cardLayout.show(mainPanel, "ScoreBoardPanel");
	}
		

}
