package aoc.day1;

import java.io.*;
import java.util.Arrays;

public class SonarDepthReport {
    public int[] measurements;

    public SonarDepthReport() {
        Class<? extends SonarDepthReport> clazz = getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");
        if (inputStream == null) {
            throw new RuntimeException("No resource named 'input.txt'");
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader buffer = new BufferedReader(reader);

        this.measurements = buffer.lines().mapToInt(Integer::parseInt).toArray();
    }

    public SonarDepthReport(String[] lines) {
        measurements = Arrays.stream(lines).mapToInt(Integer::parseInt).toArray();
    }

    public int countIncreasingMeasurements() {
        return countIncreasingValues(this.measurements);
    }

    public int countIncreasingThreeMeasurementsWindows() {
        return countIncreasingValues(createThreeMeasurementsWindows());
    }

    public int[] createThreeMeasurementsWindows() {
        if (this.measurements.length < 3) {
            return new int[]{};
        }

        int count = this.measurements.length - 2;
        int[] windows = new int[count];
        for (int i = 0; i < count; i++) {
            windows[i] = this.measurements[i] +
                    this.measurements[i + 1] +
                    this.measurements[i + 2];
        }

        return windows;
    }

    private static int countIncreasingValues(int[] values) {
        if (values.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[i - 1]) {
                count++;
            }
        }

        return count;
    }
}
