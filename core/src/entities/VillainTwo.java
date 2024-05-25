package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class VillainTwo {

    public Texture villainTextureTwo;
    private float x;
    private float y;
    private float width;
    private float height;
    private float speed;
    private boolean alive = true;
    private float timeElapsed = 0;
    private ArrayList <Bullet> bullets;
    private long lastVillainShot;
    int amp = 12;
    float frequency = 3;


    public VillainTwo(float x, float y, float speed, float width,float height) {
        this.villainTextureTwo = new Texture(Gdx.files.internal("villainTextureTwo.png"));
        this.speed = speed;
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        bullets = new ArrayList<>();
    }

    public void draw(SpriteBatch batch){

        if(alive)
            batch.draw(villainTextureTwo, x, y, width, height);


    }

    public void update(ArrayList<Bullet> vBullets, float deltaTime){
        timeElapsed += deltaTime;

        y = y + amp * MathUtils.cos(frequency * timeElapsed + 0);

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

    public void fireBullets(ArrayList<Bullet> vBullets, long shootInterval){
        long curTime = TimeUtils.millis();
        if(curTime - lastVillainShot >= shootInterval) {
            vBullets.add((new Bullet(x-220 , y-215 , 500, 500, false,740)));
            vBullets.add((new Bullet(x-220, y-130 , 500, 500, false,740)));
            vBullets.add(new Bullet(x-250,y-180,500,500,false,740));
            vBullets.add(new Bullet(x-250,y-165,500,500,false,740));
            lastVillainShot = curTime;
        }

    }




    public boolean isAlive(){
        return alive;
    }
    public void die(){
        alive = false;
    }
    public float modX(){
        return x-180;
    }
    public float modY(){
        return y-175;
    }
    public float modWidth(){
        return width-15;
    }
    public float modHeight(){
        return height-55;
    }




    public void dispose(){
        villainTextureTwo.dispose();
    }
}
