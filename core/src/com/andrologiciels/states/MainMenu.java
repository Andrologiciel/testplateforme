package com.andrologiciels.states;


import com.andrologiciels.managers.GameStateManager;
import com.andrologiciels.utils.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainMenu extends GameState {
    private Texture background, ground, character;
    private BitmapFont gameTitleText, touchText, toText, startText;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private GlyphLayout gametTittleGlyph, touchGlyph, toGlyph, startGlyph;

    public MainMenu(GameStateManager gsm) {
        super(gsm);
        this.background = new Texture("bg.bmp");
        this.ground = new Texture("bg_ground.bmp");
        this.character = new Texture("characters/p1_front.png");

        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        this.parameter.size = 128;
        this.gameTitleText = this.generator.generateFont(this.parameter);

        this.parameter.size = 64;
        this.touchText = this.generator.generateFont(this.parameter);
        this.toText = this.generator.generateFont(this.parameter);
        this.startText = this.generator.generateFont(this.parameter);

        this.gametTittleGlyph = new GlyphLayout();
        this.toGlyph = new GlyphLayout();
        this.touchGlyph = new GlyphLayout();
        this.startGlyph = new GlyphLayout();

        this.gametTittleGlyph.setText(this.gameTitleText, Constants.GAME_TITLE);
        this.touchGlyph.setText(this.toText, "Touch");
        this.toGlyph.setText(this.toText, "to");
        this.startGlyph.setText(this.toText, "start");

        this.cam.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            this.gsm.set(new PlayState(this.gsm));
        }
    }

    @Override
    public void update(float dt) {
        this.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(this.cam.combined);
        sb.begin();
        sb.draw(this.background, 0, 0);
        sb.draw(this.ground, 0, 0);
        sb.draw(this.character, this.cam.viewportWidth / 2 - this.character.getWidth() / 2, this.ground.getHeight());
        this.gameTitleText.draw(sb, this.gametTittleGlyph, this.cam.viewportWidth / 2 - this.gametTittleGlyph.width / 2,
                this.cam.viewportHeight - this.gametTittleGlyph.height);
        this.touchText.draw(sb, this.touchGlyph, this.cam.viewportWidth / 2 - this.touchGlyph.width / 2,
                this.cam.viewportHeight / 2);
        this.toText.draw(sb, this.toGlyph, this.cam.viewportWidth / 2 - this.toGlyph.width / 2,
                this.cam.viewportHeight / 2 - this.touchGlyph.height);
        this.startText.draw(sb, this.startGlyph, this.cam.viewportWidth / 2 - this.startGlyph.width / 2,
                this.cam.viewportHeight / 2 - this.startGlyph.height * 2);

        sb.end();

    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.ground.dispose();
        this.character.dispose();
        this.gameTitleText.dispose();
        this.touchText.dispose();
        this.toText.dispose();
        this.startText.dispose();
        this.generator.dispose();
    }
}
