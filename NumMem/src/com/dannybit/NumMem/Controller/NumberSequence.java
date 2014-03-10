package com.dannybit.NumMem.Controller;

import java.util.Arrays;

public class NumberSequence {

	/**
	 * Sequence for the visible buttons on the screen. 
	 */
	private int[] sequence;
	private int sequenceCounter;
	private int currentTotalButtons;
	
	public NumberSequence(int totalButtons){
		sequence = new int[totalButtons];
		/* Sets the array to -1 since of the buttons will contain zero */
		for (int i = 0; i < sequence.length; i++){
			sequence[i] = -1;
		}
		sequenceCounter = -1;
		currentTotalButtons = totalButtons;
	}
	
	
	public void addNewButton(int num){
		sequence[sequenceCounter + 1] = num;
		sequenceCounter = 0;
	}
	
	public boolean isCorrect(int numToCheck){
		if (sequence[sequenceCounter] == numToCheck){
			return true;
		}
		return false;
	}
	
	public boolean isLastInSequence(){
		if (sequence[sequenceCounter + 1] == -1){
			return true;
		}
		return false;
	}
	

	
	public boolean endOfStage(){
		if (sequenceCounter == currentTotalButtons - 1){
			return true;
		}
		return false;
	}
	
	public int getSequenceCounter(){
		return this.sequenceCounter;
	}
	
	public void addSequenceCounter(){
		sequenceCounter++;
	}
	
	public void resetSequenceCounter(){
		sequenceCounter = 0;
	}
	
	public boolean isInSequence(int numberClicked){
		for (int i = 0; i < sequenceCounter; i++){
			if (sequence[i] == numberClicked){
				return true;
			}
		}
		return false;
	}
	
	public int getSequenceLength(){
		for (int i = 0; i < sequence.length; i++){
			if (sequence[i] == -1){
				return i;
			}
		}
		return sequence.length;
	}
	
	
}
