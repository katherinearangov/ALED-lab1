package es.upm.aled.lab1.measurements;

import java.util.ArrayList;
import java.util.List;

public class FilterThresholdExam implements Filter {

	private float threshold;
	private int[] channels;

	public FilterThresholdExam(float threshold, int[] channels) {
		this.channels = channels;
		this.threshold = threshold;
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {

		List<Measurement> measurements = new ArrayList<>();

		for (Measurement m : eeg.getMeasurements()) {
			boolean valid = true;
			for (int i = 0; i < this.channels.length; i++) {
				if (m.getChannel(this.channels[i]) <= this.threshold) {
					valid = false;
					break;
				}
			}

			if (valid) {
				measurements.add(m);
			}
		}

		EEGModel filterEEG = new EEGModel();

		for (Measurement m : measurements) {
			filterEEG.addMeasurement(m);
		}

		return filterEEG;
	}

}
