package com.dannybit.NumMem.Models;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class GameButton {
	
	private int num;
	private TextButton textButton;
	private Skin skin;
	public static String neutralDrawableName = "neutral_button";
	
	
	/**
	 * Returns an array of non-visible buttons given an array of integer
	 * @param numArray
	 * @param skin
	 * @return
	 */
	public static GameButton[] getGameButtonArray(int[] numArray, Skin skin){
		GameButton[] buttons = new GameButton[numArray.length];
		for (int i = 0; i < numArray.length; i++){
			buttons[i] = new GameButton(numArray[i], skin);
		}
		return buttons;
	}
	
	public GameButton(int num, Skin skin){
		this.num = num;
		this.skin = skin;
		this.textButton = new TextButton(Integer.toString(num), new TextButtonStyle(skin.get("TextButtonStyle", TextButtonStyle.class)));
		this.textButton.setVisible(false);
	}
	
	public void setVisible(boolean visible){
		this.textButton.setVisible(visible);
	}
	
	public boolean getVisible(){
		return this.textButton.isVisible();
	}
	
	public TextButton getTextButton(){
		return this.textButton;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public void setTextButtonStyle(TextButtonStyle buttonStyle){
		this.textButton.setStyle(buttonStyle);
	}
	
	public void setNeutral(){
		textButton.getStyle().up = skin.getDrawable("neutral_button");
	}
	
	public void setCorrect(){
		textButton.getStyle().up = skin.getDrawable("correct_button");
	}
	
	public void setWrong(){
		textButton.getStyle().up = skin.getDrawable("wrong_button");
	}
	@Override
	public String toString(){
		return Integer.toString(getNum());
	}
	
	
}
