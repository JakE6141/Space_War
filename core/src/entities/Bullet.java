package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    private int speed;
    private static Texture bulletTexture;
    float x ,y;
    int width, height;
    public boolean remove = false;
    private boolean movingRight;

    public Bullet(float x, float y,int width, int height,boolean movingRight,int speed){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.movingRight = movingRight;
        this.speed = speed;

        if (bulletTexture==null)
            bulletTexture = new Texture(Gdx.files.internal("bullet.png"));
    }


    public void update(float deltaTime){

    if(movingRight)
        x+= speed * deltaTime;
    else
        x-=speed*deltaTime;

    if(x> Gdx.graphics.getWidth()||x<-230)
        remove = true;

    }

    public void render(SpriteBatch batch){
        batch.draw(bulletTexture,x,y,width,height);
    }

    public boolean intersectV1(float X, float Y, float width, float height, VillainOne villain){

        if(!villain.isAlive())
            return false;

        return x < X + width && x + width > X && y < Y + height && y + height > Y;

    }
    public boolean intersectV2(float X, float Y, float width, float height, VillainTwo villainTwo){
        if(!villainTwo.isAlive()){
            return false;
        }
        return x< X + width && x + width > X && y<Y + height && y + height > Y;
    }
    public boolean intersectH(float X, float Y, float width, float height){

        return x < X + width && x + width > X && y < Y + height && y + height > Y;
    }



    public void dispose(){
        bulletTexture.dispose();
    }
}
