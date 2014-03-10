package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Controller.GameController;


public class NewStageScreen extends AbstractScreen {
	
	private Stage stage;
	private Table table;
	public static final String defaultStageString = "Stage ";
	private Label stageLabel;
	private LabelStyle largeTitleLabelStyle;
	private float timePassedOnNewStageScreen;
	private float timeToPassOnNewStageScreen = 1f;
	private GameScreen gameScreen;
	
	public NewStageScreen(NumMem game, GameScreen gameScreen, GameController gameController){
		super(game);
		this.gameScreen = gameScreen;
		largeTitleLabelStyle = new LabelStyle(getFontRenderer().getLargeTitleFont(), Color.WHITE);
		stageLabel = new Label(defaultStageString + gameController.getStageNumber(), largeTitleLabelStyle);
		stage = new Stage(NumMem.WIDTH, NumMem.HEIGHT);
		table = new Table();
		table.setFillParent(true);
		table.debug();
		stage.addActor(table);
	}
	
	@Override
	public void show(){
		timePassedOnNewStageScreen = 0.0f;
		table.add(stageLabel);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		if (shouldSwitchScreen()){
			reset();
			game.setScreen(gameScreen);
		}
		timePassedOnNewStageScreen += delta;
		stage.draw();
		//Table.drawDebug(stage);
	}
	
	public boolean shouldSwitchScreen(){
		if (timePassedOnNewStageScreen >= timeToPassOnNewStageScreen){
			return true;
		}
		return false;
	}
	
	public void reset(){
		timePassedOnNewStageScreen = 0.0f;
	}
}
