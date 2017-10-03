package edu.fiu.cate.tools.filter;

public interface Filter {

	public void reset();
	
	public double filter(double x);
	
	public double[] filter(double[] x);

	public double[] getCoefficients();
		
	public double getLastValue();
}
