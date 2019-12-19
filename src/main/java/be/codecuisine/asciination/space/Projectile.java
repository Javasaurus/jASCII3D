/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.space;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.graphics.d3.impl.SphereMesh;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.engine.model.GameObject;
import be.codecuisine.asciination.engine.view.Camera;

/**
 *
 * @author kenne
 */
public class Projectile extends GameObject {

    public Projectile() {
        transform.scale = 5;
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
        transform.position.z -= 150f * dt;
        Vector dst = transform.position.Sub(Camera.main.transform.position);
        double dot = Camera.main.getForward().Dot(dst);
        if (dot > 0) {
            transform.position.z = 50;
        }
    }

}
