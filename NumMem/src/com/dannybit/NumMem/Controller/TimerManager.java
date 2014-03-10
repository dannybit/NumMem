package com.dannybit.NumMem.Controller;

/**
 * Manages the the time for the countdown timer depending on the sequence number and stage number
 * @author Daniel
 *
 */

public class TimerManager {

	
	public static int getTime(int stageNumber, int sequenceLength, int timeRemained){
		int stageMultiplier = GameController.MAX_STAGE - stageNumber + 1;
		int additionalSpecialTime = 0;
		if (stageMultiplier < 0){
			stageMultiplier = 1;
		}
		if (stageNumber <= GameController.MAX_STAGE){
			additionalSpecialTime = 20;
		}
		int totalSeconds = (sequenceLength) * stageMultiplier + additionalSpecialTime;
		return totalSeconds;
	}
	
	
}
