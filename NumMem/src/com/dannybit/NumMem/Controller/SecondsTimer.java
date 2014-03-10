package com.dannybit.NumMem.Controller;

public class SecondsTimer {
	
	private int secondsPassed;
	private int minutesPassed;
	private float deltaPassed;
	
	public SecondsTimer(){
		minutesPassed = 0;
		secondsPassed = 0;
		deltaPassed = 0f;
	}
	
	/**
	 * Adds the delta to the secondtimer, if 1 second is passed it returns true, if not it returns false
	 * @param delta Time passed since last render
	 * @return
	 */
	public boolean update(float delta){
		addDelta(delta);
		if (isSecondPassed()){
			resetDeltaPassed();
			secondsPassed++;
			if (isMinutePassed()){
				minutesPassed++;
				secondsPassed = 0;
			}
			return true;
		}
		return false;
	}
	
	private void addDelta(float delta){
		deltaPassed += delta;
	}
	
	private boolean isSecondPassed(){
		if (deltaPassed > 1){
			return true;
		}
		return false;
	}
	
	private boolean isMinutePassed(){
		if (secondsPassed > 60){
			return true;
		}
		return false;
	}
	
	private void resetDeltaPassed(){
		deltaPassed = deltaPassed - 1;
	}
	
	public int getSecondsPassed(){
		return this.secondsPassed;
	}
	
	public int getMinutesPassed(){
		return this.minutesPassed;
	}
	
	@Override
	public String toString(){
		String minutesPassedString = String.valueOf(minutesPassed);
		String secondsPassedString = String.valueOf(secondsPassed);
		if (minutesPassed < 10){
			minutesPassedString = "0" + minutesPassedString;
		}
		if (secondsPassed < 10){
			secondsPassedString = "0" + secondsPassedString;
		}
		return minutesPassedString + ":" + secondsPassedString;
	}
}
