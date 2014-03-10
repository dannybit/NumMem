package com.dannybit.NumMem.Renderer;

public class SwitchingStates {

	private float timePassedBeforeSwitchingStates;
	private float timeBeforeSwitchingStates;
	
	public SwitchingStates(){
		reset();
	}
	
	public void reset(){
		timePassedBeforeSwitchingStates = 0.0f;
		timeBeforeSwitchingStates = 0.8f;
	}
	
	/**
	 * returns true if its time to switch state, false otherwise
	 * @param delta
	 * @return
	 */
	public boolean update(float delta){
		timePassedBeforeSwitchingStates += delta;
		if (timePassedBeforeSwitchingStates >= timeBeforeSwitchingStates){
			return true;
		}
		return false;
	}
}
