# Java Tank Battle Game

## Description
This is a Java implementation of the classic Tank Battle game, built as a learning project to practice Object-Oriented Programming (OOP) principles and multithreading. In this game, you control a player tank, navigate through a battlefield, and destroy enemy tanks. The project includes two difficulty levels, simple recording/“resume” functionality, and basic collision/explosion effects.

---

## Features
- **Two Gameplay Modes**
    1. **New Game** – Start a fresh battle.
    2. **Resume** – Load a previous “recorded” session (if available).

---

- **Difficulty Levels**
    - **Easy**
        - Fewer enemy tanks
        - Slower enemy movement
        - Player tank has more lives and higher speed
    - **Hard**
        - More enemy tanks
        - Faster enemy movement
        - Player tank has fewer lives and lower speed

- **Player Controls**
    - **Arrow Keys** (`↑`, `↓`, `←`, `→`) to move the player tank
    - **Space Bar** to fire a shell

- **Multithreading**
    - The map/game loop runs on its own thread, allowing smooth animations and responsive input handling.

- **Simple Recording/Resume**
    - When you exit a game, the current state is written to `Data/data.txt`.
    - On launch, you can choose to resume from the last saved state.

---

## Prerequisites
- **Java Development Kit (JDK) 8 or higher**
- [Optional] An IDE such as IntelliJ IDEA or Eclipse, configured for Java development.

---

## Run Game
```bash
git clone git@github.com:Guotai812/TankBattle.git
cd tankbattle
javac -d out src/**/*.java
cp -r src/image out
java Launcher
