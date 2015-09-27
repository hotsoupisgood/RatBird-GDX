package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuOverlay {
    private long startTime;
    private int state = 0;

    public MenuOverlay(){}

    public void draw(SpriteBatch batch){
        ObjectStore.startButton.draw(batch);
        ObjectStore.optionsButton.draw(batch);
        ObjectStore.exitButton.draw(batch);
    }

    public void update(){
        switch (state) {
            case 0:// Button twitch one way
                if (System.currentTimeMillis() - startTime > 1000) {
                    ObjectStore.startButton.setRotation(5.0f);
                    ObjectStore.optionsButton.setRotation(-5.0f);
                    ObjectStore.exitButton.setRotation(5.0f);
                    startTime = System.currentTimeMillis();
                    state = 1;
                } break;
            case 1:// Button twitch other way
                if(System.currentTimeMillis() - startTime > 1000) {
                    ObjectStore.startButton.setRotation(-5.0f);
                    ObjectStore.optionsButton.setRotation(5.0f);
                    ObjectStore.exitButton.setRotation(-5.0f);
                    startTime = System.currentTimeMillis();
                    state = 0;
                } break;
            case 2:// Start button activate
                ObjectStore.startButton.update();
                break;
            case 3:// Options button activate
                ObjectStore.optionsButton.update();
                break;
            case 4:// Exit button activate
                ObjectStore.exitButton.update();
                break;
        }
    }

    public void buttonCheckBounds(float x, float y){
        if(ObjectStore.exitButton.isInBounds(x, y)){
            setState(4);
        } if(ObjectStore.startButton.isInBounds(x, y)){
            setState(2);
        }if (ObjectStore.optionsButton.isInBounds(x, y)){
            setState(3);
        }
    }

    public void setState(int state) {
        this.state = state;
    }
}
