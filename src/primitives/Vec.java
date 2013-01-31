package primitives;

public class Vec extends Triplet {

    public Vec(float x, float y, float z) {
        super(x, y, z);
    }
    
    @Override
    public void print() {
        System.out.print("v");
        super.print();
    }
    
    @Override
    public void short_print() {
        System.out.print("v");
        super.short_print();
    }
    
    public Vec vec_add(Vec v) {
        Vec temp = new Vec(
                            getX() + v.getX(),
                            getY() + v.getY(),
                            getZ() + v.getZ());
        return temp;
    }
    
    public Vec vec_sub(Vec v) {
        Vec temp = new Vec(
                            getX() - v.getX(),
                            getY() - v.getY(),
                            getZ() - v.getZ());
        return temp;
    }
    
    public Point point_add(Point p) {
        Point temp = new Point(
                                getX() + p.getX(),
                                getY() + p.getY(),
                                getZ() + p.getZ());
        return temp;
    }
    
    public Vec const_mult(float k) {
        Vec temp = new Vec(
                            getX() * k,
                            getY() * k, 
                            getZ() * k
                            );
        return temp;
    }
    
    public Vec const_div(float k) {
        Vec temp = new Vec(
                            getX()/k,
                            getY()/k,
                            getZ()/k);
        return temp;
    }
    
    public Vec reverse() {
        Vec temp = new Vec(
                            -getX(),
                            -getY(),
                            -getZ());
        return temp;             
    }
    
    public float length() {
        return (float) Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
    }
    
    public Vec normalize() {
        Vec temp = new Vec(
                            getX()/length(),
                            getY()/length(),
                            getZ()/length());
        return temp;
    }
    
    public Vec cross(Vec v) {
        Vec temp = new Vec(
                            getY()*v.getZ() - getZ()*v.getY(),
                            getZ()*v.getX() - getX()*v.getZ(),
                            getX()*v.getY() - getY()*v.getX());
        return temp;
    }
    
    public float dot(Vec v) {
        return (getX()*v.getX() + getY()*v.getY() + getZ()*v.getZ());
    }
    
}
