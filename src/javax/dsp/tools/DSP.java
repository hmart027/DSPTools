package javax.dsp.tools;

public class DSP {
	
	public static Number[] getFFT(Number[] f){
		if(f==null || f.length==0) return null;
		int N = f.length;
		int nBits = (int) (Math.log(N)/Math.log(2));
		//Generate W array
		Complex[] w = new Complex[N];
		for(int v = 0; v<N; v++){
			w[v] = new Complex(0, -2.0 * Math.PI / (double) N * (double)v).exp();
		}
		Number[] F = new Number[N];
		
		
		return F;
	}
	
	public static int bitReversal(int number, int nBits){
		int out = 0;
		for(int n = 0; n<nBits; n++){
			out += (number & (1 << n))>>n<<(nBits-1-n);
		}
		return out;
	}

}
