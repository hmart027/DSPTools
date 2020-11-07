package edu.fiu.cate.tools.filter;

public interface Filter {

	public void reset();
	
	public boolean isReady();
	
	public double filter(double x);
	
	public double[] filter(double[] x);

	public double[] getCoefficients();
		
	public double getLastValue();
	
	public int getLength();
}
