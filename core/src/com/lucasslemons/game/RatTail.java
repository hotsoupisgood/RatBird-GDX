package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RatTail {
    private int lane;
    private Vector2 location;
    final int SPEED_OF_ANI = 100;

    long startTime = 0;

    Texture texture;

    int selectedTail = 1;

    int numOfTails = 3;
    private Sprite tail[];

    public RatTail() {
        lane = 1;

        tail = new Sprite[numOfTails];
        for(int i = 0; i < 3; i++){
            tail[i] = new Sprite(new Texture(Gdx.files.internal("Rat_Tail_" + i + ".png")));
            tail[i].setSize(2, 1);
            tail[i].setOrigin(tail[i].getWidth() / 2.0f, tail[i].getHeight() / 2.0f);
            tail[i].setPosition(ObjectStore.RAT_LANE,
                    ObjectStore.lanes[ObjectStore.rat.getLane()].getOriginY() +
                                ObjectStore.lanes[ObjectStore.rat.getLane()].getY() - tail[i].getOriginY());
        }
    }

    public void update() {
        if ((System.currentTimeMillis() - startTime) > SPEED_OF_ANI) {// Change tail
            if(selectedTail == 2)
                selectedTail = 0;
            else
                selectedTail++;

            startTime = System.currentTimeMillis();
        }
        setPosition(ObjectStore.RAT_LANE,
                ObjectStore.lanes[ObjectStore.rat.getLane()].getOriginY() +
                        ObjectStore.lanes[ObjectStore.rat.getLane()].getY() - getOriginY());
    }

    public void draw(SpriteBatch batch){
        tail[selectedTail].draw(batch);
    }

    public void setSize(int x, int y){
        tail[selectedTail].setSize(x, y);
    }

    public void setOrigin(float x, float y){
        tail[selectedTail].setOrigin(x, y);
    }

    public float getWidth(){
        return tail[selectedTail].getWidth();
    }

    public float getHeight(){
        return tail[selectedTail].getHeight();
    }

    public float getOriginY(){
        return tail[selectedTail].getOriginY();
    }

    public void setPosition(float x, float y){
        tail[selectedTail].setPosition(x, y);
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

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }
}
