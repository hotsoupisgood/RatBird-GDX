package com.lucasslemons.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
    private static final String TAG = WorldRenderer.class.getName();
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public WorldRenderer () {
        init();
    }

    private void init () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(ObjectStore.VIEWPORT_WIDTH, ObjectStore.VIEWPORT_HEIGHT);
        camera.update();
    }

    public void render () {
        renderTestObjects();
    }

    private void renderTestObjects() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        switch (ObjectStore.gameState) {
            case 0:// Menu
                ObjectStore.menu.draw(batch);
                break;
            case 1:// Game
                ObjectStore.gameTime.draw(batch);
                break;
            case 2:// Game over
                ObjectStore.gameOver.draw(batch);
                break;
            case 3:// Options
                ObjectStore.optionsMenu.draw(batch);
                break;
            case 4:// Tutorial Screen

                break;
            case 5:// Loading Screen
                ObjectStore.loadingScreen.draw(batch);
                break;
            case 6:// Menu overlay
                ObjectStore.gameTime.draw(batch);
                ObjectStore.menuOverlay.draw(batch);
                break;
            case 7:// Score Screen
                ObjectStore.scoreScreen.draw(batch);
                break;
        } batch.end();
    }

    public void resize (int width, int height) {
        camera.viewportWidth = (ObjectStore.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }

    @Override public void dispose () {
        batch.dispose();
    }

}
