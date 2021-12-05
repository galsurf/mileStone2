package test;
public class StatLib {

	// simple average
	public static float avg(float[] x){
		float sum = 0;
		for(int i = 0 ; i < x.length; i++)
		{
			sum += x[i];
		}
		return sum / x.length;
	}

	// returns the variance of X and Y
	public static float var(float[] x){
		float sumSquared = 0;
		for(int i = 0 ; i < x.length ; i++)
		{
			sumSquared +=  Math.pow(x[i],2) ;
		}
		float expectionValue = avg(x);
		float variance = sumSquared/x.length - (float)Math.pow(expectionValue ,2) ;
		return variance;
	}

	// returns the covariance of X and Y //שיתופיות
	public static float cov(float[] x, float[] y){
		float[] xy = new float[x.length];
		for(int i = 0 ; i < x.length ; i++)
		{
			xy[i] = x[i] * y[i];
		}
		float avgOfXY = avg(xy);
		float avgOfX = avg(x);
		float avgOfY = avg(y);
		return avgOfXY - (avgOfX * avgOfY) ;
	}

	// returns the Pearson correlation coefficient of X and Y //מקדם התאמה // קורולציה לינארית
	public static float pearson(float[] x, float[] y){
		float sqrtOfVarianceX = (float)Math.sqrt(var(x));
		float sqrtOfVarianceY = (float)Math.sqrt(var(y));

		return cov(x , y)/(sqrtOfVarianceX * sqrtOfVarianceY);
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float[] arrOfX = new float[points.length];
		float[] arrOfY = new float[points.length];

		for(int i = 0 ; i < points.length ; i++)
		{
			arrOfX[i] = points[i].x;
			arrOfY[i] = points[i].y;
		}

		float a = cov(arrOfX , arrOfY)/ var(arrOfX);
		float avgOfX = avg(arrOfX);
		float avgOfY = avg(arrOfY);

		float b = avgOfY - a * avgOfX;
		Line myLine = new Line(a ,b);
		return myLine;
	}

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		Line myLine = linear_reg(points);
		return dev(p , myLine);
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		return Math.abs((l.a* p.x + l.b) - p.y);
	}

}
