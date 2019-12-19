/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.input;

import java.awt.event.KeyEvent;

/**
 *
 * @author kenne
 */
public enum Controls {
    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT),
    SHOOT(KeyEvent.VK_SPACE);

    private int keyCode;

    private Controls(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return this.keyCode;
    }
}
