package com.dannybit.NumMem.Controller;

import java.util.Random;

public class SequenceCounter {
	
	private Random random;
	/* The number that will populate the button cells, this array will be shuffled to create a 
	 * random effect on the game and it will of size rowButtons * rowColumns 
	 * (rowButtons and rowColumns are incremented by 1 each time */
	private int[] numberTable;
	private int totalButtons;
	
	public SequenceCounter(int totalButtons){
		this.totalButtons = totalButtons;
		generateNewNumberTable();
	}
	
	private void generateNewNumberTable(){
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
}
