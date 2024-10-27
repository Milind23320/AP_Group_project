package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.audio.Music;
public class LoadGameScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private Sound sound;
    // Textures
    private Texture backgroundTexture;
    private Texture[] levelTextures;
    private Texture quitButtonTexture;
    private Texture settingsButtonTexture;

    public LoadGameScreen(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        // Load the background texture
        backgroundTexture = new Texture("default_background.jpeg");
        quitButtonTexture = new Texture("quit_button.png");
        settingsButtonTexture = new Texture("settings_button.png");
        sound =Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
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
                        sound.play();
                        game.setScreen(new GameScreen1(game)); // Assuming GameScreen1 is Level 1
                    }
                });
                // Set color to green for easy
                levelImage.setColor(Color.GREEN);
            } else if (i == 4) { // Level 5 - Medium
                levelImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        sound.play();
                        game.setScreen(new GameScreen2(game)); // Assuming GameScreen2 is Level 5
                    }
                });
                // Set color to yellow for medium
                levelImage.setColor(Color.YELLOW);
            } else if (i == 8) { // Level 9 - Hard
                levelImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        sound.play();
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

        // Create a Table for the top-right corner buttons (quit and settings)
        Table buttonTable = new Table();
        buttonTable.top().right(); // Position at the top-right
        buttonTable.setFillParent(true);

        // Set up Quit Button
        Image quitButton = new Image(quitButtonTexture);
        quitButton.setSize(45, 45); // Resized
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new HomeScreen(game));
            }
        });

        // Set up Settings Button
        Image settingsButton = new Image(settingsButtonTexture);
        settingsButton.setSize(45, 45); // Resized
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new SettingsScreen(game,LoadGameScreen.this));
            }
        });

        // Add buttons to the buttonTable
        buttonTable.add(settingsButton).size(45, 45).padRight(10); // Padding between buttons
        buttonTable.add(quitButton).size(45, 45).padRight(10); // Padding for spacing

        // Add buttonTable to the stage last so it appears on top
        stage.addActor(buttonTable);
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        quitButtonTexture.dispose();
        settingsButtonTexture.dispose();

        // Dispose level textures
        for (Texture levelTexture : levelTextures) {
            levelTexture.dispose();
        }
    }
}
