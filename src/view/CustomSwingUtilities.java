package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class CustomSwingUtilities {
	//CUSTOM BUTTON BUIDLER
	static JButton createButton(String text, ImageIcon icon, int fontStyle, int fontSize, int width, int height) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", fontStyle, fontSize));
		try {
			// Ridimensiona l'immagine
			Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

			// Crea un nuovo ImageIcon con l'immagine ridimensionata
			ImageIcon resizedIcon = new ImageIcon(resizedImage);

			// Imposta l'icona sul bottone
			button.setIcon(resizedIcon);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setVerticalTextPosition(SwingConstants.CENTER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return button;
	}
	//CUSTOM RADIOBUTTON BUILDER
	static JRadioButton createCustomRadioButton(String text, boolean selected, ImageIcon uncheckedIcon,ImageIcon checkedIcon, int width, int height) {
		JRadioButton radioButton = new JRadioButton(text, selected);
		try {
			// Ridimensiona le immagini
			Image resizedUncheckedImage = uncheckedIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			Image resizedCheckedImage = checkedIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

			// Crea nuovi ImageIcon con le immagini ridimensionate
			ImageIcon resizedUncheckedIcon = new ImageIcon(resizedUncheckedImage);
			ImageIcon resizedCheckedIcon = new ImageIcon(resizedCheckedImage);

			radioButton.setIcon(resizedUncheckedIcon);
			radioButton.setSelectedIcon(resizedCheckedIcon);
			radioButton.setDisabledIcon(resizedUncheckedIcon);
			radioButton.setDisabledSelectedIcon(resizedCheckedIcon);
			radioButton.setOpaque(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return radioButton;
	}

	static JPanel createPlayerPanel(String playerName) {
		JPanel playerPanel = new JPanel();
		// playerPanel.setBorder(BorderFactory.createTitledBorder(playerName)); // Bordo con nome del giocatore
		playerPanel.setPreferredSize(new Dimension(200, 200)); // Dimensione arbitraria per il pannello del giocatore
		return playerPanel;
	}

	/**
     * Ridimensiona un'immagine dalle dimensioni specificate.
     *
     * @param imagePath il percorso dell'immagine originale
     * @param width     la larghezza desiderata
     * @param height    l'altezza desiderata
     * @return l'immagine ridimensionata
     * @throws IOException se si verifica un errore durante la lettura dell'immagine
     */
     static Image resizeImage(String imagePath, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(view.MainView.class.getResource(imagePath));
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }





}

