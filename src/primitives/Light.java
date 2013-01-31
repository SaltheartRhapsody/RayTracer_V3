package primitives;

public class Light {
    private Point point;
    private boolean type;

    public Light(Point point, boolean type) {
        this.point = point;
        this.type = type;
    }
    
    public void print() {
        System.out.print("    l( ");
        point.short_print();
        System.out.print(", " + type);
        System.out.println(")");
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
