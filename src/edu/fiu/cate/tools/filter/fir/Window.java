package edu.fiu.cate.tools.filter.fir;

public class Window {

	private int s;
	protected double[] w;
	
	public enum WindowType{HANNING, HAMMING, BLACKMAN};
	
	public Window(WindowType type, int size){
		this.s = size;
		this.w = new double[this.s];
		switch(type){
		case BLACKMAN:
			setBlackman(this);
			break;
		case HAMMING:
			setHamming(this);
			break;
		case HANNING:
			setHanning(this);
			break;
		default:
			break;
		}
	}
	
	public Window(WindowType type, double mainWidth){
		switch(type){
		case BLACKMAN:
			this.s = (int) Math.round(12.0*Math.PI/mainWidth);
			this.w = new double[this.s];
			setBlackman(this);
			break;
		case HAMMING:
			this.s = (int) Math.round(8.0*Math.PI/mainWidth);
			this.w = new double[this.s];
			setHamming(this);
			break;
		case HANNING:
			this.s = (int) Math.round(8.0*Math.PI/mainWidth);
			this.w = new double[this.s];
			setHanning(this);
			break;
		default:
			break;
		}
	}
	
	public static void setHanning(Window w){
		double c = 2*Math.PI/(w.s-1);
		for(int i=0; i<w.s; i++){
			w.w[i] = 0.5*(1-Math.cos(c*i));
		}
	}
	
	public static void setHamming(Window w){
		double c = 2*Math.PI/(w.s-1);
		for(int i=0; i<w.s; i++){
			w.w[i] = 0.54-0.46*Math.cos(c*i);
		}
	}
	
	public static void setBlackman(Window w){
		double c = 2*Math.PI/(w.s-1);
		for(int i=0; i<w.s; i++){
			w.w[i] = 0.42659 - 0.49656*Math.cos(c*i) + 0.076849*Math.cos(2*c*i);
		}
	}
	
}
