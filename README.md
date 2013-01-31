RayTracer_V3
============

Dipping my toe into computer graphics (ray tracing)

One thing I never got around to doing in college was taking a computer graphics course... luckily however
there's this thing called the internet.  This is my fist attempt to poke into the world of computer graphics.
Ray tracing, as it turns out, is quite cool.  It works by simulating casinting rays of light out towards a 3d
representation of a scene, determining positions relative to light sources, other objects, and the native colors of
any object the ray might bump into.  It's a simple, and elegant method which is capable of turning a simple text config
file such as "config.txt" into rather pretty looking 3d models "img.ppm".

The Main class is in RayTracer.java, and it takes a config file and outputfile as it's arguments.  The output is in *.ppm
format, so use gimp or something to view them.  It's currently a work in progress, about a week into it... I've been able
to account for multiple light sources, and spheres.  Next I'll be adding reflections, and maybe some transparancy, and
eventaully planes and polygons for more interesting scenes.
