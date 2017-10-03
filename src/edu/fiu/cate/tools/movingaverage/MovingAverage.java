package edu.fiu.cate.tools.movingaverage;

public interface MovingAverage {
	
	public static enum MovingAverageType{
		SIMPLE, CUMULATIVE, WEIGHTED, EXPONENTIAL, MODIFIED;
	}
	
	public double[] compute(double[] x);

}
