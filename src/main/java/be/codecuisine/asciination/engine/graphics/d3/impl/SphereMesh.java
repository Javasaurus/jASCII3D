/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.graphics.d3.impl;

import be.codecuisine.asciination.engine.graphics.d3.Mesh3D;
import be.codecuisine.asciination.engine.math.Vector;

/**
 *
 * @author kenne
 */
public class SphereMesh extends Mesh3D {

    public SphereMesh() {
        double t = (1.0 + Math.sqrt(5.0)) / 2.0;

        addVertices(
                new Vector(-1, t, 0),
                new Vector(1, t, 0),
                new Vector(-1, -t, 0),
                new Vector(1, -t, 0),
                new Vector(0, -1, t),
                new Vector(0, 1, t),
                new Vector(0, -1, -t),
                new Vector(0, 1, -t),
                new Vector(t, 0, -1),
                new Vector(t, 0, 1),
                new Vector(-t, 0, -1),
                new Vector(-t, 0, 1)
        );

        addTriangles(
                // 5 faces around point 0
                0, 11, 5,
                0, 5, 1,
                0, 1, 7,
                0, 7, 10,
                0, 10, 11,
                // 5 adjacent faces
                1, 5, 9,
                5, 11, 4,
                11, 10, 2,
                10, 7, 6,
                7, 1, 8,
                // 5 faces around point 3
                3, 9, 4,
                3, 4, 2,
                3, 2, 6,
                3, 6, 8,
                3, 8, 9,
                // 5 adjacent faces
                4, 9, 5,
                2, 4, 11,
                6, 2, 10,
                8, 6, 7,
                9, 8, 1
        );
    }

}
