
package primitives;

public class Pixel {
    private Point p;
    private Color c;
    
    public Pixel(Point p, Color c) {
        this.p = p;
        this.c = c;
    }
    
    public void update(Point p, Color c) {
        setPoint(p);
        setColor(c);
    }
    
    public Point getPoint() {
        return p;
    }

    public void setPoint(Point p) {
        this.p = p;
    }

    public Color getColor() {
        return c;
    }

    public void setColor(Color c) {
        this.c = c;
    }
}
