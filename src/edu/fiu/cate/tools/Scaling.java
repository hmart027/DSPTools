package edu.fiu.cate.tools;

public class Scaling {
	
	public static final int DEFAULT_WINDOW_SIZE = 3;
	public static final double PI2 = Math.PI*Math.PI;
	
	/**
	 * Changes the sampling frequency of the given signal using Lanczos resampling. 
	 * @param signal to be re-sampled
	 * @param f0 is the original sampling frequency
	 * @param f1 is the new sampling frequency
	 * @return a new copy of the original signal re-sampled at the new frequency f1
	 */
	public static double[] scale(double[] signal, float f0, float f1){
		return scale(signal, f0, f1, DEFAULT_WINDOW_SIZE);
	}
	
	/**
	 * Changes the sampling frequency of the given signal using Lanczos resampling.
	 * @param signal to be re-sampled
	 * @param f0 is the original sampling frequency
	 * @param f1 is the new sampling frequency
	 * @param w is the window size to be used for re-sampling
	 * @return a new copy of the original signal re-sampled at the new frequency f1 using a window of size w.
	 */
	public static double[] scale(double[] signal, float f0, float f1, int w){
		float r  = f0/f1;
		int l0 = signal.length;
		int l1 = (int) Math.ceil(l0/r);
		double[] out = new double[l1];
		int start, end;
		float x, t;
		for(int i=0; i<l1; i++){
			x = i*r;
			t = 0;
			start   = (int) (Math.floor(x)-w+1);
			end		= (int) (Math.floor(x)+w);
			if(start<0 || end>l0)
				continue;
			for(int y=start; y<end; y++){
				t += signal[y]*getLanczos(x-y, w);
			}
			out[i] = t;
		}
		return out;
	}
	
	/**
	 * Changes the sampling frequency of the given signal using Lanczos resampling. 
	 * @param sig to be re-sampled. first index is the electrode, second is the sample point
	 * @param f0 is the original sampling frequency
	 * @param f1 is the new sampling frequency
	 * @return a new copy of the original signal re-sampled at the new frequency f1
	 */	
	public static double[][] scale(double[][] sig, double f0, double f1){
		double[][] out = new double[sig.length][];
		for(int i=0; i<sig.length; i++){
			out[i]=scale(sig[i], (float)f0, (float)f1);
		}
		return out;
	}

	public static double getLanczos(double x, double w){
		if(x==0)
			return 1;
		if(Math.abs(x)>w)
			return 0;
		return w*Math.sin(Math.PI*x)*Math.sin(Math.PI*x/w)/(PI2*x*x);
	}

}
