package com.dannybit.NumMem.Controller;

import com.dannybit.NumMem.Models.GameButton;
import com.dannybit.NumMem.Screens.GameScreen;


public class MainController {
	
	private GameScreen gameScreen;
	private GameButton[] gameButtons;
	private GameButton firstButton;
	private int rowButtons;
	private int columnButtons;
	private NumberSequence numberSequence;
	private GameController gameController;
	private NumberTable numberTable;
	private GameButtonListener buttonListener;
	private boolean newGame;
	private CountdownTimer countdownTimer;
	private boolean firstTimeUpdating;

	public MainController(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		gameController = new GameController();
		this.rowButtons = 2;
		this.columnButtons = rowButtons;
		countdownTimer = new CountdownTimer();
	}
	
	public GameButtonListener getButtonListener(){
		if (buttonListener == null){
			buttonListener = new GameButtonListener(this);
		}
		return this.buttonListener;
	}
	
	public void startGame(){
		newGame = true;
		newStage(true);
		firstTimeUpdating = true;
	}
	
	public boolean update(float delta){
		if (firstTimeUpdating){
			countdownTimer.update(delta);
			firstTimeUpdating = false;
			return true;
		}
		/* Check only when second passed */
		if (countdownTimer.update(delta)){
			if (countdownTimer.isCountdownOver()){
				timeOver();
				countdownTimer.reset();
				firstTimeUpdating = true;
				return false;
			}
			return true;
		}
		return false;
	}
	
	public CountdownTimer getCountdownTimer(){
		return this.countdownTimer;
	}
	
	public void newStage(boolean firstStage){
		if (!firstStage){
			addStage();
		}
		numberSequence = new NumberSequence(getTotalButtons());
		numberTable = new NumberTable(getTotalButtons());
		gameButtons = GameButton.getGameButtonArray(numberTable.getTableArray(), gameScreen.getMainSkin());
		firstButton = gameButtons[numberTable.getRandomButtonIndex()];
		firstButton.setVisible(true);
		numberSequence.addNewButton(firstButton.getNum());
		firstTimeUpdating = true;
		startTimer(TimerManager.getTime(gameController.getStageNumber(), numberSequence.getSequenceLength(), countdownTimer.getPreviousSecondsPassed()));
		if (newGame){
			gameScreen.getGameTable().startTable(gameButtons, getButtonListener(), rowButtons, columnButtons);
			newGame = false;
		}
		else {
			gameScreen.newStage(gameButtons, getButtonListener(), rowButtons, columnButtons);
		}
	}
	
	public void wrongButton(){
		removeScore(5);
		gameScreen.wrongButton();
	}
	
	private void timeOver(){
		if (gameController.getLives() == 0){
			gameScreen.gameLost();
			return;
		}
		removeScore(gameController.getCurrentStageScore());
		gameController.resetCurrentStageScore();
		gameScreen.timeOver();
		gameController.removeLife();
		gameScreen.getGameTable().removeLife();
	}
	
	private void removeScore(int scoreToRemove){
		gameController.removeScore(scoreToRemove);
		gameScreen.getGameTable().changeScoreLabel(gameController.getScore());
	}
	
	
	
	public boolean alreadyWrongButton(){
		/* already clicked a wrong button */
		return gameScreen.getWrongButton();
	}
	
	public boolean alreadyClicked(int numberClicked){
		return numberSequence.isInSequence(numberClicked);
	}
	
	public void addScore(){
		gameController.addScore(countdownTimer.getTotalSeconds() + 10);
		gameScreen.getGameTable().changeScoreLabel(gameController.getScore());
	}
	
	public void addStage(){
		int newStage = gameController.addStageNumber();
		gameScreen.getGameTable().changeStageNumberLabel(newStage);
		if (!gameController.pastLimit()){
			rowButtons++;
			columnButtons = rowButtons;
		}
	}
	
	
	
	public void resetSequence(){
		for (int i = 0; i < gameButtons.length; i++){
			gameButtons[i].setNeutral();
		}
		numberSequence.resetSequenceCounter();
	}
	
	public void nextButtonInSequence(){
		if (numberSequence.endOfStage() || numberSequence.isLastInSequence()){
			addScore();
			countdownTimer.reset();
			if (numberSequence.endOfStage()){
				gameScreen.newStage();
			}
			else if (numberSequence.isLastInSequence()){
				gameScreen.newButton();
			}
		}
		else {
			numberSequence.addSequenceCounter();
		}
	}
	
	
	public void addNewButton(){
		resetButtonsStyle();
		GameButton buttonToAdd = gameButtons[numberTable.getRandomButtonIndex()];
		buttonToAdd.setVisible(true);
		numberSequence.addNewButton(buttonToAdd.getNum());
		startTimer(TimerManager.getTime(gameController.getStageNumber(), numberSequence.getSequenceLength(), countdownTimer.getPreviousSecondsPassed()));
	}
	
	private void resetButtonsStyle(){
		for (int i = 0; i < gameButtons.length; i++){
			gameButtons[i].setNeutral();
		}
	}
	
	public GameController getGameController(){
		return this.gameController;
	}
	
	public boolean isCorrectNumber(int numberClicked){
		return numberSequence.isCorrect(numberClicked);
	}
	
	public GameButtonListener getGameButtonListener(){
		if (buttonListener == null){
			buttonListener = new GameButtonListener(this);
		}
		return this.buttonListener;
	}
	
	public void addStageNumber(){
		gameController.addStageNumber();
	}
	
	public int[] getNumberTable(){
		return numberTable.getTableArray();
	}
	
	public GameButton[] getGameButtons(){
		return this.gameButtons;
	}
	
	public int getLevel(){
		return gameController.getLevel();
	}
	
	
	public boolean isLastNumber(){
		return numberSequence.isLastInSequence();
	}

	
	public GameScreen getGameScreen(){
		return this.gameScreen;
	}
	
	public int getTotalButtons(){
		return this.rowButtons * this.columnButtons;
	}
	
	public void startTimer(int timeOfTimer){
		firstTimeUpdating = true;
		countdownTimer.startTimer(timeOfTimer);
	}
	
}
