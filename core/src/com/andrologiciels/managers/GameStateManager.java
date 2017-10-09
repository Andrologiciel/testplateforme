package com.andrologiciels.managers;


import com.andrologiciels.states.GameState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<GameState> gameStates;

    public GameStateManager() {
        this.gameStates = new Stack<GameState>();
    }

    public void push(GameState gameState) {
        this.gameStates.push(gameState);
    }

    public void set(GameState gameState) {
        this.gameStates.pop().dispose();
        this.gameStates.push(gameState);
    }

    public void update(float dt) {
        this.gameStates.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        this.gameStates.peek().render(sb);
    }
}
