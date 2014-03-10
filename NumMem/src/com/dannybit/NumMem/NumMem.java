package com.dannybit.NumMem;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dannybit.NumMem.Screens.MenuScreen;

public class NumMem extends Game {
	
	public static final String LOG = NumMem.class.getSimpleName();
	public static final boolean DEV_MODE = false;
	public static int WIDTH;
	public static int HEIGHT;
	public static int VIRTUAL_WIDTH = 480;
	public static int VIRTUAL_HEIGHT = 320;
	
	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		setScreen(new MenuScreen(this));
	}
}
