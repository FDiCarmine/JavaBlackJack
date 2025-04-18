
package controller;

import java.awt.event.ActionListener;
import java.io.IOException;


import model.GameManager;
import view.AudioManager;
import view.MainView;

public class Main {

	

	@SuppressWarnings("deprecation")
	public Main(GameManager gm, MainView mv, ActionListener alv) throws IOException, InterruptedException {

		gm.addObserver(mv);
		mv.addChip5ButtonListener(alv);
		mv.addChip25ButtonListener(alv);
		mv.addChip50ButtonListener(alv);
		mv.addChip100ButtonListener(alv);
		mv.addExitButtonListener(alv);
		mv.addDealButtonListener(alv);
		mv.addHitButtonListener(alv);
		mv.addStandButtonListener(alv);
		mv.addDoubleButtonListener(alv);
		mv.addSplitButtonListener(alv);
		mv.addPlayButtonListener(alv);
		mv.addLeaveButtonListener(alv);
		mv.addSetingsButtonListener(alv);
		mv.addLeaveButtonListener(alv);
		mv.addCheckBox1Listener(alv);
		mv.addCheckBox2Listener(alv);
		mv.addCheckBox3Listener(alv);
		mv.addCheckBox4Listener(alv);
		mv.addResetScoreBoardButton(alv);
		mv.addPlayersBox1Listener(alv);
		mv.addPlayersBox2Listener(alv);
		mv.addPlayersBox3Listener(alv);
		mv.addBackButtonListener(alv);
		mv.addScoreButtonListener(alv);
		mv.addBackToMenuButtonListener(alv);
		gm.setPlayingOnSecondHand(false);
		gm.setSecondHandPlayerStand(false);
		gm.getHumanPlayer().setHasChosenToSplit(false);
		gm.setIsHumanReadyToStartRound(false);
		mv.setDealButtonStatus(true);
		gm.setPlayerStands(false);
		gm.setSecondHandPlayerStand(false);
		mv.revalidate();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		AudioManager am = AudioManager.getInstance();
		String soundtrack = am.getClass().getResource("/Audio/playlist/soundtrack.wav").getPath();// colonna sonora
		am.play(soundtrack);
		GameManager gm = GameManager.getInstance();
		MainView mv = MainView.getInstance();
		ActionListener alv = new ViewListeners();

		@SuppressWarnings("unused")
		Main m = new Main(gm, mv, alv);
		boolean enoughMoney = gm.getHumanPlayer().getRemainingChips() >= 0;

		while (enoughMoney) {

			if (gm.isRestarting())
				enoughMoney = false;
			enoughMoney = gm.getHumanPlayer().getRemainingChips() >= 0;
 			Thread.sleep(100);
			String playerCredits = gm.getHumanPlayer().getRemainingChips() + "";
			mv.updateCreditLabel(playerCredits);
			mv.revalidate();

			boolean waitingForHumanBet = gm.isHumanReadyToStartRound();
			boolean hasPlayerEndedNormalRound = gm.isPlayerStand();
			boolean hasPlayerEndedSplitRound = gm.isSecondHandPlayerStand()
					|| gm.getHumanPlayer().isSplitBusted(gm.getHumanPlayer().getSecondHand());

			boolean isSplitRoundStarted = gm.getHumanPlayer().hasChosenToSplit();

			while (waitingForHumanBet) {

				mv.setDealButtonStatus(false);
				mv.chipsButtonsStatus(false);
				gm.dealInitialBets();
				gm.dealInitialCards();
				gm.setIsHumanReadyToStartRound(false);
				mv.setHitButtonStatus(true);
				mv.setStandButtonStatus(true);
				mv.setDoubleButtonStatus(true);

				if (gm.getHumanPlayer().isSpecialRound()) {
					mv.setSplitButtonStatus(true);
				}

				break;

			}
			while (!isSplitRoundStarted && hasPlayerEndedNormalRound) {

				mv.setHitButtonStatus(false);
				mv.setStandButtonStatus(false);
				mv.setDoubleButtonStatus(false);
				mv.setSplitButtonStatus(false);

				gm.playBotTurns();

				gm.resolveBets();

				gm.updateRoundSolver();
				gm.setPlayerStands(false);
				gm.HandCleaner();
				mv.reset();
				gm.restartDeck();

				if (gm.getHumanPlayer().getRemainingChips() == 0) {
					enoughMoney = !enoughMoney;
					mv.printGameOver();
					gm.getHumanPlayer().reset();
					enoughMoney = gm.getHumanPlayer().getRemainingChips() >= 0;
					mv.reset();
					mv.switchToMainMenu();

				}

				m = new Main(gm, mv, alv);
				break;
			}

			while (hasPlayerEndedNormalRound && hasPlayerEndedSplitRound) {

				mv.repaint();
				mv.setHitButtonStatus(false);
				mv.setStandButtonStatus(false);
				mv.setDoubleButtonStatus(false);
				mv.setSplitButtonStatus(false);
				gm.playBotTurns();

				gm.resolveBets();
				gm.updateRoundSolver();
				gm.setPlayerStands(false);
				gm.HandCleaner();

				mv.reset();
				gm.restartDeck();

				if (gm.getHumanPlayer().getRemainingChips() == 0) {
					enoughMoney = !enoughMoney;
					mv.printGameOver();
					gm.getHumanPlayer().reset();
					enoughMoney = gm.getHumanPlayer().getRemainingChips() >= 0;
					mv.reset();

				}

				m = new Main(gm, mv, alv);

				break;
			}

		}

	}

}
