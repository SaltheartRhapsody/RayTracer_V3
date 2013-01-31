package primitives;

public class Ray {
    private Vec direction;
    private Point origin;
    
    public Ray(Vec v, Point p) {
        this.direction = v;
        this.origin = p;
    }
    
    public void update(Vec v, Point p) {
        this.setDirection(v);
        this.setOrigin(p);
    }

    public Vec getDirection() {
        return direction;
    }

    public void setDirection(Vec direction) {
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    
    
    
}
