/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.codecuisine.asciination.engine.math;

public class Matrix4 {

    double[][] m;

    public Matrix4() {
        m = new double[4][4];
    }

    public Matrix4 InitIdentity() {
        m[0][0] = 1;
        m[0][1] = 0;
        m[0][2] = 0;
        m[0][3] = 0;
        m[1][0] = 0;
        m[1][1] = 1;
        m[1][2] = 0;
        m[1][3] = 0;
        m[2][0] = 0;
        m[2][1] = 0;
        m[2][2] = 1;
        m[2][3] = 0;
        m[3][0] = 0;
        m[3][1] = 0;
        m[3][2] = 0;
        m[3][3] = 1;

        return this;
    }

    public Vector Transform(Vector r) {
        return new Vector(m[0][0] * r.GetX() + m[0][1] * r.GetY() + m[0][2] * r.GetZ() + m[0][3],
                m[1][0] * r.GetX() + m[1][1] * r.GetY() + m[1][2] * r.GetZ() + m[1][3],
                m[2][0] * r.GetX() + m[2][1] * r.GetY() + m[2][2] * r.GetZ() + m[2][3]);
    }

    public Matrix4 Mul(Matrix4 r) {
        Matrix4 res = new Matrix4();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res.Set(i, j, m[i][0] * r.Get(0, j)
                        + m[i][1] * r.Get(1, j)
                        + m[i][2] * r.Get(2, j)
                        + m[i][3] * r.Get(3, j));
            }
        }

        return res;
    }

    public double[][] GetM() {
        return m.clone();
    }

    public double Get(int x, int y) {
        return m[x][y];
    }

    public void SetM(double[][] m) {
        this.m = m;
    }

    public void Set(int x, int y, double value) {
        m[x][y] = value;
    }

    public Matrix4 InitTranslation(double x, double y, double z) {
        Matrix4 t = InitIdentity();
        t.m[0][3] = x;
        t.m[1][3] = y;
        t.m[2][3] = z;
        return t;
    }

    public Matrix4 InitRotation(double x, double y, double z) {
        Matrix4 rx = new Matrix4();
        Matrix4 ry = new Matrix4();
        Matrix4 rz = new Matrix4();

        x = (double) Math.toRadians(x);
        y = (double) Math.toRadians(y);
        z = (double) Math.toRadians(z);

        rz.m[0][0] = (double) Math.cos(z);
        rz.m[0][1] = -(double) Math.sin(z);
        rz.m[0][2] = 0;
        rz.m[0][3] = 0;
        rz.m[1][0] = (double) Math.sin(z);
        rz.m[1][1] = (double) Math.cos(z);
        rz.m[1][2] = 0;
        rz.m[1][3] = 0;
        rz.m[2][0] = 0;
        rz.m[2][1] = 0;
        rz.m[2][2] = 1;
        rz.m[2][3] = 0;
        rz.m[3][0] = 0;
        rz.m[3][1] = 0;
        rz.m[3][2] = 0;
        rz.m[3][3] = 1;

        rx.m[0][0] = 1;
        rx.m[0][1] = 0;
        rx.m[0][2] = 0;
        rx.m[0][3] = 0;
        rx.m[1][0] = 0;
        rx.m[1][1] = (double) Math.cos(x);
        rx.m[1][2] = -(double) Math.sin(x);
        rx.m[1][3] = 0;
        rx.m[2][0] = 0;
        rx.m[2][1] = (double) Math.sin(x);
        rx.m[2][2] = (double) Math.cos(x);
        rx.m[2][3] = 0;
        rx.m[3][0] = 0;
        rx.m[3][1] = 0;
        rx.m[3][2] = 0;
        rx.m[3][3] = 1;

        ry.m[0][0] = (double) Math.cos(y);
        ry.m[0][1] = 0;
        ry.m[0][2] = -(double) Math.sin(y);
        ry.m[0][3] = 0;
        ry.m[1][0] = 0;
        ry.m[1][1] = 1;
        ry.m[1][2] = 0;
        ry.m[1][3] = 0;
        ry.m[2][0] = (double) Math.sin(y);
        ry.m[2][1] = 0;
        ry.m[2][2] = (double) Math.cos(y);
        ry.m[2][3] = 0;
        ry.m[3][0] = 0;
        ry.m[3][1] = 0;
        ry.m[3][2] = 0;
        ry.m[3][3] = 1;

        m = rz.Mul(ry.Mul(rx)).GetM();

        return this;
    }

    public Matrix4 InitScale(double x, double y, double z) {
        Matrix4 m = InitIdentity();
        m.Set(0, 0, x);
        m.Set(1, 1, y);
        m.Set(2, 2, z);
        return m;
    }
}
