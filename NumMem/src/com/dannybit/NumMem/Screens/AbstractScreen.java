package com.dannybit.NumMem.Screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.dannybit.NumMem.NumMem;
import com.dannybit.NumMem.Models.AssetManager;
import com.dannybit.NumMem.Renderer.FontRenderer;

public class AbstractScreen implements Screen {
	
	protected final NumMem game;
	protected FontRenderer fontRenderer;
	protected Skin mainSkin;
	private Texture buttonsTexture;
	
	public AbstractScreen(NumMem game){
		this.game = game;

	}
	

	protected String getName(){
		return getClass().getName();
	}
	
	@Override
	public void render(float delta) {
		/* Renders black screen */
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
	}
	
	public Skin getSkin(){
		if (mainSkin == null){
			mainSkin = new Skin(Gdx.files.internal("default_skin/uiskin.json"));
			TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
			mainSkin.addRegions(buttonAtlas);
		}
		return mainSkin;
	}
	
	public FontRenderer getFontRenderer(){
		if (fontRenderer == null){
			fontRenderer = new FontRenderer();
		}
		return this.fontRenderer;
	}
	
	public Music getMainSoundtrack(){
		return AssetManager.getMainSoundtrack();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
