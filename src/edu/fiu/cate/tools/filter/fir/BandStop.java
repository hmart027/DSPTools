package edu.fiu.cate.tools.filter.fir;

import edu.fiu.cate.tools.filter.fir.Window.WindowType;

public class BandStop extends FIR {

	public BandStop( double fPass1, double fStop1, double fStop2, double fPass2, double aPass, double aStop){	
		double wc1 = Math.PI*(fPass1+fStop1);
		double wc2 = Math.PI*(fPass2+fStop2);
		double fGap = fStop1-fPass1;
		if(fGap<(fPass2-fStop2)) fGap=(fPass2-fStop2);
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
				weights[i] = -Math.sin(n*wc2) + Math.sin(n*wc1);
			else
				weights[i] = (wc2-wc1)/Math.PI;
			weights[i] *= 1/(Math.PI*n);
//			System.out.print(weights[i]+" ");
		}
//		System.out.println();
	}
}
