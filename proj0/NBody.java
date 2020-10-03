public class NBody {
	private static double planet_radius;

	public static double readRadius(String filename) {
	   In in = new In(filename);
	   int N = in.readInt();
	   double radius = in.readDouble();
	   return radius;
	}

	public static Planet[] readPlanets(String filename) {
	    In in = new In(filename);
	    int N = in.readInt();
	    double radius = in.readDouble();
	    Planet[] allPlanets = new Planet[N];

	    for (int i = 0; i < N; i += 1) {
	    	double xxPos = in.readDouble();
	    	double yyPos = in.readDouble();
	    	double xxVel = in.readDouble();
	    	double yyVel = in.readDouble();
	    	double mass = in.readDouble();
	    	String imgFileName = in.readString();
	    	allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

	    }
	    return allPlanets;
	}

	private void Printing_the_Universe(Planet[] allPlanets) {
		System.out.println(allPlanets.length);
		System.out.println(planet_radius);

		for (int i = 0; i < allPlanets.length; i++) {
			System.out.println(allPlanets[i].xxPos + "  " + allPlanets[i].yyPos + "  " + allPlanets[i].xxVel + "  " + allPlanets[i].yyVel + "  " + allPlanets[i].mass + "  " + allPlanets[i].imgFileName);
		}

	}

	public static void main(String[] args) {
		//Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		String backgroundImageToDraw = "images/starfield.jpg";
		String planet_Data = filename;
		
		planet_radius = readRadius(planet_Data);

		Planet[] allPlanets = readPlanets(planet_Data);

		//Creating an Animation
		StdDraw.enableDoubleBuffering();

		//Drawing the Background
		StdDraw.setScale(planet_radius, -planet_radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundImageToDraw);

		//Drawing One Planet
	    In in = new In(planet_Data);
	    int N = in.readInt();

		for (double current_T = 0; current_T < T; current_T += dt) {
			double[] allPlanets_yForces = new double[N];
			double[] allPlanets_xForces = new double[N];
			
			for (int i = 0; i < N; i += 1) {
				allPlanets_yForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				allPlanets_xForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
				allPlanets[i].update(current_T, allPlanets_yForces[i], allPlanets_xForces[i]);
				allPlanets[i].draw();
				StdDraw.show();
				StdDraw.pause(10);
			}
		}

		Printing_the_Universe(allPlanets);


	}

}




