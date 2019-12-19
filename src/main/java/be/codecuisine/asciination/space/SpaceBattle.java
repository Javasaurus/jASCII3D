/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.space;

import be.codecuisine.asciination.engine.graphics.d3.Mesh3D;
import be.codecuisine.asciination.engine.input.InputController;
import be.codecuisine.asciination.engine.model.GameObject;
import be.codecuisine.asciination.engine.GameObjectPool;
import be.codecuisine.asciination.engine.input.Controls;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.engine.view.Camera;
import be.codecuisine.asciination.playground.Demo;

/**
 *
 * @author kenne
 */
public class SpaceBattle extends Demo {

    private final GameObject spaceShip;
    private final Camera camera = new Camera();
    private final Asteroid asteroid;

    public SpaceBattle() {
        super(256, 512, 10);
        super.setTitle("SpaceBattle 2020 ~ jASCIIEngine3D Prototype ");

        Mesh3D.orthoMode = true;
        spaceShip = new PlayerShip();//new PlayerShip();
        asteroid = new Asteroid();
        asteroid.transform.position = new Vector(spaceShip.transform.position);
        asteroid.transform.position.z -= 15;
        myASCIICanvas.addKeyListener(new InputController());
    }

    public static void main(String[] args) {
        StartApp(SpaceBattle.class);
    }

    @Override
    public void update(double delta) {
        GameObjectPool.INSTANCE.Update(delta);
        if (camera.main != null && spaceShip != null) {
 //           Camera.main.transform.position = new Vector(spaceShip.transform.position);
 //           Camera.main.transform.position.z -= 1;
        }
        if(InputController.isPressed(Controls.SHOOT)){
            System.out.println(spaceShip.transform.position);
        }
    }

    @Override
    public void render() {
        GameObjectPool.INSTANCE.Render(myASCIICanvas);
        super.render();

    }
}
