package org.academiadecodigo.powrangers;

import org.academiadecodigo.powrangers.GameLogic.Obstacles.Obstacle;
import org.academiadecodigo.powrangers.GameLogic.Obstacles.ObstacleFactory;
import org.academiadecodigo.powrangers.GameLogic.PlayerRelated.LeaderBoard;
import org.academiadecodigo.powrangers.GameLogic.PlayerRelated.Player;
import org.academiadecodigo.powrangers.GameLogic.StaticResources.NumberGenerator;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

import static org.academiadecodigo.powrangers.GraphicPage.Game.Grid.BUFFER;

public class Program implements KeyboardHandler {

    public static final String prefix = "";

    // Variables used in Player generation:
    private final int startminCol = 7;
    private final int startminRow = 46;
    private final int width = 20;
    private final int height = 60;
    private int delay = 30;

    //Menus states:
    private boolean isMenuOpen;
    private boolean isGameRunning;
    private boolean isTutorialOpen;
    private boolean isLeaderboardOpen;

    //Instances:
    private Picture opening;
    private final String openingFilepath = Program.prefix + "Background2.png";
    private final String menuFilepath = Program.prefix + "Menu.png";
    private final String tutorialFilepath = Program.prefix + "Tutorial_Fixed.png";
    private final String leaderboardFilepath = Program.prefix + "LeadBackground.png";
    public static final String gamebackgroundFilepath = Program.prefix + "Background.png";

    private String gameoverFilepath = Program.prefix + "Game Over.png";
    private static ArrayList<Obstacle> obstacles;
    private LeaderBoard leaderBoard;
    private Player player;
    private Keyboard keyboard;
    private Picture menu;
    private Picture[] menus ={new Picture(BUFFER,BUFFER, menuFilepath),
            new Picture(BUFFER,BUFFER, tutorialFilepath),
            new Picture(BUFFER,BUFFER, leaderboardFilepath),
            new Picture(10,10,gamebackgroundFilepath),
            new Picture(BUFFER,BUFFER, openingFilepath),
            new Picture(BUFFER,BUFFER, gameoverFilepath),
    };

    //Variables:

    private final int jumpSound = 5;
    private final int crouchSound = 4;
    private int crouchingState = -1;

    MagicMouse magicMouse = new MagicMouse();

    public Program() throws InterruptedException {

        leaderBoard = new LeaderBoard(10);
        obstacles = new ArrayList<Obstacle>();
        player = new Player();



        this.menu = menus[4];
        menu.draw();

        magicMouse.init();

        Thread.sleep(8000);

        play();
        Picture hearts = new Picture(1000,50,Program.prefix + "Lives3.png");
        hearts.draw();
    }

    public void openMenu() {
        isMenuOpen =false;
        isGameRunning =false;
        isTutorialOpen =false;
        isLeaderboardOpen =false;
        isMenuOpen = true;
        keyboardInit();
        this.menu = menus[0];
        menu.draw();

    }

    public void openTutorial(){
        isMenuOpen = false;
        isTutorialOpen = true;
        menu.delete();

        keyboardInit();
        this.menu = menus[1];
        menu.draw();

    }

    public void openLeaderboard(){
        isMenuOpen = false;
        isLeaderboardOpen = true;
        menu.delete();
        keyboardInit();
        this.menu = menus[2];
        menu.draw();

    }

    public void play() throws InterruptedException {
        Sounds music = new Sounds();
        music.playMusic(6);
        Picture hearts = new Picture(1000,50,Program.prefix + "Lives3.png");
        hearts.draw();

        System.out.println("Starting Game");
        isMenuOpen = false;
        isGameRunning = true;
        System.out.println("Status changed");
        menu.delete();
        keyboardInit();
        System.out.println("Building Game");
        this.menu = menus[4];
        menu.draw();

        while (player.getLives() >= 1) {

            Thread.sleep(30);
            System.out.println("loop");

            if (player.getJumpCounter() == -1) { // If not jumping Run loop --------------------------------------------

                player.movePlayerImage();

            }

            if (player.getJumpCounter() != -1) { //If already jumping keep jumping -------------------------------------

                player.jump();

            }

            player.getPicture().draw(); // Draw updated Player image

            moveAll();
            ObstacleGen();
            CollisionChecker();

        }
        music.stopMusic();
        gameOver();
    }
    public void moveAll () {
        for (int i = 0; i < obstacles.size(); i++) {
            //System.out.println("in move all");
            obstacles.get(i).move();
        }
    }
    public void ObstacleGen() {
            if (NumberGenerator.NumberGenerator(10) == 0) { // Obstacle generator-----------------------------
                int counter = 0;
                for (int i = 0; i < obstacles.size(); i++) {

                    if (obstacles.get(i).getPosition().getMinCol() > 65) { // Checks if the Obstacles are too close
                        counter++;
                    }
                }
                if (counter == 0) {
                    obstacles.add(ObstacleFactory.obstacleCreator());
                }

            }
    }
    public void CollisionChecker(){
            for (int i = 0; i < obstacles.size(); i++) {  // Collision checker -----------------------------------------
                //System.out.println(obstacles.get(i).getPositionG1());
                //System.out.println("checking");
                if (player.isColided(obstacles.get(i))) {

                    obstacles.get(i).delete();
                    obstacles.remove(i);
                    //obstacles.add(ObstacleFactory.obstacleCreator());
                    break;
                }
                if (obstacles.get(i).getPosition().getMaxCol() <= 0) { // Obstacle makes it to Col 0 -------------------

                    obstacles.get(i).delete();
                    obstacles.remove(i);
                    player.incHighScore();
                    //obstacles.add(ObstacleFactory.obstacleCreator());
                    break;
                }
            }

    }
    public void Quit() throws InterruptedException {
        if(isMenuOpen){

            System.exit(0);

        } else if (isTutorialOpen) {
            isTutorialOpen = false;
            menu.delete();
            keyboardInit();
            this.menu = menus[0];
            menu.draw();

        } else if (isLeaderboardOpen) {
            isLeaderboardOpen = false;
            menu.delete();
            keyboardInit();
            this.menu = menus[0];
            menu.draw();
        } else if (isGameRunning) {
            isGameRunning = false;
            gameOver();
        }
    }
    public void gameOver() throws InterruptedException {
        Sounds music = new Sounds();
        music.playSoundEffect(0);

        player.getPicture().delete();
        Picture die = new Picture(70, 470, Program.prefix + "run0001.png");

        int counterDie = 0;

        while (counterDie != player.getFall().length - 1) {
            die = player.getFall()[counterDie];
            counterDie++;
            die.draw();
            die.translate(0,-80);
            Thread.sleep(30);

            die.delete();

        }
        die.draw();

        this.menu = menus[5];
        menu.draw();

        Thread.sleep(2000);
        resetGame();
        isMenuOpen = true;
        openMenu();
        magicMouse.init();

    }
    public void resetGame(){

        obstacles = new ArrayList<Obstacle>();
        player = new Player();

    }
    public boolean deleteOnBorder () {
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getIsOnBorderG1()) {
                obstacles.get(i).delete();
                //System.out.println("deleted");
                return false;
            }
            return true;
        }
        return true;
    }

    public static ArrayList<Obstacle> getObstacles() {

        return obstacles;

    }

    public void keyboardInit() {

        keyboard = new Keyboard(this);


        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_SPACE);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent crouch = new KeyboardEvent();
        crouch.setKey(KeyboardEvent.KEY_S);
        crouch.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent unCrouch = new KeyboardEvent();
        unCrouch.setKey(KeyboardEvent.KEY_S);
        unCrouch.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent menu = new KeyboardEvent();
        menu.setKey(KeyboardEvent.KEY_M);
        menu.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent tutorial = new KeyboardEvent();
        tutorial.setKey(KeyboardEvent.KEY_T);
        tutorial.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leaderboard = new KeyboardEvent();
        leaderboard.setKey(KeyboardEvent.KEY_L);
        leaderboard.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent game = new KeyboardEvent();
        game.setKey(KeyboardEvent.KEY_P);
        game.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        keyboard.addEventListener(up);
        keyboard.addEventListener(crouch);
        keyboard.addEventListener(unCrouch);
        keyboard.addEventListener(menu);
        keyboard.addEventListener(tutorial);
        keyboard.addEventListener(leaderboard);
        keyboard.addEventListener(quit);
        keyboard.addEventListener(game);


    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_Q:
                    isMenuOpen = false;
                    System.out.println("Q");
                    try {
                        Quit();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case KeyboardEvent.KEY_M:
                    isMenuOpen = true;
                    System.out.println("M");
                    openMenu();
                    break;
                case KeyboardEvent.KEY_T:
                    isMenuOpen = false;
                    System.out.println("T");
                    openTutorial();

                    break;
                case KeyboardEvent.KEY_L:
                    isMenuOpen = false;
                    System.out.println("L");
                    openLeaderboard();

                    break;
                case KeyboardEvent.KEY_P:
                    isMenuOpen = false;
                    System.out.println("P");
                    try {
                        play();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    break;

                case KeyboardEvent.KEY_SPACE:
                    System.out.println("Space");
                    if (player.getJumpCounter() == -1) {
                        player.setJumpCounter();
                        Sounds sound = new Sounds();
                        sound.playSoundEffect(jumpSound);
                        player.jump();

                        break;
                    } else{
                        System.out.println("currently jumping");

                        break;
                    }
                case KeyboardEvent.KEY_S:
                    System.out.println("S");
                    if (player.getJumpCounter() == -1) {
                        if(crouchingState == -1) {
                            Sounds sound = new Sounds();
                            sound.playSoundEffect(crouchSound);
                            crouchingState = 0;
                            player.crouch();

                            break;
                        }
                    } else{
                        System.out.println("currently jumping");

                        break;
                    }

                default:
                    System.out.println("ups");
                    break;
            }
        }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            //case KeyboardEvent.KEY_SPACE:
            //player.moveDown();

            //break;

            case KeyboardEvent.KEY_S:
                System.out.println("pila");
                crouchingState = -1;
                player.unCrouch();

                break;
            }
        }

    }
