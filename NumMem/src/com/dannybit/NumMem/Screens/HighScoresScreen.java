package com.dannybit.NumMem.Screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Utils.UtilMath;

public class HighScoresScreen extends AbstractScreen {

	public static final int DEFAULT_SCORES_NUMBER = 6;
	public final static String fileName = "highscores2.txt";
	private List<Player> scores;
	private Stage stage;
	private Table table;
	private LabelStyle labelTitleStyle;
	private LabelStyle labelTextStyle;
	private TextButtonStyle textButtonStyle;
	private TextButton returnToMenuButton;
	private float sidePad;
	private NumMem game;
	private MenuScreen menuScreen;
	
	public HighScoresScreen(NumMem game, MenuScreen menuScreen) {
		super(game);
		this.game = game;
		this.menuScreen = menuScreen;
	}
	
	@Override
	public void show(){
		super.show();
		sidePad = UtilMath.percentageOf(5, Gdx.graphics.getWidth());
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		scores = getScoresList();
		table = new Table();
		table.setFillParent(true);
		labelTitleStyle = new LabelStyle();
		labelTitleStyle.font = getFontRenderer().getLargeTitleFont();
		table.add(new Label("Name", labelTitleStyle)).expandX();
		table.add(new Label("Score", labelTitleStyle)).expandX();
		table.add(new Label("Stage", labelTitleStyle)).expandX();
		labelTextStyle = new LabelStyle();
		labelTextStyle.font = getFontRenderer().getSmallTitleFont();
		table.row();
		for (int i = 0; i < scores.size(); i++){
			Player player = scores.get(i);
			table.add(new Label(player.name, labelTextStyle));
			table.add(new Label(String.valueOf(player.score), labelTextStyle));
			table.add(new Label(String.valueOf(player.stageNumber), labelTextStyle));
			table.row();
		}
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = getFontRenderer().getLargeTitleFont();
		textButtonStyle.up = getSkin().getDrawable("neutral_button");
		textButtonStyle.down = getSkin().getDrawable("correct_button");
		returnToMenuButton = new TextButton("Return", textButtonStyle);
		returnToMenuButton.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(menuScreen);
				
			}
			
		});
		table.add(returnToMenuButton).colspan(3).center().width(textButtonStyle.font.getBounds("Return").width + UtilMath.percentageOf(5, NumMem.WIDTH))
										.padTop(UtilMath.percentageOf(10, NumMem.HEIGHT));
		table.center();
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		stage.draw();
		Table.drawDebug(stage);
	}
	
	static class Player {
		String name;
		int score;
		int stageNumber;
		
		Player(){
			
		}
		
		Player(Player player){
			this.name = player.name;
			this.score = player.score;
			this.stageNumber = player.stageNumber;
		}
		
		Player(String name, int score, int stageNumber){
			this.name = name;
			this.score = score;
			this.stageNumber = stageNumber;
		}
		
		public String toString(){
			return name + " : " + score + " : " + stageNumber;
		}
	}
	
	private static List<Player> getScoresList(){
		List<Player> playerScores = new ArrayList<Player>();
		FileHandle file = Gdx.files.local(fileName);
		Json json = new Json();
		 /* If file doesn't exist, add default scores:
		  * AAA : 0
		  * BBB : 0
		  * CCC : 0
		  * DDD : 0
		  * EEE : 0
		  */
		if (!file.exists()){
			for (int i = 0; i < DEFAULT_SCORES_NUMBER; i++){
				playerScores.add(new Player(convertToTripleAscii(65 + i), 0, 1));
			}
			file.writeString(json.toJson(playerScores), false);
		 }
		 playerScores = json.fromJson(List.class, file.readString());
		 return playerScores;
	}
	
	private static String convertToTripleAscii(int ascii){
		return Character.toString((char) ascii) + Character.toString((char) ascii) + Character.toString((char) ascii);
	}
	
	public static void writeNewScore(String name, int score, int stageNumber){
		FileHandle file = Gdx.files.local(fileName);
		Json json = new Json();
		List<Player> scoreList = getScoresList();
		/* Check if score is actually a highscore, if the last high score is large than the new score */
		if (scoreList.get(scoreList.size() - 1).score > score){
			System.out.println("Not an high score");
			return;
		}
		
		Player player = new Player(name, score, stageNumber);
		for (int i = 0; i < scoreList.size(); i++){
			if (scoreList.get(i).score < player.score){
					scoreList.add(i, player);
					scoreList.remove(scoreList.size() - 1);
					break;
			}
		}
		
		file.writeString(json.toJson(scoreList), false);
	}
	
	
	public static boolean isHighScore(int score){
		FileHandle file = Gdx.files.local(fileName);
		List<Player> scoreList = getScoresList();
		if (scoreList.get(scoreList.size() -1).score > score){
			return false;
		}
		return true;
	}
	
	
	
	
	

}
