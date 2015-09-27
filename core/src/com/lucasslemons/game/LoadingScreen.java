package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen {
    int state = 1;// Pre-Load = 0, Loading = 1, Done = 2

    final float MOVING_LEMON_COLOR_SPEED = 2.0f;

    private Sprite myLemonsSprite;
    private Sprite behindLemonSprite;
    private Sprite movingLemonColorSprite;

    Texture image;

    public LoadingScreen(){
        myLemonsSprite = new Sprite(getImage());
        myLemonsSprite.setSize(ObjectStore.VIEWPORT_WIDTH, ObjectStore.VIEWPORT_HEIGHT);
        myLemonsSprite.setPosition(-ObjectStore.VIEWPORT_WIDTH / 2, -ObjectStore.VIEWPORT_HEIGHT / 2);

        behindLemonSprite = new Sprite(new Texture(createWhitePixmap(32, 32)));
        behindLemonSprite.setSize(ObjectStore.VIEWPORT_WIDTH, ObjectStore.VIEWPORT_HEIGHT);
        behindLemonSprite.setPosition(-ObjectStore.VIEWPORT_WIDTH / 2, -ObjectStore.VIEWPORT_HEIGHT / 2);

        movingLemonColorSprite = new Sprite(new Texture(createYellowPixmap(32, 32)));
        movingLemonColorSprite.setSize(ObjectStore.VIEWPORT_WIDTH, ObjectStore.VIEWPORT_HEIGHT);
        movingLemonColorSprite.setPosition(-ObjectStore.VIEWPORT_WIDTH / 2, (-ObjectStore.VIEWPORT_HEIGHT / 2) - ObjectStore.VIEWPORT_HEIGHT);

    }

    public boolean update(){
        if(state == 2)
            return true;
        else if(state == 0){
            return false;
        }
        else {
            movingLemonColorSprite.setPosition(movingLemonColorSprite.getX(), movingLemonColorSprite.getY() + (Gdx.graphics.getDeltaTime() * MOVING_LEMON_COLOR_SPEED));
            if(movingLemonColorSprite.getY() > -ObjectStore.VIEWPORT_HEIGHT/2)
                state = 2;
            return false;
        }
    }

    public void draw(SpriteBatch batch){
        behindLemonSprite.draw(batch);
        movingLemonColorSprite.draw(batch);
        myLemonsSprite.draw(batch);
    }

    private Texture getImage(){
        try {
            return new Texture(Gdx.files.internal("My_Lemons_Logo.png"));
        } catch (Exception ex){
            Gdx.app.log("Cant load lemons ", " \nFile exeption");
        }
        return new Texture(createProceduralPixmap(32, 32));
    }

    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 0, 1);
        pixmap.fill();
        return pixmap;
    }

    private Pixmap createYellowPixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1.0f, 1.0f, 0.0f, 1.0f);
        pixmap.fill();
        return pixmap;
    }

    private Pixmap createWhitePixmap(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        pixmap.fill();
        return pixmap;
    }
}