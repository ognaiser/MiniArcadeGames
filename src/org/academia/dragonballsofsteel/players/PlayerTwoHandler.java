package org.academia.dragonballsofsteel.players;

import org.academia.dragonballsofsteel.SkinTypeGoku;
import org.academia.dragonballsofsteel.SkinTypeVegeta;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Handles Player 2 keyboard keys
 */

public class PlayerTwoHandler implements KeyboardHandler, PlayerHandler{

    private Picture image;
    private int speed;
    private SkinTypeGoku skin;
    private boolean isAirBorn = false;
    private Player player;

    public PlayerTwoHandler(int speed, Player player) {

        //Inicialize Properties
        image = new Picture(160, 290, SkinTypeGoku.GokuStartRight.getPath());
        image.draw();
        this.speed = speed;
        this.skin = SkinTypeGoku.GokuStartRight;
        this.player = player;

        //Inicialize Key Events

        KeyboardEvent w = new KeyboardEvent();
        w.setKey(KeyboardEvent.KEY_UP);
        w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent a = new KeyboardEvent();
        a.setKey(KeyboardEvent.KEY_LEFT);
        a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent s = new KeyboardEvent();
        s.setKey(KeyboardEvent.KEY_DOWN);
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent d = new KeyboardEvent();
        d.setKey(KeyboardEvent.KEY_RIGHT);
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent kPressed = new KeyboardEvent();
        kPressed.setKey(KeyboardEvent.KEY_K);
        kPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent kReleased = new KeyboardEvent();
        kReleased.setKey(KeyboardEvent.KEY_K);
        kReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent lPressed = new KeyboardEvent();
        lPressed.setKey(KeyboardEvent.KEY_L);
        lPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent lReleased = new KeyboardEvent();
        lReleased.setKey(KeyboardEvent.KEY_L);
        lReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        //Inicialize Keyboard
        Keyboard k = new Keyboard(this);

        //Movement Keys event Add
        k.addEventListener(w);
        k.addEventListener(a);
        k.addEventListener(s);
        k.addEventListener(d);

        //punch Key Add
        k.addEventListener(kPressed);
        k.addEventListener(kReleased);

        //Def Key Add
        k.addEventListener(lPressed);
        k.addEventListener(lReleased);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                if (checkBounderies(keyboardEvent)) {
                    image.load(SkinTypeGoku.GokuFlyRight.getPath());
                    image.translate(0, -speed);
                    isAirBorn = true;
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (checkBounderies(keyboardEvent)) {
                    image.translate(-speed, 0);
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (checkBounderies(keyboardEvent)) {
                    image.translate(0, speed);
                    if(!checkBounderies(keyboardEvent)){
                        isAirBorn =false;
                        image.load(SkinTypeGoku.GokuGroundRight.getPath());
                    }
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (checkBounderies(keyboardEvent)) {
                    image.translate(speed, 0);
                }
                break;
            case KeyboardEvent.KEY_K:
                //TODO: Code for punch action
                image.load(SkinTypeGoku.GokuPunchRight.getPath());
                break;
            case KeyboardEvent.KEY_L:
                //TODO: Code for Deff
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_K:
                //TODO:COde for when punch ends
                if(isAirBorn){
                    image.load(SkinTypeGoku.GokuFlyRight.getPath());
                }else {
                    image.load(SkinTypeGoku.GokuGroundRight.getPath());
                }
                break;
            case KeyboardEvent.KEY_L:
                //TODO: Code for when def ends
                break;
        }
    }


    private boolean checkBounderies(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                if (image.getY() - speed < 0){
                    return false;
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (image.getX() - speed < 0){
                    return false;
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (image.getY() + speed > 300){
                    return false;
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (image.getX() + speed > 760){
                    return false;
                }
                break;
        }

        return true;
    }

    public int getPosx() {
        return image.getX();
    }


    public int getPosy() {
        return image.getMaxY();
    }
}
