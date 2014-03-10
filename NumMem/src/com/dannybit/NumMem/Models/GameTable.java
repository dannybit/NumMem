package com.dannybit.NumMem.Models;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Controller.GameButtonListener;
import com.dannybit.NumMem.Controller.GameController;
import com.dannybit.NumMem.Screens.GameScreen;
import com.dannybit.NumMem.Utils.UtilMath;

public class GameTable {
	
	/* Table containing all the sub-tables */
	private Table mainTable;
	/* Table containing the header (scores, level, lives etc) of the screen */
	private Table headerTable;
	/* Table containing the buttons, (the playable area) */
	private Table numButtonsTable;
	private Table livesTable;
	private Label scoreTextLabel;
	private Label scoreLabel;
	private Label timerLabel;
	private TextButton pauseButton;
	private LabelStyle largeTitleLabelStyle;
	private LabelStyle smallTitleLabelStyle;
	private TextButtonStyle largeTextButtonStyle;
	private WindowStyle dialogStyle;
	private Drawable dialogBackground;
	private GameScreen gameScreen;
	
	/* Variables to position widgets and buttons on the screen */
	private float headerPadTop;
	private float headerPadBottom;
	private float headerHeight;
	private float playableHeight;
	private float buttonWidth;
	private float buttonHeight;
	private int lives = GameController.INITIAL_LIVES;
	private float sidePad;
	
	private static String scoreLabelString = "Score";
	private static String timerString = "00:00";
	private List<Image> lifeImages;
	private Texture lifeTexture;
	private PauseDialog pauseDialog;
	private LabelStyle dialogLabelStyle;
	private LabelStyle dialogTitleStyle;
	private Label dialogTitleLabel;
	private TextButtonStyle dialogButtonStyle;
	private Label dialogStageLabel;
	
	private ImageButton soundImageButton;
	private ImageButtonStyle soundImageButtonStyle;
	
	/**
	 * Encapsulate the main table of the game, which contains the header table and the playable table
	 * @param fontRenderer Used to get the fonts for the header labels
	 */
	public GameTable(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		this.largeTitleLabelStyle = new LabelStyle(gameScreen.getFontRenderer().getLargeTitleFont(), Color.WHITE);
		this.smallTitleLabelStyle = new LabelStyle(gameScreen.getFontRenderer().getSmallTitleFont(), Color.WHITE);
		this.dialogLabelStyle = new LabelStyle(gameScreen.getFontRenderer().getDialogFont(), Color.WHITE);
		this.dialogTitleStyle = new LabelStyle(gameScreen.getFontRenderer().getDialogTitleFont(), Color.WHITE);
		this.largeTextButtonStyle = new TextButtonStyle();
		this.dialogButtonStyle = new TextButtonStyle();
		this.dialogStageLabel = new Label("Stage: ", dialogLabelStyle);
		this.dialogButtonStyle.font = gameScreen.getFontRenderer().getDialogFont();
		soundImageButtonStyle = new ImageButtonStyle();
		if (gameScreen.getMainSoundtrack().isPlaying()){
			soundImageButtonStyle.up = gameScreen.getSkin().getDrawable("mute");
			soundImageButtonStyle.checked = gameScreen.getSkin().getDrawable("unmute");
		} else {
			soundImageButtonStyle.up = gameScreen.getSkin().getDrawable("unmute");
			soundImageButtonStyle.checked = gameScreen.getSkin().getDrawable("mute");
		}
		soundImageButton = new ImageButton(soundImageButtonStyle);

		largeTextButtonStyle.font = gameScreen.getFontRenderer().getLargeTitleFont();
		pauseDialog = new PauseDialog("", gameScreen.getSkin(), gameScreen);
		pauseDialog.getContentTable().add(dialogStageLabel);
		pauseDialog.getContentTable().row();
		pauseDialog.getContentTable().add(new Label("Do you want to Exit?", dialogLabelStyle));
		pauseDialog.button(new TextButton("Yes", dialogButtonStyle), "Y").left();
		pauseDialog.button(new TextButton("No", dialogButtonStyle), "N").right();
	    pauseDialog.getButtonTable().getCells().get(0).padRight(150/2);
		generateLabelsAndButtons();
		generateTable();
		lifeTexture = new Texture(Gdx.files.internal("data/heart.png"));
		lifeImages = new ArrayList<Image>();
		for (int i = 0; i < GameController.INITIAL_LIVES; i++){
			Image image = new Image(lifeTexture);
			lifeImages.add(image);
		}
	}
	
	/**
	 * Initializes the labels contained in the header
	 */
	private void generateLabelsAndButtons(){
		scoreTextLabel = new Label(scoreLabelString, largeTitleLabelStyle);
		scoreLabel = new Label(String.valueOf(0), smallTitleLabelStyle);
		timerLabel = new Label(timerString, largeTitleLabelStyle);
		pauseButton = new TextButton("Pause", largeTextButtonStyle);
		pauseButton.addListener(new ChangeListener(){
			public void changed(ChangeEvent event, Actor actor){
				dialogStageLabel.setText("Stage: " + gameScreen.getStageNumer());
				gameScreen.pauseGame(pauseDialog);
			}
		});
		soundImageButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Music soundTrack = gameScreen.getMainSoundtrack();
				if (soundTrack.isPlaying()){
					soundTrack.pause();
				} else {
					soundTrack.play();
				}
			}
			
		});
	}
	
	public Table getNumsButtonsTable(){
		return this.numButtonsTable;
	}
	
	/**
	 * Creates the table and all the sub-tables
	 */
	private void generateTable(){
		mainTable = new Table();
		headerTable = new Table();
		numButtonsTable = new Table();
		livesTable = new Table();
		mainTable.setFillParent(true);
		mainTable.debug();
		headerTable.debug();
		numButtonsTable.debug();
		
	}
	
	public Table getMainTable(){
		return this.mainTable;
	}
	
	public void startTable(GameButton[] gameButtons, GameButtonListener buttonListener, int rowButtons, int columnButtons){
		calculateWidgetSizes(rowButtons, columnButtons);
		headerTable.add(scoreTextLabel);
		for (int i = 0; i < lives; i++){
			livesTable.add(lifeImages.get(i)).width(NumMem.WIDTH / 480 * 32).height(NumMem.HEIGHT / 320 * 32);
		}
		headerTable.add(livesTable).expandX();
		headerTable.add(soundImageButton).right().width(NumMem.WIDTH / 480 * 48).height(NumMem.HEIGHT / 320 * 48).padRight(sidePad).padTop(UtilMath.percentageOf(2, NumMem.HEIGHT));
		headerTable.row();
		headerTable.add(scoreLabel).top();
		headerTable.add(timerLabel);
		headerTable.add(pauseButton).padRight(sidePad).padTop(sidePad / 2);
		mainTable.add(headerTable).height(headerHeight).padBottom(headerPadBottom).width(NumMem.WIDTH).padTop(headerPadTop);
		mainTable.row();
		mainTable.add(numButtonsTable).bottom().left().height(playableHeight);
		mainTable.top();
		numButtonsTable.left();
		populateButtonsTable(gameButtons, buttonListener, rowButtons, columnButtons);
	}
	
	
	public void newStage(GameButton[] gameButtons, GameButtonListener buttonListener, int rowButtons, int columnButtons){
		resetTable();
		calculateWidgetSizes(rowButtons, columnButtons);
		populateButtonsTable(gameButtons, buttonListener, rowButtons, columnButtons);
		
	}
	
	/* Calculates the size of the widgets based on the screen resolutions and the number of buttons */
	private void calculateWidgetSizes(int rowButtons, int columnButtons){
		headerPadTop = UtilMath.percentageOf(2, NumMem.HEIGHT);
		headerPadBottom = UtilMath.percentageOf(5, NumMem.HEIGHT);
		sidePad = UtilMath.percentageOf(2, NumMem.HEIGHT);
		headerHeight = UtilMath.percentageOf(20, NumMem.HEIGHT);
		playableHeight = NumMem.HEIGHT - headerHeight - headerPadBottom - UtilMath.percentageOf(5, NumMem.HEIGHT);
		buttonWidth = NumMem.WIDTH / rowButtons;
		buttonHeight = playableHeight / columnButtons;
	}
	
	
	private void resetTable(){
		numButtonsTable.clear();
	}
	
	public void populateButtonsTable(GameButton[] gameButtons, GameButtonListener buttonListener, int rowButtons, int columnsButtons){
		for (int i = 0; i < rowButtons; i++){
			for (int j = 0; j < columnsButtons; j++){
				TextButton textButton = gameButtons[(i * columnsButtons) + j].getTextButton();
				textButton.addListener(buttonListener);
				numButtonsTable.add(textButton).width(buttonWidth).height(buttonHeight);
			}
			numButtonsTable.row();
		}
	}
	
	public void changeScoreLabel(int newScore){
		scoreLabel.setText(String.valueOf(newScore));
	}
	
	
	public void changeStageNumberLabel(int newStageNumber){
		System.out.println("change this");
	}
	
	public void changeTimerLabel(String newTime){
		timerLabel.setText(newTime);
	}
	
	public void removeLife(){
		lifeImages.remove(lifeImages.size() - 1);
		lives--;
		livesTable.reset();
		for (int i = 0; i < lives; i++){
			livesTable.add(lifeImages.get(i)).width(NumMem.WIDTH / 480 * 32).height(NumMem.HEIGHT / 320 * 32);
		}
	}
}
