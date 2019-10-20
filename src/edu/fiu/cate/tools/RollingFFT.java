package edu.fiu.cate.tools;

import javax.dsp.tools.Complex;
import javax.dsp.tools.FFT;

public class RollingFFT extends FFT{
	
	protected Complex[] yBuffer;
	protected int bufferPointer = 0;
	protected float correction;
	
	protected RollingFFT() {};
	
	public static RollingFFT getInstance(int windowSize) {
		RollingFFT out = new RollingFFT();
		out.yBuffer = new Complex[windowSize];
		out.correction = 1f/(float) windowSize;
		return out;
	}
	
	public Complex[] fft(double y) {
		
		if(bufferPointer < (yBuffer.length)) {
			yBuffer[bufferPointer++] = new Complex(y, 0);
			return null;
		}else {
			for(int i=0; i<bufferPointer-1; i++) {
				yBuffer[i] = yBuffer[i+1];
			}
			yBuffer[bufferPointer-1] = new Complex(y, 0);
		}
		return fft(yBuffer);
	}
	
	public double[] getReal(Complex[] fft) {
		if(fft == null)
			return null;
		double[] out = new double[fft.length/2];
		for(int i=0; i<out.length; i++) {
			out[i] = fft[i].real()*correction;
		}
		return out;
	}
	
	public double[] getMod(Complex[] fft) {
		if(fft == null)
			return null;
		double[] out = new double[fft.length/2];
		for(int i=0; i<out.length; i++) {
			out[i] = fft[i].mod()*correction;
		}
		return out;
	}
	

}
