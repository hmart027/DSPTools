package edu.fiu.cate.tools.movingaverage;

import javax.management.RuntimeErrorException;

public class SimpleMovingAverage implements MovingAverage {
	
	private int windowSize;
	private double weight;
	
	public SimpleMovingAverage(int windowSize){
		this.windowSize = windowSize;
		this.weight = 1d/windowSize;
	}

	@Override
	public double[] compute(double[] x) {
		if(x==null || x.length==0) return null;
		if(x.length < windowSize) throw new RuntimeErrorException(new Error("Data array too small for current window size."));
		int size = x.length;
		double[] out = new double[size];
		int hSize = windowSize/2;
		double cAverage = 0; // Cumulative average
		int cIndex = 0;
		for(int i = 0; i<hSize; i++)
			cAverage += x[i]*weight;
		while(cIndex < x.length){			
			if(cIndex+hSize < x.length)
				cAverage += x[cIndex+hSize]*weight;
			if(cIndex-hSize >= 0)
				cAverage -= x[cIndex-hSize]*weight;
			out[cIndex] = cAverage;
			cIndex++;
		}
		return out;
	}
	
	public double[] substract(double[] x) {
		if(x==null || x.length==0) return null;
		if(x.length < windowSize) throw new RuntimeErrorException(new Error("Data array too small for current window size."));
		int size = x.length;
		double[] out = new double[size];
		int hSize = windowSize/2;
		double cAverage = 0; // Cumulative average
		int cIndex = 0;
		for(int i = 0; i<hSize; i++)
			cAverage += x[i]*weight;
		while(cIndex < x.length){			
			if(cIndex+hSize < x.length)
				cAverage += x[cIndex+hSize]*weight;
			if(cIndex-hSize >= 0)
				cAverage -= x[cIndex-hSize]*weight;
			out[cIndex] = x[cIndex]-cAverage;
			cIndex++;
		}
		return out;
	}

}
