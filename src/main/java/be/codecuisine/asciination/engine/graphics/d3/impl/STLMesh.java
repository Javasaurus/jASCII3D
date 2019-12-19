/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.d3.impl;

import be.codecuisine.asciination.engine.graphics.d3.Mesh3D;
import be.codecuisine.asciination.engine.math.Vector;
import javafx.collections.ObservableFloatArray;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author kenne
 */
public class STLMesh extends Mesh3D {

    public STLMesh(TriangleMesh mesh, double scale) {

        ObservableFloatArray points = mesh.getPoints();
        ObservableFaceArray faces = mesh.getFaces();

        for (int i = 0; i < points.size(); i = i + 3) {
            addVertices(new Vector(points.get(i) * scale, points.get(i + 1) * scale, points.get(i + 2) * scale));
        }
        int[] triangles = new int[faces.size()];
        addTriangles(faces.toArray(triangles));
    }

}
