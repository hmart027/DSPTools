package edu.fiu.cate.tools.filter;

import edu.fiu.cate.tools.filter.iir.CascadeIIR;
import plotter.SimplePlotter;

public class FilterTrials {

	public static void main(String[] args) {
		
		CascadeIIR filt4 = CascadeIIR.loadSOS(new double[][]{
			new double[]{0.9391,   -1.7472,    0.9391,    1.0000,   -1.8038,    0.9391},
			new double[]{1.0000,   -1.8605,    1.0000,    1.0000,   -1.8087,    0.9670},
			new double[]{1.0000,   -1.8605,    1.0000,    1.0000,   -1.8520,    0.9712}
		});
		
		double freq = 60, sampFreq = 1000;
		double[] sig = getSinusiod(freq, 1, sampFreq, 1); 
//		double[] sig = getStep(0.5, sampFreq, 1); 
		double[] x = new double[sig.length];
		for(int i=0; i<sig.length; i++){
			x[i] = i*1.0/sampFreq;
		}
		
		SimplePlotter plot = new SimplePlotter("Sine");
		plot.addPlot(x,sig, java.awt.Color.BLUE);
		plot.setMaxX(20.0/freq);
		
		double[] filtRes = filt4.filter(sig);
		plot.addPlot(x,filtRes, java.awt.Color.GREEN, false);
		
	}
	
	public static double[] getSinusiod(double freq, double amp, double samplingFreq, double len){
		double[] sig = new double[(int) (samplingFreq*len)];
		double scaling = 2.0*Math.PI*freq/(double)sig.length;
		for(int i=0; i<sig.length; i++){
			sig[i] = amp*Math.sin(scaling*i);
		}
		return sig;
	}
	
	public static double[] getStep(double loc, double samplingFreq, double len){
		double[] sig = new double[(int) (samplingFreq*len)];
		double scaling = len/(double)sig.length;
		for(int i=0; i<sig.length; i++){
			if(i*scaling>loc)
				sig[i] = 1;
			else
				sig[i] = 0;
		}
		return sig;
	}
	
	public static double[] getImpulse(double loc, double samplingFreq, double len){
		double[] sig = new double[(int) (samplingFreq*len)];
		double scaling = (double)sig.length/len;
		for(int i=0; i<sig.length; i++){
			if(i==loc*scaling)
				sig[i] = 1;
			else
				sig[i] = 0;
		}
		return sig;
	}

}
