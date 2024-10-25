package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen2 implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private Texture slingshotTexture;
    private Texture backgroundTexture;

    // Textures for birds
    private Texture redBirdTexture;
    private Texture blueBirdTexture;
    private Texture yellowBirdTexture;

    // Textures for blocks
    private Texture woodSquareTexture;
    private Texture glassTriangleTexture;
    private Texture stoneCircleTexture;

    // Textures for buttons
    private Texture quitButtonTexture;
    private Texture pauseButtonTexture;
    private Texture settingsButtonTexture;

    private Texture pigTexture1;
    private Texture pigTexture2;
    private Texture pigTexture3;

    public GameScreen2(AngryBirdsGame game) {
        this.game = game;

        // Set up viewport to maintain aspect ratio
        stage = new Stage(new FitViewport(800, 600));

        // Load essential textures
        slingshotTexture = new Texture("slingshot.png");
        backgroundTexture = new Texture("background_game2.jpg");

        // Load bird textures
        redBirdTexture = new Texture("red_bird.png");
        blueBirdTexture = new Texture("blue_bird.png");
        yellowBirdTexture = new Texture("yellow_bird.png");

        // Load block textures (example)
        woodSquareTexture = new Texture("wooden_square_block.png");
        glassTriangleTexture = new Texture("glass_triangle_block.png");
        stoneCircleTexture = new Texture("stone_circle_block.png");

        // Load button textures
        quitButtonTexture = new Texture("quit_button.png");
        pauseButtonTexture = new Texture("pause_button.png");
        settingsButtonTexture = new Texture("settings_button.png");

        pigTexture1 = new Texture("pig_small.png");
        pigTexture2 = new Texture("pig_small.png");
        pigTexture3 = new Texture("pig_small.png");

        // Initialize UI components
        setupUI();
    }

    private void setupUI() {
        // Create and add background
        Image background = new Image(backgroundTexture);
        background.setSize(800, 600);
        stage.addActor(background);

        // Create and add slingshot
        Image slingshot = new Image(slingshotTexture);
        slingshot.setSize(100, 100); // Resized for smaller screen presence
        slingshot.setPosition(110, 80);
        stage.addActor(slingshot);

        // Create and add birds
        Image redBird = new Image(redBirdTexture);
        redBird.setSize(80, 80); // Resized
        redBird.setPosition(100, 80);
        stage.addActor(redBird);

        Image blueBird = new Image(blueBirdTexture);
        blueBird.setSize(50, 50); // Resized
        blueBird.setPosition(68, 80);
        stage.addActor(blueBird);

        Image yellowBird = new Image(yellowBirdTexture);
        yellowBird.setSize(50, 50); // Resized
        yellowBird.setPosition(20, 80);
        stage.addActor(yellowBird);

        // Create and add pigs
        Image pig1 = new Image(pigTexture1);
        pig1.setSize(40, 40); // Adjust size as necessary
        pig1.setPosition(500, 80);
        stage.addActor(pig1);

        Image pig2 = new Image(pigTexture2);
        pig2.setSize(40, 40);
        pig2.setPosition(550, 80);
        stage.addActor(pig2);

        Image pig3 = new Image(pigTexture3);
        pig3.setSize(40, 40);
        pig3.setPosition(600, 80);
        stage.addActor(pig3);

        // Add blocks
        Image woodSquare = new Image(woodSquareTexture);
        woodSquare.setSize(30, 30);
        woodSquare.setPosition(300, 150);
        stage.addActor(woodSquare);

        Image glassTriangle = new Image(glassTriangleTexture);
        glassTriangle.setSize(30, 30);
        glassTriangle.setPosition(350, 150);
        stage.addActor(glassTriangle);

        Image stoneCircle = new Image(stoneCircleTexture);
        stoneCircle.setSize(30, 30);
        stoneCircle.setPosition(400, 150);
        stage.addActor(stoneCircle);

        // Add Quit Button
        Image quitButton = new Image(quitButtonTexture);
        quitButton.setSize(45, 45); // Resized
        quitButton.setPosition(730, 530);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
            }
        });
        stage.addActor(quitButton);

        // Add Pause Button
        Image pauseButton = new Image(pauseButtonTexture);
        pauseButton.setSize(45, 45); // Resized
        pauseButton.setPosition(660, 530);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.pauseCurrentScreen(GameScreen2.this);
            }
        });
        stage.addActor(pauseButton);

        // Add Settings Button
        Image settingsButton = new Image(settingsButtonTexture);
        settingsButton.setSize(45, 45);
        settingsButton.setPosition(590, 530);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });
        stage.addActor(settingsButton);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);

        // No need to dispose of anything here; just make sure the screen is not active
        Gdx.app.log("GameScreen2", "Screen is hidden.");
    }

    @Override
    public void dispose() {
        stage.dispose();
        slingshotTexture.dispose();
        backgroundTexture.dispose();

        // Dispose bird textures
        redBirdTexture.dispose();
        blueBirdTexture.dispose();
        yellowBirdTexture.dispose();

        // Dispose block textures
        woodSquareTexture.dispose();
        glassTriangleTexture.dispose();
        stoneCircleTexture.dispose();

        // Dispose button textures
        quitButtonTexture.dispose();
        pauseButtonTexture.dispose();
        settingsButtonTexture.dispose();

        // Dispose pig textures
        pigTexture1.dispose();
        pigTexture2.dispose();
        pigTexture3.dispose();
    }

    public InputProcessor getStage() {
        return stage;
    }
}
