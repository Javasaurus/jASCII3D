/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.math;

import java.util.List;

/**
 *
 * @author kenne
 */
public class MathUtil {

    /**
     *
     * @param a
     * @param b
     * @param f goes from 0 to 1
     * @return
     */
    public static double lerp(double a, double b, double f) {
        return a + f * (b - a);
    }

    public static Vector getCenter(List<Vector> vertices) {
        Vector center = new Vector(0, 0, 0);
        for (int i = 0; i < vertices.size(); i++) {
            center.x += vertices.get(i).x;
            center.y += vertices.get(i).y;
            center.z += vertices.get(i).z;
        }
        center.x /= vertices.size();
        center.y /= vertices.size();
        center.z /= vertices.size();
        return center;
    }

}
