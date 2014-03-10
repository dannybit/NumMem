package com.dannybit.NumMem.Renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dannybit.NumMem.NumMem;

public class FontRenderer {

	private FreeTypeFontGenerator fontGenerator;
	/* Font used by FontGenerator to create fonts of different sizes */
	private final String FONT_NAME = "data/new_font.ttf";
	private BitmapFont largeTitleFont;
	private BitmapFont smallTitleFont;
	private BitmapFont dialogFont;
	private BitmapFont dialogTitleFont;
	private BitmapFont newStageFont;
	private BitmapFont logoLabelFont;
	private BitmapFont instructionsTitleFont;
	private BitmapFont instructionsTextFont;
	
	public FontRenderer(){
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
		largeTitleFont = fontGenerator.generateFont(NumMem.WIDTH / 16);
		smallTitleFont = fontGenerator.generateFont(NumMem.WIDTH / 26);
		fontGenerator.dispose();
		fontGenerator = null;
	}
	
	public BitmapFont getLargeTitleFont(){
		return this.largeTitleFont;
	}
	
	public BitmapFont getSmallTitleFont(){
		return this.smallTitleFont;
	}

	public void disposeFonts() {
		largeTitleFont.dispose();
		smallTitleFont.dispose();
		
	}

	public BitmapFont getDialogFont() {
		if (dialogFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			dialogFont = fontGenerator.generateFont(NumMem.WIDTH / 22);
			fontGenerator.dispose();
		}
		return dialogFont;
	}

	public BitmapFont getDialogTitleFont() {
		if (dialogTitleFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			dialogTitleFont = fontGenerator.generateFont(NumMem.WIDTH / 18);
			fontGenerator.dispose();
		}
		return dialogTitleFont;
	}

	public BitmapFont getNewStageFont() {
		if (newStageFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			newStageFont = fontGenerator.generateFont(NumMem.WIDTH / 16);
			fontGenerator.dispose();
		}
		return newStageFont;
	}
	
	public BitmapFont getLogoLabelFont(){
		if (logoLabelFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			logoLabelFont = fontGenerator.generateFont(NumMem.WIDTH / 8);
			fontGenerator.dispose();
		}
		return logoLabelFont;
	}
	
	public BitmapFont getInstructionsTitleFont(){
		if (instructionsTitleFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			instructionsTitleFont = fontGenerator.generateFont(NumMem.WIDTH / 12);
			fontGenerator.dispose();
		}
		return instructionsTitleFont;
	}
	
	public BitmapFont getInstructionsTextFont(){
		if (instructionsTextFont == null){
			fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
			instructionsTextFont = fontGenerator.generateFont(NumMem.WIDTH / 32);
			fontGenerator.dispose();
		}
		return instructionsTextFont;
	}

	
	
}
