package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class ScoreScreen {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Arial_Black.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;
    final int fontSize = 23;
    Vector2 locationOfHighScore;

    long highScore;

    public ScoreScreen(){
        locationOfHighScore = new Vector2();
        locationOfHighScore.x = ObjectStore.HIGH_SCORE_SCREEN_X;
        locationOfHighScore.y = ObjectStore.HIGH_SCORE_SCREEN_Y;

        parameter.size = fontSize;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3.0f;
        font = generator.generateFont(parameter);
        font.setScale(.025f);
    }

    public void draw(SpriteBatch batch){
        font.draw(batch, "High Score: " + getHighScore(), locationOfHighScore.x, locationOfHighScore.y);
    }

    public void  update(){
        setHighScore(ObjectStore.prefs.getLong("highscore"));
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }

    public long getHighScore() {
        return highScore;
    }
}
