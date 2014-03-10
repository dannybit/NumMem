package com.dannybit.NumMem.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AssetManager {

	private static Music mainSoundtrack;
	
	public static Music getMainSoundtrack(){
		if (mainSoundtrack == null){
			mainSoundtrack = Gdx.audio.newMusic(Gdx.files.internal("data/nummem_soundtrack.mp3"));
		}
		return mainSoundtrack;
	}
	
}
