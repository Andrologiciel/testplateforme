package com.andrologiciels.sprites;


import com.andrologiciels.utils.Constants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Platform {
    private Texture texture;
    private Vector2 position;

    private Random random;

    public Platform(int i) {
        this.texture = new Texture("platform.png");
        this.random=new Random();
        this.position = new Vector2(this.random.nextInt(Constants.VIEWPORT_WIDTH-this.texture.getWidth()),
                (350 + this.random.nextInt(100))*(i+1));
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void dispose() {
        this.texture.dispose();
    }

}
