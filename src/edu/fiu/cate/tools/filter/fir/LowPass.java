package edu.fiu.cate.tools.filter.fir;

import edu.fiu.cate.tools.filter.fir.Window.WindowType;

public class LowPass extends FIR {

//	protected LowPass(int order) {
//		super(order);
//	}
//	
//	public LowPass(Window window, double fPass, double fStop, double aPass, double aStop){
//		super(80);
//		
//		double wc = (fPass+fStop)/2.0;
//		loadCoefficients(wc, window);
//	}
	
	/**
	 * 
	 * @param fPass normalized pass frequency (0.0-1.0)
	 * @param fStop normalized stop frequency (0.0-1.0)
	 * @param aPass pass frequency gain (dB)
	 * @param aStop stop frequency gain (dB)
	 */
	public LowPass(double fPass, double fStop, double aPass, double aStop){	
		double wc = Math.PI*(fPass+fStop);	
		Window w = new Window(WindowType.HAMMING, 2*Math.PI*(fStop-fPass));
//		System.out.println(w.w.length);
		this.init(w.w.length-1);
		loadCoefficients(wc, w);
	}
	
	public void loadCoefficients(double wc, Window w){
		double n;
		for(int i = 0; i<weights.length; i++){
			n = i - order/2.0;
			if(n!=0)
				weights[i] = Math.sin(n*wc)/(Math.PI*n);
			else
				weights[i] = wc/Math.PI;
			weights[i] *= w.w[i];
		}
	}

}
