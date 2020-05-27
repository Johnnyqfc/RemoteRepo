public class NBody{
	/**NBody is a class run your simulation */
	public static void main(String[] args){
		//store the 0th and 1st command line arguments as doubles
		In in = new In("/data/planets.txt");
		int N = in.readInt();
		//
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		//Read in the bodies, radius using methods
		double radius = readRadius("/data/planets.txt");
		Body[] bodies = readBodies("/data/planets.txt");

		//Draw background

		StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
		//Draw each planet

		for (int i = 0; i < bodies.length; i++){
			bodies[i].draw();
		}
		// for (Body b : bodies){
		// 	// System.out.println(b.mass);
		// 	b.draw();
		// }
		// for (int i = 0; i<bodies.length;i++){
		// 	bodies[i].draw();
		// }
		//To prevent flickering in the animation
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
        StdDraw.clear();
		// Time start_t = 0;
		for(int t = 0; t <= T; t++){
			//create xF,xF force array
			double[] xForces = new double[N];
			double[] yForces = new double[N];
			//cal netForce to each body
			for (int i = 0;i< N;i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			//update each planet using dt invertal
			for (int j = 0; j < N; j++){
				bodies[j].update(dt,xForces[j],yForces[j]);
			}
			for (int i = 0; i < bodies.length; i++){
				bodies[i].draw();
			}
			// for (Body b : bodies){
			// 	b.draw();
			// }
			StdDraw.show();
			StdDraw.pause(10);
		}


	}

	public static double readRadius(String dir){
		In in = new In(dir);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String dir){
		// Using In class to manipulate the element of text file
		In in = new In(dir);
		// read and skip the first two elements
		int N = in.readInt();
		double radius = in.readDouble();
		Body[] arr_b = new Body[N];

		for (int i=0;i<N;i++){
			// arr_b[i].xxPos = in.readDouble();  encounter nullexception error!!!
			// arr_b[i].yyPos = in.readDouble();	call the object before the declaration
			// arr_b[i].xxVel = in.readDouble();
			// arr_b[i].yyVel = in.readDouble();
			// arr_b[i].mass = in.readDouble();
			// arr_b[i].imgFileName = in.readString();
	      double xxPos = in.readDouble();
	      double yyPos = in.readDouble();
	      double xxVel = in.readDouble();
	      double yyVel = in.readDouble();
	      double mass = in.readDouble();
	      String img = in.readString();
	      //create the planet one by one by assigning fields to each planet
	      Body body = new Body(xxPos,yyPos,xxVel,yyVel,mass,img);
	      arr_b[i] = body; // add the planet to array of planet
		}
 		return arr_b;
	}
}