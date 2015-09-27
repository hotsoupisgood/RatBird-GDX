package com.lucasslemons.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Lane extends Sprite{
    private int laneNumber = 0;

    public Lane(float x, float y, boolean catDivider){
        setTexture(new Texture(createProceduralPixmap(32, 32)));

        if(!catDivider){
            setSize(16.0f, 0.1f);
            setOrigin(getWidth() / 2.0f, getHeight() / 2.0f);
        }
        else {
            setSize(0.1f, 16);
        }

        setPosition(x, y);
    }

    public boolean checkBounds(float y) {
        float radialDistanceBetweenTwoLanes = ObjectStore.DISTANCE_BETWEEN_LANES/2;
        if(y < getY() + radialDistanceBetweenTwoLanes &&
                y > getY() - radialDistanceBetweenTwoLanes)
            return true;
        else
            return false;
    }

    private Pixmap createProceduralPixmap (int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // Fill lane line w/ color
        pixmap.setColor(0.25f, 0.25f, 1.0f, 0.25f);
        pixmap.fill();
        return pixmap;
    }
}
