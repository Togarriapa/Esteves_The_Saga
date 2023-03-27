package org.academiadecodigo.powrangers;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MagicMouse implements MouseHandler {

    private Mouse mouse;
    Picture background = new Picture(10, 10, "resources/Menu.png");

    Picture tuto = new Picture(10,10,"resources/Tutorial1.png");

    Picture leader = new Picture(10,10,"resources/LeadBackground.png");

    Picture game = new Picture(10,10,"resources/Background2.png");

    Picture start = new Picture(530,330,"resources/Start.png");
    Picture startH = new Picture(530,330,"resources/StartH.png");

    Picture tutorial = new Picture(530,450,"resources/Tutorial.png");
    Picture tutorialH = new Picture(530,450,"resources/TutorialH.png");

    Picture leaderboard = new Picture(530,570,"resources/LeaderBoard.png");
    Picture leaderboardH = new Picture(530,570,"resources/LeaderBoardH.png");

    Picture exit = new Picture(1150,600, "resources/cross.png");

    private boolean isMenuOpen = true;

    public void init() {

        background.draw();
        exit.draw();
        start.draw();
        tutorial.draw();
        leaderboard.draw();


        mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        mouseEvent.getEventType();

        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 436 && mouseEvent.getY() > 330)) {
            setMenuOpen();
            background.delete();
            exit.delete();
            start.delete();
            tutorial.delete();
            leaderboard.delete();
            isMenuOpen = false;
        }

        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 556 && mouseEvent.getY() > 450)) {
            tuto.draw();
        } else {
            tuto.delete();
        }

        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 676 && mouseEvent.getY() > 570)) {
            leader.draw();
        } else {
            leader.delete();
        }

        if ((mouseEvent.getX() < 1213 && mouseEvent.getX() > 1150) &&
                (mouseEvent.getY() < 663 && mouseEvent.getY() > 600)) {
            System.exit(0);
        }



        // System.out.println("Click happened at x: " + mouseEvent.getX());
        // System.out.println("Click happened at y: " + mouseEvent.getY());

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent){
        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 436 && mouseEvent.getY() > 330)) {
            if(!isMenuOpen){
                return;
            } else {
                startH.draw();
            }
        } else {
            startH.delete();
        }

        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 556 && mouseEvent.getY() > 450)) {
            if(!isMenuOpen){
                return;
            } else {
                tutorialH.draw();
            }

        } else {
            tutorialH.delete();
        }

        if ((mouseEvent.getX() < 740 && mouseEvent.getX() > 530) &&
                (mouseEvent.getY() < 676 && mouseEvent.getY() > 570)) {
            if(!isMenuOpen){
                return;
            } else {
                leaderboardH.draw();
            }

        } else {
            leaderboardH.delete();
        }



        //System.out.println("Move happened at x: " + mouseEvent.getX());
        //System.out.println("Move happened at y:" + mouseEvent.getX());
    }

    public boolean isMenuOpen() {
        return isMenuOpen;
    }

    public void setMenuOpen() {
        isMenuOpen = false;
    }
}


