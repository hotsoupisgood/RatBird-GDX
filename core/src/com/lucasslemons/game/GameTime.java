package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameTime {

    private Sprite backgroundSprite;

    double startTime = 0;
    final int TIME_TO_REBIRTH = 2500;

    public GameTime() {
        backgroundSprite = new Sprite(new Texture(Gdx.files.internal("Background.png")));
        backgroundSprite.setSize(16.0f, 9.0f);
        backgroundSprite.setPosition(-8.0f, -4.5f);
    }

    public void update() {
        randReBirth();
        ObjectStore.time.update();
        try {
            if (ObjectStore.rat != null) {
                ObjectStore.rat.update();
            } else
                ObjectStore.gameState = 2;// Turn to game over screen
            for (Cat kittie : ObjectStore.cats) {
                if (kittie != null) {//dead cat filter
                    kittie.update();
                    if (ObjectStore.rat != null) {
                        if (kittie.getLeftEdge() < ObjectStore.RAT_LANE
                                && ObjectStore.rat.getLane() == kittie.getLane()) {
                            ObjectStore.rat = null;
                            ObjectStore.time.toggleActivity();
                            ObjectStore.gameState = 2;
                        }
                    }
                }
            } ObjectStore.bird.update();
        } catch (Exception ex) {//
        } ObjectStore.inGameMenuButton.update();
    }

    public void draw(SpriteBatch batch) {
        batch.disableBlending();// Disables alpha in image render
        backgroundSprite.draw(batch);// Render the background
        batch.enableBlending();// Re-enables alpha draw

        for (Lane selectedLane : ObjectStore.lanes) {
            selectedLane.draw(batch);
        } for (Cat kittie : ObjectStore.cats) {
            if (kittie != null)//dead cat filter
                kittie.draw(batch);
        } if (ObjectStore.rat != null)
            ObjectStore.rat.draw(batch);

        ObjectStore.bird.draw(batch);

        ObjectStore.inGameMenuButton.draw(batch);

        ObjectStore.time.draw(batch);
    }

    public void buttonCheckBounds(float x, float y) {
        ObjectStore.bird.birdKillCat(x, y);
        ObjectStore.inGameMenuButton.isInBounds(x, y);
    }

    public void reset() {
        ObjectStore.totalResetAllCats();
        ObjectStore.rat = new Rat();
        ObjectStore.time.resetStartScoreTime();
        ObjectStore.time.toggleActivity();
        ObjectStore.resetCatSpeed();
    }
    

    private void randReBirth() {
        if (System.currentTimeMillis() - startTime > TIME_TO_REBIRTH) {
            for (Cat kittie : ObjectStore.cats) {
                if (kittie.getState() == 1) {
                    kittie.toggleLifeState();
                }
            } startTime = System.currentTimeMillis();
            speedUp();
        }
    }

    private void speedUp(){
        float r = 0;
        for (int x = 0; x < ObjectStore.NUMBER_OF_LANES; x++) {
            for (int y = ObjectStore.NUMBER_OF_LANES - 1; y >= 0; y--) {
                if (ObjectStore.cats[x].getLane() == ObjectStore.cats[y].getLane() &&
                        x != y) {
                    r += .75f;
                }
            }
        } ObjectStore.RESTING_CAT_SPEED += r;
    }
}
