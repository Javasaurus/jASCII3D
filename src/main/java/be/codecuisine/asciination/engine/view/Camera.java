/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.view;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.engine.model.GameObject;

/**
 *
 * @author kenne
 */
public class Camera extends GameObject {

    public static Camera main;

    public Camera() {
        super();
        if (main != null) {
            main.Destroy();
        }
        main = this;
    }

    public Vector getForward() {
        return new Vector(0, 0, 1);
    }

    @Override
    public void render(ASCIICanvas canvas) {
        //Camera's are not rendered, but all objects in view should be
    }

    @Override
    public void update(double dt) {
        //if the camera rotates, so will the entire world
    }
    /*
    What do we need a camera to be... The camera needs to move up and down with the player ... but only rotate around the Z-axis...
    This means EVERY object needs to rotate around this object...That's probably easiest with a matrix transformation ... 
    
    SO IN SUMMARY :
    
    If the camera moves... the entire world moves with it (except the spaceship maybe)
    if the camera rotates...the entire world should rotate around the camera ---> move camera to origin, rotate everything, move them back
    WITH MATRICES this can be done in a single operation but for jam sake, let's not<<
     */

}
