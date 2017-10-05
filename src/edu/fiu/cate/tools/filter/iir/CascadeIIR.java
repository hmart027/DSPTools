package edu.fiu.cate.tools.filter.iir;

import edu.fiu.cate.tools.filter.Filter;

public class CascadeIIR implements Filter {
	
	private IIR[] filters;
	
	protected CascadeIIR(){};
	
	public static CascadeIIR loadSOS(double[][] sos){
		CascadeIIR irr = new CascadeIIR();
		irr.filters = new IIR[sos.length];
		for(int i = 0; i<irr.filters.length; i++){
			irr.filters[i] = IIR.loadIIR(sos[i]);
		}
		return irr;
	}

	@Override
	public void reset() {
		for(int i = 0; i<filters.length; i++){
			filters[i].reset();
		}
	}

	@Override
	public double filter(double x) {
		double out = x;
		for(int i = 0; i<filters.length; i++){
			out = filters[i].filter(out);
		}
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

}
