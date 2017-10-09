package com.andrologiciels.sprites;


import com.andrologiciels.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Character {
    private Texture texture;
    private Vector2 position, fall;

    private Animation walking;

    public Character(float x, float y) {
        this.texture = new Texture("characters/p1_stand.png");
        this.position = new Vector2(x, y);
        this.fall = new Vector2(0, 0);

        this.walking = new Animation(11, 0.5f);
    }

    public void update(float dt) {
        this.fall.add(0, Constants.GRAVITY); //-- valeur de gravité
        this.fall.scl(dt); //-- Multiplie valeur par le Delta Time
        this.position.add(this.fall);
        this.fall.scl(1 / dt);

        if (this.position.y < 70) {
            position.y = 70;
            this.fall.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                this.fall.y == 0) {
            this.texture = new Texture("characters/p1_stand.png");
        }
        if (this.fall.y>0) //On saute
        {
            this.texture = new Texture("characters/p1_jump.png");
        }
        if (this.fall.y<0) //On tombe
        {
            this.texture = new Texture("characters/p1_hurt.png");
        }

//        System.out.println(this.fall.y);
    }

    public void moveRight() {
        this.position.x += Constants.CHAR_SPEED;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
        if (this.position.x > Constants.VIEWPORT_WIDTH - this.texture.getWidth()) {
            this.position.x = Constants.VIEWPORT_WIDTH - this.texture.getWidth();
        }
    }

    public void moveLeft() {
        this.position.x -= Constants.CHAR_SPEED;
        this.texture = this.walking.getTexture();
        this.walking.update(Gdx.graphics.getDeltaTime());
        if (this.position.x <= 0) {
            this.position.x = 0;
        }
    }

    public void jump() {
        if (this.fall.y == 0)
            this.fall.y = 1350;
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
