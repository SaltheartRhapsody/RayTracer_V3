package primitives;

public class Point extends Triplet {

    public Point(float x, float y, float z) {
        super(x, y, z);
    }
    
    @Override
    public void print() {
        System.out.print("p");
        super.print();
    }
    
    public void short_print() {
        System.out.print("p");
        super.short_print();
    }
    
    public Point vec_add(Vec v) {
        Point temp = new Point (
                                getX()+v.getX(),
                                getY()+v.getY(),
                                getZ()+v.getZ());
        return temp;
    }
    
    public Point vec_sub(Vec v) {
        Point temp = new Point (
                                getX()-v.getX(),
                                getY()-v.getY(),
                                getZ()-v.getZ());
        return temp;
    }
    
    public Vec point_sub(Point p) {
        Vec temp = new Vec(
                            getX()-p.getX(),
                            getY()-p.getY(),
                            getZ()-p.getZ());
        return temp;
    }
}
