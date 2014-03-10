package com.dannybit.NumMem.Models;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dannybit.NumMem.Screens.GameScreen;

public class PauseDialog extends Dialog {

	private GameScreen gameScreen;
	public PauseDialog(String string, Skin skin, GameScreen gameScreen) {
		super(string, skin);
		this.gameScreen = gameScreen;
		
	}
	
	@Override
	public void result(Object object){
		if (((String) object).equals("Y")){
			gameScreen.endGame();
		}
		else if (((String) object).equals("N")){
			gameScreen.unpause();
		}
	}

}
