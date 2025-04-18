package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SettingsPanel extends JPanel {

	private JPanel settingsButtonsHolder = new JPanel();
	private JPanel numberOfPlayersCheckBox = new JPanel();
	private JPanel numberOfDecksCheckBox = new JPanel();
	private JButton backButton,resetScoreBoardButton;
	private JRadioButton deckCheckBox1, deckCheckBox2, deckCheckBox3, deckCheckBox4;
	private JRadioButton playersCheckBox1, playersCheckBox2, playersCheckBox3;

	private ArrayList<JRadioButton> numberOfDeckCb = new ArrayList<>();
	private ArrayList<JRadioButton> numberOfPlayersCb = new ArrayList<>();

	private void addDeckCheckBox() {
		numberOfDeckCb.stream().forEach(checkBox -> {
			numberOfDecksCheckBox.add(checkBox);
			checkBox.setOpaque(false);
			checkBox.setBorderPainted(false);
		});
	}

	private void addPlayersCheckBox() {
		numberOfPlayersCb.stream().forEach(checkBox -> {
			numberOfPlayersCheckBox.add(checkBox);
			checkBox.setOpaque(false);
			checkBox.setBorderPainted(false);
		});
	}

	public SettingsPanel() {
		float[] colors = Color.RGBtoHSB(0, 102, 51, null);
		setLayout(new GridBagLayout());
		setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		setVisible(true);

		numberOfDecksCheckBox.setLayout(new BoxLayout(numberOfDecksCheckBox, BoxLayout.X_AXIS));
		numberOfDecksCheckBox.setOpaque(false);

		numberOfPlayersCheckBox.setLayout(new BoxLayout(numberOfPlayersCheckBox, BoxLayout.X_AXIS));

		settingsButtonsHolder.setLayout(new BoxLayout(settingsButtonsHolder, BoxLayout.Y_AXIS));
		settingsButtonsHolder.setOpaque(false);
		settingsButtonsHolder.add(numberOfDecksCheckBox);
		settingsButtonsHolder.add(Box.createVerticalStrut(10));
		settingsButtonsHolder.add(numberOfPlayersCheckBox);

		JLabel deckCbLabel = new JLabel("NUMBER OF DECKS");
		deckCbLabel.setForeground(Color.WHITE.brighter());
		deckCbLabel.setFont(new Font("Bold", Font.BOLD, 20));

		ButtonGroup deckGroup = new ButtonGroup();
		ImageIcon checked = new ImageIcon(getClass().getResource("/GameButtons/check_square_grey.png"));
		ImageIcon unchecked = new ImageIcon(getClass().getResource("/GameButtons/check_square_grey_checkmark.png"));

		deckCheckBox1 = CustomSwingUtilities.createCustomRadioButton("2", false, checked, unchecked, 25, 25);
		deckCheckBox2 = CustomSwingUtilities.createCustomRadioButton("4", false, checked, unchecked, 25, 25);

		deckCheckBox3 = CustomSwingUtilities.createCustomRadioButton("6", false, checked, unchecked, 25, 25);
		deckCheckBox4 = CustomSwingUtilities.createCustomRadioButton("8", false, checked, unchecked, 25, 25);
		numberOfDeckCb.addAll(List.of(deckCheckBox1, deckCheckBox2, deckCheckBox3, deckCheckBox4));

		numberOfDeckCb.stream().forEach(x -> {
			x.setFont(new Font("Bold", Font.BOLD, 20));
			x.setBorderPainted(false);
			x.setFocusPainted(false);
			x.setForeground(Color.BLACK.darker().darker());
			deckGroup.add(x);
		});

		numberOfDecksCheckBox.add(deckCbLabel);

		addDeckCheckBox();

		numberOfPlayersCheckBox.setLayout(new BoxLayout(numberOfPlayersCheckBox, BoxLayout.X_AXIS));
		numberOfPlayersCheckBox.setOpaque(false);

		JLabel playersCbLabel = new JLabel("NUMBER OF ARTIFICIAL PLAYERS");
		playersCbLabel.setForeground(Color.WHITE.brighter());
		playersCbLabel.setFont(new Font("Bold", Font.BOLD, 20));
		ButtonGroup playerGroup = new ButtonGroup();

		playersCheckBox1 = CustomSwingUtilities.createCustomRadioButton("1", false, checked, unchecked, 25, 25);
		playersCheckBox2 = CustomSwingUtilities.createCustomRadioButton("2", false, checked, unchecked, 25, 25);

		playersCheckBox3 = CustomSwingUtilities.createCustomRadioButton("3", false, checked, unchecked, 25, 25);

		numberOfPlayersCb.addAll(List.of(playersCheckBox1, playersCheckBox2, playersCheckBox3));

		numberOfPlayersCheckBox.add(Box.createHorizontalStrut(24));
		numberOfPlayersCheckBox.add(playersCbLabel);
		addPlayersCheckBox();
		numberOfPlayersCb.stream().forEach(x -> {
			x.setFont(new Font("Bold", Font.BOLD, 20));
			x.setBorderPainted(false);
			x.setFocusPainted(false);
			x.setForeground(Color.BLACK.darker().darker());
			playerGroup.add(x);
		});

		ImageIcon buttonImage = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_gloss - small.png"));

		backButton = CustomSwingUtilities.createButton("BACK TO MAIN MENU ", buttonImage, Font.TYPE1_FONT, 15, 180, 50);
		ImageIcon pressedButton = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_border.png"));
		ImageIcon resizedPressedIcon = new ImageIcon(
				pressedButton.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH));
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setOpaque(false);
		backButton.setPressedIcon(resizedPressedIcon);
		
		playersCheckBox3.doClick();
		deckCheckBox2.doClick();

		resetScoreBoardButton = CustomSwingUtilities.createButton("RESETSCOREBOARD", buttonImage, Font.TYPE1_FONT, 15,180, 50);
		resetScoreBoardButton.setBorderPainted(false);
		resetScoreBoardButton.setOpaque(false);
		resetScoreBoardButton.setRequestFocusEnabled(false);
		resetScoreBoardButton.setFocusPainted(false);
		resetScoreBoardButton.setPressedIcon(resizedPressedIcon);
		resetScoreBoardButton.setContentAreaFilled(false);
		
		
		settingsButtonsHolder.add(backButton);
		settingsButtonsHolder.add(resetScoreBoardButton);
		add(settingsButtonsHolder);

	}

	public JRadioButton getDeckCheckBox1() {
		return deckCheckBox1;
	}

	public JRadioButton getDeckCheckBox2() {
		return deckCheckBox2;
	}

	public JRadioButton getDeckCheckBox3() {
		return deckCheckBox3;
	}

	public JRadioButton getDeckCheckBox4() {
		return deckCheckBox4;
	}

	public JRadioButton getPlayersCheckBox1() {
		return playersCheckBox1;
	}

	public JRadioButton getPlayersCheckBox2() {
		return playersCheckBox2;
	}

	public JRadioButton getPlayersCheckBox3() {
		return playersCheckBox3;
	}

	public JButton getBackButton() {
		return backButton;
	}
	
	public JButton getResetScoreBoardButton() {
		return resetScoreBoardButton;
	}



	


}
