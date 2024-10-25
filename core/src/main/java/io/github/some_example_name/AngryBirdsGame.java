package io.github.some_example_name;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AngryBirdsGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    private Screen currentGameScreen;

    public void setCurrentGameScreen(Screen screen) {
        if (this.currentGameScreen != null) {
            this.currentGameScreen.hide(); // Pause the current screen if switching
        }
        this.currentGameScreen = screen;
        this.setScreen(screen);
        screen.show();
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Set the starting screen (e.g., Home Screen)
        font =new BitmapFont();
        this.setScreen(new HomeScreen(this));
    }
    public void render(){
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void pauseCurrentScreen(Screen currentScreen) {
        this.setScreen(new PauseScreen(this, currentScreen));
    }

}
