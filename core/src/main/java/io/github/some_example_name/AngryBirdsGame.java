package io.github.some_example_name;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AngryBirdsGame extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Set the starting screen (e.g., Home Screen)
        this.setScreen(new HomeScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
