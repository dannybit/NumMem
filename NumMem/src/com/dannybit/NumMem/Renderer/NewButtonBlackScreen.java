package com.dannybit.NumMem.Renderer;

public class NewButtonBlackScreen {
	
	private float timePassedOnBlackScreen;
	private float timeOnBlackScreen;
	private boolean onBlackScreen;
	
	public NewButtonBlackScreen(){
		reset();
	}
	
	public void start(float timeOnBlackScreen){
		onBlackScreen = true;
		this.timeOnBlackScreen = timeOnBlackScreen;
	}
	
	public boolean update(float delta){
		timePassedOnBlackScreen += delta;
		if (timePassedOnBlackScreen >= timeOnBlackScreen){
			reset();
		}
		return onBlackScreen;
	}
	
	public void reset(){
		timePassedOnBlackScreen = 0.0f;
		timeOnBlackScreen = 0.0f;
		onBlackScreen = false;
	}
}
