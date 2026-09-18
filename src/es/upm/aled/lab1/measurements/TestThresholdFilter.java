package es.upm.aled.lab1.measurements;

import java.io.IOException;

public class TestThresholdFilter {

	// Generates a controlled EEG where only measurement 150 exceeds the threshold.
	// Used to verify that FilterThreshold extracts 100 measurements before and
	// after it.

	public static void main(String[] args) {

		EEGModel testEEG = new EEGModel();

		for (int i = 0; i < 300; i++) {
			float[] channels = new float[1];

			if (i == 150) {
				channels[0] = 18750;
			} else {
				channels[0] = 0;
			}

			Measurement m = new Measurement(channels);
			testEEG.addMeasurement(m);
		}

		try {
			testEEG.saveFile("ThresholdTest.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
