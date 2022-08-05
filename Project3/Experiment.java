//Abu Koroma
// 1908-111-3

import java.io.PrintStream;

public class Experiment {
	public static final int SAMPLE_SIZE = 10000;
	public static final int BIT_SIZE = 101;
	public static long lastTime = 0;
	public static PrintStream ps = System.out;
	
	public static long stop() {
		long newTime = System.nanoTime();
		long deltaTime = newTime - lastTime;
		lastTime = newTime;
		return deltaTime;
	}
	
	
	public static void addTest(Set s, String[] samples) {
		stop();
		for(int i = 0; i < samples.length / 2; i++) {
			s.add(samples[i]);
		}
		long fullTime = stop();
		ps.println(" TIME TO ADD: " + (fullTime / (samples.length / 2.0)));
	}
	
	
	public static void containsTest(Set s, String[] samples) {
		int falsePos = 0;
		int falseNeg = 0;
		stop();
		for (int i = 0; i < samples.length / 2; i++) {
			if(!s.contains(samples[i])) {
				falseNeg++;
			}
		}
		
		for (int i = samples.length / 2; i < samples.length; i++) { //not in the set
			if(!s.contains(samples[i])) {
				falsePos++;
			}
		}
		
		long fullTime = stop();
		int totalFails = falsePos + falseNeg;
		ps.println(" ACCURACY: " + (samples.length - totalFails) + " / " + samples.length + " " + 
		((samples.length - totalFails) * 100 / samples.length));
		ps.println(" TIME TO CHECK CONTAINS: " + (fullTime / (samples.length * 1.0)));
	}
	
	public static void main(String[] args) {
		String[]  samples = new String[SAMPLE_SIZE];
		for(int i = 0; i < samples.length; i++) {
			samples[i] = java.util.UUID.randomUUID().toString();
		}

		Set naive = new NaiveListSet();
		Set bloom = new BloomFilterSet(BIT_SIZE);
		ps.println("NAIVE: ");
		addTest(naive, samples);
		containsTest(naive, samples);
		ps.println("BLOOM: ");
		addTest(bloom, samples);
		containsTest(bloom, samples);
	}
	

}
