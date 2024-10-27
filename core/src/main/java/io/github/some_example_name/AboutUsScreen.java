package io.github.some_example_name;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class AboutUsScreen implements Screen {
    private Sound sound;
    private AngryBirdsGame game;
    private Stage stage;
    private Texture backgroundTexture;
    private Texture darkBoxTexture;
    private Texture closeButtonTexture; // Texture for the close button
    private Screen pauseprevscreen;
    public AboutUsScreen(AngryBirdsGame game,Screen pauseprevscreen) {
        this.game = game;

        this.pauseprevscreen=pauseprevscreen;
        stage = new Stage(new FitViewport(800, 600));

        // Load the background texture
        backgroundTexture = new Texture("About_us_screen.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        // Load the dark box texture
        darkBoxTexture = new Texture("black_screen.png");

        // Load the close button texture
        closeButtonTexture = new Texture("cross.png"); // Use your image filename here

        // Create and add background
        Image background = new Image(backgroundTexture);
        background.setSize(800, 600);
        stage.addActor(background);

        // Create a dark box using the new texture
        Image darkBox = new Image(new TextureRegionDrawable(darkBoxTexture));
        darkBox.setSize(300, 700);
        darkBox.setPosition(287, -100);
        stage.addActor(darkBox);

        // Add the "About Us" title
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = new BitmapFont();
        titleStyle.fontColor = Color.WHITE;

        Label aboutUsTitle = new Label("About Us", titleStyle);
        aboutUsTitle.setFontScale(2);
        aboutUsTitle.setPosition(375, 430);
        stage.addActor(aboutUsTitle);

        Label Welcometitle = new Label("Welcome\n\nto\n\nAngryBirdCity", titleStyle);
        Welcometitle.setFontScale(1.3f);
        Welcometitle.setPosition(110, 270);
        stage.addActor(Welcometitle);

        Label Waypointer = new Label("This Way!", titleStyle);
        Waypointer.setFontScale(0.9f);
        Waypointer.setPosition(130, 220);
        stage.addActor(Waypointer);

        // Add descriptive text about the developers
        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = new BitmapFont();
        textStyle.fontColor = Color.WHITE;

        Label aboutUsText = new Label(
            "This game was developed by 2 engineering students of the 2nd year CSE department of IIITD:- \n\nMilind Kumar, roll - 2023320\nDeepak Meena, roll - 2023188\n\n\nWe hope you enjoy playing!",
            textStyle
        );
        aboutUsText.setFontScale(1.2f);
        aboutUsText.setWrap(true);
        aboutUsText.setWidth(240);
        aboutUsText.setPosition(320, 220);
        stage.addActor(aboutUsText);

        // Add the close button image
        Image closeButton = new Image(closeButtonTexture);
        closeButton.setSize(50, 50); // Set the size of the button
        closeButton.setPosition(750, 550); // Position it in the top-right corner
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new SettingsScreen(game,pauseprevscreen)); // Navigate to SettingsScreen
            }
        });
        stage.addActor(closeButton);
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        darkBoxTexture.dispose();
        closeButtonTexture.dispose(); // Dispose of the close button texture
    }
}
