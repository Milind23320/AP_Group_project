package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/** First screen of the application. Displayed after the application is created. */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class HomeScreen implements Screen {
    private final AngryBirdsGame game;
    private Stage stage;
    private Texture background;
    private BitmapFont font;
    private Skin skin;

    public HomeScreen(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        background = new Texture("background_home.png");

        // Load font and create skin
        font = new BitmapFont(Gdx.files.internal("fonts/default.fnt")); // Ensure this file exists
        font.getData().setScale(1);
        skin = new Skin();
        skin.add("default-font", font);

        // Create a TextButtonStyle
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = skin.getFont("default-font");
        skin.add("default", textButtonStyle);

        createUI();
    }

    private void createUI() {
        // Use the created TextButtonStyle
        TextButton newGameButton = new TextButton("New Game", skin);
        newGameButton.setPosition((float) Gdx.graphics.getWidth()/2 - newGameButton.getWidth()/2, 300);
        stage.addActor(newGameButton);

        TextButton loadGameButton = new TextButton("Load Game", skin);
        loadGameButton.setPosition((float) Gdx.graphics.getWidth()/2 - loadGameButton.getWidth()/2 , 200);
        stage.addActor(loadGameButton);

        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.setPosition((float) Gdx.graphics.getWidth()/2 - settingsButton.getWidth()/2, 100);
        stage.addActor(settingsButton);

        // Add click listeners for buttons
        // TODO: Implement event handling for navigation
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event ,float x,float y){
                game.setScreen(new GameScreen1(game));
            }
        });
        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event ,float x, float y){
                game.setScreen(new LoadGameScreen(game));
            }
        });
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event ,float x, float y){
                game.setScreen(new SettingsScreen(game));
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        // Draw the background, stretched to fit the entire screen
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.batch.end();
        stage.act();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        // Make sure the viewport scales properly
        stage.getViewport().setWorldSize(width, height);
        game.batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        font.dispose();
        skin.dispose();
    }
}
