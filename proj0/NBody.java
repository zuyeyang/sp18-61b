public class NBody{
  public static double readRadius(String filepath){
    In in = new In(filepath);
    int number_planet = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String filepath){
    In in = new In(filepath);
    int number_planet = in.readInt();
    double radius = in.readDouble();
    Planet[] all_planets = new Planet[5];
    for (int i =0; i< 5; i++){
      all_planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
    }
    return all_planets;
  }

  public static void draw_background(double radius){
    String imagepath = "images/starfield.jpg";
    StdDraw.setScale(-radius,radius);
    StdDraw.clear();
    StdDraw.picture(0,0,imagepath);
  }

   public static void main(String[] args){
     double T = Double.parseDouble(args[0]);
     double dt = Double.parseDouble(args[1]);
     String filename = args[2];
     Planet[] all_planets = readPlanets(filename);
     int l = all_planets.length;
     double radius = readRadius(filename);
     StdDraw.enableDoubleBuffering();

     int t = 0;

     for (;t<=T;t+=dt){
       double[] xForces = new double[l];
       double[] yForces = new double[l];

       for (int i = 0; i<l; i++){
         xForces[i] = all_planets[i].calcNetForceExertedByX(all_planets);
         yForces[i] = all_planets[i].calcNetForceExertedByY(all_planets);
       }

       for (int i = 0; i<l; i++){
         all_planets[i].update(dt,xForces[i],yForces[i]);
       }


       draw_background(radius);
       for (int p=0; p<l; p++){
         all_planets[p].draw();
       }
       StdDraw.show();
       StdDraw.pause(10);
     }
       StdOut.printf("%d\n", all_planets.length);
       StdOut.printf("%.2e\n", radius);
       for (int i = 0; i < all_planets.length; i++) {
           StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                         all_planets[i].xxPos, all_planets[i].yyPos, all_planets[i].xxVel,
                         all_planets[i].yyVel, all_planets[i].mass, all_planets[i].imgFileName);
       }
   }

}
