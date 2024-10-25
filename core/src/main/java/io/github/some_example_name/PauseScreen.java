package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private Texture backgroundTexture;
    private Screen prevScreen; // Previous screen reference

    public PauseScreen(AngryBirdsGame game, Screen prevScreen) {
        this.game = game;
        this.prevScreen = prevScreen;
        this.stage = new Stage(new ScreenViewport());

        // Load the background texture
        backgroundTexture = new Texture("default_background.jpeg");

        // Set up background image
        Image background = new Image(backgroundTexture);
        background.setFillParent(true); // Ensures it covers the entire screen
        stage.addActor(background);

        // Set up font and style for the buttons
        font = new BitmapFont(); // You can use a custom font file if needed
        Skin skin = new Skin();
        skin.add("default-font", font);

        // Create a TextButtonStyle with the font
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.font = skin.getFont("default-font");
        buttonStyle.fontColor = Color.WHITE; // Set font color for better visibility

        // Set up UI elements
        createUI(buttonStyle);
    }

    private void createUI(TextButtonStyle buttonStyle) {
        // Create and configure Resume button
        TextButton resumeButton = new TextButton("Resume", buttonStyle);
        resumeButton.setPosition(100, 300); // Positioning
        resumeButton.setSize(150, 50); // Size adjustment
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(prevScreen);// Return to the game screen that was paused
                if(prevScreen instanceof GameScreen1){
                    Gdx.input.setInputProcessor(((GameScreen1) prevScreen).getStage());
                } else if (prevScreen instanceof GameScreen2) {
                    Gdx.input.setInputProcessor(((GameScreen2) prevScreen).getStage());
                } else if (prevScreen instanceof GameScreen3) {
                    Gdx.input.setInputProcessor(((GameScreen3) prevScreen).getStage());
                }
            }
        });
        stage.addActor(resumeButton);

        // Create and configure Main Menu button
        TextButton mainMenuButton = new TextButton("Main Menu", buttonStyle);
        mainMenuButton.setPosition(100, 200); // Positioning
        mainMenuButton.setSize(150, 50); // Size adjustment
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game)); // Navigate to the main menu
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
        // Clear the screen with a dark color
        Gdx.gl.glClearColor(0, 0, 0, 0.7f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the stage
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
        backgroundTexture.dispose();
    }

    public InputProcessor getStage() {
        return stage;
    }
}
