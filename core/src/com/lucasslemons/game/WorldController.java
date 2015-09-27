package com.lucasslemons.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter{

    public WorldController () {
        Gdx.input.setInputProcessor(this);
    }

    public void update (float deltaTime) {
        switch (ObjectStore.gameState) {
            case 0:// Menu
                ObjectStore.menu.update();
                break;
            case 1:// Game
                ObjectStore.gameTime.update();
                break;
            case 2:// Game over
                ObjectStore.gameOver.update();
                break;
            case 3:// Options
                ObjectStore.optionsMenu.update();
                break;
            case 4:// Tutorial
                break;
            case 5:// Loading screen
                if(ObjectStore.loadingScreen.update())
                    ObjectStore.gameState = 0;
                break;
            case 6:// Overlay menu
                ObjectStore.menuOverlay.update();
                break;
            case 7:// Score Screen
                ObjectStore.scoreScreen.update();
                break;
        }
    }
}
