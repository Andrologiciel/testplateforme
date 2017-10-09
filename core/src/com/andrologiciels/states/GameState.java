package com.andrologiciels.states;


import com.andrologiciels.managers.GameStateManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {
    protected OrthographicCamera cam;
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.cam = new OrthographicCamera();
        this.gsm = gsm;
    }

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();


}
