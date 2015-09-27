package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private int state = 0; // State it is in, 0 = resting, 1 = move to rat, 2 = return to resting;
    private boolean select = false;
    private Vector2 location;
    private Sprite sprite;

    int toggleAni = 0;
    int width = 2, height = 2;
    float speedX, speedY;
    long startTime = 0;

    Texture textureOne, textureTwo;

    public Bird(){
        textureOne = new Texture(Gdx.files.internal("Bird_1.png"));
        textureTwo = new Texture(Gdx.files.internal("Bird_2.png"));

        sprite = new Sprite(textureOne);

        setSize(width, height);

        setOrigin(getWidth() / 2.0f, getHeight() / 2.0f);

        location = new Vector2(ObjectStore.BIRD_PLACE_X, ObjectStore.BIRD_PLACE_Y);
        setPosition(location.x, location.y);
    }

    public void birdKillCat(float x, float y){
        if(getSelect()){
            for(int i = 0; i < ObjectStore.NUMBER_OF_LANES; i++){
                try {
                    if (ObjectStore.cats[i].isInBounds(x, y)) {
                        toRatKill(i);
                    }
                } catch (Exception ex){
//                    Gdx.app.log("TouchUp", "\nDon't beat off dead cat....");
                }
            } toggleSelect();
        }
    }

    private void toRatKill(final int catIterator){
        Runnable runRatKill = new Runnable() {
            @Override
            public void run() {
                float duration = .5f;// Time takes for bird to intercept he cat
                float distanceCoveredByCatBeforeHit = duration * ObjectStore.RESTING_CAT_SPEED;// Determine the distance the cat will travel during the time it take the bird to intercept
                float targetX = ObjectStore.cats[catIterator].getX() - distanceCoveredByCatBeforeHit;// Target rat x coord
                float targetY = ObjectStore.cats[catIterator].getY();// Target cat y coord

                speedX = ((getX() - targetX)/duration);// Determine x speed needed to intercept cat
                speedY = ((getY() - targetY)/duration);// Determine y speed needed to intercept cat
                state = 1;// Start bird on track to intercept with cat

                while (getY() > targetY){}// Hold up till bird hits cat position

                ObjectStore.cats[catIterator].toggleLifeState(); // Kill cat
                catBirth(catIterator);// Create cats in the lanes adjacent to the one just killed
                duration = 2.0f;// Time takes for bird to intercept he cat
                targetX = ObjectStore.BIRD_PLACE_X;// Re-target home x coord
                targetY = ObjectStore.BIRD_PLACE_Y;// Re-target home y coord

                speedX = ((getX() - targetX)/duration);
                speedY = ((getY() - targetY)/duration);
                state = 2;// Start bird on intercept path with restion position

                while (getY() < targetY){}// Hold up till bird returns to resting position

                state = 0;// Set bird in resting position
            }

        };

        new Thread(runRatKill).start();
    }

    private void catBirth(int i){
        try {
            if(ObjectStore.cats[i - 1].getState() == 1)
                ObjectStore.cats[i - 1].toggleLifeState();
            else
                ObjectStore.cats[i - 1].reset();
        } catch (ArrayIndexOutOfBoundsException ex){
//            Gdx.app.log("CatBirth in Bird", " Cat birth in non existent lane");
        } try {
            if(ObjectStore.cats[i + 1].getState() == 1)
                ObjectStore.cats[i + 1].toggleLifeState();
            else
                ObjectStore.cats[i + 1].reset();
        } catch (ArrayIndexOutOfBoundsException ex){
//            Gdx.app.log("CatBirth in Bird", " Cat birth in non existent lane");
        }
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update() {
        doAni();
        if(state == 1) {// Moving to cat
            location.x = location.x - (speedX * Gdx.graphics.getDeltaTime());
            location.y = location.y - (speedY * Gdx.graphics.getDeltaTime());
        }if (state == 2){// Moving back to resting
            location.x = location.x - (speedX * Gdx.graphics.getDeltaTime());
            location.y = location.y - (speedY * Gdx.graphics.getDeltaTime());
        } setPosition(location.x, location.y);
    }

    public void setSize(int x, int y){
        sprite.setSize(x, y);
    }

    public void setOrigin(float x, float y){
        sprite.setOrigin(x, y);
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public void toggleSelect(){
        if(select) {
            select = false;
        } else {
            select = true;
        }
    }

    public void doAni(){
        switch (toggleAni) {
            case 0:// Button twitch one way
                if (System.currentTimeMillis() - startTime > 1250) {
                    sprite.setTexture(textureOne);
                    startTime = System.currentTimeMillis();
                    toggleAni = 1;
                } break;
            case 1:// Button twitch other way
                if(System.currentTimeMillis() - startTime > 1250) {
                    sprite.setTexture(textureTwo);
                    startTime = System.currentTimeMillis();
                    toggleAni = 0;
                } break;
        }
    }

    public boolean getSelect(){
        return select;
    }

    public float getLeftEdge(){
        return getX();
    }

    public float getRightEdge(){
        return getX() + getWidth();
    }

    public float getBottomEdge(){
        return getY();
    }

    public float getTopEdge(){
        return getY() + getHeight();
    }

    public boolean isInBounds(float x, float y){
        if(x > getLeftEdge() && x < getRightEdge() &&
                y < getTopEdge() && y > getBottomEdge())
            return true;
        else
            return false;
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }
}
