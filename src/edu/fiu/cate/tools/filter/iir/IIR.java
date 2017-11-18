package edu.fiu.cate.tools.filter.iir;

import edu.fiu.cate.tools.filter.Filter;

public class IIR implements Filter{
	
	protected int l;
	protected double[] a, b, delays;
	
	protected IIR(){};
	
	public static IIR loadIIR(double[] b, double[] a){
		if(b==null || a==null) return null;
		if(b.length<=0) return null;
		if(b.length>a.length+1) return null;
		IIR filt = new IIR();
		filt.init(b.length);
		for(int i=0; i<filt.l; i++){
			filt.delays[i] = 0;
		}
		for(int i=0; i<filt.l; i++){
			filt.b[i] = b[i];
		}
		if(a.length<filt.l){
			filt.a[0] = 1;
			for(int i=0; i<a.length; i++){
				filt.a[i+1] = a[i];
			}
		}else{
			for(int i=0; i<a.length; i++){
				filt.a[i] = a[i];
			}
		}
		return filt;
	}
	
	public static IIR loadIIR(double[] c){
		if(c==null || c.length<=0) return null;
		IIR filt = new IIR();
		filt.init(c.length/2);
		for(int i=0; i<filt.l; i++){
			filt.delays[i] = 0;
		}
		for(int i=0; i<filt.l; i++){
			filt.b[i] = c[i];
		}
		if(c.length%2==0)
			for(int i=0; i<filt.l; i++){
				filt.a[i] = c[filt.l+i];
			}
		else{
			filt.a[0] = 1;
			for(int i=1; i<filt.l; i++){
				filt.a[i] = c[filt.l-1+i];
			}
		}
			
		return filt;
	}
	
	private void init(int l){
		this.l = l;
		b = new double[l];
		a = new double[l];
		delays = new double[l];
	}

	@Override
	public synchronized void reset() {
		for(int i = 0; i<l; i++){
			this.delays[i]= 0.0;
		}	
	}

	@Override	
	public synchronized double filter(double x) {
		double y = a[0]*(x*b[0]+delays[0]);
		for(int i=0; i<l-1; i++){
			delays[i] = x*b[i+1]+delays[i+1]-y*a[i+1];
		}
		return y;
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
		return 0;
	}

}
