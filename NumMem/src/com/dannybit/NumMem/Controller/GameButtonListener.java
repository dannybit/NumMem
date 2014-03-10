package com.dannybit.NumMem.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dannybit.NumMem.NumMem;

public class GameButtonListener extends ChangeListener {

	private MainController controller;
	
	public GameButtonListener(MainController controller){
		this.controller = controller;
	}
	
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		if (controller.alreadyWrongButton()){
			return;
		}
		TextButton buttonClicked = ((TextButton) actor);
		int numberClicked = Integer.parseInt(buttonClicked.getLabel().getText().toString());
		if (controller.isCorrectNumber(numberClicked)){
			changeButtonToCorrect(buttonClicked);
		    controller.nextButtonInSequence();
		}
		/* Not correct */
		else {
			/* The button is already green */
			if (controller.alreadyClicked(numberClicked)){
				return;
			};
			changeButtonToIncorrect(buttonClicked);
			controller.wrongButton();
		}
	}
	
	private void changeButtonToCorrect(TextButton buttonToChange){
		buttonToChange.getStyle().up = controller.getGameScreen().getMainSkin().getDrawable("correct_button");
	}
	
	private void changeButtonToIncorrect(TextButton buttonToChange){
		buttonToChange.getStyle().up = controller.getGameScreen().getMainSkin().getDrawable("wrong_button");
	}

}
