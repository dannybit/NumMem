package com.dannybit.NumMem.Controller;

import java.util.Random;

public class NumberTable {
	
	private int[] numberTable;
	private int[] randomPositionTable;
	private int unstruckIndices;
	private int currentTotalButtons;
	private Random random;
	
	public NumberTable(int totalButtons){
		random = new Random();
		generateNewNumberTable(totalButtons);
		generateNewRandomPositionTable(totalButtons);
		this.currentTotalButtons = totalButtons;
	}
	
	private void generateNewNumberTable(int totalButtons){
		/* create array of size rowButtons * rowColumns, fill it with incremental numbers, and shuffle it */
		numberTable = new int[totalButtons];
		fillTable(numberTable);
		shuffleArray(numberTable);
	}
	
	/* fills the array with integers from 0 to the array length */
	private void fillTable(int[] numbers){
		for (int i = 0; i < numbers.length; i++){
			numbers[i] = i;
		}
	}
	
	/* Shuffles a primitives array of integer using Fisher–Yates */
	private void shuffleArray(int[] numbers){
		for (int i = numbers.length - 1; i > 0; i--)
	    {
	      int index = random.nextInt(i + 1);
	      int a = numbers[index];
	      numbers[index] = numbers[i];
	      numbers[i] = a;
	    }
	}
	
	private void generateNewRandomPositionTable(int totalButtons){
		randomPositionTable = new int[totalButtons];
		fillTable(randomPositionTable);
		unstruckIndices = totalButtons;
	}
	
	/* Returns random index of Button to show on the screen */
	public int getRandomButtonIndex(){
		int randomInt = random.nextInt(unstruckIndices);
		int temp = randomPositionTable[randomInt];
		randomPositionTable[randomInt] = randomPositionTable[unstruckIndices - 1];
		randomPositionTable[unstruckIndices - 1] = temp;
		unstruckIndices--;
		return temp;
	}
	
	public void newTable(int totalButtons){
		generateNewNumberTable(totalButtons);
		generateNewRandomPositionTable(totalButtons);
		this.currentTotalButtons = totalButtons;
	}
	
	public int[] getTableArray(){
		return this.numberTable;
	}
}
