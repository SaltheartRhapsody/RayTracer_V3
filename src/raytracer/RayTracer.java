package raytracer;

import java.io.FileNotFoundException;


/**
 * Main class - Not much going on here, OO design ftw!
 * @author Perrin
 */
public class RayTracer {

    public static void main(String[] args) throws FileNotFoundException {
        
        // defaults
        String cfg, out;
        if(args.length != 2) {
            System.out.println("No arguments: running defaults");
            cfg = "default_config.txt";
            out = "default_img.ppm";
        } else {
            cfg = args[0];
            out = args[1];
        }
                    
        // parse and store config file
        Config conf = Config.parse(cfg);       
        
        // initialize viewing window parameters
        Window w = new Window(conf);
        
        // initialize tracer object from config and window objects
        Tracer t = new Tracer(conf, w);
        
        // Render the image
        t.render(out);
    }
}

