package com.lucasslemons.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game implements ApplicationListener {
    private static final String TAG = Game.class.getName();

    private WorldController worldController;

    private WorldRenderer worldRenderer;

    TouchInput touchInput;

    private boolean paused;

    private void baseInit(){
        ObjectStore.prefs = Gdx.app.getPreferences("HighScore");

        ObjectStore.scoreScreen = new ScoreScreen();

        ObjectStore.loadingScreen = new LoadingScreen();

        ObjectStore.menu = new Menu();

        ObjectStore.menuOverlay = new MenuOverlay();

        ObjectStore.gameTime = new GameTime();

        ObjectStore.time = new Time();

        ObjectStore.gameOverImage = new GameOverImage();

        ObjectStore.gameOver = new GameOver();

        ObjectStore.optionsMenu =  new OptionsMenu();

        ObjectStore.soundButton = new SoundButton();
        ObjectStore.inGameMenuButton = new InGameMenuButton();
        ObjectStore.startButton = new StartButton();
        ObjectStore.optionsButton = new OptionsButton();
        ObjectStore.exitButton = new ExitButton();
        ObjectStore.backButton = new BackButton();
        ObjectStore.reTryButton = new ReTryButton();

        ObjectStore.CAT_LANES = new float[ObjectStore.NUMBER_OF_LANES];

        for(int i = 0; i < ObjectStore.NUMBER_OF_LANES; i++){
            ObjectStore.CAT_LANES[i] = ObjectStore.LANE_BOTTOM_BOUND + (i * ObjectStore.DISTANCE_BETWEEN_LANES);
        }

        ObjectStore.lanes = new Lane[ObjectStore.NUMBER_OF_LANES + 1];

        for(int i = 0; i < ObjectStore.NUMBER_OF_LANES; i++){
            ObjectStore.lanes[i] = new Lane(-8.0f, ObjectStore.CAT_LANES[i], false);
        }

        ObjectStore.lanes[ObjectStore.NUMBER_OF_LANES] = new Lane(ObjectStore.RAT_LANE, -8, true);// Initialize rat lane at top of ObjectStore.lanes array

        ObjectStore.cats = new Cat[ObjectStore.NUMBER_OF_LANES];

        for(int i = 0; i < ObjectStore.NUMBER_OF_LANES; i++){
            ObjectStore.cats[i] = new Cat(i);
        }

        ObjectStore.rat = new Rat();
        ObjectStore.ratTail = new RatTail();

        ObjectStore.bird = new Bird();
    }

    @Override public void create () {
        // Set Libgdx log level to DEBUG
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Initialize basic objects
        baseInit();
        // Initialize controller and renderer
        worldController = new WorldController();
        worldRenderer = new WorldRenderer();
        //Touch input
        touchInput = new TouchInput();
        Gdx.input.setInputProcessor(touchInput);
        // Game world is active on start
        paused = false;
    }
    @Override public void render () {

        // Do not update game world when paused.
        if (!paused) {
            // Update game world by the time that has passed since last rendered frame.
            worldController.update(Gdx.graphics.getDeltaTime());
        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(1/255.0f, 1/255.0f, 237/255.0f, 255/255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        worldRenderer.render();
    }
    @Override public void resize (int width, int height) {
        worldRenderer.resize(width, height);
    }
    @Override public void pause () {
        paused = true;
    }
    @Override public void resume () {
        paused = false;
    }
    @Override public void dispose () {
        worldRenderer.dispose();
    }
}
