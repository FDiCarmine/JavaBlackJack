package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Bot;
import model.Chips;
import model.Dealer;
import model.GameManager;
import model.HumanPlayer;
import model.Player;
import model.ScoreBoardManager;

@SuppressWarnings("serial")
public class PlayPanel extends JPanel {

	private AudioManager am = AudioManager.getInstance();
	private ScoreBoardManager sbm = new ScoreBoardManager();
	private boolean checkNickName;

	private JPanel centralGridLayoutPanel;
	private JPanel card0Grid, card1Grid, card2Grid, card3Grid, cardDealerGrid, splitPanel;
	private JPanel player0Chips, player1Chips, player2Chips, player3Chips;
	private JPanel player0Grid, player1Grid, player2Grid, player3Grid, dealerGrid, emptyGrid;

	private JPanel bottomPanel;
	private JPanel topPanel;

	private JPanel doubleHandsChipPanel;
	private JLabel playerCredit;

	private JButton chips5, chips25, chips50, chips100;
	private JButton hitButton, standButton, doubleButton, splitButton, dealButton;
	private JButton exitButton;

	private ArrayList<JPanel> cardsPanels = new ArrayList<>();
	private ArrayList<JPanel> chipsPanels = new ArrayList<>();
	private ArrayList<JPanel> playersPanels = new ArrayList<>();
	private ArrayList<JButton> chipsButtons = new ArrayList<>();
	private ArrayList<JButton> actionButtons = new ArrayList<>();

	public PlayPanel() {

		// CREO IL PANNELLO PRINCIPALE CHE CONTIENE TUTTI I COMPONENTI
		float[] colors = Color.RGBtoHSB(0, 102, 51, null);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		this.setOpaque(false);

		// #############################################################################################
		// CREAZIONE DEI PANNELLI PRINCIPALI
		doubleHandsChipPanel = new JPanel();
		doubleHandsChipPanel.setLayout(new BorderLayout());
		doubleHandsChipPanel.setOpaque(false);

		playerCredit = new JLabel();
		playerCredit.setText("REMAINING CREDITS : " + "" + "$");
		playerCredit.setForeground(Color.WHITE.brighter().brighter());

		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

		topPanel.setOpaque(true);
		topPanel.setBackground(Color.CYAN.darker().darker().darker());
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK.brighter()));
		topPanel.add(playerCredit);

		topPanel.add(Box.createGlue());
		exitButton = CustomSwingUtilities.createButton("",
				new ImageIcon(getClass().getResource("/GameButtons/icon_cross.png")), Font.BOLD, 15, 20, 20);
		exitButton.setOpaque(false);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setPressedIcon(new ImageIcon(getClass().getResource("/GameButtons/icon_outline_cross.png")));
		topPanel.add(exitButton);

		add(topPanel, BorderLayout.NORTH);

		// PANNELLO CENTRALE CON GRIDBAG
		centralGridLayoutPanel = new JPanel(new GridBagLayout());
		centralGridLayoutPanel.setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		// PANNELLO DELLE CHIPS SU ASSE X BOX
		player0Chips = new JPanel();
		player1Chips = new JPanel();
		player2Chips = new JPanel();
		player3Chips = new JPanel();
		chipsPanels.addAll(List.of(player0Chips, player1Chips, player2Chips, player3Chips));
		SetChipsPanelStatus(chipsPanels);

		// PANNELLO DELLE CARTE SU ASSE X BOX

		card0Grid = new JPanel();
		card1Grid = new JPanel();
		card2Grid = new JPanel();
		card3Grid = new JPanel();
		cardDealerGrid = new JPanel();
		splitPanel = new JPanel();

		cardsPanels.addAll(List.of(card0Grid, card1Grid, card2Grid, card3Grid, cardDealerGrid, splitPanel));
		SetCardsPanelStatus(cardsPanels);

		// CREAZIONE PANNELLO SU ASSE Y BOX CHE OSPITA CARTE E CHIPS
		player0Grid = new JPanel();
		player1Grid = new JPanel();
		player2Grid = new JPanel();
		player3Grid = new JPanel();
		dealerGrid = new JPanel();
		emptyGrid = new JPanel();

		playersPanels.addAll(List.of(player0Grid, player1Grid, player2Grid, player3Grid, dealerGrid, emptyGrid));
		SetPlayersPanelStatus(playersPanels);

		// CREAZIONE DEL PANNELLO INFERIORE CHE CONTIENE I BOTTONI
		bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.setBackground(Color.CYAN.darker().darker().darker());
		bottomPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK.brighter()));

		// CREAZIONE DEI BOTTONI

		chips5 = CustomSwingUtilities.createButton("5",
				new ImageIcon(getClass().getResource("/GameButtons/CHIPS/CHIPS5.png")), Font.TYPE1_FONT, 18, 40, 40);
		chips25 = CustomSwingUtilities.createButton("25",
				new ImageIcon(getClass().getResource("/GameButtons/CHIPS/CHIPS25.png")), Font.TYPE1_FONT, 18, 40, 40);
		chips50 = CustomSwingUtilities.createButton("50",
				new ImageIcon(getClass().getResource("/GameButtons/CHIPS/CHIPS50.png")), Font.TYPE1_FONT, 18, 40, 40);
		chips100 = CustomSwingUtilities.createButton("100",
				new ImageIcon(getClass().getResource("/GameButtons/CHIPS/CHIPS100.png")), Font.TYPE1_FONT, 18, 40, 40);
		chipsButtons.addAll(List.of(chips5, chips25, chips50, chips100));

		ImageIcon buttonImage = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_gloss - small.png"));

		hitButton = CustomSwingUtilities.createButton("HIT", buttonImage, Font.TYPE1_FONT, 15, 100, 40);
		standButton = CustomSwingUtilities.createButton("STAND", buttonImage, Font.TYPE1_FONT, 15, 100, 40);
		doubleButton = CustomSwingUtilities.createButton("DOUBLE", buttonImage, Font.TYPE1_FONT, 15, 100, 40);
		splitButton = CustomSwingUtilities.createButton("SPLIT", buttonImage, Font.TYPE1_FONT, 15, 100, 40);
		dealButton = CustomSwingUtilities.createButton("DEAL", buttonImage, Font.TYPE1_FONT, 12, 100, 40);
		actionButtons.addAll(List.of(hitButton, standButton, doubleButton, splitButton, dealButton));

		bottomButtonsSetStatus();
		// ############################################################################################

		// METODO CHE COMPONE I PANNELLI

		playerPanelsAssembler();
		// METODO CHE IMMETTE I PANNELLI NEL GRIDBAG DEL PANNELLO CENTRALE
		playerPanelsGridDistribution();
		// METODO CHE AGGIUNGE I BOTTONI AL BOTTOMPANEL
		addButtonsToBottomPanel();
		// AGGIUNTA DEL PANNELLO INFERIORE CHE CONTIENE I BOTTONI AL PANNELLO DEL
		// GRIDBAG CENTRALE
		add(bottomPanel, BorderLayout.SOUTH);

		// #############################################################################################
		setHitButtonStatus(false);
		setStandButtonStatus(false);
		setDoubleButtonStatus(false);
		setSplitButtonStatus(false);

	}

	// -METODI SETTERS PER SUPPORTO AL COSTRUTTORE
	// ##################################################################################################
	private void SetCardsPanelStatus(ArrayList<JPanel> cardsPanels) {
		cardsPanels.stream().forEach(x -> {
			x.setLayout(new BoxLayout(x, BoxLayout.X_AXIS));
			x.setOpaque(false);

		});
	}

	private void SetChipsPanelStatus(ArrayList<JPanel> cardsPanels) {
		chipsPanels.stream().forEach(x -> {
			x.setLayout(new BoxLayout(x, BoxLayout.X_AXIS));
			x.setOpaque(false);

		});
	}

	private void SetPlayersPanelStatus(ArrayList<JPanel> playersPanels) {
		playersPanels.stream().forEach(x -> {
			x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
			x.setOpaque(false);

		});
	}



	private void playerPanelsAssembler() {

		player0Grid.add(player0Chips);
		player0Grid.add(card0Grid);

		player1Grid.add(player1Chips);
		player1Grid.add(card1Grid);

		player2Grid.add(player2Chips);
		player2Grid.add(card2Grid);

		player3Grid.add(player3Chips);
		player3Grid.add(card3Grid);

		dealerGrid.add(cardDealerGrid);
	}

	private void playerPanelsGridDistribution() {
		// DISPOSIZIONE PANNELLI NEL LAYOUT PRINCIPALE CON TECNICA GBC

		GridBagConstraints gbc = new GridBagConstraints();

		player0Grid.setPreferredSize(new Dimension(230, 200));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_END;
		centralGridLayoutPanel.add(player0Grid, gbc);
		player1Grid.setPreferredSize(new Dimension(230, 200));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.LINE_END;
		centralGridLayoutPanel.add(player1Grid, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_END;
		centralGridLayoutPanel.add(emptyGrid, gbc);// EMPTY

		player2Grid.setPreferredSize(new Dimension(230, 200));
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.LINE_START;
		centralGridLayoutPanel.add(player2Grid, gbc);

		player3Grid.setPreferredSize(new Dimension(230, 200));
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_END;
		centralGridLayoutPanel.add(player3Grid, gbc);

		dealerGrid.setPreferredSize(new Dimension(260, 200));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		centralGridLayoutPanel.add(dealerGrid, gbc);

		splitPanel.setPreferredSize(new Dimension(200, 100));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 0, 0, 0);
		splitPanel.setBorder(BorderFactory.createTitledBorder("SplitHand"));
		splitPanel.setVisible(false);
		gbc.anchor = GridBagConstraints.CENTER;
		centralGridLayoutPanel.add(splitPanel, gbc);

		add(centralGridLayoutPanel, BorderLayout.CENTER);

		// doubleHandsChipPanel.setPreferredSize(new Dimension(30, 30));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(0, 30, 35, 0);

		// splitPanel.setVisible(false);
		gbc.anchor = GridBagConstraints.LINE_START;
		centralGridLayoutPanel.add(doubleHandsChipPanel, gbc);

		// add(centralGridLayoutPanel, BorderLayout.CENTER);

	}

	private void bottomButtonsSetStatus() {
		ImageIcon pressedChip = new ImageIcon(getClass().getResource("/GameButtons/CHIPS/PressChip.png"));
		chipsButtons.stream().forEach(x -> {
			x.setOpaque(false);
			x.setBorderPainted(false);
			x.setContentAreaFilled(false);
			x.setFocusPainted(false);
			x.setForeground(Color.BLACK.darker().darker());
			x.setPressedIcon(pressedChip);

		});

		ImageIcon pressedButton = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_border.png"));
		ImageIcon resizedPressedIcon = new ImageIcon(
				pressedButton.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH));

		actionButtons.stream().forEach(y -> {
			y.setOpaque(false);
			y.setBorderPainted(false);
			y.setContentAreaFilled(false);
			y.setFocusPainted(false);
			y.setForeground(Color.BLACK.darker().darker());
			y.setPressedIcon(resizedPressedIcon);
		});
	}

	private void addButtonsToBottomPanel() {
		GridBagConstraints gbc = new GridBagConstraints();

		chipsButtons.stream().forEach(x -> {
			gbc.gridx = chipsButtons.indexOf(x);
			gbc.gridy = 0;
			gbc.weightx = 0.1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.LAST_LINE_START;
			bottomPanel.add(x, gbc);

		});

		actionButtons.stream().forEach(y -> {
			gbc.gridx = actionButtons.indexOf(y) + 4;
			gbc.gridy = 0;
			gbc.weightx = 0.1;
			gbc.weighty = 0.1;
			gbc.anchor = GridBagConstraints.LAST_LINE_START;
			bottomPanel.add(y, gbc);

		});
		bottomPanel.remove(dealButton);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.anchor = GridBagConstraints.PAGE_END;
		centralGridLayoutPanel.add(dealButton, gbc);
	}
	// ####################################################################################################
	// GETTERS UTILIZZATI PER COSTRUIRE I LISTENERS

	// ####################################################################################################
	// METODI PER DISEGNARE SUL PANNELLO

	/**
	 * enable or disable visibility of the split panel, to be used in case of
	 * SplitHand
	 *
	 * @param status boolean true or false
	 */

	public void enableSplitPanel(boolean status) {
		splitPanel.setVisible(status);
	}

	public JButton getHitButton() {
		return hitButton;
	}

	public JButton getStandButton() {
		return standButton;
	}

	public JButton getDoubleButton() {
		return doubleButton;
	}

	public JButton getSplitButton() {
		return splitButton;
	}

	public JButton getDealButton() {
		return dealButton;
	}

	public JButton getChips5Button() {
		return chips5;
	}

	public JButton getChips25Button() {
		return chips25;
	}

	public JButton getChips50Button() {
		return chips50;
	}

	public JButton getChips100Button() {
		return chips100;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setDealButtonStatus(boolean status) {
		getDealButton().setEnabled(status);
		// getDealButton().setVisible(status);
	}

	public void setHitButtonStatus(boolean status) {
		getHitButton().setEnabled(status);
		// getHitButton().setVisible(status);
	}

	public void setStandButtonStatus(boolean status) {
		getStandButton().setEnabled(status);
		// getStandButton().setVisible(status);
	}

	public void setDoubleButtonStatus(boolean status) {
		getDoubleButton().setEnabled(status);
		getDoubleButton().setVisible(status);
	}

	public void setSplitButtonStatus(boolean status) {
		getSplitButton().setEnabled(status);
		getSplitButton().setVisible(status);
	}

	public void chipsButtonsStatus(boolean status) {
		getChips100Button().setEnabled(status);
		getChips5Button().setEnabled(status);
		getChips25Button().setEnabled(status);
		getChips50Button().setEnabled(status);

	}

	public void splitPlayerCards() {

		splitPanel.setVisible(true);
		splitPanel.add(card0Grid.getComponent(1));
		card0Grid.getComponentCount();
		splitPanel.getComponentCount();
	}

	public void noBetForPlayer() {
		JOptionPane.showMessageDialog(null, "Devi prima effettuare una puntata!", "", JOptionPane.PLAIN_MESSAGE);
	}

	public void updateCreditLabel(String updatedText) {
		playerCredit.setText("REMAINING CREDITS : " + updatedText + "$");
	}

	public void drawPlayerChips(Player player, boolean isSecondPanel) {
		if (!(player instanceof Dealer)) {
			ArrayList<Chips> chips = (player instanceof Bot) ? ((Bot) player).getCurrentBet()
					: ((HumanPlayer) player).getCurrentBet();

			if (chips != null && !chips.isEmpty()) {
				Chips lastChip = chips.getLast();

				Image resizedImage = null;
				try {
					resizedImage = CustomSwingUtilities.resizeImage("/GameButtons/CHIPS/" + lastChip.name() + ".png",
							15, 15);
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				
				JLabel chipLabel = new JLabel(new ImageIcon(resizedImage));
				JPanel playerPanel = getPlayerChipsPanel(player, false);

				if (playerPanel != null && !isSecondPanel) {
					playerPanel.removeAll();
					am.play(getClass().getResource("/Audio/chipSound.wav").getPath());
					playerPanel.add(chipLabel);
					playerPanel.revalidate();
					playerPanel.repaint();
				}

				if (player instanceof HumanPlayer && isSecondPanel) {
					JPanel doubleHandsChipPanel = getPlayerChipsPanel(player, true);

					if (doubleHandsChipPanel != null) {
				;
						am.play(getClass().getResource("/Audio/chipSound.wav").getPath());
						doubleHandsChipPanel.add(chipLabel, BorderLayout.CENTER);
						doubleHandsChipPanel.revalidate();
						doubleHandsChipPanel.repaint();

					}
				}
			}
		}
	}

	private JPanel getPlayerChipsPanel(Player player, boolean isSplit) {
		if (player instanceof HumanPlayer) {
			return isSplit ? doubleHandsChipPanel : player0Chips;
		} else {
			switch (player.getName()) {
			case "bot(1)":
				return player1Chips;
			case "bot(2)":
				return player2Chips;
			case "bot(3)":
				return player3Chips;
			default:
				return null;
			}
		}
	}

	// #############################################################################################################################
	/**
	 * public void drawPlayerCard(Player player,int index) throws IOException {
	 *
	 * Image resizedImage = CustomSwingUtilities.resizeImage("/Cards/" +
	 * player.getHand().get(index) + ".png", 40, 70); JLabel chipLabel = new
	 * JLabel(new ImageIcon(resizedImage)); JPanel playerPanel =
	 * getPlayerCardsPanel(player, false);
	 *
	 * if (playerPanel != null) {
	 *
	 * am.play(getClass().getResource("/Audio/cardDealt.wav").getPath());
	 * playerPanel.add(chipLabel); playerPanel.revalidate(); playerPanel.repaint();
	 * }
	 *
	 * if (player instanceof HumanPlayer && ((HumanPlayer)
	 * player).isPlayingOnSecondHand()) { Image resizedImage1 =
	 * CustomSwingUtilities.resizeImage("/Cards/" + ((HumanPlayer)
	 * player).getSecondHand().get(index) + ".png", 40, 70); JLabel chipLabel1 = new
	 * JLabel(new ImageIcon(resizedImage1)); JPanel splitPanel =
	 * getPlayerCardsPanel(player, true);
	 *
	 * if (splitPanel != null) {
	 *
	 * am.play(getClass().getResource("/Audio/cardDealt.wav").getPath());
	 * splitPanel.add(chipLabel1); splitPanel.revalidate(); splitPanel.repaint();
	 *
	 * } } }
	 * @throws IOException
	 */

	 public void dataInsertPanel() throws IOException {



	        while (!checkNickName) {
	            String name = JOptionPane.showInputDialog(null, "Insert your nickname:", "Input", JOptionPane.QUESTION_MESSAGE);

	            if (name == null || name.equalsIgnoreCase("undef") ||  name.isBlank()) {
	                JOptionPane.showMessageDialog(null, "Nickname cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
	                continue;
	            }

	            if (sbm.checkForNicknameAlreadyTaken(name)) {
	                JOptionPane.showMessageDialog(null, "Nickname already taken! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	            	sbm.addNameToFile(name);
	            	GameManager.getInstance().getHumanPlayer().setName(name);
	            	MainView.getInstance().switchPlayPanel(false);
	            	checkNickName = true;
	                JOptionPane.showMessageDialog(null, "Nickname accepted: " + name, "Success", JOptionPane.INFORMATION_MESSAGE);

	                break;
	            }
	        }
	    }



	public void drawPlayerCard(Player player, int index)  {
		if (player instanceof HumanPlayer && ((HumanPlayer) player).isPlayingOnSecondHand()) {
			//the error castlass exception is safe because the expected espression evaluation is short circuited 
			// Draw only in the second hand panel
			try {
				drawCardOnSecondHand((HumanPlayer) player, index);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Draw in the main hand panel
			try {
				drawCardOnMainHand(player, index);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void drawCardOnMainHand(Player player, int index) throws IOException {

		Image resizedImage = CustomSwingUtilities.resizeImage("/Cards/" + player.getHand().get(index) + ".png", 30, 60);
		Image dealerHiddenCard = CustomSwingUtilities.resizeImage("/Cards/back04" + ".png", 30, 60);
		ImageIcon disabledIcon = new ImageIcon(dealerHiddenCard);
		JLabel chipLabel = new JLabel(new ImageIcon(resizedImage));
		if (player instanceof Dealer && index == 1) {
			chipLabel.setDisabledIcon(disabledIcon);
			chipLabel.setEnabled(false);
		}
		JPanel playerPanel = getPlayerCardsPanel(player, false);

		if (playerPanel != null) {
			am.play(getClass().getResource("/Audio/cardDealt.wav").getPath());
			playerPanel.add(chipLabel);
			playerPanel.revalidate();
			playerPanel.repaint();
		}
	}

	private void drawCardOnSecondHand(HumanPlayer player, int index) throws IOException {
		Image resizedImage = CustomSwingUtilities.resizeImage("/Cards/" + player.getSecondHand().get(index) + ".png",
				30, 60);
		JLabel chipLabel = new JLabel(new ImageIcon(resizedImage));
		JPanel splitPanel = getPlayerCardsPanel(player, true);

		if (splitPanel != null) {
			am.play(getClass().getResource("/Audio/cardDealt.wav").getPath());
			splitPanel.add(chipLabel);
			splitPanel.revalidate();
			splitPanel.repaint();
		}
	}

	private JPanel getPlayerCardsPanel(Player player, boolean isSplit) {
		if (player instanceof HumanPlayer) {
			return isSplit ? splitPanel : card0Grid;
		} else {
			switch (player.getName()) {
			case "bot(1)":
				return card1Grid;
			case "bot(2)":
				return card2Grid;
			case "bot(3)":
				return card3Grid;
			case "DEALER":
				return cardDealerGrid;
			default:
				return null;
			}
		}
	}

	// ###########################################################################################################
	public void drawInitialHands(List<Player> data) {
		IntStream.range(0, 2).forEach(i -> {
			data.forEach(x -> {
				try {
					Thread.sleep(300);
					drawPlayerCard(x, i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
	}

	public void showDealerHiddenCard() {
		cardDealerGrid.getComponent(1).setEnabled(true);
		cardDealerGrid.revalidate();
		cardDealerGrid.repaint();

	}

	public void printRoundLabel(List<String> roundSolver) {
		int index = 0;
		for (String result : roundSolver) {
			System.out.println(result);
			JLabel solve = new JLabel();
			JLabel solve2 = new JLabel();
			if (index == 0) {
				switch (result) {
				case "PLAYERWIN" -> solve.setIcon(new ImageIcon(getClass().getResource("/RoundImg/youWin.png")));
				case "PLAYERLOST" -> solve.setIcon(new ImageIcon(getClass().getResource("/RoundImg/youLose.png")));
				case "TIE" -> solve.setIcon(new ImageIcon(getClass().getResource("/RoundImg/tie.png")));

				}
				this.revalidate();
				emptyGrid.add(solve);
				emptyGrid.add(Box.createVerticalStrut(100));

			} else {
				switch (result) {
				case "PLAYERWIN" -> solve2.setIcon(new ImageIcon(getClass().getResource("/RoundImg/secondHandWin.png")));
				case "PLAYERLOST" -> solve2.setIcon(new ImageIcon(getClass().getResource("/RoundImg/SecondHandLose.png")));
				case "TIE" -> solve2.setIcon(new ImageIcon(getClass().getResource("/RoundImg/tie.png")));

				}
				this.revalidate();
				emptyGrid.add(solve2);
				emptyGrid.add(Box.createVerticalStrut(40));

			}

			this.repaint();
			AudioManager.getInstance().play(getClass().getResource("/Audio/notify.wav").getPath());
			index++;
			emptyGrid.repaint();

		}
		roundSolver.clear();

	}
	
	
	public void notEnoghMoneyForSplitDialog() {
		JOptionPane.showMessageDialog(null, "Not enough Credit For Split or Double", "", JOptionPane.PLAIN_MESSAGE);
	}

	public void notEnoughMoneyForDouble() {
		notEnoghMoneyForSplitDialog();
	}


}
