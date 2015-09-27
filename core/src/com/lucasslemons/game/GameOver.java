package com.lucasslemons.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver {
    int state = 0;
    boolean select = false;

    public GameOver(){
    }

    public void update(){
        switch (state){
            case 0:
                ObjectStore.reTryButton.update();
                break;
            case 1:
//                ObjectStore.gameOverImage.setRotation(-15);
                break;
        }

    }

    public void draw(SpriteBatch batch){
        ObjectStore.gameTime.draw(batch);// Draw game to maintain a backdrop
        ObjectStore.gameOverImage.draw(batch);
        ObjectStore.reTryButton.draw(batch);
    }

    public void isInBounds(float x, float y){
        ObjectStore.reTryButton.isInBounds(x, y);
    }

    public void setState(int state) {
        this.state = state;
    }
}
