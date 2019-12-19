package be.codecuisine.asciination.engine.math;

public class Vector {

    public double x;
    public double y;
    public double z;

    public Vector() {

    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector get) {
        this.x = get.x;
        this.y = get.y;
        this.z = get.z;
    }

    public double[] toArray() {
        return new double[]{x, y, z, 1};
    }

    public double Length() {
        return (double) Math.sqrt(x * x + y * y + z * z);
    }

    public double Max() {
        return Math.max(x, Math.max(y, z));
    }

    public double Dot(Vector r) {
        return x * r.GetX() + y * r.GetY() + z * r.GetZ();
    }

    public Vector Cross(Vector r) {
        double x_ = y * r.GetZ() - z * r.GetY();
        double y_ = z * r.GetX() - x * r.GetZ();
        double z_ = x * r.GetY() - y * r.GetX();

        return new Vector(x_, y_, z_);
    }

    public Vector Normalized() {
        double length = Length();

        return new Vector(x / length, y / length, z / length);
    }

    public Vector Rotate(Vector axis, double angle) {
        double sinAngle = (double) Math.sin(-angle);
        double cosAngle = (double) Math.cos(-angle);

        return this.Cross(axis.Mul(sinAngle)).Add( //Rotation on local X
                (this.Mul(cosAngle)).Add( //Rotation on local Z
                        axis.Mul(this.Dot(axis.Mul(1 - cosAngle))))); //Rotation on local Y
    }

    public Vector Lerp(Vector dest, double lerpFactor) {
        return dest.Sub(this).Mul(lerpFactor).Add(this);
    }

    public Vector Add(Vector r) {
        return new Vector(x + r.GetX(), y + r.GetY(), z + r.GetZ());
    }

    public Vector Add(double r) {
        return new Vector(x + r, y + r, z + r);
    }

    public Vector Sub(Vector r) {
        return new Vector(x - r.GetX(), y - r.GetY(), z - r.GetZ());
    }

    public Vector Sub(double r) {
        return new Vector(x - r, y - r, z - r);
    }

    public Vector Mul(Vector r) {
        return new Vector(x * r.GetX(), y * r.GetY(), z * r.GetZ());
    }

    public Vector Mul(double r) {
        return new Vector(x * r, y * r, z * r);
    }

    public Vector Div(Vector r) {
        return new Vector(x / r.GetX(), y / r.GetY(), z / r.GetZ());
    }

    public Vector Div(double r) {
        return new Vector(x / r, y / r, z / r);
    }

    public Vector Abs() {
        return new Vector(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public String toString() {
        return "(" + x + " " + y + " " + z + ")";
    }

    public Vector Set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector Set(Vector r) {
        Set(r.GetX(), r.GetY(), r.GetZ());
        return this;
    }

    public double GetX() {
        return x;
    }

    public void SetX(double x) {
        this.x = x;
    }

    public double GetY() {
        return y;
    }

    public void SetY(double y) {
        this.y = y;
    }

    public double GetZ() {
        return z;
    }

    public void SetZ(double z) {
        this.z = z;
    }

    public boolean equals(Vector r) {
        return x == r.GetX() && y == r.GetY() && z == r.GetZ();
    }
}
