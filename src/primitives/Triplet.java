package primitives;

/**
 * Generic abstract for 3d points and vectors
 * @author Perrin
 */
abstract public class Triplet {
    
    protected float x, y, z;
    
    public Triplet(float x, float y, float z) {
        this.x = x;
        this.y = y; 
        this.z = z;
    }
    
    public void update(float x, float y, float z) {
        setX(x);
        setY(y);
        setZ(z);
    }
    
    protected void print() {
        System.out.println("(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")");
    }
    
    protected void short_print() {
        System.out.print("(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")");
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
