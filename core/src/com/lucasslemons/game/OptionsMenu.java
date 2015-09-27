package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OptionsMenu {
    private Sprite backgroundSprite;

    Texture backgroundTexture;

    public OptionsMenu(){
        backgroundTexture = new Texture(Gdx.files.internal("HowToScreen.png"));

        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(16.0f, 9.0f);
        backgroundSprite.setPosition(-8.0f, -4.5f);
    }

    public void draw(SpriteBatch batch){
        batch.disableBlending();// Disables alpha in image render
        backgroundSprite.draw(batch);// Render the background
        batch.enableBlending();// Re-enables alpha draw

        ObjectStore.backButton.draw(batch);

        ObjectStore.soundButton.draw(batch);
    }

    public void  update(){
        ObjectStore.optionsButton.update();
        ObjectStore.backButton.update();
    }

    public void buttonCheckBounds(float x, float y){
        ObjectStore.soundButton.isInBounds(x, y);
        ObjectStore.backButton.isInBounds(x, y);
    }
}
