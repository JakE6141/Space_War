package com.ship.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShipGame extends Game {

      public SpriteBatch batch;
      public Music introMusic;


    public void create() {
        batch = new SpriteBatch(); //init batch obj
        this.setScreen(new MainMenuScreen(this,batch));
        introMusic = Gdx.audio.newMusic(Gdx.files.internal("game.wav"));
        introMusic.setLooping(true);
        introMusic.setVolume(0.2f);
        introMusic.play();

    }
    public void render(){
        super.render();
    }
    public void dispose(){
        batch.dispose();
    }
}
