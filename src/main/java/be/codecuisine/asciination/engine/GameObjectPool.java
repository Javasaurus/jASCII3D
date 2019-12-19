/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.math.Vector;
import be.codecuisine.asciination.engine.model.GameObject;
import be.codecuisine.asciination.engine.view.Camera;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author kenne
 */
public class GameObjectPool {

    public ArrayBlockingQueue<GameObject> existingGameObjects = new ArrayBlockingQueue<GameObject>(32000);

    public static final GameObjectPool INSTANCE = new GameObjectPool();

    private GameObjectPool() {

    }

    public void Create(GameObject go) {
        existingGameObjects.add(go);
    }

    public void Destroy(GameObject go) {
        existingGameObjects.remove(go);
        go = null;
    }

    public void Update(double dt) {
        for (GameObject obj : existingGameObjects) {
            obj.lifeTime += dt;
            obj.update(dt);
        }
    }

    public void Render(ASCIICanvas canvas) {
        if (Camera.main != null) {
            for (GameObject obj : existingGameObjects) {
                if (obj == null) {
                    continue;
                }
                Vector dst = obj.transform.position.Sub(Camera.main.transform.position);
                double dot = Camera.main.getForward().Dot(dst);
                if (dot < 0) {
                    if (obj.mesh != null) {
                        obj.mesh.transformedVertices = obj.transform.ApplyTransformation(obj.mesh.vertices);
                    }
                    obj.render(canvas);
                }
            }

        }
    }

}
