
package primitives;

public class Material {
    private float odr, odg, odb, osr, osg, osb, ka, kd, ks, n;

    public Material(float odr, float odg, float odb, float osr, float osg, float osb, float ka, float kd, float ks, float n) {
        this.odr = odr;
        this.odg = odg;
        this.odb = odb;
        this.osr = osr;
        this.osg = osg;
        this.osb = osb;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.n = n;
    }
    
    public void update(float odr, float odg, float odb, float osr, float osg, float osb, float ka, float kd, float ks, float n) {
        this.odr = odr;
        this.odg = odg;
        this.odb = odb;
        this.osr = osr;
        this.osg = osg;
        this.osb = osb;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.n = n;
    }
    
    public void print() {
        System.out.println(
                            "m(" + odr + ", " + odg + ", " + odb + 
                            ", " + osr + ", " + osg + ", " + osb + 
                            ", " + ka + ", " + kd + ", " + ks + ", " + n + ")");
    }
    
    public void short_print() {
        System.out.print(
                            "m(" + odr + ", " + odg + ", " + odb + 
                            ", " + osr + ", " + osg + ", " + osb + 
                            ", " + ka + ", " + kd + ", " + ks + ", " + n + ")");
    }
    
    public float getOdr() {
        return odr;
    }

    public void setOdr(float odr) {
        this.odr = odr;
    }

    public float getOdg() {
        return odg;
    }

    public void setOdg(float odg) {
        this.odg = odg;
    }

    public float getOdb() {
        return odb;
    }

    public void setOdb(float odb) {
        this.odb = odb;
    }

    public float getOsr() {
        return osr;
    }

    public void setOsr(float osr) {
        this.osr = osr;
    }

    public float getOsg() {
        return osg;
    }

    public void setOsg(float osg) {
        this.osg = osg;
    }

    public float getOsb() {
        return osb;
    }

    public void setOsb(float osb) {
        this.osb = osb;
    }

    public float getKa() {
        return ka;
    }

    public void setKa(float ka) {
        this.ka = ka;
    }

    public float getKd() {
        return kd;
    }

    public void setKd(float kd) {
        this.kd = kd;
    }

    public float getKs() {
        return ks;
    }

    public void setKs(float ks) {
        this.ks = ks;
    }

    public float getN() {
        return n;
    }

    public void setN(float n) {
        this.n = n;
    }
}
