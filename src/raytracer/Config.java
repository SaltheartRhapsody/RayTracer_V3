package raytracer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import primitives.Color;
import primitives.Light;
import primitives.Material;
import primitives.Point;
import primitives.Sphere;
import primitives.Vec;

/**
 * Class for storing basic config information.
 * @author Perrin
 */
public class Config {
    
    private float viewdist, fov_w, fov_h, pix_w;
    private Vec viewdir, updir;
    private Point eye;
    private Color background;
    private Material material;
    private Vector<Light> lights = new Vector<>();
    private Vector<Sphere> spheres = new Vector<>();
    
    /**
    * Class method: parses info from a config file and returns an initialized config object.
    * Note: very specific config file syntax.
    * @return Config object
    **/
    public static Config parse(String config) throws FileNotFoundException {
        
        Scanner scan = new Scanner(new File(config));
        Config conf = new Config();
        
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] tokens = line.split("\\s+");
            switch(tokens[0]) {
                case "eye":
                    conf.setEye(new Point(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
                    break;
                case "viewdir":
                    conf.setViewdir(new Vec(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
                case "updir":
                    conf.setUpdir(new Vec(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
                case "viewdist":
                    conf.setViewdist(Float.parseFloat(tokens[1]));
                    break;
                case "fov_w":
                    conf.setFov_w(Float.parseFloat(tokens[1]));
                    break;
                case "fov_h":
                    conf.setFov_h(Float.parseFloat(tokens[1]));
                    break;
                case "pix_w":
                    conf.setPix_w(Float.parseFloat(tokens[1]));
                    break;
                case "bkgcolor":
                    conf.setBackground(new Color(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])));
                    break;
                case "light":
                    if(Integer.parseInt(tokens[4]) == 1) {
                        conf.getLights().add(new Light(
                                                        new Point(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])),
                                                        true));
                    } else {
                        conf.getLights().add(new Light(
                                                        new Point(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])),
                                                        false));
                    }
                    break;
                case "materialcolor":
                    conf.setMaterial(new Material(
                                                    Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]),
                                                    Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5]), Float.parseFloat(tokens[6]),
                                                    Float.parseFloat(tokens[7]), Float.parseFloat(tokens[8]), Float.parseFloat(tokens[9]), Float.parseFloat(tokens[10])));
                    break;
                case "sphere":
                    conf.getSpheres().add(new Sphere(
                                                        new Point(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3])),
                                                        Float.parseFloat(tokens[4]),
                                                        conf.getMaterial()
                                                        ));
                    break;
                default:
                    break;
            }
        }
        
        scan.close();
        return conf;
    }
    
    public void print() {
        System.out.println("***** Config File *****\n");
        System.out.print("eye: ");
        getEye().print();
        System.out.print("viewdir: ");
        getViewdir().print();
        System.out.print("updir: ");
        getUpdir().print();
        System.out.println("viewdist: " + getViewdist());
        System.out.println("fov_w: " + getFov_w());
        System.out.println("fov_h: " + getFov_h());
        System.out.println("pix_w: " + getPix_w());
        System.out.print("bkgcolor: ");
        getBackground().print();
        System.out.println("Lights:");
        for(Light light : getLights()) {
            light.print();
        }
        System.out.println("Spheres:");
        for(Sphere sphere : getSpheres()) {
            sphere.print();
        }
        System.out.println("\n***** End Config File *****");
    }

    public float getViewdist() {
        return viewdist;
    }

    public void setViewdist(float viewdist) {
        this.viewdist = viewdist;
    }

    public float getFov_w() {
        return fov_w;
    }

    public void setFov_w(float fov_w) {
        this.fov_w = fov_w;
    }

    public float getFov_h() {
        return fov_h;
    }

    public void setFov_h(float fov_h) {
        this.fov_h = fov_h;
    }

    public float getPix_w() {
        return pix_w;
    }

    public void setPix_w(float pix_w) {
        this.pix_w = pix_w;
    }

    public Vec getViewdir() {
        return viewdir;
    }

    public void setViewdir(Vec viewdir) {
        this.viewdir = viewdir;
    }

    public Vec getUpdir() {
        return updir;
    }

    public void setUpdir(Vec updir) {
        this.updir = updir;
    }

    public Point getEye() {
        return eye;
    }

    public void setEye(Point eye) {
        this.eye = eye;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Vector<Sphere> getSpheres() {
        return spheres;
    }

    public void setSpheres(Vector<Sphere> spheres) {
        this.spheres = spheres;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Vector<Light> getLights() {
        return lights;
    }

    public void setLights(Vector<Light> lights) {
        this.lights = lights;
    }
}
