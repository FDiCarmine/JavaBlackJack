package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {

	private static AudioManager instance;
	private Clip clip;
	private FloatControl volumeControl;


	public static AudioManager getInstance() {
		if (instance == null) {
			instance = new AudioManager();
		}
		return instance;
	}

	private AudioManager() {
	}

	public void play(String filename) {
		try {

			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			clip.start();
			setVolume(0.0f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (clip != null) {
			clip.stop();
			clip.close();
		}
	}

	public void setVolume(float volume) {
		if (volumeControl != null) {
			volumeControl.setValue(volume);
		}
	}




}
