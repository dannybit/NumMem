package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Controller.GameController;

public class SwitchingStageScreen extends AbstractScreen {

	private Stage stage;
	private Table table;
	private Label stageLabel;
	private String stageLabelString;
	private LabelStyle labelStyle;
	private GameController gameController;
	
	
	public SwitchingStageScreen(NumMem game, GameController gameController) {
		super(game);
		this.gameController = gameController;
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		labelStyle = new LabelStyle();
		labelStyle.font = getFontRenderer().getLargeTitleFont();
		stageLabel = new Label("Stage: ", labelStyle);
		table.add(stageLabel);
		stage.addActor(table);
	}
	
	@Override
	public void show(){
		stageLabel.setText(stageLabelString + gameController.getStageNumber());
	}
	
	@Override
	public void render(float delta){
		stage.draw();
	}
}
