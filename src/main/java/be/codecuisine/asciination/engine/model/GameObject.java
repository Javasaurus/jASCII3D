/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.model;

import be.codecuisine.asciination.engine.GameObjectPool;
import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.graphics.d3.Mesh3D;
import be.codecuisine.asciination.engine.math.Transformation;
import be.codecuisine.asciination.engine.math.Vector;

/**
 *
 * @author kenne
 */
public abstract class GameObject {

    public float lifeTime;
    public Transformation transform = new Transformation();
    public Mesh3D mesh;

    public GameObject() {
        GameObjectPool.INSTANCE.Create(this);
        transform.position = new Vector();
        transform.rotation = new Vector();
        transform.scale = 1;
    }

    public void Destroy() {
        GameObjectPool.INSTANCE.Destroy(this);
    }

    public void AttachMesh(Mesh3D mesh) {
        this.mesh = mesh;
    }

    public abstract void render(ASCIICanvas canvas);

    public abstract void update(double dt);

}
