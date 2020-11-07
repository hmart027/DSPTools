package edu.fiu.cate.tools.filter.iir;

import java.io.Serializable;

import edu.fiu.cate.tools.filter.Filter;

public class CascadeIIR implements Filter, Serializable {

	private static final long serialVersionUID = -7806552956293581827L;
	
	protected boolean ready;
	protected int cSample;
	protected int maxLen=-1;
	private IIR[] filters;
	
	protected CascadeIIR(){};
	
	public static CascadeIIR loadSOS(double[][] sos){
		CascadeIIR irr = new CascadeIIR();
		irr.filters = new IIR[sos.length];
		for(int i = 0; i<irr.filters.length; i++){
			irr.filters[i] = IIR.loadIIR(sos[i]);
			if(irr.filters[i].l>irr.maxLen)
				irr.maxLen = irr.filters[i].l; 
		}
		return irr;
	}

	@Override
	public void reset() {
		for(int i = 0; i<filters.length; i++){
			filters[i].reset();
		}
		cSample = 0;
		ready = false;
	}

	@Override
	public double filter(double x) {
		double out = x;
		for(int i = 0; i<filters.length; i++){
			out = filters[i].filter(out);
		}
		if(cSample++ > maxLen+1)
			ready = true;
		return out;
	}

	@Override
	public double[] filter(double[] x) {
		if(x==null || x.length==0) return null;
		double[] out = new double[x.length];
		for(int i = 0; i<x.length; i++){
			out[i] = filter(x[i]);
		}
		return out;
	}

	@Override
	public double[] getCoefficients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLastValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public synchronized boolean isReady() {
		return ready;
	}

	@Override
	public int getLength() {
		return maxLen;
	}

}
