package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Time {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Arial_Black.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;
    final int fontSize = 80;

    private Vector2 location;

    String time;

    int state = 0;

    long current;
    long startTime = System.currentTimeMillis();
    long startTimeDelay = 0;
    long timeDifference = 0;
    final long DELAY_TIME = 100;// In millis

    public Time(){
        time = " " + System.currentTimeMillis();
//        parameter.size = fontSize;
        parameter.size = (int)Math.ceil(fontSize);
        generator.scaleForPixelHeight((int)Math.ceil(fontSize));
        parameter.minFilter = Texture.TextureFilter.Nearest;
        parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 8.0f;
        font = generator.generateFont(parameter);
        font.setScale(.025f);

        location = new Vector2();
        location.x = ObjectStore.TIME_LOCATION_X;
        location.y =  ObjectStore.TIME_LOCATION_Y;
    }

    public void update(){
        switch (state){
            case 0:// Time
                timeDifference = System.currentTimeMillis() - startTime;
                if (System.currentTimeMillis() - startTimeDelay > DELAY_TIME) {
                    current = ((System.currentTimeMillis()) - startTime)/100;
                    time = "" + (current);
                    startTimeDelay = System.currentTimeMillis();
                } break;
            case 1:// End
                break;
        }
    }

    public void draw(SpriteBatch batch) {
        switch (state){
            case 0:// Time
                font.draw(batch, time, location.x, location.y);
                break;
            case 1:// End
                font.draw(batch, time, location.x, location.y);
                break;
        }
    }

    public int getState () {return state;}

    public void setState (int state) {
        this.state = state;
    }

    public float getX() {return location.x;}

    public float getY() {
        return location.y;
    }

    public void resetStartScoreTime () {startTime = System.currentTimeMillis();}

    public void returnToGame () {
        startTime = System.currentTimeMillis() - timeDifference;
    }

    public void toggleActivity(){
        if (getState() == 1){
            setState(0);
            if (ObjectStore.prefs.getLong("highscore") < current)
                ObjectStore.prefs.putLong("highscore", current);
            ObjectStore.prefs.flush();
        } else { setState(1); }
    }
}
