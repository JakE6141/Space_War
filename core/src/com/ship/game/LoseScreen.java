package com.ship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoseScreen implements Screen {
    final ShipGame game;
    final SpriteBatch batch;
    final Texture background = new Texture(Gdx.files.internal("background.png"));
    final Texture loseText = new Texture(Gdx.files.internal("loseText.png"));
    final OrthographicCamera camera;

    public LoseScreen(final ShipGame game, SpriteBatch batch){

        this.game = game;
        this.batch = batch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1070, 630);
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(loseText, 210, -50, 650, 650);
        batch.end();
        if(Gdx.input.isTouched()){
            game.setScreen(new MainMenuScreen(game, batch));
        }

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        loseText.dispose();

    }
}
