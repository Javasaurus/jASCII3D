/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.space;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.graphics.d3.impl.SphereMesh;
import be.codecuisine.asciination.engine.model.GameObject;
import be.codecuisine.asciination.engine.view.Camera;

/**
 *
 * @author kenne
 */
public class Asteroid extends GameObject {

    public Asteroid() {
        super();
        transform.scale = 8;
        AttachMesh(new SphereMesh());
    }

    @Override
    public void render(ASCIICanvas canvas) {
        if (mesh == null) {
            return;
        }
        mesh.Render(canvas);
    }

    @Override
    public void update(double dt) {
        transform.position.z += 10f * dt;
        if (transform.position.z < Camera.main.transform.position.z) {
            transform.position.z = 50;
        }
    }

}
