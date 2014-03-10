package com.dannybit.NumMem.Controller;

public class WrongButtonSelected {

	private boolean wrongButtonSelected;
	private float timePassedSinceWrongButtonSelected;
	private float timeToPassWithWrongButtonSelected;
	
	public WrongButtonSelected(){
		wrongButtonSelected = false;
		timePassedSinceWrongButtonSelected = 0.0f;
		timeToPassWithWrongButtonSelected = 0.5f;
	}
	
	public void start(float timeToPassWithWrongButtonSelected){
		timePassedSinceWrongButtonSelected = 0.0f;
		this.timeToPassWithWrongButtonSelected = timeToPassWithWrongButtonSelected;
		wrongButtonSelected = true;
	}
	
	public boolean update(float delta){
		timePassedSinceWrongButtonSelected += delta;
		if (timePassedSinceWrongButtonSelected >= timeToPassWithWrongButtonSelected){
			timePassedSinceWrongButtonSelected = 0;
			wrongButtonSelected = false;
		}
		return !wrongButtonSelected;
	}
	
	public boolean isWrongButtonSelected(){
		return this.wrongButtonSelected;
	}
	
	
}
