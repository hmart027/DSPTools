package edu.fiu.cate.tools.filter.fir;

import edu.fiu.cate.tools.filter.fir.Window.WindowType;

public class BandPass extends FIR {

	/**
	 * 
	 * @param fPass normalized pass frequency (0.0-1.0)
	 * @param fStop normalized stop frequency (0.0-1.0)
	 * @param aPass pass frequency gain (dB)
	 * @param aStop stop frequency gain (dB)
	 */
	public BandPass( double fStop1, double fPass1, double fPass2, double fStop2, double aPass, double aStop){	
		double wc1 = Math.PI*(fPass1+fStop1);
		double wc2 = Math.PI*(fPass2+fStop2);
		double fGap = fPass1-fStop1;
		if(fGap<(fStop2-fPass2)) fGap=(fStop2-fPass2);
		Window w = new Window(WindowType.HAMMING, 2*Math.PI*(fGap));
		System.out.println(w.w.length);
		this.init(w.w.length-1);
		loadCoefficients(wc1, wc2, w);
	}
	
	public void loadCoefficients(double wc1, double wc2, Window w){
		double n;
		for(int i = 0; i<weights.length; i++){
			n = i - order/2.0;
			if(n!=0)
				weights[i] = Math.sin(n*wc2)/(Math.PI*n) - Math.sin(n*wc1)/(Math.PI*n);
			else
				weights[i] = (wc2-wc1)/Math.PI;
			weights[i] *= w.w[i];
		}
	}

}
