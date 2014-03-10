package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Utils.UtilMath;

public class InstructionsScreen extends AbstractScreen {

	private static Label instructionsTitle;
	private Label instructions1;
	private Label instructions2;
	private Label instructions3;
	private Label instructions4;
	private static String instructionsText1 = "Click the nubered button";
	private static String instructionsText2 = "A new button will appear";
	private static String instructionsText3 = "Guess the sequence that buttons appearead";
	private static String instructionsText4 = "Watch out for the time, GOOD LUCK!";
	private Stage stage;
	private Table table;
	private Table textTable;
	private TextButton textButton;
	private TextButtonStyle buttonStyle;
	private MenuScreen menuScreen;
	
	public InstructionsScreen(NumMem game, MenuScreen menuScreen) {
		super(game);
		this.menuScreen = menuScreen;
	}
	
	@Override
	public void show(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = getFontRenderer().getSmallTitleFont();
		buttonStyle.up = getSkin().getDrawable("neutral_button");
		buttonStyle.down = getSkin().getDrawable("correct_button");
		textButton = new TextButton("Return", buttonStyle);
		textButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(menuScreen);
			}
			
		});
		instructionsTitle = new Label("Instructions", new LabelStyle(getFontRenderer().getInstructionsTitleFont(), Color.WHITE));
		instructions1 = new Label(instructionsText1, new LabelStyle(getFontRenderer().getInstructionsTextFont(), Color.WHITE));
		instructions2 = new Label(instructionsText2, new LabelStyle(getFontRenderer().getInstructionsTextFont(), Color.WHITE));
		instructions3 = new Label(instructionsText3, new LabelStyle(getFontRenderer().getInstructionsTextFont(), Color.WHITE));
		instructions4 = new Label(instructionsText4, new LabelStyle(getFontRenderer().getInstructionsTextFont(), Color.WHITE));
		table.add(instructionsTitle).padBottom(UtilMath.percentageOf(10, NumMem.HEIGHT));
		table.row();
		textTable = new Table();
		textTable.add(instructions1);
		textTable.row();
		textTable.add(instructions2);
		textTable.row();
		textTable.add(instructions3);
		textTable.row();
		textTable.add(instructions4);
		table.add(textTable).center().padBottom(UtilMath.percentageOf(10, NumMem.HEIGHT));
		table.row();
		table.add(textButton).width(buttonStyle.font.getBounds(textButton.getText()).width + UtilMath.percentageOf(10, NumMem.WIDTH))
								.height(buttonStyle.font.getBounds(textButton.getText()).height + UtilMath.percentageOf(10, NumMem.HEIGHT));
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		stage.draw();
	}

}
