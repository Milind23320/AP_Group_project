package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseScreen implements Screen {
    private AngryBirdsGame game;
    private Stage stage;

    public PauseScreen(AngryBirdsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        createUI();
    }

    private void createUI() {
        TextButton resumeButton = new TextButton("Resume", new TextButton.TextButtonStyle());
        resumeButton.setPosition(200, 300);
        stage.addActor(resumeButton);

        TextButton quitButton = new TextButton("Quit", new TextButton.TextButtonStyle());
        quitButton.setPosition(200, 200);
        stage.addActor(quitButton);

        // TODO: Implement event handling for resume and quit
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
    }
}

