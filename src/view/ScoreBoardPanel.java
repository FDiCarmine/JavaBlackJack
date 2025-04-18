package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ScoreBoardPanel extends JPanel {

	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton backToMenuButton;

	public ScoreBoardPanel() {

		Border thickBorder = new LineBorder(Color.BLACK, 5);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(thickBorder, "SCOREBOARD");

		titledBorder.setTitleJustification(TitledBorder.CENTER);

		titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 24));
		titledBorder.setTitleColor(Color.ORANGE);

		scrollPane.setBorder(titledBorder);

		float[] colors = Color.RGBtoHSB(0, 102, 51, null);
		setLayout(new BorderLayout());
		setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		setOpaque(true);

		textAreaSetters(textArea);
		scrollPane.setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		scrollPane.setOpaque(true);

		add(scrollPane, BorderLayout.CENTER);

		textAreaWriter();

		ImageIcon buttonImage = new ImageIcon(
				getClass().getResource("/GameButtons/button_rectangle_depth_gloss - small.png"));

		backToMenuButton = CustomSwingUtilities.createButton("BACK TO MENU ", buttonImage, Font.TYPE1_FONT, 20, 200,
				50);
		backToMenuButton.setBorderPainted(false);
		backToMenuButton.setOpaque(false);
		backToMenuButton.setRequestFocusEnabled(false);
		backToMenuButton.setFocusPainted(false);

		add(backToMenuButton, BorderLayout.SOUTH);

		setVisible(true);
	}

	public JButton getBackToMenuButton() {
		return backToMenuButton;
	}

	private void textAreaWriter() {
		String projectPath = System.getProperty("user.dir");
		File scores = new File(projectPath, "Scores.txt");
		String path = scores.getAbsolutePath();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				textArea.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void textAreaSetters(JTextArea textArea) {

		float[] colors = Color.RGBtoHSB(0, 102, 51, null);
		textArea.setFont(new Font("Serif", Font.BOLD, 20));
		textArea.setEditable(true);
		textArea.setForeground(Color.white.brighter().brighter());
		textArea.setBackground(Color.getHSBColor(colors[0], colors[1], colors[2]));
		textArea.setOpaque(true);
	}

	public void updateScoreBoardPanel() {
		clearTextArea();
		textAreaWriter();
		textArea.revalidate();
		textArea.repaint();
		revalidate();
		repaint();
	}

	public void clearTextArea() {
		textArea.setText(""); 
		revalidate(); 
		repaint(); 
	}

}
