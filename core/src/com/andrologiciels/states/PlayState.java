package com.andrologiciels.states;

import com.andrologiciels.managers.GameStateManager;
import com.andrologiciels.sprites.Character;
import com.andrologiciels.sprites.Platform;
import com.andrologiciels.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PlayState extends GameState {

    private Texture background, ground;

    private Character character;

    private boolean backwards = false;

    private Array<Platform> platforms;

    private Vector2 backgroundPos1, backgroundPos2;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.background = new Texture("bg.bmp");
        this.ground = new Texture("bg_ground.bmp");
        this.character = new Character(Constants.VIEWPORT_WIDTH / 2, 70);
        this.platforms = new Array<Platform>();
        for (int i=0; i<Constants.PLATFORM_COUNT;i++)
        {
            this.platforms.add(new Platform(i));
        }

        this.cam.setToOrtho(false, Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
        this.backgroundPos1=new Vector2(0, this.cam.position.y-Constants.VIEWPORT_HEIGHT/2);
        this.backgroundPos2=new Vector2(0, this.cam.position.y-Constants.VIEWPORT_HEIGHT/2
                +this.background.getHeight());
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.character.moveRight();
            this.backwards=false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.character.moveLeft();
            this.backwards=true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            this.character.jump();
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
        this.character.update(dt);
        this.cam.position.y+=1;
        this.cam.update();
        this.updateBackground();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
        sb.setProjectionMatrix(this.cam.combined);
        sb.begin();
        sb.draw(this.background, 0, this.backgroundPos1.y);
        sb.draw(this.background, 0, this.backgroundPos2.y);
        sb.draw(this.ground, 0, 0);

        sb.draw(this.character.getTexture(),
                this.backwards ? this.character.getPosition().x+this.character.getTexture().getWidth()
                : this.character.getPosition().x,
                this.character.getPosition().y,
                this.backwards ? - this.character.getTexture().getWidth():
                this.character.getTexture().getWidth(),this.character.getTexture().getHeight());
        for (Platform platform:this.platforms) {
            sb.draw(platform.getTexture(), platform.getPosition().x, platform.getPosition().y);
        }
        sb.end();
    }

    private void updateBackground(){
        if (this.cam.position.y-Constants.VIEWPORT_HEIGHT/2 >
                this.backgroundPos1.y + this.background.getHeight())
        {
            this.backgroundPos1.add(0,this.background.getHeight()*2);
        }
        if (this.cam.position.y-Constants.VIEWPORT_HEIGHT/2 >
                this.backgroundPos2.y + this.background.getHeight())
        {
            this.backgroundPos2.add(0,this.background.getHeight()*2);
        }
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.ground.dispose();

        this.character.dispose();
        for (Platform platform:this.platforms) {
            platform.dispose();
        }
    }
}
