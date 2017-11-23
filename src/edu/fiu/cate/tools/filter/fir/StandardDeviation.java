package edu.fiu.cate.tools.filter.fir;

public class StandardDeviation extends FIR{
	
	double w;
	
	public StandardDeviation(int windowSize){
		super(windowSize-1);
		w = 1.0/(double)windowSize;
		for(int i=0;i<windowSize;i++)
			this.weights[i]=w;
	}
	
	@Override
	public synchronized double filter(double x){
		double mean = 0, sumOfSquares = 0;
		pastVals.add(x);
		//getMean
		for(int i = 0; i<order+1; i++){
			mean += pastVals.get(order-i);
			sumOfSquares += Math.pow(pastVals.get(order-i), 2);
		}
//		mean *= w;
//		for(int i = 0; i<order+1; i++){
//			std += Math.pow(pastVals.get(order-i)-mean, 2);
//		}
		pastVals.remove(0);
//		return Math.pow(std*w, 0.5);
		lastY = Math.sqrt((sumOfSquares-mean*mean*w)/order);
		
		if(cSample++ > order+1)
			ready = true;
		
		return lastY;
	}
	
}
