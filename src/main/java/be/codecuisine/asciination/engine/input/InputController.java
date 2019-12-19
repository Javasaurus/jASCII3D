/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;

/**
 *
 * @author kenne
 */
public class InputController implements KeyListener {

    public static EnumMap<Controls, Boolean> INPUT = new EnumMap<Controls, Boolean>(Controls.class);

    public static boolean isPressed(Controls control) {
        return INPUT.getOrDefault(control, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Controls control : Controls.values()) {
            if (control.getKeyCode() == e.getKeyCode()) {
                INPUT.put(control, true);
                return;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Controls control : Controls.values()) {
            if (control.getKeyCode() == e.getKeyCode()) {
                INPUT.put(control, false);
                return;
            }
        }
    }

}
