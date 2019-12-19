/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.space;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.graphics.d3.impl.STLMesh;
import be.codecuisine.asciination.engine.graphics.d3.impl.SphereMesh;
import be.codecuisine.asciination.engine.input.Controls;
import be.codecuisine.asciination.engine.input.InputController;
import be.codecuisine.asciination.engine.io.STLParser;
import be.codecuisine.asciination.engine.math.MathUtil;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.engine.model.GameObject;
import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author kenne
 */
public class PlayerShip extends GameObject {

    private Vector previousPosition = new Vector();
    private Vector previousRotation = new Vector();

    public double rollingSpeed = 145;
    public double horizontalSpeed = 250;
    public double verticalSpeed = 250;

    public PlayerShip() {
        super();

        transform.position.x = -54.5;
        transform.position.y = 60.0;
        transform.position.z = -.5;
        transform.rotation.x = -80;
        transform.rotation.y = 0;
        transform.rotation.z = 0;

        TriangleMesh shipMesh = STLParser.getMesh(STLParser.PLAYER_SHIP);
        AttachMesh(new STLMesh(shipMesh, .3f));
     //   AttachMesh(new SphereMesh());
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

        handleXMovement(dt);
        handleYMovement(dt);
        handleShoot(dt);

        previousPosition.x = transform.position.x;
        previousPosition.y = transform.position.y;
        previousPosition.z = transform.position.z;
        previousRotation.x = transform.rotation.x;
        previousRotation.y = transform.rotation.y;
        previousRotation.z = transform.rotation.z;

    }

    private void handleXMovement(double dt) {
        transform.rotation.z = MathUtil.lerp(transform.rotation.z, 0, rollingSpeed * dt);
        if (InputController.isPressed(Controls.LEFT)) {
            transform.rotation.z = MathUtil.lerp(transform.rotation.z, +45, rollingSpeed * dt);
            transform.position.x -= horizontalSpeed * dt;
        }
        if (InputController.isPressed(Controls.RIGHT)) {
            transform.rotation.z = MathUtil.lerp(transform.rotation.z, -45, rollingSpeed * dt);
            transform.position.x += horizontalSpeed * dt;
        }
    }

    private void handleYMovement(double dt) {
        transform.rotation.x = MathUtil.lerp(transform.rotation.x, -80, rollingSpeed * dt);
        if (InputController.isPressed(Controls.UP)) {
            transform.rotation.x = MathUtil.lerp(transform.rotation.x, -45, rollingSpeed * dt);
            transform.position.y += verticalSpeed * dt;
        }
        if (InputController.isPressed(Controls.DOWN)) {
            transform.rotation.x = MathUtil.lerp(transform.rotation.x, -140, rollingSpeed * dt);
            transform.position.y -= verticalSpeed * dt;
        }
    }

    private void handleShoot(double dt) {
        if (InputController.isPressed(Controls.SHOOT)) {
            Projectile projectile = new Projectile();
            projectile.transform.position = new Vector(transform.position);
        }
    }

}
