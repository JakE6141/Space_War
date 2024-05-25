package com.ship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainMenuScreen implements Screen {


    final Texture play = new Texture(Gdx.files.internal("play.png"));
    final Texture background = new Texture(Gdx.files.internal("background.png"));
    final Texture title = new Texture(Gdx.files.internal("title.png"));

    final ShipGame game;
    final private SpriteBatch batch;
    final OrthographicCamera camera;


    public MainMenuScreen(final ShipGame game, SpriteBatch inBatch){

        this.game = game;
        batch = inBatch;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1070,630);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(title, 210, 80, 650, 650);

        batch.draw(play, 382, 40, 280, 280);

        batch.end();



        if (Gdx.input.isTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            if (touchX>=410&&touchX<=614&&touchY>=343&&touchY<=440) {
                game.setScreen(new instructScreen(game,batch));

            }

        }
    }

    @Override
    public void resize(int width, int height) {

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
        play.dispose();
        background.dispose();
        title.dispose();
        batch.dispose();



    }

}



