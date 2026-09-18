package es.upm.aled.lab1.measurements;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	private int[] validChannels;

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	public FilterExtractChannels(int[] validChannels) {
		this.validChannels = validChannels;
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {

		Measurement[] measurements = new Measurement[eeg.getMeasurements().length];

		int position = 0;

		for (Measurement m : eeg.getMeasurements()) {

			float[] channels = new float[this.validChannels.length];

			for (int i = 0; i < this.validChannels.length; i++) {
				channels[i] = m.getChannel(this.validChannels[i]);
			}

			Measurement ms = new Measurement(channels);
			measurements[position] = ms;
			position++;

		}

		EEGModel filterEEG = new EEGModel(measurements);

		return filterEEG;
	}

}
