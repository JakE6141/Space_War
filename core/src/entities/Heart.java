package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {
    private Texture texture;
    public boolean show;
    private float x;
    private float y;
    private float width;
    private float height;

    public Heart(Texture texture, float x,float y){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.show = true;
        this.width = 50;
        this.height = 50;
    }

    public void render(SpriteBatch batch){
        if(show)
            batch.draw(texture,x,y,width,height);
    }
    public void remove(){
        show = false;
    }

    public void dispose(){
        texture.dispose();
    }





}
