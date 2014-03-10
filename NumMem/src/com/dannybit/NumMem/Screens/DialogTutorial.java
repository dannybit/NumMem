package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dannybit.NumMem.NumMem;

public class DialogTutorial extends AbstractScreen {

	private Stage stage;
	private Skin skin;
	
	public DialogTutorial(NumMem game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void show(){
		Gdx.input.setInputProcessor(stage = new Stage());
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		ExitDialog exitDialog = new ExitDialog("confirm exit", getSkin());
		stage.addActor(exitDialog);
	}

	public static class ExitDialog extends Dialog{

		public ExitDialog(String title, Skin skin) {
			super(title, skin);
		}
		{
			text("Do you really want to leave?");
			button("Yes");
			button("No");
		}
		
	}
	
	@Override
	public void render(float delta){
		super.render(delta);
		stage.draw();
	}
}
