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
public class GameScreen2_levelend implements Screen {
    private Sound sound;
    private AngryBirdsGame game;
    private Stage stage;
    private Texture slingshotTexture;
    private Texture backgroundTexture;

    // Textures for birds
    private Texture redBirdTexture;
    private Texture blueBirdTexture;
    private Texture yellowBirdTexture;

    // Textures for obstacles (Glass versions)
    private Texture glassSquareTexture;
    private Texture glassCircleTexture;
    private Texture glassRectangleTexture;
    private Texture glassTriangleTexture;
    private Texture glassRightTriangleTexture;
    private Texture glassSmallStickTexture;
    private Texture glassStickTexture;

    // Textures for buttons
    private Texture quitButtonTexture;
    private Texture pauseButtonTexture;
    private Texture settingsButtonTexture;

    private Texture pigTexture1;
    private Texture pigTexture2;
    private Texture pigTexture3;
    // Level completion texture
    private Texture levelCompletedTexture;

    public GameScreen2_levelend(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(800, 600));
        sound =Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        // Load essential textures
        slingshotTexture = new Texture("slingshot.png");
        backgroundTexture = new Texture("background_game2.jpg");
        redBirdTexture = new Texture("red_bird.png");
        blueBirdTexture = new Texture("blue_bird.png");
        yellowBirdTexture = new Texture("yellow_bird.png");

        // Load glass block textures
        glassSquareTexture = new Texture("glass_square_block.png");
        glassCircleTexture = new Texture("glass_circle_block.png");
        glassRectangleTexture = new Texture("glass_rectangle_block.png");
        glassTriangleTexture = new Texture("glass_triangle_block.png");
        glassRightTriangleTexture = new Texture("glass_right_triangle_block.png");
        glassSmallStickTexture = new Texture("glass_small_stick.png");
        glassStickTexture = new Texture("glass_stick.png");

        // Load button textures
        quitButtonTexture = new Texture("quit_button.png");
        pauseButtonTexture = new Texture("pause_button.png");
        settingsButtonTexture = new Texture("settings_button.png");

        // Load pig textures
        pigTexture1 = new Texture("pig_small.png");
        pigTexture2 = new Texture("pig_small.png");
        pigTexture3 = new Texture("pig_small.png");
        levelCompletedTexture = new Texture("level_completed.png"); // Load level completion image

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

        // Create a simple structure to cover pigs
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
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.WHITE;

        Label retryButton = new Label("Retry", buttonStyle);
        retryButton.setFontScale(1.5f);
        retryButton.setPosition(300, 230);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new GameScreen2(game)); // Go back to GameScreen2
            }
        });
        stage.addActor(retryButton);

        // Create Next Level button
        Label nextLevelButton = new Label("Next Level", buttonStyle);
        nextLevelButton.setFontScale(1.5f);
        nextLevelButton.setPosition(300, 180);
        nextLevelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play();
                game.setScreen(new GameScreen3(game)); // Go to GameScreen3
            }
        });
        stage.addActor(nextLevelButton);
    }

    private void arrangeObstacles() {
        // Base blocks
        Image glassSquareLeft = new Image(glassSquareTexture);
        glassSquareLeft.setSize(40, 40);
        glassSquareLeft.setPosition(450, 80);
        stage.addActor(glassSquareLeft);

        Image glassSquareMiddle = new Image(glassSquareTexture);
        glassSquareMiddle.setSize(40, 40);
        glassSquareMiddle.setPosition(500, 80);
        stage.addActor(glassSquareMiddle);

        Image glassSquareRight = new Image(glassSquareTexture);
        glassSquareRight.setSize(40, 40);
        glassSquareRight.setPosition(550, 80);
        stage.addActor(glassSquareRight);

        // Vertical pillars (using rectangles or sticks)
        Image glassRectangleLeft = new Image(glassRectangleTexture);
        glassRectangleLeft.setSize(20, 100);
        glassRectangleLeft.setPosition(430, 80);
        stage.addActor(glassRectangleLeft);

        Image glassRectangleRight = new Image(glassRectangleTexture);
        glassRectangleRight.setSize(20, 100);
        glassRectangleRight.setPosition(590, 80);
        stage.addActor(glassRectangleRight);

        // Top horizontal block
        Image glassHorizontal = new Image(glassStickTexture);
        glassHorizontal.setSize(160, 20);
        glassHorizontal.setPosition(442, 178);
        stage.addActor(glassHorizontal);

        // Small glass triangles
        Image glassTriangleLeft = new Image(glassTriangleTexture);
        glassTriangleLeft.setSize(20, 20);
        glassTriangleLeft.setPosition(475, 118);
        stage.addActor(glassTriangleLeft);

        Image glassTriangleRight = new Image(glassTriangleTexture);
        glassTriangleRight.setSize(20, 20);
        glassTriangleRight.setPosition(555, 118);
        stage.addActor(glassTriangleRight);

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
                game.pauseCurrentScreen(GameScreen2_levelend.this);
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
                game.setScreen(new SettingsScreen(game,GameScreen2_levelend.this));
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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        slingshotTexture.dispose();
        backgroundTexture.dispose();
        redBirdTexture.dispose();
        blueBirdTexture.dispose();
        yellowBirdTexture.dispose();
        glassSquareTexture.dispose();
        glassCircleTexture.dispose();
        glassRectangleTexture.dispose();
        glassTriangleTexture.dispose();
        glassRightTriangleTexture.dispose();
        glassSmallStickTexture.dispose();
        glassStickTexture.dispose();
        quitButtonTexture.dispose();
        pauseButtonTexture.dispose();
        settingsButtonTexture.dispose();
        pigTexture1.dispose();
        pigTexture2.dispose();
        pigTexture3.dispose();
        levelCompletedTexture.dispose();
    }
}

