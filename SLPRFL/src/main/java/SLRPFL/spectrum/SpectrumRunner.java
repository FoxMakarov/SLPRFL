package SLRPFL.spectrum;

import SLRPFL.PDG.PDGAnalysis;
import SLRPFL.Struct.Location;
import SLRPFL.Struct.Matrix;
import SLRPFL.Struct.Spectrum;
import SLRPFL.Utils.MapOutput;
import SLRPFL.coverage.CoverageMatrix;
import SLRPFL.formulas.Formula;
import SLRPFL.Utils.SortMap;

import java.util.*;

public class SpectrumRunner {

    private Map<Location, Spectrum> spectrumMap = new HashMap<>();

    private Map<Location, Double> suspiciousScoreMap = new HashMap<>();

    private Map<Location, Double> dpSuspiciousScoreMap = new HashMap<>();

    private Map<Location, Integer> locationIntegerMap = new HashMap<>();

    private Map<String, Integer> passedTestIntegerMap = new HashMap<>();

    private PageRankRunner pageRankRunner;

    private int failedTestNumber;

    private int passedTestNumber;

    private String fileSus = "Results/";

    private String fileDpSus = "Results/";


    public SpectrumRunner(CoverageMatrix coverageMatrix, PDGAnalysis analysis, String fileSusVersion, String OutputFolder) {
        failedTestNumber = coverageMatrix.getFailedTests().size();
        passedTestNumber = coverageMatrix.getPassedTests().size();

        this.fileSus = this.fileSus + OutputFolder + fileSusVersion + "_suspicious.txt";
        this.fileDpSus = this.fileDpSus + OutputFolder + fileSusVersion + "_dpSuspicious.txt";

        getSpectrum(coverageMatrix);
        pageRankRunner = new PageRankRunner(coverageMatrix, analysis, locationIntegerMap, passedTestIntegerMap);
    }

    private void getSpectrum(CoverageMatrix matrix) {

        Integer currentLine = 0;
        Integer currentPassedTestMap = 0;

        // For each line of code to analyze
        for(Location location : matrix.getCoverageMatrix().keySet()) {
            int testPassingExecutingNumber = 0;
            int testFailingExecutingNumber = 0;
            int testPassingNotExecutingNumber = 0;
            int testFailingNotExecutingNumber = 0;

            Set<String>currentExecution = matrix.getCoverageMatrix().get(location);

            // for passed tests
            for(String testMethod : matrix.getPassedTests().keySet()) {
                Boolean executed = currentExecution.contains(testMethod);
                if(executed) {
                    if(!passedTestIntegerMap.containsKey(testMethod)) {
                        passedTestIntegerMap.put(testMethod, currentPassedTestMap);
                        currentPassedTestMap++;
                    }

                    testPassingExecutingNumber++;
                } else {
                    testPassingNotExecutingNumber++;
                }
            }

            //for failed tests
            for(String testMethod : matrix.getFailedTests().keySet()) {
                Boolean executed = currentExecution.contains(testMethod);
                if(executed) {
                    testFailingExecutingNumber++;
                } else {
                    testFailingNotExecutingNumber++;
                }

            }

            spectrumMap.put(location, new Spectrum(testPassingExecutingNumber, testFailingExecutingNumber,
                    testPassingNotExecutingNumber, testFailingNotExecutingNumber));

            /*if(matrix.getClassExecutedByFailedTest().contains(location.getClassName())) {
                locationIntegerMap.put(location, currentLine);
                currentLine++;
            }*/
            locationIntegerMap.put(location, currentLine);
            currentLine++;

        }

    }

    public void computeSuspiciousScore(Formula formula) {
        for(Location location : spectrumMap.keySet()) {
            Spectrum spectrum = spectrumMap.get(location);
            Double suspiciousScore = formula.compute(spectrum.testPassingNotExecutingNumber, spectrum.testFailingNotExecutingNumber,
                    spectrum.testPassingExecutingNumber, spectrum.testFailingExecutingNumber);

            suspiciousScoreMap.put(location, suspiciousScore);
        }

        suspiciousScoreMap = SortMap.sortDescend(suspiciousScoreMap);
        MapOutput.output(fileSus, suspiciousScoreMap, 10);

    }

    public void computeDpSuspiciousScore(Formula formula) {
        int lineNumber = locationIntegerMap.size();
        Double maxSuccessfulNumber = 0.0;
        for(int i = 0; i < lineNumber; i++) {
            if(pageRankRunner.getXPassedMatrix().getValue(i, 0) > maxSuccessfulNumber) {
                maxSuccessfulNumber = pageRankRunner.getXPassedMatrix().getValue(i, 0);
            }
        }

        Double maxFailedNumber = 0.0;
        for(int i = 0; i < lineNumber; i++) {
            if(pageRankRunner.getXFailedMatrix().getValue(i, 0) > maxFailedNumber) {
                maxFailedNumber = pageRankRunner.getXFailedMatrix().getValue(i, 0);
            }
        }

        for(Location location : locationIntegerMap.keySet()) {

            int row = locationIntegerMap.get(location);
            Double sSocre = pageRankRunner.getXPassedMatrix().getValue(row, 0) / maxSuccessfulNumber;
            Double fScore = pageRankRunner.getXFailedMatrix().getValue(row, 0) / maxFailedNumber;
            Double dpSuspicious = formula.compute(passedTestNumber - sSocre * passedTestNumber, failedTestNumber - fScore * failedTestNumber,
                    sSocre * passedTestNumber, fScore * failedTestNumber);

            dpSuspiciousScoreMap.put(location, dpSuspicious);
        }

        dpSuspiciousScoreMap = SortMap.sortDescend(dpSuspiciousScoreMap);
        MapOutput.output(fileDpSus, dpSuspiciousScoreMap, 10);

    }

    public Map<Location, Double> getSuspiciousScoreMap() {
        return suspiciousScoreMap;
    }

    public Map<Location, Double> getDpSuspiciousScoreMap() {
        return dpSuspiciousScoreMap;
    }


}
