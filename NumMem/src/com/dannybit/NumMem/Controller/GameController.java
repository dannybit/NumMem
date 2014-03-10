package com.dannybit.NumMem.Controller;

import java.util.HashMap;
import java.util.Map;

public class GameController {
	
	public static int MAX_STAGE = 6;
	private int level;
	private int score;
	private int lives;
	public static int INITIAL_LIVES = 3;
	private int stageNumber;
	private int currentStageScore;
	
	
	public GameController(){
		newGame();
	}
	
	public void newGame(){
		this.level = 1;
		this.score = 0;
		this.stageNumber = 1;
		this.currentStageScore = 0;
		this.lives = 3;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	

	public int addScore(int scoreToAdd){
		this.score += scoreToAdd;
		currentStageScore += scoreToAdd;
		return score;
	}
	
	public void removeScore(int scoreToRemove){
		if (score - scoreToRemove < 0){
			score = 0;
			return;
		}
		this.score -= scoreToRemove;
	}
	
	public void removeLife(){
		lives--;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public boolean pastLimit(){
		return this.stageNumber > MAX_STAGE;
	}
	
	/**
	 * Adds level and reset the stage number.
	 */
	public void newLevel(){
		level++;
		stageNumber = 1;
	}

	public void setStageNumber(int stageNumber) {
		this.stageNumber = stageNumber;
	}
	
	public int getStageNumber(){
		return this.stageNumber;
	}
	
	public int addStageNumber(){
		currentStageScore = 0;
		return ++stageNumber;
	}
	
	public int getCurrentStageScore(){
		return this.currentStageScore;
	}
	
	public void resetCurrentStageScore(){
		this.currentStageScore = 0;
	}

	public int getLives() {
		return lives;
	}
	
	
}
