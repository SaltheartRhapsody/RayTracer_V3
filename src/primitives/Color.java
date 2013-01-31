package primitives;


public class Color extends Triplet {

    public Color(float x, float y, float z) {
        super(x, y, z);
    }
    
    @Override
    public void print() {
        System.out.print("c");
        super.print();
    }
    
    @Override
    public void short_print() {
        System.out.print("c");
        super.short_print();
    }
    
    public float getR() {
        return super.getX();
    }
    
    public void setR(float r) {
        super.setX(r);
    }
    
    public float getG() {
        return super.getY();
    }
    
    public void setG(float g) {
        super.setY(g);
    }
    
    public float getB() {
        return super.getZ();
    }
    
    public void setB(float b) {
        super.setZ(b);
    }
    
}
