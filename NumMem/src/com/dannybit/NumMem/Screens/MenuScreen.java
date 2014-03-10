package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Utils.UtilMath;

public class MenuScreen extends AbstractScreen {
	
	private Stage stage;
	private Table table;
	
	private Label logoLabel;
	private float titlePad;
	
	private TextButton bPlay;
	private TextButton bInstructions;
	private TextButton bHighScores;
	private float buttonWidth;
	private float buttonHeight;
	private float paddingButtons;
	private TextButtonStyle buttonStyle;
	
	private ImageButton soundImageButton;
	private ImageButtonStyle imageButtonStyle;
	private HighScoresScreen highScoresScreen;
	private InstructionsScreen instructionsScreen;
	private MenuChangeListener menuChangeListener;
	
	
	
	public MenuScreen(NumMem game){
		super(game);
		highScoresScreen = new HighScoresScreen(game, this);
		instructionsScreen = new InstructionsScreen(game, this);
		getMainSoundtrack().play();
		
	}
	
	@Override
	public void show(){
		super.show();
		stage = new Stage(NumMem.WIDTH, NumMem.HEIGHT);
		Gdx.input.setInputProcessor(stage);
		titlePad = UtilMath.percentageOf(4, NumMem.HEIGHT);
		paddingButtons = UtilMath.percentageOf(1, NumMem.HEIGHT);
		table = new Table();
		table.setFillParent(true);
		logoLabel = new Label("NumMem", new LabelStyle(getFontRenderer().getLogoLabelFont(), Color.WHITE));
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = getFontRenderer().getSmallTitleFont();
		buttonStyle.up = getSkin().getDrawable("neutral_button");
		buttonStyle.down = getSkin().getDrawable("correct_button");
		bPlay = new TextButton("Play", buttonStyle);
		bInstructions = new TextButton("Instructions", buttonStyle);
		bHighScores = new TextButton("Highscores", buttonStyle);
		buttonWidth = buttonStyle.font.getBounds(" Instructions ").width + UtilMath.percentageOf(10, NumMem.WIDTH);
		buttonHeight = (NumMem.HEIGHT - UtilMath.percentageOf(25, NumMem.HEIGHT)) / 4;
		imageButtonStyle = new ImageButtonStyle();
		imageButtonStyle.up = getSkin().getDrawable("mute");
		imageButtonStyle.checked = getSkin().getDrawable("unmute");
		soundImageButton = new ImageButton(imageButtonStyle);
		
		table.add(logoLabel).expandX().padBottom(titlePad).padTop(titlePad).height(UtilMath.percentageOf(18, NumMem.HEIGHT));
		table.row();
		table.add(bPlay).width(buttonWidth).padBottom(paddingButtons).height(buttonHeight);
		table.row();
		table.add(bHighScores).width(buttonWidth).padBottom(paddingButtons).height(buttonHeight);
		table.row();
		table.add(bInstructions).width(buttonWidth).padBottom(paddingButtons).height(buttonHeight);
		table.row();
		table.add(soundImageButton).right().padBottom(UtilMath.percentageOf(2, NumMem.HEIGHT)).width(NumMem.WIDTH / 480 * 64).height(NumMem.HEIGHT / 320 * 64);
		stage.addActor(table);
		table.debug();
		
		menuChangeListener = new MenuChangeListener(this);
		bPlay.addListener(menuChangeListener);
		bHighScores.addListener(menuChangeListener);
		bInstructions.addListener(menuChangeListener);
		soundImageButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (getMainSoundtrack().isPlaying()){
					getMainSoundtrack().pause();
				} else {
					getMainSoundtrack().play();
				}
				
			}
		});
	}
	
	private class MenuChangeListener extends ChangeListener {
		
		private MenuScreen menuScreen;
		
		private MenuChangeListener(MenuScreen menuScreen){
			this.menuScreen = menuScreen;
		}
		
		@Override
		public void changed(ChangeEvent event, Actor actor){
			if (actor.equals(bPlay)){
				game.setScreen(new GameScreen(game, menuScreen));
			}
			
			if (actor.equals(bHighScores)){
				game.setScreen(highScoresScreen);
			}
			
			
			else if (actor.equals(bInstructions)){
				game.setScreen(instructionsScreen);
			}
		}
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		stage.act();
		stage.draw();
	}
	
	
}
