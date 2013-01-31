package primitives;

public class Sphere {
    private Point p;
    private float r;
    private Material m;

    public Sphere(Point p, float r, Material m) {
        this.p = p;
        this.r = r;
        this.m = m;
    }
    
    public void update(Point p, float r, Material m) {
        setCenter(p);
        setRadius(r);
        setMaterial(m);
    }
    
    public void print() {
        System.out.print("    s( ");
        getCenter().short_print();
        System.out.print(", " + getRadius() + ", ");
        getMaterial().short_print();
        System.out.println(")");
    }
    
    public Point getCenter() {
        return p;
    }

    public void setCenter(Point p) {
        this.p = p;
    }

    public float getRadius() {
        return r;
    }

    public void setRadius(float r) {
        this.r = r;
    }

    public Material getMaterial() {
        return m;
    }

    public void setMaterial(Material c) {
        this.m = m;
    }
}
