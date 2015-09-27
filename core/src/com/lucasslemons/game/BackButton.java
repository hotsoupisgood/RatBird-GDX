package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BackButton {
    Texture texture;
    private Sprite sprite;
    private Vector2 location;

    int state = 0;

    public BackButton(){
        texture = new Texture(Gdx.files.internal("Back_Button.png"));

        sprite = new Sprite(texture);
        sprite.setSize(2, 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        location = new Vector2();
        location.x = ObjectStore.IN_GAME_MENU_BUTTON_X;
        location.y = ObjectStore.IN_GAME_MENU_BUTTON_Y - 1;

        sprite.setPosition(location.x, location.y);
    }

    public void update(){
        switch (state){
            case 0:// Resting state
                break;
            case 1:// Has been selected and is shooting off the screen
                if(location.x < ObjectStore.VIEWPORT_WIDTH/2)
                    location.x = location.x + 1.0f;
                else {
                    ObjectStore.gameState = 0;
                    returnHome();
                    setState(0);
                } break;
        } setPosition(location.x, location.y);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
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
            setState(1);
            return true;
        } else
            return false;
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void setRotation(float degrees){
        sprite.setRotation(degrees);
    }

    public void returnHome(){
        location.x = ObjectStore.IN_GAME_MENU_BUTTON_X;
        location.y =  ObjectStore.IN_GAME_MENU_BUTTON_Y - 1;
    }

    public void setState(int state) {
        this.state = state;
    }
}
