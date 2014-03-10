package com.dannybit.NumMem.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Controller.GameButtonListener;
import com.dannybit.NumMem.Controller.MainController;
import com.dannybit.NumMem.Controller.WrongButtonSelected;
import com.dannybit.NumMem.Models.AssetManager;
import com.dannybit.NumMem.Models.GameButton;
import com.dannybit.NumMem.Models.GameTable;
import com.dannybit.NumMem.Models.PauseDialog;
import com.dannybit.NumMem.Renderer.NewButtonBlackScreen;
import com.dannybit.NumMem.Renderer.SwitchingStates;

/**
 * Screen of the playable game.
 * This game can be shown when switching from the start menu or when starting a new level; 
 * @author Dannybit
 *
 */
public class GameScreen extends AbstractScreen {
	private NumMem game;
	private Stage stage;
	private GameTable gameTable;
	private MainController controller;
	private TextureAtlas buttonAtlas;
	private TextButtonStyle textButtonStyle;
	/* Black screen that appears between buttons */
	private NewButtonBlackScreen newButtonBlackScreen;
	private SwitchingStates switchingStates;
	/* Screen to show when a wrongButtonSelected, all it does is render the current screen showing the red and green buttons for a fixed amount of time */
	private WrongButtonSelected wrongButtonSelected;
	private boolean blackScreen;
	private boolean switchStates;
	/* Switching from startMenu */
	private boolean newStage;
	private boolean newGame;
	private boolean paused;
	private boolean wrongButton;
	private boolean newButtonFlag;
	private boolean newStageFlag;
	private boolean timeOverFlag;
	private MenuScreen menuScreen;
	
	/**
	 * 
	 * @param game The game instance used to switch between screens
	 */
	public GameScreen(NumMem game, MenuScreen menuScreen) {
		super(game);
		this.game = game;
		this.menuScreen = menuScreen;
		stage = new Stage(NumMem.WIDTH, NumMem.HEIGHT);
		fontRenderer = getFontRenderer();
		mainSkin = getSkin();
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = fontRenderer.getLargeTitleFont();
		textButtonStyle.up = mainSkin.getDrawable(GameButton.neutralDrawableName);
		mainSkin.add("TextButtonStyle", textButtonStyle);
		controller = new MainController(this);
		gameTable = new GameTable(this);
		stage.addActor(gameTable.getMainTable());
		newButtonBlackScreen = new NewButtonBlackScreen();
		switchingStates = new SwitchingStates();
		wrongButtonSelected = new WrongButtonSelected();
		startingNewGame();
	}
	
	private void startingNewGame(){
		newStage = true;
		newGame = true;
	}

	
	@Override
	public void show(){
		Gdx.input.setInputProcessor(stage);
		//getMainSoundtrack().play();
		if (newStage){
			newStage = false;
			game.setScreen(new NewStageScreen(game,this, controller.getGameController()));
			return;
		}
		if (newGame){
			//Play soundtrack
			controller.startGame();
			newGame = false;
		}
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		if (paused){
			stage.act();
			stage.draw();
			return;
		}
		/* Checks if one second passed, if second passed update the timer */ 
		if (controller.update(delta)){
			gameTable.changeTimerLabel(controller.getCountdownTimer().toString());
		}
		
		if (switchStates){
			if (switchingStates.update(delta)){
				switchingStates.reset();
				switchStates = false;
				switchState();
			}
		}
		if (blackScreen){
			if (!newButtonBlackScreen.update(delta)){
				newButtonBlackScreen.reset();
				blackScreen = false;
				gameTable.getNumsButtonsTable().setVisible(true);
				if (newButtonFlag){
					controller.addNewButton();
					newButtonFlag = false;
				}
				else if (newStageFlag){
					controller.newStage(false);
					newStageFlag = false;
				}
				else if (timeOverFlag){
					controller.newStage(true);
					timeOverFlag = false;
				}
			}
			return;
		}
		
		if (wrongButton){
			if (wrongButtonSelected.update(delta)){
				wrongButton = false;
				controller.resetSequence();
			}
		}
		stage.act();
		stage.draw();
		if (NumMem.DEV_MODE){
			Table.drawDebug(stage);
		}

	}
	
	public boolean getWrongButton() {
		return wrongButton;
	}

	public void setWrongButton(boolean wrongButton) {
		this.wrongButton = wrongButton;
	}

	public void newStage(GameButton[] gameButtons, GameButtonListener buttonListener, int rowButtons, int columnButtons){
		gameTable.newStage(gameButtons, buttonListener, rowButtons, columnButtons);
		game.setScreen(new NewStageScreen(game, this, controller.getGameController()));
	}
	
	public Skin getMainSkin(){
		return this.mainSkin;
	}
	
	public GameTable getGameTable(){
		return this.gameTable;
	}
	
	public NumMem getGame(){
		return this.game;
	}
	
	public void newButton(){
		switchStates = true;
		newButtonFlag = true;
	}
	
	public void newStage(){
		switchStates = true;
		newStageFlag = true;
	}
	
	public void timeOver(){
		/* Show screen for fixed amount time than switch screen */
		switchStates = true;
		timeOverFlag = true;
	}
	
	
	
	private void switchState(){
		newButtonBlackScreen.start(0.5f);
		blackScreen = true;
		gameTable.getNumsButtonsTable().setVisible(false);
	}
	
	public void renderBlackScreen(){
		/* Renders black screen */
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}
	
	public void wrongButton(){
		wrongButton = true;
		wrongButtonSelected.start(1f);
	}
	
	public Stage getStage(){
		return this.stage;
	}

	public void pauseGame(PauseDialog pauseDialog) {
		pauseDialog.show(stage);
		paused = true;
		
	}

	public int getStageNumer() {
		return controller.getGameController().getStageNumber();
	}

	public void endGame() {
		stage.dispose();
		game.setScreen(menuScreen);
		
	}
	
	public void gameLost(){
		game.setScreen(new EndGameScreen(game, controller.getGameController()));
		AssetManager.getMainSoundtrack().stop();
	}

	public void unpause() {
		paused = false;
	}
}

