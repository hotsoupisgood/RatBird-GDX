package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameOverImage {
    private Sprite sprite;
    private Vector2 location;

    int state = 0;
    boolean select = false;

    public GameOverImage(){
        sprite = new Sprite(new Texture(Gdx.files.internal("Game_Over.png")));
        sprite.setSize(12, 8);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        location = new Vector2();
        location.x =   0 - getOriginX();
        location.y =  -3.0f;
        sprite.setPosition(location.x, location.y);
    }

    public void update(){
        switch (state){
            case 0:// Resting
                break;
            case 1:// Fly off screen
                break;
        }
        setPosition(location.x, location.y);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public boolean getSelect(){
        return select;
    }

    public float getLeftEdge(){
        return getX();
    }

    public float getRightEdge(){
        return getX() + sprite.getWidth();
    }

    public float getBottomEdge(){
        return getY();
    }

    public float getTopEdge(){
        return getY() + sprite.getHeight();
    }

    public boolean isInBounds(float x, float y){
        if(x > getLeftEdge() && x < getRightEdge() &&
                y < getTopEdge() && y > getBottomEdge()) {
            state = 1;
            return true;
        }
        else
            return false;
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }

    public float getOriginX(){return sprite.getOriginX();}

    public float getHeight(){return sprite.getHeight();}

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void setRotation(float degrees){
        sprite.setRotation(degrees);
    }

    public void setState(int state) {
        this.state = state;
    }
}
