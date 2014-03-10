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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Controller.GameController;
import com.dannybit.NumMem.Models.AssetManager;
import com.dannybit.NumMem.Utils.UtilMath;

public class EndGameScreen extends AbstractScreen {

	private NumMem game;
	private GameController gameController;
	private String gameOverString = "Game Over";
	private Label gameOverLabel;
	private LabelStyle labelTitleStyle;
	private LabelStyle labelTextStyle;
	private Stage stage;
	private Table table;
	private Label scoreLabel;
	private Label stageNumberLabel;
	private TextField nameTextField;
	private TextFieldStyle textFieldStyle;
	public static int NAME_LETTERS_LIMIT = 8;
	private TextButtonStyle textButtonStyle;
	private TextButton submitButton;
	private boolean isHighScore;
	
	public EndGameScreen(NumMem game, GameController gameController){
		super(game);
		this.game = game;
		this.gameController = gameController;
	}
	
	@Override
	public void show(){
		super.show();
		stage = new Stage(NumMem.WIDTH, NumMem.HEIGHT);
		Gdx.input.setInputProcessor(stage);
		isHighScore = HighScoresScreen.isHighScore(gameController.getScore());
		table = new Table();
		table.setFillParent(true);
		labelTitleStyle = new LabelStyle();
		labelTitleStyle.font = getFontRenderer().getLargeTitleFont();
		labelTextStyle = new LabelStyle();
		labelTextStyle.font = getFontRenderer().getSmallTitleFont();
		gameOverLabel = new Label(gameOverString, labelTitleStyle);
		scoreLabel = new Label("Score: " + gameController.getScore(), labelTextStyle);
		stageNumberLabel = new Label("Stage: " + gameController.getStageNumber(), labelTextStyle);
		if (isHighScore){
			textFieldStyle = new TextFieldStyle();
			textFieldStyle.font = getFontRenderer().getSmallTitleFont();
			textFieldStyle.fontColor = Color.WHITE;
			textFieldStyle.background = getSkin().getDrawable("default-window");
			nameTextField = new TextField("", textFieldStyle);
			table.add(gameOverLabel).center().colspan(2).top().padTop(UtilMath.percentageOf(8, NumMem.HEIGHT))
						.padBottom(UtilMath.percentageOf(5, NumMem.HEIGHT));
			table.row();
			table.add(scoreLabel).bottom().padRight(UtilMath.percentageOf(5, NumMem.WIDTH)).padBottom(UtilMath.percentageOf(5, NumMem.HEIGHT));
			table.add(stageNumberLabel).bottom().padBottom(UtilMath.percentageOf(5, NumMem.HEIGHT));
			table.row();
			table.add(new Label("Name: ", labelTextStyle));
			String boundsString = "";
			for (int i = 0; i < NAME_LETTERS_LIMIT; i++){
				boundsString =  boundsString + "a";
			}
			table.add(nameTextField).width(getFontRenderer().getSmallTitleFont().getBounds(boundsString).width);
			table.row();
			textButtonStyle = new TextButtonStyle();
			textButtonStyle.font = getFontRenderer().getSmallTitleFont();
			textButtonStyle.up = getSkin().getDrawable("neutral_button");
			textButtonStyle.down = getSkin().getDrawable("correct_button");
			submitButton = new TextButton("Submit", textButtonStyle);
			submitButton.addListener(new ChangeListener(){
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					HighScoresScreen.writeNewScore(nameTextField.getText(), gameController.getScore(), gameController.getStageNumber());
					AssetManager.getMainSoundtrack().play();
					game.setScreen(new MenuScreen(game));
				}
			});
			table.add(submitButton).width(textButtonStyle.font.getBounds("Submit").width + UtilMath.percentageOf(5, NumMem.WIDTH));
		}
		table.row();
		table.debug();
		table.top();
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		stage.draw();
		//Table.drawDebug(stage);
	}
	
	
}
