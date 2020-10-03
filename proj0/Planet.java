public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //r²=dx²+dy²
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        double distance = Math.sqrt(dx*dx + dy*dy);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        double force = (G*this.mass*p.mass)/(distance*distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double total_force = calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double distance = calcDistance(p);
        double x_force = (total_force*dx)/distance;
        return x_force;
    }

    public double calcForceExertedByY(Planet p) {
        double total_force = calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double distance = calcDistance(p);
        double y_force = (total_force*dy)/distance;
        return y_force;
    }

    public boolean equals(Planet p) {
        if (p.xxPos == this.xxPos && p.yyPos == this.yyPos){
            return true;
        } else {
            return false;
        }
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double net_x_force = 0;
        for (int i = 0; i < allPlanets.length; i+=1) {
            if (equals(allPlanets[i]) == true) {
                continue;
            } else {
                double x_force = calcForceExertedByX(allPlanets[i]);
                net_x_force += x_force;
            }

        }

        return net_x_force;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double net_y_force = 0;
        for (int i = 0; i < allPlanets.length; i+=1) {
            if (equals(allPlanets[i]) == true) {
                continue;
            } else {
                double y_force = calcForceExertedByY(allPlanets[i]);
                net_y_force += y_force;
            }

        }

        return net_y_force;
    }

    public void update(double time, double fx, double fy) {
        double a_x = fx/this.mass;
        double a_y = fy/this.mass;

        this.xxVel = this.xxVel + (time*a_x);
        this.yyVel = this.yyVel + (time*a_y);

        this.xxPos = this.xxPos + (time*xxVel);
        this.yyPos = this.yyPos + (time*yyVel);

    }

    public void draw() {
        String relative_imgFileName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, relative_imgFileName);
    }


}





