package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class PauseScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private BitmapFont font;

    public PauseScreen(AngryBirdsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        // Create the font and style needed for the buttons
        font = new BitmapFont(); // Default font, can load custom font if required
        Skin skin = new Skin();
        skin.add("default", font);

        // Create a TextButtonStyle with the font
        TextButtonStyle style = new TextButtonStyle();
        style.font = skin.getFont("default");

        // Create UI elements
        createUI(style);
    }

    private void createUI(TextButtonStyle buttonStyle) {
        TextButton resumeButton = new TextButton("Resume", buttonStyle);
        resumeButton.setPosition(350, 300); // Adjust as needed
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen1(game)); // Go back to the game screen
            }
        });
        stage.addActor(resumeButton);

        TextButton mainMenuButton = new TextButton("Main Menu", buttonStyle);
        mainMenuButton.setPosition(350, 200); // Adjust as needed
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game)); // Go to the main menu screen
            }
        });
        stage.addActor(mainMenuButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
