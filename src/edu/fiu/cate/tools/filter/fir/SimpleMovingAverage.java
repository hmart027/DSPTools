package edu.fiu.cate.tools.filter.fir;

public class SimpleMovingAverage extends FIR {
	
	public SimpleMovingAverage(int windowSize){
		super(windowSize-1);
		double weight = 1d/(double)windowSize;
		for(int i=0; i<windowSize; i++)
			this.weights[i] = weight;
	}

}
