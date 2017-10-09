package com.andrologiciels.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<Texture> textures;
    private float maxFrameTime, currentFrameTime;
    private int frameCount, frame;

    public Animation(int frameCount, float cycleTime) {
        this.frameCount = frameCount;
        this.textures = new Array<Texture>();

        for (int i = 1; i <= this.frameCount; i++) {
            this.textures.add(new Texture("characters/p1_walk/p1_walk" + String.valueOf(i) + ".png"));
        }
        this.maxFrameTime = cycleTime / this.frameCount;
        this.frame = 0;
    }

    public void update(float dt) {
        this.currentFrameTime += dt;
        if (this.currentFrameTime > this.maxFrameTime) {
            this.frame++;
            this.currentFrameTime = 0;
        }
        if (this.frame >= frameCount) {
            this.frame = 0;
        }
    }

    public Texture getTexture() {
        return this.textures.get(this.frame);
    }
}
