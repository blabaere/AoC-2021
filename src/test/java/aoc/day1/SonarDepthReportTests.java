package aoc.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SonarDepthReportTests {

    @Test
    public void ShouldLoadOneLine() {
        String[] lines = new String[]{"199"};
        SonarDepthReport report = new SonarDepthReport(lines);

        assertEquals(1, report.measurements.length);
        assertArrayEquals(new int[]{199}, report.measurements);
    }

    @Test
    public void ShouldLoadSeveralLines() {
        String[] lines = new String[]{"1", "2", "4", "1"};
        SonarDepthReport report = new SonarDepthReport(lines);

        assertEquals(4, report.measurements.length);
        assertArrayEquals(new int[]{1, 2, 4, 1}, report.measurements);
    }

    @Test
    @DisplayName("countIncreasingMeasurements on a single point")
    public void WhenThereIsOneMeasure_NumberOfIncrease_ShouldBeZero() {
        SonarDepthReport report = createReport("1");

        assertEquals(0, report.countIncreasingMeasurements());
    }

    @Test
    @DisplayName("countIncreasingMeasurements on a single upward slope")
    public void countSingleUpwardSlope() {
        SonarDepthReport report = createReport("1000", "1001");

        assertEquals(1, report.countIncreasingMeasurements());
    }

    @Test
    @DisplayName("countIncreasingMeasurements on a single downward slope")
    public void countSingleDownwardSlope() {
        SonarDepthReport report = createReport("5", "1");

        assertEquals(0, report.countIncreasingMeasurements());
    }

    private static SonarDepthReport createReport(String... lines) {
        return new SonarDepthReport(lines);
    }

    @Test
    public void DisplayPuzzleAnswer() {
        SonarDepthReport report = new SonarDepthReport();
        int answer = report.countIncreasingMeasurements();

        System.out.println("Puzzle answer: " + answer);
    }

    @Test
    public void createSlidingWindow_ForTooSmallData_ReturnsEmpty() {
        SonarDepthReport report = createReport("1", "0");
        int[] slidingWindows = report.createThreeMeasurementsWindows();

        assertEquals(0, slidingWindows.length);
    }

    @Test
    public void createSlidingWindows_ForWindowSize_ReturnsSingleItem() {
        SonarDepthReport report = createReport("4", "5", "6");
        int[] slidingWindows = report.createThreeMeasurementsWindows();

        assertArrayEquals(new int[]{15}, slidingWindows);
    }

    @Test
    public void createSlidingWindows_ForMoreThanWindowSize_ReturnsAllItems() {
        SonarDepthReport report = createReport("4", "5", "6", "8", "1");
        int[] slidingWindows = report.createThreeMeasurementsWindows();

        assertArrayEquals(new int[]{15, 19, 15}, slidingWindows);
    }

    @Test
    public void displaySecondPuzzleAnswer() {
        SonarDepthReport report = new SonarDepthReport();
        int answer = report.countIncreasingThreeMeasurementsWindows();

        System.out.println("2nd puzzle: " + answer);
    }
}
