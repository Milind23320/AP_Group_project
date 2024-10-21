//package io.github.some_example_name.lwjgl3;
//
//import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
//import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import io.github.some_example_name.AngryBirdsGame;
//
//
///** Launches the desktop (LWJGL3) application. */
//public class Lwjgl3Launcher {
//    public static void main(String[] args) {
//        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
//        createApplication();
//    }
//
//    private static Lwjgl3Application createApplication() {
//        return new Lwjgl3Application(new AngryBirdsGame(), getDefaultConfiguration());
//    }
//
//    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
//        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
//        configuration.setTitle("YourProjectName");
//        //// Vsync limits the frames per second to what your hardware can display, and helps eliminate
//        //// screen tearing. This setting doesn't always work on Linux, so the line after is a safeguard.
//        configuration.useVsync(true);
//        //// Limits FPS to the refresh rate of the currently active monitor, plus 1 to try to match fractional
//        //// refresh rates. The Vsync setting above should limit the actual FPS to match the monitor.
//        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
//        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
//        //// useful for testing performance, but can also be very stressful to some hardware.
//        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
//        configuration.setWindowedMode(640, 480);
//        //// You can change these files; they are in lwjgl3/src/main/resources/ .
//        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
//        return configuration;
//    }
//}


package io.github.some_example_name.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.some_example_name.AngryBirdsGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new AngryBirdsGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

        // Set the title of the game window
        configuration.setTitle("Angry Birds Game");

        // VSync helps to limit the FPS to the screen's refresh rate, reducing screen tearing.
        configuration.useVsync(true);

        // Limit FPS to the refresh rate of the currently active monitor, with a small buffer.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        // Set window size; you can adjust these dimensions to match your desired game resolution.
        configuration.setWindowedMode(1280, 720);

        // Allow resizing of the window; set to false if you want to lock the window size.
        configuration.setResizable(true);

        // Set custom window icons (different sizes for better scaling on various resolutions).
        // Make sure these files are placed in the assets folder or in the appropriate resources directory.
        configuration.setWindowIcon("assets/icons/icon128.png", "assets/icons/icon64.png", "assets/icons/icon32.png", "assets/icons/icon16.png");

        return configuration;
    }
}
