public class Body {
	double myXPos;            // current x position
	double myYPos;            // current y position
	double myXVel;            // current velocity in x direction 
	double myYVel;            // current velocity in y direction
	double myMass;            // mass of body
	String myFileName;        // file name (in images folder) 
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
		
	public Body(Body p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
		
	}
	public double calcDistance(Body p) {
		return Math.sqrt((myXPos-p.myXPos)*(myXPos-p.myXPos)+(myYPos-p.myYPos)*(myYPos-p.myYPos));
	}
	
	public double calcForceExertedBy(Body p){
		return ((6.67e-11)*this.myMass*p.myMass)/(this.calcDistance(p)*this.calcDistance(p));
	}
	
	public double calcForceExertedByX(Body p) {
		return this.calcForceExertedBy(p)*(p.myXPos-this.myXPos)/this.calcDistance(p);
	}
	
	public double calcForceExertedByY(Body p) {
		return this.calcForceExertedBy(p)*(p.myYPos-this.myYPos)/this.calcDistance(p);
	}
	
	public double calcNetForceExertedByX(Body[] allPlanets) {
		double sum = 0.0;
		for (int i=0; i<allPlanets.length; i++) {
			if (! allPlanets[i].equals(this)) {
				sum += calcForceExertedByX(allPlanets[i]);
			}
		}
		return sum;	
	}
	
	public double calcNetForceExertedByY(Body[] allPlanets) {
		double sum = 0.0;
		for (int i=0; i<allPlanets.length; i++) {
			if (! allPlanets[i].equals(this)) {
				sum += calcForceExertedByY(allPlanets[i]);
			}
		}
		return sum;	
	}
	
	public void update(double seconds, double xforce, double yforce) {
		double myXAcc = xforce/this.myMass;
		double myYAcc = yforce/this.myMass;
		myXVel += seconds*myXAcc;
		myYVel += seconds*myYAcc;
		myXPos += seconds*myXVel;
		myYPos += seconds*myYVel;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}

}