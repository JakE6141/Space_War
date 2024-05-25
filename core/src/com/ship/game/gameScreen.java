package com.ship.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import entities.Bullet;
import entities.Heart;
import entities.heroShip; //hero ship class
import entities.VillainOne;
import java.util.ArrayList;
import entities.VillainTwo;


public class gameScreen implements Screen {
//fields
    //Array Lists
    ArrayList<Bullet> bullets;
    ArrayList<VillainOne> villains;
    ArrayList<Heart> hearts;
    public ArrayList<Bullet> villainBullet;
    public ArrayList<Bullet> villainTwoBullet;
    //Object classes
    final private heroShip heroShip; //obj
    final private VillainOne villainOne;
    final private VillainTwo villainTwo;
    final ShipGame game;
    //sounds
    final private Sound gunShot = Gdx.audio.newSound(Gdx.files.internal("gunShot.wav"));;
    final private Sound oneHit = Gdx.audio.newSound(Gdx.files.internal("oneHit.wav"));
    final private Sound finalHit = Gdx.audio.newSound(Gdx.files.internal("finalHit.wav"));
    final private Sound heroDamage = Gdx.audio.newSound(Gdx.files.internal("heroHit.wav"));
    //textures
    final private Texture explode = new Texture(Gdx.files.internal("explode.png"));
    final private Texture background = new Texture(Gdx.files.internal("background.png"));
    final private Texture heartTexture = new Texture(Gdx.files.internal("heart.png"));
    //extras, batch,camera etc.
    final private SpriteBatch batch;
    private int villainHits = 0;
    private int villainTwoHits = 0;
    boolean facingRight = true;
    final private OrthographicCamera camera;
    private Heart heart1;
    private Heart heart2;
    private Heart heart3;
    private Heart heart4;
    private int heroHits = 0;
    private long lastHitTime = 0;
    private final int coolDown = 1000;

//end fields


    //constructor
    public gameScreen(final ShipGame game,SpriteBatch inputBatch){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1070, 630);
        batch = inputBatch; //linked to constructor - linked to batch in instructScreen
        heroShip = new heroShip(100,Gdx.graphics.getHeight()/2,125,125,300); //for heroShip constructor
        bullets = new ArrayList<>();
        villainOne = new VillainOne(1070,200,100,100,370,600);
        villainTwo = new VillainTwo(1070,900,350,100,100);
        villains = new ArrayList<>();
        hearts = new ArrayList<>();
        villainBullet = new ArrayList<>();
        villainTwoBullet = new ArrayList<>();
        heart1 = new Heart(heartTexture,5,570);
        hearts.add(heart1);
        heart2 = new Heart(heartTexture,55,570);
        hearts.add(heart2);
        heart3 = new Heart(heartTexture,105,570);
        hearts.add(heart3);
        heart4 = new Heart(heartTexture,155,570);
        hearts.add(heart4);

    }



    @Override
    public void show() {




    }



    @Override
    public void render(float delta) {

        int adjustedWidth = 1090;//so spaceship can't go out of bounds
        int adjustedHeight = 655;


        camera.update();
        batch.setProjectionMatrix(camera.combined);


        villainOne.update(villainBullet,delta);
        if(!villainOne.isAlive())
            villainTwo.update(villainBullet, delta);





//DRAWING===============================================================================================================
        batch.begin();

        batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        for(Heart heart : hearts)
            heart.render(batch);
        heroShip.draw(batch,heroShip.heroShipTexture); //getting texture from public texture in heroShip class
        villainOne.draw(batch, villainOne.villainOneTexture);
        villainTwo.draw(batch);

        for(Bullet bullet : bullets) {
            if (bullet.intersectV1(villainOne.getX(), villainOne.getY(), villainOne.getWidth(), villainOne.getHeight(), villainOne)) {
                oneHit.play(1.0f);
                villainHits++;
                batch.draw(explode, villainOne.getX() + 140, villainOne.getY() + 160, 150, 150);
                if (villainHits >= 120) {
                    finalHit.play(1.0f);
                    villainOne.die();

                }
            }
            if(bullet.intersectV2(villainTwo.modX(),villainTwo.modY(),villainTwo.modWidth(),villainTwo.modHeight(),villainTwo)){
                oneHit.play(1.0f);
                villainTwoHits++;
                batch.draw(explode,villainTwo.modX()+150,villainTwo.modY()+140,150,150);
                if(villainTwoHits>=140) {
                    finalHit.play();
                    villainTwo.die();
                    game.setScreen(new GameWinScreen(game,batch));
                }
            }
        }



        //REMOVE HEARTS FOR BOTH SETS OF BULLET HITS
        heartRemove(villainBullet);//villain one bullet list
        heartRemove(villainTwoBullet);//villain two bullet list



            


        for(Bullet bullet : villainBullet)
            bullet.render(batch);

        for(Bullet bullet : villainTwoBullet)
            bullet.render(batch);

        for(Bullet bullet : bullets)
            bullet.render(batch);


        batch.end();
//END DRAWING===========================================================================================================




//SHOOTING=========================================================================================================================
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            if(facingRight)
            {
                gunShot.play(1.0f);
                bullets.add(new Bullet(heroShip.getX() - 180, heroShip.getY() - 135, 500, 500,true,600));
                bullets.add(new Bullet(heroShip.getX() - 180, heroShip.getY() - 185, 500, 500,true,600));
            }
            else
            {
                gunShot.play(1.0f);
                bullets.add(new Bullet(heroShip.getX() - 200, heroShip.getY() - 135, 500, 500,false,600));
                bullets.add(new Bullet(heroShip.getX() - 200, heroShip.getY() - 185, 500, 500,false,600));
            }

        }


     //VILLAIN SHOOTING
      villainOne.fireBullet(villainBullet,600);

      if(!villainOne.isAlive())
        villainTwo.fireBullets(villainTwoBullet,600);



        //update/remove hero bullets
      ArrayList<Bullet> bulletsRemove = new ArrayList<>();
      for(Bullet bullet : bullets){
          bullet.update(delta);
          if(bullet.remove)
             bulletsRemove.add(bullet);

        }
        bullets.removeAll(bulletsRemove);

        //update/remove villain bullets
        ArrayList<Bullet> villainbulletsRemove = new ArrayList<>();
        for(Bullet bullets : villainBullet) {
            bullets.update(delta);
            if(!villainOne.isAlive())
                villainbulletsRemove.add(bullets);
        }
        villainBullet.removeAll(villainbulletsRemove);

        ArrayList<Bullet> villainTwoBulletRemove = new ArrayList<>();
        for(Bullet bullets : villainTwoBullet) {
            bullets.update(delta);
            if (!villainTwo.isAlive())
                villainTwoBulletRemove.add(bullets);
        }
        villainTwoBullet.removeAll(villainTwoBulletRemove);
//END SHOOTING===================================================================================================================




//HERO SHIP MOVEMENT IS BELOW======================================================================================================
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A))
        {  //else if would allow for grid like movement(for future reference)
            facingRight = false;
            if(heroShip.getX()>-25)
                heroShip.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D))
        {
            facingRight = true;
            if(heroShip.getX()+heroShip.getWidth()<adjustedWidth)
                heroShip.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S))
        {
            if(heroShip.getY()>-25)
                heroShip.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W))
        {
            if(heroShip.getY()+heroShip.getHeight()<adjustedHeight)
                heroShip.moveUp();
        }
//HERO SHIP MOVEMENT IS ABOVE=============================================================================================================


    }

//heart remove method for each villain bullet hit
    private void heartRemove(ArrayList<Bullet>vBullet){
        if(TimeUtils.timeSinceMillis(lastHitTime)>coolDown) {
            for (Bullet villainBullets : vBullet) {
                if (villainBullets.intersectH(heroShip.getAlternateX(), heroShip.getAlternateY(), heroShip.getAlternateWidth(), heroShip.getAlternateHeight())) {

                    heroDamage.play(1.0f);
                    heroHits++;
                    lastHitTime = TimeUtils.millis();

                    if (heroHits == 1)
                        heart4.remove();
                    if (heroHits == 2)
                        heart3.remove();
                    if (heroHits == 3)
                        heart2.remove();
                    if(heroHits==4) {
                        heart1.remove();
                        game.setScreen(new LoseScreen(game,batch));
                    }
                    break;


                }

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
        background.dispose();
        batch.dispose();
        gunShot.dispose();
        oneHit.dispose();
        finalHit.dispose();
        heroDamage.dispose();
        explode.dispose();
        heartTexture.dispose();
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
        for (Bullet bullet : villainBullet) {
            bullet.dispose();
        }
        for (Bullet bullet : villainTwoBullet) {
            bullet.dispose();
        }
        for (Heart heart : hearts) {
            heart.dispose();
        }
        villainOne.dispose();
        villainTwo.dispose();
        heroShip.dispose();




    }
}
