package entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ship.game.gameScreen;



public class heroShip {

    private heroShip heroShip;
    private gameScreen gameScreen;
    private float x;
    private float y;
    final private float width;
    final private float height;
    private float speed;   //speed will change with boosters
    public Texture heroShipTexture;


    public heroShip(float x, float y, float width, float height, float speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.heroShipTexture = new Texture(Gdx.files.internal("spaceHero.png"));

    }



    public void draw(SpriteBatch batch, Texture heroShipTexture){
        batch.draw(heroShipTexture,x,y,width,height);
    }


    public void moveLeft(){

        float move = speed*Gdx.graphics.getDeltaTime();
        x-=move; //x-move  moving left taking away x - left
         heroShipTexture = new Texture("backWardHero.png");

    }
    public void moveRight(){

        float move = speed*Gdx.graphics.getDeltaTime();
        x+=move;
        heroShipTexture = new Texture("spaceHero.png");

    }
    public void moveDown(){

        float move = speed*Gdx.graphics.getDeltaTime();
        y-=move;

    }
    public void moveUp(){

        float move = speed*Gdx.graphics.getDeltaTime();
        y+=move;

    }

    public float getX(){
        return x;
    }
    public float getY(){

        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }


    public float getAlternateY(){
        return y-160;
    }
    public float getAlternateHeight(){
        return height-95;
    }
    public float getAlternateX(){
        return x-150;
    }
    public float getAlternateWidth(){
        return width-50 ;
    }

    public void dispose(){
        heroShipTexture.dispose();
    }




}
