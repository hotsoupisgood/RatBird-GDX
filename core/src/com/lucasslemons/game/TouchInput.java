package com.lucasslemons.game;

import com.badlogic.gdx.InputProcessor;

public class TouchInput implements InputProcessor {
    int downPressX;
    int downPressY;

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        downPressX = x;
        downPressY = y;
        float conX = ObjectStore.convertPxToRatioX(x);
        float conY = ObjectStore.convertPxToRatioY(y);

        switch (ObjectStore.gameState) {
            case 0:// Menu
                break;
            case 1:// Game
                if (ObjectStore.bird.isInBounds(conX, conY)) {
                    ObjectStore.bird.toggleSelect();
                }

                if (conX < ObjectStore.RAT_LANE + 4 && // Stops the cats at the rat vert lane
                        ObjectStore.rat != null) {// To keep post dead rat errors
                    for (int i = 0; i < ObjectStore.NUMBER_OF_LANES; i++) {
                        if (ObjectStore.lanes[i].checkBounds(conY)) {
                            ObjectStore.rat.setLane(i);
                        }
                    }
                } break;
            case 2:// Game over
                break;
            case 3:// Options
                break;
            case 4:// Tutorial
                break;
            case 5:// Loading screen
                break;
            case 6:// Overlay menu
                break;
        } return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        float conX = ObjectStore.convertPxToRatioX(x);
        float conY = ObjectStore.convertPxToRatioY(y);

        switch (ObjectStore.gameState) {
            case 0:// Menu
                ObjectStore.menu.buttonCheckBounds(conX, conY);
                break;
            case 1:// Game
                ObjectStore.gameTime.buttonCheckBounds(conX, conY);
                break;
            case 2:// Game over
                ObjectStore.gameOver.isInBounds(conX, conY);
                break;
            case 3:// Options
                ObjectStore.optionsMenu.buttonCheckBounds(conX, conY);
                break;
            case 4:// Tutorial
                break;
            case 5:// Loading screen
                break;
            case 6:// Overlay menu
                ObjectStore.menuOverlay.buttonCheckBounds(conX, conY);
                break;
        } return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}
