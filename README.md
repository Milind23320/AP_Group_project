Angry Birds Game - Static GUI
This project is a static GUI implementation for an Angry Birds-inspired game. It features several game screens and UI components, laying the foundation for gameplay mechanics that will be added in future development. This README will provide an overview of the classes, their purposes, and how the project is organized.

Project Overview
The static GUI serves as the visual structure of the game, featuring different screens to navigate through game levels, settings, pause states, and more. While the core game logic isn't implemented yet, the GUI sets up the environment where the gameplay will occur.

Class Descriptions
1. AboutUsScreen
Displays information about the game developers or team. It is a simple screen dedicated to providing credits and background information.
2. AngryBirdsGame
The main class of the project, managing the flow of the application.
Initializes all screens and serves as the entry point for navigation between screens.
Responsible for starting the game and switching between different game states (e.g., home screen, game screens, pause menu).
3. GameScreen1
Represents the first level of the game.
Includes the setup of birds, obstacles, slingshots, and pigs.
Handles the static arrangement of objects to simulate gameplay.
This screen provides a placeholder layout for gameplay and user interaction elements.
4. GameScreen1_levelend
Displays the screen that appears when the player completes the first level.
Provides options for replaying or proceeding (in future levels).
5. GameScreen2
Represents the second level of the game.
Similar to GameScreen1, it sets up different elements with more complex arrangements to mimic increased difficulty.
6. GameScreen2_levelend
Displays the screen shown when the player completes the second level.
Provides feedback on the player's performance.
7. GameScreen3
Represents the third and final level of the game.
More intricate setup compared to the previous levels, showcasing a challenging layout of obstacles, pigs, and birds.
Sets up a more dynamic background and interface.
8. GameScreen3_levelend
The final level-end screen that appears once the player completes GameScreen3.
Unlike other level-end screens, it does not provide a "Next Level" option as GameScreen3 is the last level.
9. HomeScreen
The initial screen that the player sees upon launching the game.
Provides options to start the game, load a saved game, access settings, or view information about the developers (via the AboutUsScreen).
Central hub for navigating to other screens.
10. LoadGameScreen
Provides an interface to load a previously saved game.
This screen offers the player the opportunity to resume where they left off.
11. Obstacle
Placeholder class for different obstacles in the game, such as wooden blocks.
Will be expanded in future development to include collision detection and interactions.
12. PauseScreen
Screen that appears when the game is paused.
Provides options to resume, restart, or exit to the home screen.
Helps manage game state and user inputs during a pause.
13. SettingsScreen
Allows players to adjust game settings such as volume, controls, and other preferences.
Includes options to save changes and return to the previous screen.
Project Structure
The project is structured to separate visual elements (GUI) from the core game logic, which will be added in future updates. The current design prioritizes a user-friendly interface and clear navigation between different parts of the game.

Package: io.github.some_example_name
Game Screens: GameScreen1, GameScreen2, GameScreen3, HomeScreen, etc.
End Level Screens: GameScreen1_levelend, GameScreen2_levelend, GameScreen3_levelend.
General Screens: AboutUsScreen, PauseScreen, SettingsScreen.
Main Game Class: AngryBirdsGame.
Utility Classes: Obstacle.
Future Development
Game Logic Implementation: Adding the core mechanics of gameplay (slingshot physics, bird launches, pig collisions, etc.).
Gameplay Classes: Expanding placeholder classes for objects like RedBird, BlueBird, Pig, Slingshot, and Obstacle.
Scoring and Feedback: Implementing scoring systems, feedback messages, and progress tracking.
Level Design: Creating more detailed and interactive levels, expanding beyond the static structures currently set up.
Getting Started
Prerequisites
Java Development Kit (JDK) 8 or later
LibGDX Framework
Development environment (e.g., IntelliJ IDEA, Eclipse)
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/your_repository_url.git
(Not needed, since the repository is right here)
Open the project in your preferred development environment.
Import the project as a Gradle project (LibGDX setup).
Run the AngryBirdsGame class to start the application.
Controls (For GUI Navigation)
Use the mouse to interact with UI elements.
Buttons on various screens will allow you to navigate between game states.
Credits
This game was developed as part of a project to build a static GUI for an Angry Birds-inspired game. Additional game features will be added in future updates.
By-
Milind Kumar, 2023320
Deepak Meena, 2023188
P.S.
The sound and music was taken from libgdx simple game.
