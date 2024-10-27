package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Music;
public class SettingsScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private Texture backgroundTexture;
    private Texture crossButtonTexture;
    private Screen previousScreen; // Reference to the screen from which Settings was opened
    private Sound sound;
    public SettingsScreen(AngryBirdsGame game, Screen previousScreen) {

        this.game = game;
        this.previousScreen = previousScreen;
        this.stage = new Stage(new ScreenViewport());
        sound =Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));

        // Load the background texture (same as PauseScreen)
        backgroundTexture = new Texture("default_background.jpeg");
        crossButtonTexture = new Texture("cross.png");

        // Set up background
        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        // Create the font and style for the labels
        BitmapFont font = new BitmapFont(); // Default font, customize as needed
        Skin skin = new Skin();
        skin.add("default-font", font);
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = skin.getFont("default-font");
        labelStyle.fontColor = Color.WHITE; // Set font color

        // Create UI elements (text options and buttons)
        createUI(labelStyle);
    }

    private void createUI(LabelStyle labelStyle) {
        // "Sound" Option Label
        Label soundLabel = new Label("Sound", labelStyle);
        soundLabel.setPosition(100, 300);// Adjust positioning as needed
        stage.addActor(soundLabel);

        // "Music" Option Label
        Label musicLabel = new Label("Music", labelStyle);
        musicLabel.setPosition(100, 250);// Adjust positioning as needed
        musicLabel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                sound.play();
                if (game.isMusicPlaying()) {
                    game.pauseMusic();
                } else {
                    game.resumeMusic();
                }
            }
        });
        stage.addActor(musicLabel);

        // "Rate Us" Option Label
        Label rateUsLabel = new Label("Rate Us", labelStyle);
        rateUsLabel.setPosition(100, 200); // Adjust positioning as needed
        stage.addActor(rateUsLabel);

        // "About Us" Option Label
        Label aboutUsLabel = new Label("About Us", labelStyle);
        aboutUsLabel.setPosition(100, 150); // Adjust positioning as needed
        aboutUsLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new AboutUsScreen(game,previousScreen)); // Navigate to the AboutUsScreen
            }
        });
        stage.addActor(aboutUsLabel);

        // Add "Cross" Button for Toggling Back
        Image crossButton = new Image(crossButtonTexture);
        crossButton.setSize(45, 45); // Adjust the size as needed
        crossButton.setPosition(1400, 850); // Position it at the top-right corner or where you prefer
        crossButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                // Navigate back to the previous screen (PauseScreen or HomeScreen)
                game.setScreen(previousScreen);
                if (previousScreen instanceof PauseScreen) {
                    Gdx.input.setInputProcessor(((PauseScreen) previousScreen).getStage());
                } else if (previousScreen instanceof HomeScreen) {
                    Gdx.input.setInputProcessor(((HomeScreen) previousScreen).getStage());
                }
            }
        });
        stage.addActor(crossButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        // Do not dispose resources here; just stop receiving input
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Dispose of textures when no longer needed
        stage.dispose();
        backgroundTexture.dispose();
        crossButtonTexture.dispose();
        sound.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
