package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;


import java.util.ArrayList;


public class VillainOne {

    private float x;
    private float y;
    final private float width;
    final private float height;
    public float speed;
    public Texture villainOneTexture;
    final private float frequency =3;
    final private float amp = 8;
    private float timeElapsed = 0;
    private boolean alive = true;
    private ArrayList<Bullet> bullets;
    private long lastVillainShot;
    final private long shootInterval;




    public VillainOne(float x, float y, float width, float height, float speed, long shootInterval){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.villainOneTexture = new Texture(Gdx.files.internal("villainOne.png"));
        bullets = new ArrayList<>();
        this.shootInterval = shootInterval;
        lastVillainShot = 0;


    }

    public void draw( SpriteBatch batch, Texture villianOneTexture){
        if(alive) {
            batch.draw(villianOneTexture, x, y, width, height);

        }

    }
    public void update(ArrayList<Bullet>villainBullet,float deltaTime)
    {

        timeElapsed += deltaTime;//accumulate time elapsed

        y = y + amp * MathUtils.sin(frequency * timeElapsed + 0);//0 for phase shift

        x -= speed * deltaTime;

        if (y < 0)
            y = 0;
        else if (y > Gdx.graphics.getHeight() - height)
            y = Gdx.graphics.getHeight() - height;


       if(x<-120)
         x=1070;

       for(Bullet bullet : bullets){
           bullet.update(deltaTime);
       }
    }

    public void fireBullet(ArrayList<Bullet>villainBullet, long shootInterval){
        long curTime = TimeUtils.millis();
        if(curTime - lastVillainShot >= shootInterval) {
            villainBullet.add((new Bullet(x-230 , y-130 , 500, 500, false,740)));
            villainBullet.add((new Bullet(x-230, y-210 , 500, 500, false,740)));
            lastVillainShot = curTime;
        }

    }


    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;

    }

    public float getX(){
        return x-150;  //adjustments for hitbox
    }
    public float getY(){
        return y-175;
    }
    public float getWidth(){
        return width-20;
    }
    public float getHeight(){
        return height-50;
    }


    public void dispose() {
        villainOneTexture.dispose();
    }

}
