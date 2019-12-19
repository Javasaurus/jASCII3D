/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.d3;

import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.math.MathUtil;
import be.codecuisine.asciination.engine.math.Triangle;
import be.codecuisine.asciination.engine.math.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kenne
 */
public abstract class Mesh3D {

    public List<Vector> vertices = new ArrayList<Vector>();
    public Vector[] transformedVertices;
    public List<Triangle> tris = new ArrayList<Triangle>();

    public Mesh3D() {

    }

    public void addVertices(Vector... nVertices) {
        for (int i = 0; i < nVertices.length; i++) {
            vertices.add(nVertices[i]);
        }
    }

    public void addTriangles(int... nIndexes) {
        if (nIndexes.length % 3 != 0) {
            return;
        }
        for (int i = 0; i < nIndexes.length; i = i + 3) {
            Triangle t = new Triangle(nIndexes[i], nIndexes[i + 1], nIndexes[i + 2]);
            tris.add(t);
        }
    }

    public static boolean orthoMode = false;

    public void Render(ASCIICanvas canvas) {
        if (transformedVertices == null) {
            return;
        }
        for (Triangle triangle : tris) {

            triangle.p1 = transformedVertices[triangle.p1Index];
            triangle.p2 = transformedVertices[triangle.p2Index];
            triangle.p3 = transformedVertices[triangle.p3Index];
            SetPerspective(triangle.p1, orthoMode ? 1 : triangle.p1.z / 50);
            SetPerspective(triangle.p2, orthoMode ? 1 : triangle.p2.z / 50);
            SetPerspective(triangle.p3, orthoMode ? 1 : triangle.p3.z / 50);

            canvas.fillTriangle(triangle.p1, triangle.p2, triangle.p3, triangle.getColor());
        }
    }

    private void SetPerspective(Vector point, double w) {
        point.x /= w;
        point.y /= w;
        point.z /= w;
    }

    public void centerMesh() {
        Vector center = MathUtil.getCenter(vertices);
        for (Vector vertex : vertices) {
            vertex = vertex.Sub(center);
        }
    }

}
