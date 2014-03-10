package com.dannybit.NumMem.Renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dannybit.NumMem.Controller.GameController;
import com.dannybit.NumMem.Utils.UtilMath;

public class SwitchingStatesRenderer {

	private GameController gameController;
	private BitmapFont newStageFont;
	private SpriteBatch batch;
	private String stageMessage = "Stage: ";
	private String levelMessage = "Level: ";
	private boolean switchingStates;
	private SpriteBatch spriteBatch;
	
	public SwitchingStatesRenderer(FontRenderer renderer, GameController gameController){
		spriteBatch = new SpriteBatch();
		this.newStageFont = renderer.getNewStageFont();
		this.gameController = gameController;
		this.switchingStates = false;
	}
	
	public boolean switchingStates(){
		return switchingStates;
	}
	
	public void renderStageLabelScreen(float delta){
		batch.begin();
		stageMessage += gameController.getStageNumber();
		newStageFont.draw(batch, stageMessage, 
				Gdx.graphics.getWidth() / 2 - newStageFont.getBounds(stageMessage).width/2, 
				Gdx.graphics.getHeight() / 2 + newStageFont.getBounds(stageMessage).height/2);
		batch.end();
	}
	
}
