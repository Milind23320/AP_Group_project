package io.github.some_example_name;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
public class AngryBirdsGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    private Screen currentGameScreen;
    private Music music;

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
        music= Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        this.setScreen(new HomeScreen(this));
    }
    public void render(){
        super.render();
    }

    public void stopMusic() {
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }

    public void pauseMusic() {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    public void resumeMusic() {
        if (music != null && !music.isPlaying()) {
            music.play();
        }
    }

    public boolean isMusicPlaying() {
        return music != null && music.isPlaying();
    }

    public void disposeMusic() {
        if (music != null) {
            music.dispose();
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        disposeMusic();
    }

    public void pauseCurrentScreen(Screen currentScreen) {
        this.setScreen(new PauseScreen(this, currentScreen));
    }

}
