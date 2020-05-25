import java.lang.Math;

public class Body{

	public double xxPos; //current x position
	public double yyPos;
	public double xxVel; //current velocity in x direction
	public double yyVel; 
	public double mass;
	public String imgFileName; //
	public static double g = 6.673e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double d;
		d = Math.sqrt(Math.pow((this.xxPos - b.xxPos),2) + Math.pow((this.yyPos - b.yyPos), 2));
		return d;
	}

	public double calcForceExertedBy(Body b){
		double exertedF;
		exertedF = (g * (mass*b.mass)) / Math.pow(this.calcDistance(b),2);
		return exertedF;
	}

	public double calcForceExertedByX(Body b){
		double dx;
		double dy;
		double exertedFbyX;
		double r;
		//double exertedFy;
		dx = b.xxPos - this.xxPos;
		dy = b.yyPos - this.yyPos;
		r = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
		exertedFbyX = calcForceExertedBy(b)*dx/r;
		return exertedFbyX;
	}

	public double calcForceExertedByY(Body b){
		double dx;
		double dy;
		// double exertedFbyX;
		double exertedFbyY;
		double r;
		dx = b.xxPos - this.xxPos;
		dy = b.yyPos - this.yyPos;
		r = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
		exertedFbyY = calcForceExertedBy(b)*dy/r;
		return exertedFbyY;
	}

	public double calcNetForceExertedByX(Body[] arr_b){
		double netFbyX=0;
		for(int i=0; i < arr_b.length; i++){
			if(arr_b[i].equals(this)){
				continue;
			}
			netFbyX = netFbyX + calcForceExertedByX(arr_b[i]);
		}
		return netFbyX;
	}

	public double calcNetForceExertedByY(Body[] arr_b){
		double netFbyY=0;
		for(int i=0; i < arr_b.length; i++){
			if(arr_b[i].equals(this)){
				continue;
			}
			netFbyY = netFbyY + calcForceExertedByY(arr_b[i]);
		}
		return netFbyY;
	}

	public void update(double dt, double f_X, double f_Y){
		//a = F/m
		double a_X = f_X/this.mass;
		double a_Y = f_Y/this.mass;
		xxVel+= a_X * dt;
		yyVel+= a_Y * dt;
		xxPos+= xxVel*dt;
		yyPos+= yyVel*dt;
	}
}