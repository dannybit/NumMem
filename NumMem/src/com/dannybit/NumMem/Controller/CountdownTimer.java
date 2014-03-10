package com.dannybit.NumMem.Controller;

/**
 * Countdown timer used to know how much time left to guess the sequence in  before the player loses
 * @author Daniel
 *
 */

public class CountdownTimer {
	
	private int seconds;
	private int minutes;
	private float deltaPassed;
	private boolean timerStopped;
	private int previousSecondsPassed;
	
	public CountdownTimer(){
		reset();
	}
	
	public void reset(){
		this.previousSecondsPassed = seconds;
		this.seconds = 0;
		this.minutes = 0;
		this.deltaPassed = 0f;
		timerStopped = true;
	}
	
	public void startTimer(int totalSeconds){
		this.minutes = totalSeconds / 60;
		this.seconds = totalSeconds % 60;
		timerStopped = false;
	}
	
	/**
	 * Updates the timer, returns true for every second that passes so the renderer knows when to update the label.
	 * @param delta
	 * @return
	 */
	public boolean update(float delta){
		if (timerStopped){
			return false;
		}
		addDelta(delta);
		if (isSecondPassed()){
			resetDeltaPassed();
			seconds--;
			if (seconds < 0){
				minutes--;
				seconds = 60;
			}
			return true;
		}
		return false;
	}

	
	public boolean isCountdownOver(){
		if (getMinutes() <= 0 && getSeconds() <= 0){
			return true;
		}
		return false;
	}
	
	
	private void addDelta(float delta){
		this.deltaPassed += delta;
	}
	
	private boolean isSecondPassed(){
		if (deltaPassed > 1){
			return true;
		}
		return false;
	}
	
	private void resetDeltaPassed(){
		deltaPassed = deltaPassed - 1;
	}
	
	public int getSeconds(){
		return this.seconds;
	}
	
	public int getMinutes(){
		return this.minutes;
	}
	
	@Override
	public String toString(){
		String minutesString = "";
		if (getMinutes() < 10){
			minutesString += "0";
		}
		minutesString += String.valueOf(getMinutes());
		String secondsString = "";
		if (getSeconds() < 10){
			secondsString += "0";
		}
		secondsString += String.valueOf(getSeconds());

		return  minutesString + ":" + secondsString;
	}
	
	public int getTotalSeconds(){
		return (60 * minutes) + seconds;
	}
	
	public int getTotalTimeRemained(){
		return getTotalSeconds();
	}
	
	public int getPreviousSecondsPassed(){
		return this.previousSecondsPassed;
	}
	
}
