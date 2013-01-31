package raytracer;

import primitives.Point;
import primitives.Vec;

/**
 * Class for storing viewing window attributes.
 * @author Perrin
 */
public class Window {
    private Point ul, ur, ll, lr, eye;
    private float window_w, window_h, pix_w, pix_h;
    private Vec delta_w, delta_h;
    
    public Window(Config conf) {
        Vec n, u, v;
        n = conf.getViewdir().normalize();
        u = conf.getViewdir().cross(conf.getUpdir()).normalize();
        v = u.cross(conf.getViewdir()).normalize();
        
        this.window_h = (float) ((2*conf.getViewdist()) * Math.tan((conf.getFov_h()*(Math.PI/180))/2));
        this.window_w = (float) ((2*conf.getViewdist()) * Math.tan((conf.getFov_w()*(Math.PI/180))/2));
        
        this.ul = (n.const_mult(conf.getViewdist())).vec_add(v.const_mult(window_h/2)).vec_sub(u.const_mult(window_w/2)).point_add(conf.getEye());
        this.ur = (n.const_mult(conf.getViewdist())).vec_add(v.const_mult(window_h/2)).vec_add(u.const_mult(window_w/2)).point_add(conf.getEye());
        this.ll = (n.const_mult(conf.getViewdist())).vec_sub(v.const_mult(window_h/2)).vec_sub(u.const_mult(window_w/2)).point_add(conf.getEye());
        this.lr = (n.const_mult(conf.getViewdist())).vec_sub(v.const_mult(window_h/2)).vec_add(u.const_mult(window_w/2)).point_add(conf.getEye());
        
        this.pix_w = conf.getPix_w();
        this.pix_h = (float) Math.floor((window_h/window_w)*pix_w);
        this.delta_h = ur.point_sub(ul).const_div(pix_w - 1);
        this.delta_w = ll.point_sub(ul).const_div(pix_h - 1);
        this.eye = conf.getEye();
        
    }

    public Point getUl() {
        return ul;
    }

    public void setUl(Point ul) {
        this.ul = ul;
    }

    public Point getUr() {
        return ur;
    }

    public void setUr(Point ur) {
        this.ur = ur;
    }

    public Point getLl() {
        return ll;
    }

    public void setLl(Point ll) {
        this.ll = ll;
    }

    public Point getLr() {
        return lr;
    }

    public void setLr(Point lr) {
        this.lr = lr;
    }

    public float getWindow_w() {
        return window_w;
    }

    public void setWindow_w(float window_w) {
        this.window_w = window_w;
    }

    public float getWindow_h() {
        return window_h;
    }

    public void setWindow_h(float window_h) {
        this.window_h = window_h;
    }

    public float getPix_w() {
        return pix_w;
    }

    public void setPix_w(float pix_w) {
        this.pix_w = pix_w;
    }

    public float getPix_h() {
        return pix_h;
    }

    public void setPix_h(float pix_h) {
        this.pix_h = pix_h;
    }

    public Vec getDelta_w() {
        return delta_w;
    }

    public void setDelta_w(Vec delta_w) {
        this.delta_w = delta_w;
    }

    public Vec getDelta_h() {
        return delta_h;
    }

    public void setDelta_h(Vec delta_h) {
        this.delta_h = delta_h;
    }

    public Point getEye() {
        return eye;
    }

    public void setEye(Point eye) {
        this.eye = eye;
    }
}
