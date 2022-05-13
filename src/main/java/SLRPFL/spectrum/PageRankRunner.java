package SLRPFL.spectrum;

import SLRPFL.PDG.PDGAnalysis;
import SLRPFL.Struct.Location;
import SLRPFL.Struct.Matrix;
import SLRPFL.coverage.CoverageMatrix;
import javafx.scene.paint.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PageRankRunner {

    private Matrix failedTransformMatrix;

    private Matrix passedTransformMatrix;

    private Matrix vFailedMatrix;

    private Matrix vPassedMatrix;

    private Matrix xFailedMatrix;

    private Matrix xPassedMatrix;


    private Double d = new Double(0.85);

    private Double alpha = new Double(0.001);


    public PageRankRunner(CoverageMatrix coverageMatrix, PDGAnalysis analysis, Map<Location, Integer> locationIntegerMap,  Map<String, Integer> passedTestIntegerMap){
         setMatrix(coverageMatrix, analysis, locationIntegerMap, passedTestIntegerMap);
         computeXMatrix();
         int i = 5;
    }


    private void setMatrix(CoverageMatrix coverageMatrix, PDGAnalysis analysis, Map<Location, Integer> locationIntegerMap, Map<String, Integer> passedTestIntegerMap){
        int lineNumber = locationIntegerMap.size();

        int passedTestNumber = passedTestIntegerMap.size();
        int failedTestNumber = coverageMatrix.getFailedTests().size();

        failedTransformMatrix = new Matrix(lineNumber + failedTestNumber, lineNumber + failedTestNumber);
        passedTransformMatrix = new Matrix(lineNumber + passedTestNumber, lineNumber + passedTestNumber);

        xFailedMatrix = new Matrix(lineNumber + failedTestNumber, 1);
        xPassedMatrix = new Matrix(lineNumber + passedTestNumber, 1);

        vFailedMatrix = new Matrix(lineNumber + failedTestNumber, 1);
        vPassedMatrix  =new Matrix(lineNumber + passedTestNumber, 1);


        //set transformMatrix : line to line
        for(Location location : analysis.getDependencyMatrix().keySet()) {
            if (!locationIntegerMap.containsKey(location)) {
                continue;
            }

            int col = locationIntegerMap.get(location);

            for(Location loc : analysis.getDependencyMatrix().get(location)) {
                if(!locationIntegerMap.containsKey(loc)) {
                    continue;
                }

                int row = locationIntegerMap.get(loc);
                failedTransformMatrix.setMatrix(row, col, 1.0);
                passedTransformMatrix.setMatrix(row, col, 1.0);
            }
        }

        passedTransformMatrix.normalization(lineNumber);
        failedTransformMatrix.normalization(lineNumber);
        passedTransformMatrix.transpose(lineNumber);
        failedTransformMatrix.transpose(lineNumber);
        passedTransformMatrix.partMul(alpha, lineNumber, lineNumber);
        failedTransformMatrix.partMul(alpha, lineNumber, lineNumber);

        //set  transformMatrix : line to testMethod, testMethod to line
        Map<String, Integer> lineExecutedByTest = coverageMatrix.getLineExecutedByTest();

        for(Location location : locationIntegerMap.keySet()) {
            int row = locationIntegerMap.get(location);
            Double s2tNum = 1.0 / coverageMatrix.getCoverageMatrix().get(location).size();

            for(String testMethod : coverageMatrix.getCoverageMatrix().get(location)){
                if(passedTestIntegerMap.containsKey(testMethod)) {
                    Double t2sNum = 1.0 / lineExecutedByTest.get(testMethod);
                    int col = passedTestIntegerMap.get(testMethod);

                    passedTransformMatrix.setMatrix(row, col + lineNumber, t2sNum);
                    passedTransformMatrix.setMatrix(col + lineNumber, row, s2tNum);
                } else {
                    Double t2sNum = 1.0 / lineExecutedByTest.get(testMethod);
                    int col = coverageMatrix.getFailedTests().get(testMethod);

                    failedTransformMatrix.setMatrix(row, col + lineNumber, t2sNum);
                    failedTransformMatrix.setMatrix(col + lineNumber, row, s2tNum);
                }
            }
        }

        //set vMatrix and xMatrix
        for(int i = 0; i < lineNumber; i++) {
            vPassedMatrix.setMatrix(i, 0, 0.0);
            vFailedMatrix.setMatrix(i, 0, 0.0);
            xPassedMatrix.setMatrix(i, 0, 0.0);
            xFailedMatrix.setMatrix(i, 0, 0.0);
        }

        for(int i = 0; i < passedTestNumber; i++) {
            vPassedMatrix.setMatrix(lineNumber + i, 0,1.0 / (failedTestNumber + passedTestNumber));
            xPassedMatrix.setMatrix(lineNumber + i, 0,1.0 / (failedTestNumber + passedTestNumber));
        }

        Double addDenominator = 0.0;
        for (String test : coverageMatrix.getFailedTests().keySet()) {
            addDenominator = addDenominator + 1.0 / lineExecutedByTest.get(test);
        }

        for (String test : coverageMatrix.getFailedTests().keySet()) {
            Double numerator = 1.0 / lineExecutedByTest.get(test);
            int row = lineNumber + coverageMatrix.getFailedTests().get(test);
            vFailedMatrix.setMatrix(row, 0, numerator / addDenominator);
            xFailedMatrix.setMatrix(row, 0, numerator / addDenominator);
        }
    }

    private  void computeXMatrix() {
        for(int i = 0; i < 25; i++) {
            xFailedMatrix = failedTransformMatrix.multiply(d).multiply(xFailedMatrix).add(vFailedMatrix.multiply(1.0 - d));
            xPassedMatrix = passedTransformMatrix.multiply(d).multiply(xPassedMatrix).add(vPassedMatrix.multiply(1.0 - d));
        }
    }

    public Matrix getXFailedMatrix() {
        return xFailedMatrix;
    }

    public Matrix getXPassedMatrix() {
        return  xPassedMatrix;
    }


}
