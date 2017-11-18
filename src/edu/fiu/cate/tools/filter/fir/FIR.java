package edu.fiu.cate.tools.filter.fir;

import java.util.ArrayList;
import java.util.Arrays;

import edu.fiu.cate.tools.filter.Filter;

public class FIR implements Filter{

	protected int order;
	protected double[] weights;
	protected ArrayList<Double> pastVals;
	protected double lastY;
	
	public enum TYPES{
		LOW_PASS,HIGH_PASS,BAND_PASS,BAND_STOP;
	}
	
	protected FIR(){}
	
	public FIR(int order){
		init(order);
	}
	
	protected void init(int order){
		this.order = order;
		this.weights = new double[order+1];
		Double[] vals = new Double[order];
		Arrays.fill(vals, 0d);
		this.pastVals = new ArrayList<Double>(Arrays.asList(vals));
	}
	
	@Override
	public synchronized void reset(){
		for(int i = 0; i<order+1; i++){
			this.filter(0);
		}
	}

	@Override
	public synchronized double filter(double x){
		double y = 0;
		pastVals.add(x);
		for(int i = 0; i<order+1; i++){
			y += weights[i]*pastVals.get(order-i);
		}
		lastY = y;
		pastVals.remove(0);
		return y;
	}

	@Override
	public double[] filter(double[] x){
		if(x==null || x.length==0) return null;
		double[] out = new double[x.length];
		for(int i = 0; i<x.length; i++)
			out[i] = filter(x[i]);
		return out;
	}

	@Override
	public double[] getCoefficients(){
		return Arrays.copyOf(weights, weights.length);
	}
	
	public int getOrder(){
		return order;
	}

	@Override
	public double getLastValue(){
		return lastY;
	}
}
