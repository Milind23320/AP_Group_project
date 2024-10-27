package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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

public class GameScreen1 implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private Texture slingshotTexture;
    private Texture backgroundTexture;

    // Textures for birds
    private Texture redBirdTexture;
    private Texture blueBirdTexture;
    private Texture yellowBirdTexture;

    // Textures for obstacles
    private Texture woodSquareTexture;
    private Texture woodCircleTexture;
    private Texture woodRectangleTexture;
    private Texture woodTriangleTexture;
    private Texture woodRightTriangleTexture;
    private Texture woodSmallStickTexture;
    private Texture woodStickTexture;

    // Textures for buttons
    private Texture quitButtonTexture;
    private Texture pauseButtonTexture;
    private Texture settingsButtonTexture;

    private Texture pigTexture1;
    private Texture pigTexture2;
    private Texture pigTexture3;

    public GameScreen1(AngryBirdsGame game) {
        this.game = game;
        // Set up viewport to maintain aspect ratio
        stage = new Stage(new FitViewport(800, 600));

        // Load essential textures
        slingshotTexture = new Texture("slingshot.png");
        backgroundTexture = new Texture("background_game.jpeg");

        // Load bird textures
        redBirdTexture = new Texture("red_bird.png");
        blueBirdTexture = new Texture("blue_bird.png");
        yellowBirdTexture = new Texture("yellow_bird.png");

        // Load block textures
        woodSquareTexture = new Texture("wooden_square_block.png");
        woodCircleTexture = new Texture("wooden_circle_block.png");
        woodRectangleTexture = new Texture("wooden_rectangle_block.png");
        woodTriangleTexture = new Texture("wooden_triangle_block.png");
        woodRightTriangleTexture = new Texture("wooden_right_triangle_block.png");
        woodSmallStickTexture = new Texture("wooden_small_stick.png");
        woodStickTexture = new Texture("wooden_stick.png");

        // Load button textures
        quitButtonTexture = new Texture("quit_button.png");
        pauseButtonTexture = new Texture("pause_button.png");
        settingsButtonTexture = new Texture("settings_button.png");

        // Load pig textures
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

        // Create a simple structure to cover pigs
        arrangeObstacles();

        // Add buttons (quit, pause, settings)
        setupButtons();
        createFinishLevelButton();
    }

    private void arrangeObstacles() {
        // Base blocks
        Image woodSquareLeft = new Image(woodSquareTexture);
        woodSquareLeft.setSize(40, 40);
        woodSquareLeft.setPosition(450, 80); // Move slightly to the left
        stage.addActor(woodSquareLeft);

        Image woodSquareMiddle = new Image(woodSquareTexture);
        woodSquareMiddle.setSize(40, 40);
        woodSquareMiddle.setPosition(500, 80); // Place in the middle
        stage.addActor(woodSquareMiddle);

        Image woodSquareRight = new Image(woodSquareTexture);
        woodSquareRight.setSize(40, 40);
        woodSquareRight.setPosition(550, 80); // Move slightly to the right
        stage.addActor(woodSquareRight);

        // Vertical pillars (using rectangles or sticks)
        Image woodRectangleLeft = new Image(woodRectangleTexture);
        woodRectangleLeft.setSize(20, 100); // Taller rectangle
        woodRectangleLeft.setPosition(430, 80); // Above the left block
        stage.addActor(woodRectangleLeft);

        Image woodRectangleRight = new Image(woodRectangleTexture);
        woodRectangleRight.setSize(20, 100); // Taller rectangle
        woodRectangleRight.setPosition(590, 80); // Above the right block
        stage.addActor(woodRectangleRight);

        // Top horizontal block
        Image woodHorizontal = new Image(woodStickTexture);
        woodHorizontal.setSize(160, 20); // Longer horizontal block as roof
        woodHorizontal.setPosition(442, 178);
        stage.addActor(woodHorizontal);

        // Small wooden triangles for added stability (optional)
        Image woodTriangleLeft = new Image(woodTriangleTexture);
        woodTriangleLeft.setSize(20, 20);
        woodTriangleLeft.setPosition(475, 118); // Positioned closer to the top block
        stage.addActor(woodTriangleLeft);

        Image woodTriangleRight = new Image(woodTriangleTexture);
        woodTriangleRight.setSize(20, 20);
        woodTriangleRight.setPosition(555, 118); // Positioned closer to the top block
        stage.addActor(woodTriangleRight);

        // Adjust pigs to fit inside the structure
        Image pig1 = new Image(pigTexture1);
        pig1.setSize(30, 30); // Slightly smaller pigs
        pig1.setPosition(455, 85); // Move over the left base block
        stage.addActor(pig1);

        Image pig2 = new Image(pigTexture2);
        pig2.setSize(30, 30); // Slightly smaller pigs
        pig2.setPosition(505, 85); // Move over the middle base block
        stage.addActor(pig2);

        Image pig3 = new Image(pigTexture3);
        pig3.setSize(30, 30); // Slightly smaller pigs
        pig3.setPosition(555, 85); // Move over the right base block
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
                game.pauseCurrentScreen(GameScreen1.this);
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
                game.setScreen(new SettingsScreen(game, GameScreen1.this));
            }
        });
        stage.addActor(settingsButton);
    }

    private void createFinishLevelButton() {
        // Create "Finish Level" button
        Label.LabelStyle buttonStyle = new Label.LabelStyle();
        buttonStyle.font = new BitmapFont(); // Use a suitable font
        buttonStyle.fontColor = Color.WHITE; // White text color

        Label finishLevelButton = new Label("Finish Level", buttonStyle);
        finishLevelButton.setFontScale(2f); // Scale up the text

        // Position at the bottom right of the screen
        finishLevelButton.setPosition(650, 30); // Adjust the position as needed (x=650, y=30)

        finishLevelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen1_levelend(game)); // Navigate to level end screen
            }
        });

        stage.addActor(finishLevelButton);
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
        woodSquareTexture.dispose();
        woodCircleTexture.dispose();
        woodRectangleTexture.dispose();
        woodTriangleTexture.dispose();
        woodRightTriangleTexture.dispose();
        woodSmallStickTexture.dispose();
        woodStickTexture.dispose();
        quitButtonTexture.dispose();
        pauseButtonTexture.dispose();
        settingsButtonTexture.dispose();
        pigTexture1.dispose();
        pigTexture2.dispose();
        pigTexture3.dispose();
    }

    public InputProcessor getStage() {
        return stage;
    }
}




