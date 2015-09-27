package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


class Cat {
    private int lane;
    private Vector2 location;
    private int state = 0;// 0 = active, 1 = de-active
    private Sprite sprite;

    public int randomCatFacePickerInt = (new Random().nextInt(3) + 1);//Pick random cat face number 1-3, gets added to file name

    Texture catFace;
    long startTime = 0;
    long startTimeForSwitch = 0;
    int toggleWaddle = 0;

    final int WIDTH = 1, HEIGHT = 1;
    final int TIME_TO_SWITCH = 0;
    final int WADDLE_TIME = 1250;
    final float ROTATION_AMOUNT = 15;

    public Cat(int constrLane){
        setLane(constrLane);

        catFace = new Texture(Gdx.files.internal("Cat_Face_" + randomCatFacePickerInt + ".png"));;
        sprite = new Sprite(catFace);

        setSize(WIDTH, HEIGHT);

        setOrigin(getWIDTH() / 2.0f, getHEIGHT() / 2.0f);
        location = new Vector2(ObjectStore.CAT_START_POS,
                ObjectStore.lanes[lane].getOriginY() + ObjectStore.lanes[lane].getY() - getOriginY());
        setPosition(location.x, location.y);
    }

    public void update() {
        waddle();
        checkOverlap();
        switch (state) {
            case 0:
                if (location.x > ObjectStore.RAT_LANE) {
                    startTimeForSwitch = System.currentTimeMillis();
                    location.x = location.x - (ObjectStore.RESTING_CAT_SPEED * Gdx.graphics.getDeltaTime());
                } else {
                    if (System.currentTimeMillis() - startTimeForSwitch > TIME_TO_SWITCH) {
                        if(ObjectStore.rat.getLane() > getLane()) {
                            setLane(getLane() + 1);
                            startTimeForSwitch = System.currentTimeMillis();
                            reset();

                        } else if(ObjectStore.rat.getLane() < getLane()) {
                            setLane(getLane() - 1);
                            startTimeForSwitch = System.currentTimeMillis();
                            reset();
                        }
                    }
                } break;
        }
        location.y = ObjectStore.lanes[getLane()].getOriginY() + ObjectStore.lanes[getLane()].getY() - getOriginY();
        setPosition(location.x, location.y);
    }

    public void draw(SpriteBatch batch){if(state == 0){sprite.draw(batch);}}

    public void reset() {location.x = ObjectStore.CAT_START_POS; setState(0);}

    public void setSize(int x, int y){sprite.setSize(x, y);}

    public void setOrigin(float x, float y){
        sprite.setOrigin(x, y);
    }

    public float getWIDTH(){
        return sprite.getWidth();
    }

    public float getHEIGHT(){
        return sprite.getHeight();
    }

    public float getOriginY(){
        return sprite.getOriginY();
    }

    public float getOriginX(){
        return sprite.getOriginX();
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x, y);
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }

    public float getLeftEdge(){
        return getX();
    }

    public float getRightEdge(){
        return getX() + getWIDTH();
    }

    public float getBottomEdge(){
        return getY();
    }

    public float getTopEdge(){
        return getY() + getHEIGHT();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public boolean isInBounds(float x, float y){
        if(x > getLeftEdge() &&
                x < getRightEdge() &&
                y < getTopEdge() &&
                y > getBottomEdge())
            return true;
        else
            return false;
    }

    public void waddle(){
        switch (toggleWaddle) {
            case 0:// Button twitch one way
                if (System.currentTimeMillis() - startTime > WADDLE_TIME) {
                    sprite.setRotation(ROTATION_AMOUNT);
                    startTime = System.currentTimeMillis();
                    toggleWaddle = 1;
                } break;
            case 1:// Button twitch other way
                if(System.currentTimeMillis() - startTime > WADDLE_TIME) {
                    sprite.setRotation(-ROTATION_AMOUNT);
                    startTime = System.currentTimeMillis();
                    toggleWaddle = 0;
                } break;
        }
    }

    public void checkOverlap(){
        for(Cat kittie : ObjectStore.cats){
            if(kittie != this && kittie.isInBounds(getX() + getOriginX(), getY() + getOriginY())){
                location.x = location.x + (getWIDTH() * 2);
            }
        }
    }

    public void toggleLifeState(){
        if(state == 0){
            reset();
            state = 1;
        } else if (state == 1){
            reset();
            state = 0;
        }
    }
}