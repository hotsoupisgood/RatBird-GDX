package com.lucasslemons.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Rat {
    private int lane = 1;
    private Vector2 location;

    private Sprite sprite;

    int width = 1, height = 1;


    public Rat() {
        sprite = new Sprite(new Texture(Gdx.files.internal("Rat_Body.png")));

        setSize(width, height);

        setOrigin(getWidth() / 2.0f, getHeight() / 2.0f);

        location = new Vector2(ObjectStore.RAT_LANE - getOriginX(), ObjectStore.lanes[lane].getOriginY() + ObjectStore.lanes[lane].getY() - getOriginY());

        setPosition(location.x, location.y);
    }

    public void update() {
        ObjectStore.ratTail.update();
        setPosition(ObjectStore.RAT_LANE - getOriginX(),
                ObjectStore.lanes[lane].getOriginY() + ObjectStore.lanes[lane].getY() - getOriginY());
    }

    public void draw(SpriteBatch batch){
        ObjectStore.ratTail.draw(batch);
        sprite.draw(batch);
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
