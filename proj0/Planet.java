public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;


  public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                  }
    public Planet(Planet p){
      this.xxPos = p.xxPos;
      this.yyPos = p.yyPos;
      this.xxVel = p.xxVel;
      this.yyVel = p.yyVel;
      this.mass = p.mass;
      this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
      double dx = (p.xxPos - this.xxPos);
      double dy = (p.yyPos - this.yyPos);
      double distance = Math.sqrt(dx*dx + dy*dy);
      return distance;
    }
    public double calcForceExertedBy(Planet p){
      double G = 6.67e-11;
      double r = this.calcDistance(p);
      double force = G * p.mass*this.mass/(r*r);
      return force;
    }
    public double calcForceExertedByX(Planet p){
      double F = this.calcForceExertedBy(p);
      double r = this.calcDistance(p);
      double dx = (p.xxPos - this.xxPos);
      return F*dx / r;
    }
    public double calcForceExertedByY(Planet p){
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = (p.yyPos - this.yyPos);
        return F*dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
      double xnet = 0;
      for (int i = 0; i < allPlanets.length; i+=1)
      {
          if (this.equals(allPlanets[i]) == true){
            ;
          } else {
            xnet += this.calcForceExertedByX(allPlanets[i]);
          }
      }
      return xnet;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
      double xnet = 0;
      for (int i = 0; i < allPlanets.length; i+=1)
      {
          if (this.equals(allPlanets[i]) == true){
            ;
          } else {
            xnet += this.calcForceExertedByY(allPlanets[i]);
          }
      }
      return xnet;
    }

    public void update(double dt, double fX, double fY){
      double ax = fX/this.mass;
      double ay = fY/this.mass;
      this.xxVel += dt*ax;
      this.yyVel += dt*ay;
      this.xxPos += dt*this.xxVel;
      this.yyPos += dt*this.yyVel;
    }

    public void draw(){
      String filename = "images/"+imgFileName;
      StdDraw.picture(xxPos,yyPos,filename);
    }



}
