package me.dayanath.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by USER on 12/7/2017.
 */

public class Alien {
    public int x, y;
    public boolean active;
    public Sprite sprite;

    public void draw(Batch batch) {
        sprite.setPosition((float)x, (float)y);
        sprite.draw(batch);
    }
}
