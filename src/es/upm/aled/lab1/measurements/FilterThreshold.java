package es.upm.aled.lab1.measurements;

public class FilterThreshold implements Filter {

	private int channel;
	private float threshold;

	public FilterThreshold(int channel, float threshold) {

		this.channel = channel;
		this.threshold = threshold;
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {

		float sample;
		int numMeasurementSample = -1;

		for (int i = 0; i < eeg.getMeasurements().length; i++) {
			sample = eeg.getMeasurements()[i].getChannel(channel);

			if (sample > this.threshold) {
				numMeasurementSample = i;
				break;
			}
		}

		if (numMeasurementSample == -1) {
			System.out.println("No se ha encontrado ninguna muestra mayor al valor proporcionado.");

			return new EEGModel();

		}

		if (numMeasurementSample < 100 || eeg.getMeasurements().length - numMeasurementSample - 1 < 100) {
			System.out.println(
					"No se puede construir el EEGModel, porque no hay suficientes valores en uno o ambos extremos.");

			return new EEGModel();
		}

		// Extract 100 measurements before and after the threshold crossing.
		Measurement[] measurements = new Measurement[201];
		int position = 0;

		for (int i = numMeasurementSample - 100; i <= numMeasurementSample + 100; i++) {
			measurements[position] = eeg.getMeasurements()[i];
			position++;
		}

		return new EEGModel(measurements);

	}

}
