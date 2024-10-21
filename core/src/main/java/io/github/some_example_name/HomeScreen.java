package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/** First screen of the application. Displayed after the application is created. */
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HomeScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;
    private Texture background;

    public HomeScreen(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        background = new Texture("background_home.png");
        createUI();
    }

    private void createUI() {
        TextButton newGameButton = new TextButton("New Game", new TextButton.TextButtonStyle());
        newGameButton.setPosition(100, 300);
        stage.addActor(newGameButton);

        TextButton loadGameButton = new TextButton("Load Game", new TextButton.TextButtonStyle());
        loadGameButton.setPosition(100, 200);
        stage.addActor(loadGameButton);

        TextButton settingsButton = new TextButton("Settings", new TextButton.TextButtonStyle());
        settingsButton.setPosition(100, 100);
        stage.addActor(settingsButton);

        // Add click listeners for buttons
        // TODO: Implement event handling for navigation
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();
        stage.act();
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
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
