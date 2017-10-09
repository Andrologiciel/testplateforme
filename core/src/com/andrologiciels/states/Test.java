package com.andrologiciels.states;

import com.andrologiciels.managers.GameStateManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Test extends GameState {

    private Texture perso;

    public Test(GameStateManager gsm) {
        super(gsm);
        this.perso = new Texture("characters/p1_front.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(this.perso, 300, 300);
        sb.end();
    }

    @Override
    public void dispose() {
        this.perso.dispose();

    }
}
