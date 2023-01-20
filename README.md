
# JavaSnake
This is a light version of Snake coded entirely within Java, with a focus on design patterns.
These are listed as ***@design-pattern*** parameters where applicable, and also listed down below with further
clarification.

The colours are inspired by Google's Snake Game.

<img src="https://img.shields.io/badge/Java-F80000?&style=for-the-badge&logo=Oracle&logoColor=white" alt="Java">

***

# Cooperation
We began work on Monday the 16th.

Firstly, we needed to decide what application we were going to make.
We had a few ideas: Checkers, Chess, Frogger and Snake. Chess and Checker were a bit boring, so we decided to choose 
between Frogger and Snake. Snake seemed easier to make, so our final choice was Snake.

Secondly we did some pair programming with Code With Me, an integrated feature in IntelliJ, and started working on our 
snake application. We decided it would be better for us if we made our application, before we decided on our design 
patterns. So we started developing our snake application for the console.

After a while, we found out that it would be easier for us if we were to make it in JFrame due to requiring constant 
user inputs. We started working on that. The pair programming was a bit rough at the start, because Jesper already had 
quite some Java knowledge compared to Jochem, but after the first day, we had a frame, and we knew how to draw on the 
frame. 

The day after we would work on the game from home. Jochem was going to make the food items and make them spawn on the 
frame, and Jesper would make the KeyboardAdapter and make the snake move with them. If we had questions we would ask 
them and the other person would help them the best they could. so on the second day, we had a working snake game!

On the third day, we were going to decide the design patterns. We accidentally made a facade with how we call upon 
JFrame and the KeyboardAdapter is an adapter of itself. We went to split up our gigantic Game class into Game, Snake, 
and Food. Luckily we had already planned for that, so we finished quickly. Jochem refactored the Food, Jesper finished 
up the KeyboardAdapter, and we did the Snake class together, since it was more difficult due to the movement. Finally,
we added the Singleton design pattern to the Main class to make sure there can only be one game at a time.

# Design patterns
***
*These can be quickly located by searching for the @design-pattern tag.*

## Adapter
Located in `game\snake\KeyboardAdapter.java`.

This adapter is designed to translate low-level key presses to high-level events. We can then use these events to
execute code if certain events occur. In our case, we use the arrow keys to control the snake.

## State
Located in `game\snake\Game.java`

The game itself has several states; loading - playing - win/lose.\
It will first load very quickly before starting the actual game. Afterwards, the game will remain in a playing state until
the player either collides or meets the score threshold, at which point they will either win or lose.

More specifically; the game class is instantiated as NOT running, then as its constructor is fired, it will transfer into
the playing state. When meeting the win/lose conditions, it will stop again and display the final results.

## Facade
Located in `game\snake\Frame.java`

We use JFrame in our project. This provides us with a small interactive window which we can use as canvas for our game.
When instantiating the Frame itself, it calls upon the Game constructor and assigns it to the screen of the frame.
However, it does not expose the underlying code of the game directly, which makes it a facade.

## Singleton
Located in `game\snake\Main.java`

Because we only need one game at a time, the application entrypoint has a method designed to avoid multiple instances.
It calls upon this method when the Main class is instantiated, verifies whether a Frame already exists (as it is the next
step in the sequence), and either returns the existing Frame or creates a new one.