package edu.fiu.cate.tools.filter.iir;

import java.util.ArrayList;

import edu.fiu.cate.tools.filter.Filter;

public class IIR implements Filter{
	
	protected int aL, bL;
	protected double[] a, b;
	protected ArrayList<Double> pastIn, pastOut;
	
	protected IIR(){};
	
	public static IIR loadIIR(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		for(int i = 0; i<aL; i++){
			this.pastIn.set(i, 0.0);
		}		
		for(int i = 0; i<bL; i++){
			this.pastOut.set(i, 0.0);
		}		
	}

	@Override
	public double filter(double x) {
		double y = 0, yO=0;
		pastIn.add(x);
		for(int i = 0; i<aL; i++){
			y += a[i]*pastIn.get(aL-i);
		}
		pastIn.remove(0);
		yO = y;
		for(int i = 0; i<bL; i++){
			y += b[i]*pastOut.get(bL-i);
		}
		pastOut.add(yO);
		pastOut.remove(0);
		return y;
	}

	@Override
	public double[] filter(double[] x) {
		if(x==null || x.length==0) return null;
		double[] out = new double[x.length];
		for(int i = 0; i<x.length; i++)
			out[i] = filter(x[i]);
		return out;
	}

	@Override
	public double[] getCoefficients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLastValue() {
		return pastOut.get(bL-1);
	}

}
