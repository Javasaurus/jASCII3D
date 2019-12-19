/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.d3.impl;

import be.codecuisine.asciination.engine.graphics.d3.Mesh3D;
import be.codecuisine.asciination.engine.graphics.ascii.ui.ASCIICanvas;
import be.codecuisine.asciination.engine.math.Matrix4;
import be.codecuisine.asciination.engine.math.Triangle;
import be.codecuisine.asciination.engine.math.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kenne
 */
public class CubeMesh extends Mesh3D {

    public CubeMesh() {

        addVertices(
                new Vector(0, 0, 0),
                new Vector(1, 0, 0),
                new Vector(1, 1, 0),
                new Vector(0, 1, 0),
                new Vector(0, 1, 1),
                new Vector(1, 1, 1),
                new Vector(1, 0, 1),
                new Vector(0, 0, 1)
        );

        addTriangles(
                0, 2, 1, //face front
                0, 3, 2);
        addTriangles(
                2, 3, 4, //face top
                2, 4, 5);
        addTriangles(
                1, 2, 5, //face right
                1, 5, 6);
        addTriangles(
                0, 7, 4, //face left
                0, 4, 3);
        addTriangles(
                5, 4, 7, //face back
                5, 7, 6);
        addTriangles(
                0, 6, 7, //face bottom
                0, 1, 6);

    }

   
}
