package com.lucasslemons.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ObjectStore {
    // ----------- States Of Game ------------
    // Menu = 0
    // Game Time = 1
    // Game Over = 2
    // Options = 3
    // Tutorial Overlay = 4
    // Loading Screen = 5
    // Menu Overlay = 6
    // Score Screen = 7
    public static int gameState = 5;
    // Game screen
    public static GameTime gameTime;
    public static Time time;
    // Loading screen
    public static LoadingScreen loadingScreen;
    // Lane locations
    public static Lane lanes[];
    // Cat objects
    public static Cat cats[];
    // Main rat object
    public static Rat rat;
    // Rat tail object
    public static RatTail ratTail;
    // Main bird object
    public static Bird bird;
    // Options Menu
    public static OptionsMenu optionsMenu;
    // Game over state
    public static GameOver gameOver;
    // Game over visual
    public static GameOverImage gameOverImage;
    // Menu
    public static Menu menu;
    // Menu overlay
    public static MenuOverlay menuOverlay;
    // Buttons
    public static SoundButton soundButton;
    public static StartButton startButton;
    public static OptionsButton optionsButton;
    public static ExitButton exitButton;
    public static InGameMenuButton inGameMenuButton;
    public static BackButton backButton;
    public static ReTryButton reTryButton;
    // Score screen
    public static ScoreScreen scoreScreen;
    // Visible game world is
    public static final float VIEWPORT_HEIGHT = 9.0f;
    // Visible game world is
    public static final float VIEWPORT_WIDTH = 16.0f;
    // Location of time
    public static final float TIME_LOCATION_X = -2.0f;
    public static final float TIME_LOCATION_Y =  4.0f;
    // Home Location of in game menu button
    public static final float IN_GAME_MENU_BUTTON_X = -7.5f;
    public static final float IN_GAME_MENU_BUTTON_Y =  2.5f;
    // Home location of start button
    public static final float HOME_START_LOCATION_X =  0.5f;
    public static final float HOME_START_LOCATION_Y =  0.0f;
    // Location of high score screen
    public static final float HIGH_SCORE_SCREEN_X = 1.0f;
    public static final float HIGH_SCORE_SCREEN_Y = 3.75f;
    // Amount of lanes
    public static final int NUMBER_OF_LANES = 4;
    // Top bound of cat lane
    public static final float LANE_TOP_BOUND = 2.5f;
    // Bottom bound of cat lane
    public static final float LANE_BOTTOM_BOUND = -3.5f;
    // Distance between lanes
    public static final float DISTANCE_BETWEEN_LANES = (ObjectStore.LANE_TOP_BOUND - ObjectStore.LANE_BOTTOM_BOUND)/ObjectStore.NUMBER_OF_LANES;;
    // Cat lanes
    public static float CAT_LANES[];
    // Resting bird position
    public static final float BIRD_PLACE_X = 5.0f;
    public static final float BIRD_PLACE_Y = 2.5f;
    // X border to the rat lane
    public static final float RAT_LANE = -5.0f;
    // The speed of cat
    public final static float FINAL_CAT_SPEED = 3.5f;
    public static float RESTING_CAT_SPEED = FINAL_CAT_SPEED;
    // Starting x position of cat
    public static final float CAT_START_POS =  8.0f;
    // Preferences
    public static Preferences prefs;
    // Convert pixel number to the scaled x and y values, see VIEWPORT_WIDTH and VIEWPORT_HEIGHT
    public static float convertPxToRatioX(float pxX){
        return ((VIEWPORT_WIDTH/ Gdx.graphics.getWidth()) * pxX) - VIEWPORT_WIDTH/2;
    } public static float convertPxToRatioY(float pxY){
        return VIEWPORT_HEIGHT/2 - ((VIEWPORT_HEIGHT/Gdx.graphics.getHeight()) * pxY);
    } // Reset cat speed
    public static void resetCatSpeed(){
        RESTING_CAT_SPEED = FINAL_CAT_SPEED;
    } // Reset all cats
    public static void totalResetAllCats(){
        int i = 0;
        for(Cat kittie : ObjectStore.cats){
            kittie.setLane(i);
            kittie.reset();
            kittie.setState(0);
            i++;
        }
    }
}
