package com.andrologiciels;

import com.andrologiciels.managers.GameStateManager;
import com.andrologiciels.states.MainMenu;
import com.andrologiciels.states.Test;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LaunchGame extends ApplicationAdapter {
    private GameStateManager gsm;
    private SpriteBatch sb;

    @Override
    public void create() {
        this.gsm = new GameStateManager();
        this.sb = new SpriteBatch();

       // this.gsm.push(new Test(this.gsm));
        this.gsm.push(new MainMenu(this.gsm));

    }

    @Override
    public void render() {
        this.gsm.update(Gdx.graphics.getDeltaTime());
        this.gsm.render(this.sb);

    }

    @Override
    public void dispose() {
        this.sb.dispose();
    }
}
