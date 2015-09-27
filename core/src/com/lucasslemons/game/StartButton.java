package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StartButton {
    Texture texture;
    private Sprite sprite;
    private Vector2 location;

    int state = 0;

    public StartButton(){
        texture = new Texture(Gdx.files.internal("Start_Button.png"));
        sprite = new Sprite(texture);
        sprite.setSize(5, 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        location = new Vector2();
        location.x = ObjectStore.HOME_START_LOCATION_X - sprite.getWidth();
        location.y = ObjectStore.HOME_START_LOCATION_Y;
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
                    ObjectStore.gameState = 1;
                    ObjectStore.inGameMenuButton.setState(0);
                    ObjectStore.menuOverlay.setState(0);
                    ObjectStore.menu.setState(0);
                    ObjectStore.time.returnToGame();
                    returnHome();
                    setState(0);
                } break;
        } setPosition(location.x, location.y);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void returnHome(){
        location.x = ObjectStore.HOME_START_LOCATION_X - sprite.getWidth();
        location.y = ObjectStore.HOME_START_LOCATION_Y;
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
        if(x > getLeftEdge() &&
                x < getRightEdge() &&
                y < getTopEdge() &&
                y > getBottomEdge()) {
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

    public void setState(int state) {
        this.state = state;
    }
}
