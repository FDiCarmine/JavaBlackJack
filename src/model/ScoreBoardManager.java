package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 * Manages scoreboard and player nickname persistence using file I/O operations.
 * Responsible for saving player scores, checking for existing nicknames,
 * and resetting score and nickname records.
 */
public class ScoreBoardManager {
	/** Absolute path to the Scores file. */
	private String path;
	/** Absolute path to the Names file. */
	private String namesPath;
    /**
     * Appends the current player's name and remaining chips to the Scores.txt file.
     * @param hp the HumanPlayer whose data will be saved
     */
	public void saveData(HumanPlayer hp) {
		
		String projectPath = System.getProperty("user.dir");
		File scores = new File(projectPath, "Scores.txt");
		path = scores.getAbsolutePath();

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {

			bufferedWriter.write("Name: " + hp.getName() + "   Score: " + hp.getRemainingChips() + "$");
			bufferedWriter.newLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    /**
     * Appends a new player name to the Names.txt file.
     * @param name the player name to be saved
     */
	public void addNameToFile(String name) {

		String projectPath = System.getProperty("user.dir");
		File names = new File(projectPath, "Names.txt");
		namesPath = names.getAbsolutePath();
		try (BufferedWriter bufferedWriterTwo = new BufferedWriter(new FileWriter(namesPath, true))) {

			bufferedWriterTwo.write(name);
			bufferedWriterTwo.newLine();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Checks whether the given nickname is already present in the Names.txt file.
     * @param name the nickname to check
     * @return true if the nickname already exists, false otherwise
     */
	public boolean checkForNicknameAlreadyTaken(String name) {

		String projectPath = System.getProperty("user.dir");
		File names = new File(projectPath, "Names.txt");
		ArrayList<String> nickNames = new ArrayList<>();
		namesPath = names.getAbsolutePath();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(namesPath))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				nickNames.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nickNames.contains(name);

	}
    /**
     * Clears the contents of both Scores.txt and Names.txt.
     * This method effectively resets the scoreboard and the saved player names.
     * @throws IOException if an I/O error occurs while resetting the files
     */
	public void resetScores() throws IOException {
		String projectPath = System.getProperty("user.dir");
		Path scorePath = Paths.get(projectPath,"Scores.txt");
		Path namesPath = Paths.get(projectPath,"Names.txt");
        
        try {
        	Files.newBufferedWriter(scorePath).flush();
        	 Files.newBufferedWriter(namesPath).flush();
            Files.newBufferedWriter(scorePath).close();
            Files.newBufferedWriter(namesPath).close();
     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
