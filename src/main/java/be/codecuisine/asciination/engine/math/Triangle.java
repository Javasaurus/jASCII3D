/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.math;

import be.codecuisine.asciination.engine.graphics.ascii.ASCIIColor;
import be.codecuisine.asciination.playground.Demo;

/**
 *
 * @author kenne
 */
public class Triangle {

    public Vector p1;
    public Vector p2;
    public Vector p3;
    public int p1Index;
    public int p2Index;
    public int p3Index;

    public Triangle(int p1Index, int p2Index, int p3Index) {
        this.p1Index = p1Index;
        this.p2Index = p2Index;
        this.p3Index = p3Index;
    }

    public Vector getNormal() {
        Vector edge1 = p2.Sub(p1);
        Vector edge2 = p3.Sub(p1);
        return edge1.Cross(edge2).Normalized();
    }

    public int getColor() {
        //determine the color of the triangle...

        double angleCos = Math.abs(getNormal().z);
        return ASCIIColor.getPixel(angleCos);
    }

}
