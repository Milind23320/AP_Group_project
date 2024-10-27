package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
public class GameScreen3_levelend implements Screen {
    private Sound sound;
    private AngryBirdsGame game;
    private Stage stage;
    private Texture slingshotTexture;
    private Texture backgroundTexture;

    // Textures for birds
    private Texture redBirdTexture;
    private Texture blueBirdTexture;
    private Texture yellowBirdTexture;

    // Textures for obstacles (stone counterparts)
    private Texture stoneSquareTexture;
    private Texture stoneCircleTexture;
    private Texture stoneRectangleTexture;
    private Texture stoneTriangleTexture;
    private Texture stoneRightTriangleTexture;
    private Texture stoneSmallStickTexture;
    private Texture stoneStickTexture;

    // Textures for buttons
    private Texture quitButtonTexture;
    private Texture pauseButtonTexture;
    private Texture settingsButtonTexture;

    private Texture pigTexture1;
    private Texture pigTexture2;
    private Texture pigTexture3;
    // Level completion texture
    private Texture levelCompletedTexture;

    public GameScreen3_levelend(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(800, 600));

        // Load essential textures
        slingshotTexture = new Texture("slingshot.png");
        backgroundTexture = new Texture("background_game3.jpeg"); // Retain background from GameScreen3
        redBirdTexture = new Texture("red_bird.png");
        blueBirdTexture = new Texture("blue_bird.png");
        yellowBirdTexture = new Texture("yellow_bird.png");

        // Load block textures (stone counterparts)
        stoneSquareTexture = new Texture("stone_square_block.png");
        stoneCircleTexture = new Texture("stone_circle_block.png");
        stoneRectangleTexture = new Texture("stone_rectangle_block.png");
        stoneTriangleTexture = new Texture("stone_triangle_block.png");
        stoneRightTriangleTexture = new Texture("stone_right_triangle_block.png");
        stoneSmallStickTexture = new Texture("stone_small_stick.png");
        stoneStickTexture = new Texture("stone_stick.png");

        // Load button textures
        quitButtonTexture = new Texture("quit_button.png");
        pauseButtonTexture = new Texture("pause_button.png");
        settingsButtonTexture = new Texture("settings_button.png");

        // Load pig textures
        pigTexture1 = new Texture("pig_small.png");
        pigTexture2 = new Texture("pig_small.png");
        pigTexture3 = new Texture("pig_small.png");
        levelCompletedTexture = new Texture("level_completed.png"); // Load level completion image
        sound =Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
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
        slingshot.setSize(100, 100);
        slingshot.setPosition(110, 80);
        stage.addActor(slingshot);

        // Create and add birds
        Image redBird = new Image(redBirdTexture);
        redBird.setSize(80, 80);
        redBird.setPosition(100, 80);
        stage.addActor(redBird);

        Image blueBird = new Image(blueBirdTexture);
        blueBird.setSize(50, 50);
        blueBird.setPosition(68, 80);
        stage.addActor(blueBird);

        Image yellowBird = new Image(yellowBirdTexture);
        yellowBird.setSize(50, 50);
        yellowBird.setPosition(20, 80);
        stage.addActor(yellowBird);

        // Create a simple structure using stone blocks
        arrangeObstacles();

        // Add buttons (quit, pause, settings)
        setupButtons();

        // Add the level completed image at the center of the screen
        Image levelCompletedImage = new Image(levelCompletedTexture);
        levelCompletedImage.setSize(400, 320);
        levelCompletedImage.setPosition(200, 300);
        stage.addActor(levelCompletedImage);

        // Create Retry button
        Label.LabelStyle buttonStyle = new Label.LabelStyle();
        buttonStyle.font = new BitmapFont(); // Use a suitable font
        buttonStyle.fontColor = Color.WHITE; // White text color

        Label retryButton = new Label("Retry", buttonStyle);
        retryButton.setFontScale(1.5f);
        retryButton.setPosition(300, 230);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new GameScreen3(game)); // Retry the same screen
            }
        });
        stage.addActor(retryButton);
    }

    private void arrangeObstacles() {
        // Base blocks
        Image stoneSquareLeft = new Image(stoneSquareTexture);
        stoneSquareLeft.setSize(40, 40);
        stoneSquareLeft.setPosition(450, 80);
        stage.addActor(stoneSquareLeft);

        Image stoneSquareMiddle = new Image(stoneSquareTexture);
        stoneSquareMiddle.setSize(40, 40);
        stoneSquareMiddle.setPosition(500, 80);
        stage.addActor(stoneSquareMiddle);

        Image stoneSquareRight = new Image(stoneSquareTexture);
        stoneSquareRight.setSize(40, 40);
        stoneSquareRight.setPosition(550, 80);
        stage.addActor(stoneSquareRight);

        // Vertical pillars (using rectangles or sticks)
        Image stoneRectangleLeft = new Image(stoneRectangleTexture);
        stoneRectangleLeft.setSize(20, 100);
        stoneRectangleLeft.setPosition(430, 80);
        stage.addActor(stoneRectangleLeft);

        Image stoneRectangleRight = new Image(stoneRectangleTexture);
        stoneRectangleRight.setSize(20, 100);
        stoneRectangleRight.setPosition(590, 80);
        stage.addActor(stoneRectangleRight);

        // Top horizontal block
        Image stoneHorizontal = new Image(stoneStickTexture);
        stoneHorizontal.setSize(160, 20);
        stoneHorizontal.setPosition(442, 178);
        stage.addActor(stoneHorizontal);

        // Adjust pigs to fit inside the structure
        Image pig1 = new Image(pigTexture1);
        pig1.setSize(30, 30);
        pig1.setPosition(455, 85);
        stage.addActor(pig1);

        Image pig2 = new Image(pigTexture2);
        pig2.setSize(30, 30);
        pig2.setPosition(505, 85);
        stage.addActor(pig2);

        Image pig3 = new Image(pigTexture3);
        pig3.setSize(30, 30);
        pig3.setPosition(555, 85);
        stage.addActor(pig3);
    }

    private void setupButtons() {
        // Quit Button
        Image quitButton = new Image(quitButtonTexture);
        quitButton.setSize(45, 45);
        quitButton.setPosition(730, 530);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new HomeScreen(game));
            }
        });
        stage.addActor(quitButton);

        // Pause Button
        Image pauseButton = new Image(pauseButtonTexture);
        pauseButton.setSize(45, 45);
        pauseButton.setPosition(660, 530);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.pauseCurrentScreen(GameScreen3_levelend.this);
            }
        });
        stage.addActor(pauseButton);

        // Settings Button
        Image settingsButton = new Image(settingsButtonTexture);
        settingsButton.setSize(45, 45);
        settingsButton.setPosition(590, 530);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new SettingsScreen(game, GameScreen3_levelend.this));
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
        slingshotTexture.dispose();
        backgroundTexture.dispose();
        redBirdTexture.dispose();
        blueBirdTexture.dispose();
        yellowBirdTexture.dispose();
        stoneSquareTexture.dispose();
        stoneCircleTexture.dispose();
        stoneRectangleTexture.dispose();
        stoneTriangleTexture.dispose();
        stoneRightTriangleTexture.dispose();
        stoneSmallStickTexture.dispose();
        stoneStickTexture.dispose();
        quitButtonTexture.dispose();
        pauseButtonTexture.dispose();
        settingsButtonTexture.dispose();
        pigTexture1.dispose();
        pigTexture2.dispose();
        pigTexture3.dispose();

    }
}
