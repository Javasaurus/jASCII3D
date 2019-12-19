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
public class Transformation {

    public Vector position;
    public Vector rotation;
    public float scale;

    public Vector ApplyTransformation(Vector vector) {
        Vector transformedVector = new Vector();

        double tmpScale = scale;
        tmpScale = (scale / position.z);

        Matrix4 rotationMatrix = new Matrix4();
        rotationMatrix.InitRotation(rotation.x, rotation.y, rotation.z);

        transformedVector = transformedVector.Mul(tmpScale);
        transformedVector = rotationMatrix.Transform(transformedVector);
        transformedVector = transformedVector.Add(position);

        return transformedVector;
    }

    public Vector[] ApplyTransformation(List<Vector> vertices) {
        Vector[] transformedVertices = new Vector[vertices.size()];

        double tmpScale = scale;
        tmpScale = (scale / position.z);
        Vector center = MathUtil.getCenter(vertices);

        Matrix4 rotationMatrix = new Matrix4();
        rotationMatrix.InitRotation(rotation.x, rotation.y, rotation.z);

        for (int i = 0; i < vertices.size(); i++) {
            transformedVertices[i] = new Vector(vertices.get(i));
            transformedVertices[i] = new Vector(vertices.get(i)).Sub(center);
            transformedVertices[i] = transformedVertices[i].Mul(tmpScale);
            transformedVertices[i] = rotationMatrix.Transform(transformedVertices[i]);
            transformedVertices[i] = transformedVertices[i].Add(position);

        }

        return transformedVertices;
    }

 

}
