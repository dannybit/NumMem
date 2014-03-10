package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.Game;
import com.dannybit.NumMem.NumMem;

public class NewButtonScreen extends AbstractScreen {

	private Game game;
	private GameScreen gameScreen;
	private float timePassedBlackScreen = 0.0f;
	private float timeOnBlackScreen = 0.5f;
	private boolean onBlackScreen;
	
	public NewButtonScreen(NumMem game, GameScreen gameScreen) {
		super(game);
		this.game = game;
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void show(){
		onBlackScreen = true;
	}
	
	@Override
	public void render(float delta){
		if (!onBlackScreen){
			reset();
			game.setScreen(gameScreen);
		}
		timePassedBlackScreen += delta;
		if (timePassedBlackScreen >= timeOnBlackScreen){
			onBlackScreen = false;
		}
		super.render(delta);
		
	}
	
	private void reset(){
		timePassedBlackScreen = 0.0f;
		timeOnBlackScreen = 0.5f;
	}

}
