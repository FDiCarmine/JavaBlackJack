package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel {

	private JPanel mainButtonsPanel;

	private JButton playButton;
	private JButton settingsButton;
	private JButton scoreButton;
	private JButton leaveButton;

	private ImageIcon menuIcon;
	private JLabel logo;

	public MenuPanel() {
		// CREO IL PANNELLO PRINCIPALE CHE CONTIENE TUTTI I COMPONENTI
		// logo.setIcon(menuIcon);

		float[] colors = Color.RGBtoHSB(0, 102, 51, null);
		setLayout(new GridBagLayout());
		setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		setVisible(true);

		logo = new JLabel();
		logo.setVisible(true);
		menuIcon = new ImageIcon(getClass().getResource("/Backgrounds/JBJlogoFull.png"));// da rifinire

		logo.setIcon(menuIcon);

		mainButtonsPanel = new JPanel();
		mainButtonsPanel.setLayout(new BoxLayout(mainButtonsPanel, BoxLayout.Y_AXIS));

		// mainButtonsPanel.setPreferredSize(new Dimension(40,40));
		mainButtonsPanel.setOpaque(false);
		mainButtonsPanel.setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));

		ImageIcon buttonImage = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_gloss - small.png"));

		playButton = CustomSwingUtilities.createButton("PLAY", buttonImage, Font.BOLD, 20, 230, 40);
		settingsButton = CustomSwingUtilities.createButton("SETTINGS", buttonImage, Font.BOLD, 20, 230, 40);
		scoreButton = CustomSwingUtilities.createButton("SCORE", buttonImage, Font.BOLD, 20, 230, 40);
		leaveButton = CustomSwingUtilities.createButton("EXIT", buttonImage, Font.BOLD, 20, 230, 40);

		leaveButton.setForeground(Color.BLACK.darker().darker().darker());
		scoreButton.setForeground(Color.BLACK.darker().darker().darker());
		settingsButton.setForeground(Color.BLACK.darker().darker().darker());
		playButton.setForeground(Color.BLACK.darker().darker().darker());

		ImageIcon pressedButton = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_border.png"));
		ImageIcon resizedPressedIcon = new ImageIcon(
				pressedButton.getImage().getScaledInstance(230, 40, Image.SCALE_SMOOTH));

		playButton.setPressedIcon(resizedPressedIcon);
		settingsButton.setPressedIcon(resizedPressedIcon);
		scoreButton.setPressedIcon(resizedPressedIcon);
		leaveButton.setPressedIcon(resizedPressedIcon);

		playButton.setOpaque(false);
		settingsButton.setOpaque(false);
		scoreButton.setOpaque(false);
		leaveButton.setOpaque(false);

		playButton.setBorderPainted(false);
		settingsButton.setBorderPainted(false);
		scoreButton.setBorderPainted(false);
		leaveButton.setBorderPainted(false);

		playButton.setFocusPainted(false);
		settingsButton.setFocusPainted(false);
		scoreButton.setFocusPainted(false);
		leaveButton.setFocusPainted(false);

		playButton.setContentAreaFilled(false);
		settingsButton.setContentAreaFilled(false);
		scoreButton.setContentAreaFilled(false);
		leaveButton.setContentAreaFilled(false);

		mainButtonsPanel.add(playButton);
		mainButtonsPanel.add(settingsButton);
		mainButtonsPanel.add(scoreButton);
		mainButtonsPanel.add(leaveButton);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(100, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;

		add(mainButtonsPanel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(0, 0, 160, 10);
		gbc.anchor = GridBagConstraints.CENTER;

		add(logo, gbc);

	}

	public JButton getPlayButton() {
		return playButton;
	}

	public JButton getSettingsButton() {
		return settingsButton;
	}

	public JButton getScoreButton() {
		return scoreButton;
	}

	public JButton getLeaveButton() {
		return leaveButton;
	}






}
