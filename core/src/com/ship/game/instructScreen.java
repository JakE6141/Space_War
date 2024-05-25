package com.ship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class instructScreen implements Screen {



	final SpriteBatch batch;

	final OrthographicCamera camera;

	final Texture background = new Texture(Gdx.files.internal("background.png"));
	final Texture instructions = new Texture(Gdx.files.internal("directions.png"));

	final ShipGame game;


	public instructScreen(final ShipGame game,SpriteBatch inBatch){

		this.game = game;
		batch = inBatch;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1070, 630);


	}



	@Override
	public void render (float delta) {

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(instructions,210, 50, 650, 650);
		batch.end();



		if(Gdx.input.justTouched()){

				game.setScreen(new gameScreen(game,batch));
		}


	}


	@Override
	public void show () {

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
	public void hide () {

	}

	public void dispose () {
		batch.dispose();
		background.dispose();
		instructions.dispose();


	}

}
