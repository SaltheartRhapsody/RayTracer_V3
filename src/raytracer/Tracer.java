package raytracer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;
import primitives.Color;
import primitives.Light;
import primitives.Material;
import primitives.Pixel;
import primitives.Point;
import primitives.Ray;
import primitives.Sphere;
import primitives.Vec;

/**
 * This is where most of the magic happens - All of the actual logic pertaining to ray tracing can be found here.
 * @author Perrin
 */
public class Tracer {
    private Vector<Sphere> spheres;
    private Vector<Light> lights;
    private Color background;
    private Window window;
    
    public Tracer(Config conf, Window w){
        this.spheres = conf.getSpheres();
        this.lights = conf.getLights();
        this.background = conf.getBackground();
        this.window = w;
    }   
    
    
    /*
     * Calculate and return the shadow coefficient as a normalized value (over each light source) for a given pixel.
     */
    public float shadows(float var1, float var2, float var3, float var4, float var5, Point intersect, int index) {
        float second = 0;
        
        for(int i = 0; i < lights.size(); i++) {
            Vec N, L, V, H;
            
            Point center = spheres.get(index).getCenter();
            
            N = (intersect.point_sub(center)).normalize();
            L = (lights.get(i).getPoint().point_sub(intersect)).normalize();
            V = (window.getEye().point_sub(intersect)).normalize();
            H = (L.vec_add(V)).const_div(((L.vec_add(V)).length()));
             
            float S = 1;
            for(int j=0; j < spheres.size(); j++) {
                float xd, yd, zd, xo, yo, zo, xc, yc, zc, rc, A, B, C, disc, t0, t1;
                
                xd = L.getX();
                yd = L.getY();
                zd = L.getZ();
                xo = intersect.getX();
                yo = intersect.getY();
                zo = intersect.getZ();
                xc = spheres.get(j).getCenter().getX();
                yc = spheres.get(j).getCenter().getY();
                zc = spheres.get(j).getCenter().getZ();
                rc = spheres.get(j).getRadius();
                
                A = xd*xd + yd*yd + zd*zd;
                B = 2*(xd*(xo-xc) + yd*(yo-yc) + zd*(zo-zc));
                C = (xo-xc)*(xo-xc) + (yo-yc)*(yo-yc) + (zo-zc)*(zo-zc) - rc*rc;

                disc = B*B - 4*A*C;
                
                t0 = -B + (float) Math.sqrt(disc)/(2*A);
                t1 = -B - (float) Math.sqrt(disc)/(2*A);
                
                if(t0 > t1) {
                    float flip = t0;
                    t0 = t1;
                    t1 = flip;
                }
                
                if(disc >= 0 && t0 > 0 && t0 < (intersect.point_sub(lights.get(i).getPoint())).length()) {
                    S = 0;
                }
            } // end spheres for loop
            float temp;
            if(N.dot(L) < 0) { 
                temp = 0; 
            } else {
                temp = N.dot(L);
            }
            
            second = second + S*(((var1*var2)*temp) + (float) ((var3*var4)*Math.pow(N.dot(H), var5)));
        } // end lights for loop
        return second;
    }
    
    /*
     * Calculate and return the color of a given pixel based on the Phong Illumination model.  Note shadow coefficients for each 
     * RGB component need to be calculated seperately.
     */
    public Color shade_ray(int index, Point intersect){
        float ka, kd, ks, odr, odg, odb, osr, osg, osb, n, R, G, B;
        ka = spheres.get(index).getMaterial().getKa();
        kd = spheres.get(index).getMaterial().getKd();
        ks = spheres.get(index).getMaterial().getKs();
        odr = spheres.get(index).getMaterial().getOdr();
        odg = spheres.get(index).getMaterial().getOdg();
        odb = spheres.get(index).getMaterial().getOdb();
        osr = spheres.get(index).getMaterial().getOsr();
        osg = spheres.get(index).getMaterial().getOsg();
        osb = spheres.get(index).getMaterial().getOsb();
        n = spheres.get(index).getMaterial().getN();
        
        R = (ka*odr) + shadows(kd, odr, osr, ks, n, intersect, index);
        G = (ka*odg) + shadows(kd, odg, osg, ks, n, intersect, index);
        B = (ka*odb) + shadows(kd, odb, osb, ks, n, intersect, index);
       
        // max value for ppm file format is 1 || 255, this corrects for possible over saturation.
        if(R > 1) { R = 1;}
        if(G > 1) { G = 1;}
        if(B > 1) { B = 1;}
       
        return new Color(R,G,B);
    }
    
    /* 
     *  Calculates the sphere/ray intersection point of a cast ray, and makes a call to shade_ray to calculate the appropriate color
     *  based on the Phong Illumination model.  Basic vector math is utilized to determine closest sphere, light, etc.
     *  Note: I'm pretty sure once I start playing around with reflections this will have to be reworked as a recursive function.
     */
    public Color ray_trace(Ray ray) {
        int index = -1;
        float t = Float.MAX_VALUE;
        
        for(int i = 0; i < spheres.size(); i++) {
            float xd, yd, zd, xo, yo, zo, xc, yc, zc, rc, A, B, C, disc, t0, t1;
            
            xd = ray.getDirection().getX();
            yd = ray.getDirection().getY();
            zd = ray.getDirection().getZ();
            xo = ray.getOrigin().getX();
            yo = ray.getOrigin().getY();
            zo = ray.getOrigin().getZ();
            xc = spheres.get(i).getCenter().getX();
            yc = spheres.get(i).getCenter().getY();
            zc = spheres.get(i).getCenter().getZ();
            rc = spheres.get(i).getRadius();
            
            A = xd*xd + yd*yd + zd*zd;
            B = 2*(xd*(xo-xc) + yd*(yo-yc) + zd*(zo-zc));
            C = (xo-xc)*(xo-xc) + (yo-yc)*(yo-yc) + (zo-zc)*(zo-zc) - rc*rc;
            
            disc = B*B - (4*A*C);
            
            if(disc < 0) {
                continue;
            }

            if(disc == 0) {
                    t0 = -B/(2*A);
                    if(t0 < t && t0 > 0) {
                            t=t0;
                            index = i;
                    }
            } else {
                    t0 = (-B + (float) Math.sqrt(disc))/(2*A);
                    t1 = (-B - (float) Math.sqrt(disc))/(2*A);

                    if( t0 > t1) {
                            float flip = t0;
                            t0 = t1;
                            t1 = flip;
                    }

                    if(t1 < 0) {
                            continue;
                    }

                    if(t0 < 0 && t1 < t) {
                            t = t1;
                            index = i;
                    } else if(t0 > 0 && t0 < t) {
                            t = t0;
                            index = i;
                    }
            }
        }// end of for loop
        if(index < 0) {
            return background;
        } else {
            Point intersect = (ray.getDirection().const_mult(t)).point_add(window.getEye());
            return shade_ray(index, intersect);
        }       
    }// end of ray_trace
    
    /*
     * Initializes 3d viewing space and pixel grid, then casts a ray from the camera position out through each pixel, returning the 
     * appropriate color and shading.  Prints image in *.ppm format to the given filename.
     */
    public void render(String filename) throws FileNotFoundException {
        int pix_h, pix_w;
        pix_h = (int) window.getPix_h();
        pix_w = (int) window.getPix_w();
        
        Pixel[][] grid = new Pixel[pix_h][pix_w];
        
        for(int i = 0; i < pix_h; i++) {
            for(int j = 0; j < pix_w; j++) {
                
                Point p = window.getDelta_h().const_mult(j).vec_add(window.getDelta_w().const_mult(i)).point_add(window.getUl());
                Vec d = p.point_sub(window.getEye()).normalize();
                Ray r = new Ray(d, window.getEye());
                Color c = ray_trace(r);
                                
                grid[i][j] = new Pixel(p, c);
            }
        }
        
        try(
                PrintWriter toFile = new PrintWriter(filename);
                ){
            toFile.println("P3");
            toFile.println(pix_w + " " + pix_h);
            toFile.println(255);
            
            for(int i = 0; i < pix_h; i++) {
                int k = 0;
                for(int j = 0; j < pix_w; j++) {
                    if(k>10) {
                        toFile.println();
                        k = 0;
                    }
                    toFile.print(
                                    (int) Math.ceil(grid[i][j].getColor().getR()*255) + " " +
                                    (int) Math.ceil(grid[i][j].getColor().getG()*255) + " " +
                                    (int) Math.ceil(grid[i][j].getColor().getB()*255) + " ");
                    k++;
                }
                    
            }
            
        }
     
    }
}

