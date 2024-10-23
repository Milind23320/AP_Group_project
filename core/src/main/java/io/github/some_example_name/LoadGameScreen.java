package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class LoadGameScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;

    // Textures
    private Texture backgroundTexture;
    private Texture[] levelTextures;

    public LoadGameScreen(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        // Load the background texture
        backgroundTexture = new Texture("default_background.jpeg");

        // Load level textures
        levelTextures = new Texture[12];
        for (int i = 0; i < 12; i++) {
            levelTextures[i] = new Texture("level" + (i + 1) + ".png");
        }

        // Set up background
        Image background = new Image(backgroundTexture);
        background.setFillParent(true); // Fill the screen
        stage.addActor(background);

        // Create a Table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Set up level images and categorize by difficulty
        for (int i = 0; i < 12; i++) {
            Image levelImage = new Image(levelTextures[i]);

            // Adjust size
            levelImage.setSize(100, 100); // Set appropriate size for each level image

            // Add click listener for specific levels
            if (i == 0) { // Level 1 - Easy
                levelImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GameScreen1(game)); // Assuming GameScreen1 is Level 1
                    }
                });
                // Set color to green for easy
                levelImage.setColor(Color.GREEN);
            } else if (i == 4) { // Level 5 - Medium
                levelImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GameScreen2(game)); // Assuming GameScreen2 is Level 5
                    }
                });
                // Set color to yellow for medium
                levelImage.setColor(Color.YELLOW);
            } else if (i == 8) { // Level 9 - Hard
                levelImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GameScreen3(game)); // Assuming GameScreen3 is Level 9
                    }
                });
                // Set color to red for hard
                levelImage.setColor(Color.RED);
            }

            // Arrange in a grid format - 4 columns
            table.add(levelImage).size(100, 100).pad(15);
            if ((i + 1) % 4 == 0) {
                table.row(); // Move to next row after 4 items
            }
        }

        // Add table to stage
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw stage, which will render all actors
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
        backgroundTexture.dispose();

        // Dispose level textures
        for (Texture levelTexture : levelTextures) {
            levelTexture.dispose();
        }
    }
}
